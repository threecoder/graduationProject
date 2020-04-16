package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.lutayy.campbackend.service.AdminService;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    VoteMapper voteMapper;
    @Autowired
    VoteOptionMapper voteOptionMapper;
    @Autowired
    AdminReAuthorityMapper adminReAuthorityMapper;
    @Autowired
    GetObjectHelper getObjectHelper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Override
    public JSONObject setNewPassword(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        if (oldPassword.equals(newPassword)) {
            result.put("msg", "旧密码与新密码不能相同！");
            return result;
        }
        Integer adminId = jsonObject.getInteger("id");
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        if (admin == null) {
            result.put("msg", "系统中无该管理员！");
            return result;
        }
        if (!admin.getAdminPassword().equals(oldPassword)) {
            result.put("msg", "旧密码错误！");
        } else {
            admin.setAdminPassword(newPassword);
            if (adminMapper.updateByPrimaryKeySelective(admin) > 0) {
                result.put("code", "success");
                result.put("msg", "密码更新成功！");
            } else {
                result.put("msg", "密码更新失败！请重试");
            }
        }
        return result;
    }

    @Override
    public JSONObject addNewAdmin(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");

        String account=jsonObject.getString("account");
        String name=jsonObject.getString("name");
        AdminExample adminExample=new AdminExample();
        adminExample.createCriteria().andAdminAccountEqualTo(account);
        if(adminMapper.countByExample(adminExample)>0){
            result.put("msg", "该账号已存在");
            return result;
        }
        Admin admin=new Admin();
        admin.setAdminAccount(account);
        admin.setAdminName(name);
        if(adminMapper.insertSelective(admin)>0){
            result.put("code", "success");
            result.put("msg", "新增管理员账号成功！");
        }else {
            result.put("msg", "系统繁忙，请重试");
        }
        return result;
    }

    @Override
    public JSONObject lockOrUnlockAccount(JSONObject jsonObject, boolean lock) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        String account = jsonObject.getString("account");
        Admin admin = getObjectHelper.getAdminFromAccount(account);
        if (admin == null) {
            result.put("msg", "对应的账号不存在，请检查输入是否正确");
            return result;
        }
        admin.setIsLocked(lock);
        if (adminMapper.updateByPrimaryKeySelective(admin) > 0) {
            result.put("code", "success");
            if (lock)
                result.put("msg", "冻结账号" + account + "成功");
            else
                result.put("msg", "解冻账号" + account + "成功");
        } else {
            result.put("msg", "系统繁忙，请重试！");
        }
        return result;
    }

    @Override
    public JSONObject getAdminList(String name, String account, Integer currentPage, Integer pageSize) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        JSONArray list=new JSONArray();
        AdminExample adminExample=new AdminExample();
        AdminExample.Criteria criteria=adminExample.createCriteria();
        if (account!=null && !account.equals(""))
            criteria.andAdminAccountEqualTo(account);
        if(name!=null && !name.equals(""))
            criteria.andAdminNameLike("%"+name+"%");
        long total=adminMapper.countByExample(adminExample);
        List<Admin> admins=adminMapper.selectByExample(adminExample);
        data.put("total", total);
        for(Admin admin:admins){
            JSONObject object=new JSONObject();
            object.put("account", admin.getAdminAccount());
            object.put("name", admin.getAdminName());
            object.put("lock", admin.getIsLocked()?"是":"否");
            list.add(object);
        }
        data.put("data", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "列表获取成功！");
        return result;
    }

    //管理员获取成绩审核员名单
    @Override
    public JSONObject getCheckerList() {
        JSONObject result=new JSONObject();

        AdminReAuthorityExample adminReAuthorityExample=new AdminReAuthorityExample();
        adminReAuthorityExample.createCriteria().andAuthorityIdEqualTo(AuthorityParam.GRADE);
        List<AdminReAuthority> adminReAuthorities=adminReAuthorityMapper.selectByExample(adminReAuthorityExample);
        JSONArray data=new JSONArray();
        for(AdminReAuthority adminReAuthority:adminReAuthorities){
            Admin admin=adminMapper.selectByPrimaryKey(adminReAuthority.getAdminId());
            if(admin==null){
                continue;
            }
            JSONObject object=new JSONObject();
            object.put("id", adminReAuthority.getAdminId());
            object.put("name", admin.getAdminName());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "审核管理员名单获取成功！");
        result.put("data", data);
        return result;
    }

    //学员管理
    //管理员添加单个学员
    @Override
    public JSONObject addSingleStudent(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        String idCard = jsonObject.getString("idNum");
        String phone = jsonObject.getString("phone");
        String name = jsonObject.getString("name");
        if (getObjectHelper.getStudentFromIdCard(idCard) != null) {
            result.put("msg", "身份证已存在");
            return result;
        }
        if (getObjectHelper.getStudentFromPhone(phone) != null) {
            result.put("msg", "手机号已存在");
            return result;
        }
        Student student = new Student();
        student.setStudentName(name);
        student.setStudentPhone(phone);
        student.setStudentIdcard(idCard);
        student.setEnterTime(new Date());
        if (studentMapper.insertSelective(student) > 0) {
            result.put("code", "success");
            result.put("msg", "添加学员成功！");
        } else {
            result.put("msg", "系统繁忙，请稍后再试");
        }
        return result;
    }

    @Override
    public JSONObject importStudentByFile(Integer memberId, MultipartFile file) {
        JSONObject result = new JSONObject();

        //System.out.println(memberId);
        Member member = memberMapper.selectByPrimaryKey(memberId);
        result.put("code", "fail");
        if (member == null) {
            result.put("msg", "用户不存在!");
            return result;
        }
        if (file == null || file.isEmpty()) {
            result.put("msg", "文件不能为空");
            return result;
        }
        String fileName = file.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            result.put("msg", "文件格式错误");
            return result;
        }
        InputStream in;
        try {
            in = file.getInputStream();
            Map<String, List<Map<String, String>>> map = ExcelUtil.readXls(in);
            if (map.isEmpty()) {
                result.put("msg", "上传文件数据为空");
                return result;
            }
            Set<String> excelSheets = map.keySet();
            int totalCount = 0;
            String existTagTip = "";
            int existTotalCount = 0;
            String nameWrongTagTip = "";
            int nameWrongTotalCount = 0;
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                totalCount = totalCount + list.size();
                for (Map<String, String> row : list) {
                    // TODO 批量添加
                    Student student = excelRowToStudent(row);
                    Student studentCheck = getObjectHelper.getStudentFromIdCard(student.getStudentIdcard());
                    //系统已有该身份证的账号
                    if (studentCheck != null) {
                        //系统已有账号但无挂靠关系
                        if (getObjectHelper.getMemberReStudentByStudentId(studentCheck.getStudentId()) == null) {
                            //与系统已有账号的姓名身份证不一致，不予绑定
                            if (!studentCheck.getStudentName().equals(student.getStudentName())) {
                                nameWrongTotalCount += 1;
                                nameWrongTagTip += student.getStudentName() + "(" + student.getStudentIdcard() + ")；";
                                continue;
                            }
                            MemberReStudent memberReStudent = new MemberReStudent();
                            memberReStudent.setStudentId(studentCheck.getStudentId());
                            memberReStudent.setMemberKeyId(memberId);
                            memberReStudentMapper.insert(memberReStudent);
                            studentCheck.setCompany(member.getMemberName());
                            studentCheck.setHasOrg(true);
                            studentMapper.updateByPrimaryKeySelective(studentCheck);
                            continue;
                        }
                        existTagTip += student.getStudentName() + "(" + student.getStudentIdcard() + ")；";
                        existTotalCount += 1;
                        continue;
                    }
                    if (getObjectHelper.getStudentFromPhone(student.getStudentPhone()) != null) {
                        student.setStudentPhone(null);
                    }
                    student.setHasOrg(true);
                    student.setCompany(member.getMemberName());
                    studentMapper.insertSelective(student);
                    MemberReStudent memberReStudent = new MemberReStudent();
                    memberReStudent.setStudentId(getObjectHelper.getStudentFromIdCard(student.getStudentIdcard()).getStudentId());
                    memberReStudent.setMemberKeyId(memberId);
                    memberReStudentMapper.insert(memberReStudent);
                }
            }
            String msg = "";
            if (existTotalCount > 0 && nameWrongTotalCount == 0) {
                msg = "尝试导入学员" + totalCount + "个，" + (totalCount - existTotalCount) + "个导入并绑定成功，"
                        + existTotalCount + "个在系统中已有挂靠关系，分别是：" + existTagTip;
            } else if (existTotalCount == 0 && nameWrongTotalCount > 0) {
                msg = "尝试导入学员" + totalCount + "个，" + (totalCount - nameWrongTotalCount) + "个导入并绑定成功，"
                        + nameWrongTotalCount + "个与已有账号姓名不一致，分别是：" + nameWrongTagTip;
            } else if (existTotalCount > 0 && nameWrongTotalCount > 0) {
                msg = "尝试导入学员" + totalCount + "个，" + (totalCount - nameWrongTotalCount - existTotalCount) + "个导入并绑定成功，"
                        + nameWrongTotalCount + "个与已有账号姓名不一致，分别是：" + nameWrongTagTip
                        + existTotalCount + "个在系统中已有挂靠关系，分别是：" + existTagTip;
            } else {
                msg = "全部学员信息导入成功，一共" + totalCount + "个";
            }
            result.put("code", "success");
            result.put("msg", msg);
            return result;
        } catch (Exception e) {
            result.put("msg", "导入异常，请检查格式");
            return result;
        }
    }

    private Student excelRowToStudent(Map<String, String> row) {
        Student student = new Student();
        String value;
        value = row.get("姓名");
        if (value != null && !value.equals(""))
            student.setStudentName(value);
        value = row.get("身份证");
        if (value != null && !value.equals(""))
            student.setStudentIdcard(value);
        value = row.get("手机号码");
        if (value != null && !value.equals(""))
            student.setStudentPhone(value);
        return student;
    }

    @Override
    public JSONObject getStudentList(String name, String idNum, String phone, String company, Integer hasOrg,
                                     Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        if (currentPage == null || currentPage < 1) currentPage = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        if (name != null && !name.equals("")) criteria.andStudentNameLike("%" + name + "%");
        if (idNum != null && !idNum.equals("")) criteria.andStudentIdcardEqualTo(idNum);
        if (phone != null && !phone.equals("")) criteria.andStudentPhoneEqualTo(phone);
        if (company != null && !company.equals("")) criteria.andCompanyEqualTo("%" + company + "%");
        if (hasOrg != null) criteria.andHasOrgEqualTo(hasOrg.equals(1));
        studentExample.setOffset((currentPage - 1) * pageSize);
        studentExample.setLimit(pageSize);
        List<Student> students = studentMapper.selectByExample(studentExample);
        long total = studentMapper.countByExample(studentExample);
        result.put("code", "success");
        if (students.size() == 0) {
            result.put("msg", "无符合查询条件的学员！");
        } else {
            result.put("msg", "查询成功！");
        }
        JSONObject data = new JSONObject();
        data.put("total", total);
        JSONArray list = new JSONArray();
        for (Student student : students) {
            JSONObject object = new JSONObject();
            object.put("idNum", student.getStudentIdcard());
            object.put("name", student.getStudentName());
            object.put("phone", student.getStudentPhone());
            object.put("email", student.getStudentEmail());
            object.put("company", student.getCompany());
            object.put("position", student.getStudentPosition());
            object.put("city", student.getStudentCity());
            object.put("province", student.getStudentProvince());
            object.put("area", student.getStudentArea());
            object.put("zone", student.getStudentAddress());
            MemberReStudent memberReStudent = getObjectHelper.getMemberReStudentByStudentId(student.getStudentId());
            object.put("companyId", memberReStudent == null ? null : memberReStudent.getMemberKeyId());
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject deleteOneStudentFromMember(JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        Integer memberId = jsonObject.getInteger("memberId");
        String studentIdcard = jsonObject.getString("idNum");
        result.put("code", "fail");
        Member member = memberMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            result.put("msg", "系统中不存在该会员用户");
            return result;
        }
        Student student = getObjectHelper.getStudentFromIdCard(studentIdcard);
        if (student == null) {
            result.put("msg", "系统中不存在该学员用户");
            return result;
        }
        int studentId = student.getStudentId();
        MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria = memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andMemberKeyIdEqualTo(member.getMemberKeyId());
        if (memberReStudentMapper.selectByExample(memberReStudentExample).size() == 0) {
            result.put("msg", "系统中无此挂靠关系");
            return result;
        }
        if (memberReStudentMapper.deleteByExample(memberReStudentExample) > 0) {
            result.put("code", "success");
            result.put("msg", "解除成功");
            student.setCompany(null);
            student.setHasOrg(false);
            studentMapper.updateByPrimaryKeySelective(student);
        } else {
            result.put("msg", "系统繁忙，请稍后再试");
        }
        return result;
    }

    //管理员修改学员挂靠公司
    @Override
    public JSONObject modifyRely(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Student student = getObjectHelper.getStudentFromIdCard(jsonObject.getString("idNum"));
        if (student == null) {
            result.put("msg", "系统中无该学员信息！");
            return result;
        }
        int memberId=jsonObject.getInteger("memberId");
        if(memberId!=0) {
            Member member = memberMapper.selectByPrimaryKey(memberId);
            if (member == null) {
                result.put("msg", "系统中无对应会员信息，修改失败！");
                return result;
            }
            MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
            memberReStudentExample.createCriteria().andStudentIdEqualTo(student.getStudentId());
            List<MemberReStudent> memberReStudents = memberReStudentMapper.selectByExample(memberReStudentExample);
            if (memberReStudents.size() > 0) {
                MemberReStudent memberReStudent = memberReStudents.get(0);
                memberReStudent.setMemberKeyId(member.getMemberKeyId());
                memberReStudentMapper.updateByExample(memberReStudent, memberReStudentExample);
            } else {
                MemberReStudent memberReStudent = new MemberReStudent();
                memberReStudent.setStudentId(student.getStudentId());
                memberReStudent.setMemberKeyId(memberId);
                memberReStudentMapper.insert(memberReStudent);
            }
            student.setCompany(member.getMemberName());
            student.setHasOrg(true);
            studentMapper.updateByPrimaryKeySelective(student);
        }else {
            MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
            memberReStudentExample.createCriteria().andStudentIdEqualTo(student.getStudentId());
            memberReStudentMapper.deleteByExample(memberReStudentExample);
            student.setCompany(null);
            student.setHasOrg(false);
            studentMapper.updateByPrimaryKey(student);
        }
        result.put("code", "success");
        result.put("msg", "更改学员挂靠成功！");
        return result;
    }

    //管理员修改学员资料
    @Override
    public JSONObject modifyStudentInfo(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");

        String idCard=jsonObject.getString("idNum");
        Student student=getObjectHelper.getStudentFromIdCard(idCard);
        if (student == null) {
            result.put("msg", "系统中无该学员信息！");
            return result;
        }

        String name=jsonObject.getString("name");
        String phone=jsonObject.getString("phone");
        String email=jsonObject.getString("email");
        String position=jsonObject.getString("position");
        String province=jsonObject.getString("province");
        String city=jsonObject.getString("city");
        String area=jsonObject.getString("area");
        String address=jsonObject.getString("zone");

        student.setStudentName(name);
        student.setStudentPhone(phone);
        student.setStudentEmail(email);
        student.setStudentPosition(position);
        student.setStudentProvince(province);
        student.setStudentCity(city);
        student.setStudentArea(area);
        student.setStudentAddress(address);
        if(studentMapper.updateByPrimaryKey(student)>0){
            result.put("code", "success");
            result.put("msg", "学员信息修改成功！");
        }else {
            result.put("msg", "系统繁忙，请重试");
        }
        return result;
    }

    @Override
    public JSONObject resetPassword(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Student student=getObjectHelper.getStudentFromIdCard(jsonObject.getString("idNum"));
        if(student==null){
            result.put("msg", "系统中无该学员");
            return result;
        }
        student.setStudentPassword("123456");
        if(studentMapper.updateByPrimaryKey(student)>0){
            result.put("code", "success");
            result.put("msg", "密码初始化成功！");
        }else {
            result.put("msg", "系统繁忙，请重试");
        }
        return result;
    }

    //会员管理
    @Override
    public ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request) {
        String fileName = "member_template.xlsx";
//        ServletContext servletContext=request.getServletContext();
//        String path=servletContext.getRealPath("/WEB-INF/templates/"+fileName);
        String path = "./src/main/resources/templates/member_template.xlsx";
        File file = new File(path);
        InputStream in;
        ResponseEntity<byte[]> response = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            HttpStatus statusCode = HttpStatus.OK;
            response = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public JSONObject importMemberByFile(MultipartFile file) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        if (file == null || file.isEmpty()) {
            result.put("msg", "文件不能为空");
            return result;
        }
        String fileName = file.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            result.put("msg", "文件格式错误");
            return result;
        }
        InputStream in;
        try {
            in = file.getInputStream();
            Map<String, List<Map<String, String>>> map = ExcelUtil.readXls(in);
            if (map.isEmpty()) {
                result.put("msg", "上传文件数据为空");
                return result;
            }
            Set<String> excelSheets = map.keySet();
            int totalCount = 0;
            String existTagTip = "";
            int existTotalCount = 0;
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                totalCount = totalCount + list.size();
                for (Map<String, String> row : list) {
                    // TODO 批量添加
                    Member member = excelRowToMember(row);
                    Member memberCheck = getObjectHelper.getMemberFromMemberName(member.getMemberName());
                    //系统已有该身份证的账号
                    if (memberCheck != null) {
                        existTotalCount++;
                        existTagTip += member.getMemberName() + "；";
                        continue;
                    }
                    member.setMemberId(UUIDUtil.getMemberUUID());
                    member.setEnterDate(new Date());
                    memberMapper.insertSelective(member);
                }
            }
            String msg = "尝试导入学员" + totalCount + "个，" + (totalCount - existTotalCount) + "个导入并创建账号成功。 ";
            if (existTotalCount > 0) {
                msg += ("其中" + existTotalCount + "个在系统中已有账号，分别是：" + existTagTip);
            }
            result.put("code", "success");
            result.put("msg", msg);
            return result;
        } catch (Exception e) {
            result.put("msg", "导入异常，请检查格式");
            return result;
        }
    }
    private Member excelRowToMember(Map<String, String> row) {
        Member member = new Member();
        String value;
        value = row.get("会员名称");
        if (value != null && !value.equals(""))
            member.setMemberName(value);
        value = row.get("联系手机");
        if (value != null && !value.equals(""))
            member.setMemberPhone(value);
        value = row.get("电子邮箱");
        if (value != null && !value.equals(""))
            member.setMemberEmail(value);
        value = row.get("省");
        if (value != null && !value.equals(""))
            member.setMemberProvince(value);
        value = row.get("市");
        if (value != null && !value.equals(""))
            member.setMemberCity(value);
        value = row.get("区/镇");
        if (value != null && !value.equals(""))
            member.setMemberArea(value);
        value = row.get("详细地址");
        if (value != null && !value.equals(""))
            member.setMemberAddress(value);
        return member;
    }

    @Override
    public JSONObject getMemberList(Integer type, Integer deadline, String name,
                                    Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        if (pageSize <= 0) {
            pageSize = 10;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        String memberName = name;
        int isInDeadLine = -1;
        if (deadline != null) {
            isInDeadLine = deadline;
        }
        int isVip = -1;
        if (type != null) {
            isVip = type;
        }

        //获取两个月后的时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 2);

        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        if (memberName != null && !memberName.equals("")) {
            criteria.andMemberNameLike("%" + memberName + "%");
        }
        if (isVip != -1) {
            if (isVip == 0) {
                criteria.andIsVipEqualTo(false);
            } else {
                criteria.andIsVipEqualTo(true);
                if (isInDeadLine != -1) {
                    if (isInDeadLine == 1) {
                        criteria.andVipEndDateBetween(new Date(), c.getTime());//两个月内到期
                    }
                    if (isInDeadLine == 0) {
                        criteria.andVipEndDateGreaterThan(c.getTime());
                    }
                }
            }
        }
        memberExample.setOrderByClause("enter_date DESC");
        List<Member> members = memberMapper.selectByExample(memberExample);
        JSONArray list = new JSONArray();
        int i = 1;//计数
        int sum = 0;//每页数目;
        for (Member member : members) {
            if (i <= pageSize * (currentPage - 1)) {
                i++;
                continue;
            }
            JSONObject object = new JSONObject();
            object.put("name", member.getMemberName());
            object.put("id", member.getMemberKeyId());
            object.put("phone", member.getMemberPhone());
            object.put("enterDate", member.getEnterDate());
            object.put("email", member.getMemberEmail());
            object.put("vip", 0);
            if (member.getIsVip()) {
                object.put("vip", 1);
            }
            object.put("vipBegin", member.getVipBeginDate());
            object.put("vipEnd", member.getVipEndDate());
            if (member.getVipEndDate().compareTo(new Date()) >= 0 && member.getVipEndDate().compareTo(c.getTime()) <= 0) {
                object.put("deadline", 1);
            } else {
                object.put("deadline", 0);
            }
            object.put("province", member.getMemberProvince());
            object.put("city", member.getMemberCity());
            object.put("area", member.getMemberArea());
            object.put("zone", member.getMemberAddress());

            list.add(object);
            sum++;
            if (sum == pageSize) {
                break;
            }
        }
        data.put("list", list);
        result.put("code", "success");
        result.put("msg", "查询成功");
        result.put("data", data);
        return result;

    }
    //管理员获取会员列表用于下拉框
    @Override
    public JSONObject getMemSelectList() {
        JSONObject result=new JSONObject();
        JSONArray data=new JSONArray();

        MemberExample memberExample=new MemberExample();
        memberExample.setOrderByClause("member_name ASC");
        List<Member> members=memberMapper.selectByExample(memberExample);
        for(Member member:members){
            JSONObject object=new JSONObject();
            object.put("id", member.getMemberKeyId());
            object.put("name", member.getMemberName());
            object.put("phone", member.getMemberPhone());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "列表获取成功！");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getOneMemberStudentList(Integer memberId) {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria = memberReStudentExample.createCriteria();
        criteria.andMemberKeyIdEqualTo(memberId);
        List<MemberReStudent> memberReStudents = memberReStudentMapper.selectByExample(memberReStudentExample);
        if (memberReStudents.size() == 0) {
            result.put("code", "success");
            result.put("msg", "该会员名下暂无学员");
            result.put("data", null);
            return result;
        }
        for (MemberReStudent memberReStudent : memberReStudents) {
            JSONObject object = new JSONObject();
            Student student = studentMapper.selectByPrimaryKey(memberReStudent.getStudentId());
            object.put("name", student.getStudentName());
            object.put("idNum", student.getStudentIdcard());
            object.put("phone", student.getStudentPhone());
            object.put("position", student.getStudentPosition());
            object.put("email", student.getStudentEmail());
            object.put("province", student.getStudentProvince());
            object.put("city", student.getStudentCity());
            object.put("area", student.getStudentArea());
            object.put("zone", student.getStudentAddress());
            data.add(object);
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取会员名下学员列表成功");
        return result;
    }

}
