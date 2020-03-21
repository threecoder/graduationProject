package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

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
    GetObjectHelper getObjectHelper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    //学员管理
    @Override
    public JSONObject getStudentList(String name, String idNum, String phone, String company, Integer hasOrg,
                                     Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        if (currentPage == null || currentPage < 1) currentPage = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        if (name != null) criteria.andStudentNameLike("%" + name + "%");
        if (idNum != null) criteria.andStudentIdcardEqualTo(idNum);
        if (phone != null) criteria.andStudentPhoneEqualTo(phone);
        if (company != null) criteria.andCompanyEqualTo("%" + company + "%");
        if (hasOrg != null) criteria.andHasOrgEqualTo(hasOrg.equals(1));
        studentExample.setOffset((currentPage - 1) * pageSize);
        studentExample.setLimit(pageSize);
        List<Student> students = studentMapper.selectByExample(studentExample);
        long total=studentMapper.countByExample(studentExample);
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
        if (deadline!=null) {
            isInDeadLine = deadline;
        }
        int isVip = -1;
        if (type!=null) {
            isVip = type;
        }

        //获取两个月后的时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 2);

        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        if (memberName != null) {
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
            object.put("id", member.getMemberId());
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
