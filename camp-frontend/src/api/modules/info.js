import { request } from "../request.js"

//设置学生信息
const setStudentInfo = par => request('/campback/student/setUserInfo', par, 'post', 'json');

//设置会员信息
const setMemberInfo = par => request('/campback/member/setUserInfo', par, 'post', 'json');

//获取学生信息
const getStudentInfo = () => request('/campback/student/getUserInfo', {}, 'get', 'json');

//获取会员信息
const getMemberInfo = () => request('/campback/member/getUserInfo', {}, 'get', 'json');

//修改学生密码
const setStudentPassword = par => request('/campback/student/setNewPassword', par, 'post', 'json');

//修改会员密码
const setMemberPassword = par => request('/campback/member/setNewPassword', par, 'post', 'json');

export default {
    setStudentInfo,
    setMemberInfo,
    getStudentInfo,
    getMemberInfo,
    setStudentPassword,
    setMemberPassword
}