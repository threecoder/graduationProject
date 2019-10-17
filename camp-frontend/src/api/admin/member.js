import { request } from "../request"
const commomUrl = "/campback/admin"

//获取会员列表
export const getMemberList = par => request(`${commomUrl}/getMemberList`, par, 'post', 'json');

//获取某个会员的学员列表
export const getStudentList = memberId => request(`${commomUrl}/getOneMemberStudentList?memberId=${memberId}`, {}, 'get', 'json');

//接触某个学员的挂靠
export const deleteStudentFromMember = par => request(`${commomUrl}/deleteOneStudentFromMember`, par, 'post', 'json');

//下载会员名单模板
export const getMemberTemplate = () => request(`${commomUrl}/getMemberTemplate`, {}, 'get', 'json');