import { request } from "../request"
const commomUrl = "/campback/admin"

//获取会员列表
const getMemberList = par => request(`${commomUrl}/getMemberList`, par, 'post', 'json');

//获取某个会员的学员列表
const getStudentList = memberId => request(`${commomUrl}/getOneMemberStudentList?memberId=${memberId}`, {}, 'get', 'json');

//解除某个学员的挂靠
const deleteStudentFromMember = par => request(`${commomUrl}/deleteOneStudentFromMember`, par, 'post', 'json');

//下载会员名单模板
const getMemberTemplate = () => request(`${commomUrl}/getMemberTemplate`, {}, 'get', 'blob');

//下载学员名单模板
const getStudentTemplate = () => request(`${commomUrl}/getStudentTemplate`, {}, 'get', 'blob');

//提醒会员续费
const remindRenew = memberId => request(`${commomUrl}/remindRenew?memberId=${memberId}`, {}, 'get', 'json');

//添加单个会员
const addSingleMember = par => request(`${commomUrl}/addSingleMember`, par, 'post', 'json');

export default {
    getMemberList,
    getStudentList,
    deleteStudentFromMember,
    getMemberTemplate,
    getStudentTemplate,
    remindRenew,
    addSingleMember
}