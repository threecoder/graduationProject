import { request } from "../request";
import { studentPrefix, memberPrefix } from '../../const';

//设置学生信息
const setStudentInfo = data => request(studentPrefix + '/setUserInfo', 'post', data);

//设置会员信息
const setMemberInfo = data => request(memberPrefix + '/setUserInfo', 'post', data);

//获取学生信息
const getStudentInfo = () => request(studentPrefix + '/getUserInfo', 'get');

//获取会员信息
const getMemberInfo = () => request(memberPrefix + '/getUserInfo', 'get');

//修改学生密码
const setStudentPassword = data => request(studentPrefix + '/setNewPassword', 'post', data);

//修改会员密码
const setMemberPassword = data => request(memberPrefix + '/setNewPassword', 'post', data);

const setPassword = (idType, data) => {
    if (idType == 0) {
        return setStudentPassword(data);
    } else {
        return setMemberPassword(data);
    }
}

export default {
    setStudentInfo,
    setMemberInfo,
    getStudentInfo,
    getMemberInfo,
    setStudentPassword,
    setMemberPassword,
    setPassword
}