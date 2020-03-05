import { request } from "../request";
import { apiPrefix } from '../../const';

//获取会员列表
const getMemberList = par => request(`${apiPrefix}/getMemberList`, 'get', par, 'get');

//获取某个会员的学员列表
const getStudentList = memberId => request(`${apiPrefix}/getOneMemberStudentList`, 'get', { memberId });

//解除某个学员的挂靠
const deleteStudentFromMember = data => request(`${apiPrefix}/deleteOneStudentFromMember`, 'post', data);

//修改会员资料
const modifyInfo = data => request(`${apiPrefix}/modifyMemberInfo`, 'post', data);

//下载会员名单模板
const getMemberTemplate = () => request(`${apiPrefix}/getMemberTemplate`, 'get', {}, 'blob');

//下载学员名单模板
const getStudentTemplate = () => request(`${apiPrefix}/getStudentTemplate`, 'get', {}, 'blob');

//提醒会员续费
const remindRenew = memberId => request(`${apiPrefix}/remindRenew`, 'get', { memberId });

//添加单个会员
const addSingleMember = data => request(`${apiPrefix}/addSingleMember`, 'post', data);

export default {
    getMemberList,
    getStudentList,
    deleteStudentFromMember,
    modifyInfo,
    getMemberTemplate,
    getStudentTemplate,
    remindRenew,
    addSingleMember
}