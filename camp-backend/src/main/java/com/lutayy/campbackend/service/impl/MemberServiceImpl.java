package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.dao.MemberReStudentMapper;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.lutayy.campbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY ;

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;

    /** 由身份证获得学员Id **/
    private Student getStudentIdFromIdCard(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        Student student=students.get(0);
        return student;
    }
    /** 由手机号码获得学员Id **/
    private Student getStudentIdFromPhone(String phone){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentPhoneEqualTo(phone);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        Student student=students.get(0);
        return student;
    }
    /** 检查学员是否已存在绑定关系 **/
    private MemberReStudent getMemberReStudentByStudentId(int studentId){
        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()==0){
            return null;
        }else {
            return memberReStudents.get(0);
        }
    }

    @Override
    public Object getUserInfo(String id) {
        JSONObject result=new JSONObject();
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在！");
            return result;
        }
        JSONObject data=new JSONObject();
        data.put("phone", member.getMemberPhone());
        data.put("name", member.getMemberName());
        data.put("email", member.getMemberEmail());
        data.put("province", member.getMemberProvince());
        data.put("city", member.getMemberCity());
        data.put("area",member.getMemberArea());
        data.put("zone", member.getMemberAddress());
        if(!member.getIsVip()){
            data.put("memberFlag", "否");
        }else{
            data.put("memberFlag", "是");
        }
        data.put("memberBeginDate", member.getVipBeginDate());
        data.put("memberEndDate", member.getVipEndDate());
        data.put("telephone", member.getMemberTel());

        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject setUserInfo(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String id=jsonObject.getString("id");

        String phone=jsonObject.getString("phone");
        String name=jsonObject.getString("name");
        String email=jsonObject.getString("email");
        String province=jsonObject.getString("province");
        String city=jsonObject.getString("city");
        String area=jsonObject.getString("area");//区镇
        String zone=jsonObject.getString("zone");

        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在！");
            return result;
        }
        member.setMemberPhone(phone);
        member.setMemberName(name);
        member.setMemberEmail(email);
        member.setMemberProvince(province);
        member.setMemberCity(city);
        member.setMemberArea(area);
        member.setMemberAddress(zone);
        if(memberMapper.updateByPrimaryKeySelective(member)>0){
            result.put("code", "success");
            result.put("msg","信息修改成功");
        }else{
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后尝试");
        }
        return result;
    }

    @Override
    public JSONObject setNewPassword(JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject rechargeVIP(String id) {
        JSONObject result=new JSONObject();
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }
        if(!member.getIsVip()){
            result.put("code", "fail");
            result.put("msg","当前用户非协会会员,如需办理会员,请联系工作人员");
            return result;
        }
        return result;
    }

    @Override
    public JSONObject importSingleStudent(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        String idNum=jsonObject.getString("idNum");
        String phone=jsonObject.getString("phone");
        String name=jsonObject.getString("name");

        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }

        Student student=getStudentIdFromIdCard(idNum);
        if(student==null){
            Student newStudent=new Student();
            newStudent.setStudentName(name);
            newStudent.setStudentIdcard(idNum);
            String msg;
            //手机号已存在于系统中，则置为空，需对应学员自行设置
            if(getStudentIdFromPhone(phone)==null){
                newStudent.setStudentPhone(phone);
                msg="";
            }else{
                msg="该手机号已存在,请让学员自行更改； ";
            }
            if(studentMapper.insertSelective(newStudent)>0){
                MemberReStudent memberReStudent=new MemberReStudent();
                memberReStudent.setMemberId(memberId);
                int studentId=getStudentIdFromIdCard(idNum).getStudentId();
                memberReStudent.setStudentId(studentId);
                if(memberReStudentMapper.insert(memberReStudent)>0){
                    result.put("code", "success");
                    result.put("msg",msg+"导入并绑定成功!");
                    return result;
                }else {
                    result.put("code", "success");
                    result.put("msg",msg+"导入成功,但绑定失败!");
                    return result;
                }
            }else {
                result.put("code", "fail");
                result.put("msg","系统繁忙,请稍后尝试");
                return result;
            }
        }
        if(student.getStudentPhone()!=null){
            if(!student.getStudentName().equals(name)||!student.getStudentPhone().equals(phone)){
                result.put("code", "fail");
                result.put("msg", "账号绑定失败,所填身份证、姓名或手机号码不一致");
                return result;
            }
        }else {
            if(!student.getStudentName().equals(name)) {
                result.put("code", "fail");
                result.put("msg", "账号绑定失败,所填身份证、姓名不一致");
                return result;
            }
        }

        if(getMemberReStudentByStudentId(student.getStudentId())!=null){
            result.put("code", "fail");
            result.put("msg", "绑定失败!系统内已有该账号且有已有挂靠关系");
            return result;
        }
        MemberReStudent memberReStudent=new MemberReStudent();
        memberReStudent.setMemberId(memberId);
        memberReStudent.setStudentId(student.getStudentId());
        if(memberReStudentMapper.insert(memberReStudent)>0){
            result.put("code", "success");
            result.put("msg","账号已存在; 绑定成功!");
            return result;
        }else {
            result.put("code", "fail");
            result.put("msg","绑定失败,请检查与该学员的挂靠关系或稍后再试");
            return result;
        }
    }

    @Override
    public JSONObject deleteOneStudent(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        String idNum=jsonObject.getString("idNum");
        String phone=jsonObject.getString("phone");

        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }
        Student student=getStudentIdFromIdCard(idNum);
        if(student==null){
            result.put("code", "fail");
            result.put("msg","该账号不存在!");
            return result;
        }
        if(student.getStudentPhone()!=null){
            if(!student.getStudentPhone().equals(phone)){
                result.put("code", "fail");
                result.put("msg", "操作失败,所填身份证和手机号码不一致");
                return result;
            }
        }

        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andMemberIdEqualTo(memberId).andStudentIdEqualTo(student.getStudentId());
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()==0){
            result.put("code", "fail");
            result.put("msg","该学员不挂靠在名下");
            return result;
        }
        MemberReStudent memberReStudent=memberReStudents.get(0);
        if(memberReStudentMapper.deleteByExample(memberReStudentExample)>0){
            result.put("code", "success");
            result.put("msg","成功解除与该学员的挂靠关系!");
        }else{
            result.put("code", "fail");
            result.put("msg","操作失败,请稍后再试");
        }
        return result;
    }

    @Override
    public ResponseEntity<byte[]> getStudentTemplate(HttpServletRequest request) {
        String fileName="student_template.xlsx";
//        ServletContext servletContext=request.getServletContext();
//        String path=servletContext.getRealPath("/WEB-INF/templates/"+fileName);
        String path="./src/main/resources/templates/student_template.xlsx";
        File file=new File(path);
        InputStream in;
        ResponseEntity<byte[]> response=null;
        try{
            in=new FileInputStream(file);
            byte[] bytes=new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition", "attachment;filename="+fileName);
            HttpStatus statusCode=HttpStatus.OK;
            response = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            in.close();
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
    
    @Override
    public JSONObject importStudentByFile(HttpServletRequest request) {
        JSONObject result=new JSONObject();

        /**
         * 获取当前用户
         **/
        Cookie[] cookies = request.getCookies();
        String token = null;
        result.put("code","error");
        if(cookies == null){
            result.put("msg","未登录");
            return result;
        }
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("token")){
                token=cookies[i].getValue();
                break;
            }
        }
        if(token==null||token.equals("")){
            result.put("msg","请重新登陆！");
            return result;
        }
        TokenRequest tokenRequest= JwtUtil.unsign(token,TokenRequest.class,SECRET_KEY);
        if(tokenRequest!=null) {
            if (tokenRequest.getRole() == null || !tokenRequest.getRole().equals("member")) {
                result.put("msg", "未登录");
                return result;
            }
        }
        else {
            result.put("msg","请重新登陆！");
            return result;
        }
        String memberId = tokenRequest.getName();
        //System.out.println(memberId);
        Member member=memberMapper.selectByPrimaryKey(memberId);
        result.put("code", "fail");
        if(member==null){
            result.put("msg","用户不存在!");
            return result;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        if(file==null||file.isEmpty()){
            result.put("msg", "文件不能为空");
            return result;
        }
        String fileName=file.getOriginalFilename().toLowerCase();
        if(!fileName.endsWith("xls")&&!fileName.endsWith("xlsx")){
            result.put("msg", "文件格式错误");
            return result;
        }
        InputStream in;
        try{
            in=file.getInputStream();
            Map<String, List<Map<String, String>>> map = ExcelUtil.readXls(in);
            if (map.isEmpty()) {
                result.put("msg", "上传文件数据为空");
                return result;
            }
            Set<String> excelSheets = map.keySet();
            int totalCount = 0;
            String existTagTip = "";
            int existTotalCount = 0;
            String nameWrongTagTip="";
            int nameWrongTotalCount=0;
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                totalCount = totalCount + list.size();
                for (Map<String, String> row : list) {
                    // TODO 批量添加
                    Student student=excelRowToTeacher(row);
                    Student studentCheck=getStudentIdFromIdCard(student.getStudentIdcard());
                    //系统已有该身份证的账号
                    if (studentCheck!=null){
                        //系统已有账号但无挂靠关系
                        if(getMemberReStudentByStudentId(studentCheck.getStudentId())==null){
                            //与系统已有账号的姓名身份证不一致，不予绑定
                            if(!studentCheck.getStudentName().equals(student.getStudentName())){
                                nameWrongTotalCount+=1;
                                nameWrongTagTip+=student.getStudentName()+"("+student.getStudentIdcard()+")；";
                                continue;
                            }
                            MemberReStudent memberReStudent=new MemberReStudent();
                            memberReStudent.setStudentId(studentCheck.getStudentId());
                            memberReStudent.setMemberId(memberId);
                            memberReStudentMapper.insert(memberReStudent);
                            studentCheck.setCompany(member.getMemberName());
                            studentMapper.updateByPrimaryKeySelective(studentCheck);
                            continue;
                        }
                        existTagTip+=student.getStudentName()+"("+student.getStudentIdcard()+")；";
                        existTotalCount+=1;
                        continue;
                    }
                    if(getStudentIdFromPhone(student.getStudentPhone())!=null){
                        student.setStudentPhone(null);
                    }
                    student.setCompany(member.getMemberName());
                    studentMapper.insertSelective(student);
                    MemberReStudent memberReStudent=new MemberReStudent();
                    memberReStudent.setStudentId(getStudentIdFromIdCard(student.getStudentIdcard()).getStudentId());
                    memberReStudent.setMemberId(memberId);
                    memberReStudentMapper.insert(memberReStudent);
                }
            }
            String msg = "";
            if(existTotalCount > 0 && nameWrongTotalCount == 0) {
                msg = "尝试导入学员" + totalCount + "个，"+(totalCount - existTotalCount)+"个导入并绑定成功，"
                    + existTotalCount+"个在系统中已有挂靠关系，分别是：" + existTagTip;
            } else if(existTotalCount == 0 && nameWrongTotalCount > 0){
                msg = "尝试导入学员" + totalCount + "个，"+(totalCount - nameWrongTotalCount)+"个导入并绑定成功，"
                        + nameWrongTotalCount+"个与已有账号姓名不一致，分别是：" + nameWrongTagTip;
            } else if(existTotalCount > 0 && nameWrongTotalCount > 0){
                msg = "尝试导入学员" + totalCount + "个，"+(totalCount - nameWrongTotalCount - existTotalCount)+"个导入并绑定成功，"
                        + nameWrongTotalCount+"个与已有账号姓名不一致，分别是：" + nameWrongTagTip
                        + existTotalCount+"个在系统中已有挂靠关系，分别是：" + existTagTip;
            } else {
                msg = "全部学员信息导入成功，一共" + totalCount + "个";
            }
            result.put("code", "success");
            result.put("msg", msg);
            return result;
        }catch (Exception e){
            result.put("msg", "导入异常，请检查格式");
            return result;
        }
    }
    private Student excelRowToTeacher(Map<String, String> row) {
        Student student = new Student();
        String value;
        value = row.get("姓名");
        if(value != null&&!value.equals(""))
            student.setStudentName(value);
        value = row.get("身份证");
        if(value != null&&!value.equals(""))
            student.setStudentIdcard(value);
        value = row.get("手机号码");
        if(value != null&&!value.equals(""))
            student.setStudentPhone(value);
        return student;
    }

    @Override
    public JSONObject getStudentList(String memberId) {
        JSONObject result=new JSONObject();
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            result.put("data", null);
            return result;
        }
        JSONArray data=new JSONArray();
        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()==0){
            result.put("code", "fail");
            result.put("msg","名下无学员");
            result.put("data", null);
            return result;
        }
        for(MemberReStudent memberReStudent:memberReStudents){
            JSONObject object=new JSONObject();
            Student student=studentMapper.selectByPrimaryKey(memberReStudent.getStudentId());
            object.put("name", student.getStudentName());
            object.put("idNum", student.getStudentIdcard());
            object.put("phone", student.getStudentPhone());
            object.put("position", student.getStudentPositon());
            object.put("email", student.getStudentEmail());
            object.put("province", student.getStudentProvince());
            object.put("city", student.getStudentCity());
            object.put("area", student.getStudentArea());
            object.put("zone", student.getStudentAddress());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg","学员列表获取成功");
        result.put("data", data);
        return result;
    }
}
