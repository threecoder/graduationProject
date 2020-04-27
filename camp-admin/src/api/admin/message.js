import { request } from "../request";
import { apiPrefix } from "../../const";

//查询未读消息数量
const getUnReadMsgNum = () => request(`${apiPrefix}/getUnReadMsgNum`, 'get');

//获取消息列表
const getMsgList = par => request(`${apiPrefix}/getMsgList`, 'get', par);

//获取某条消息详情
const getMsgDetail = msgID => request(`${apiPrefix}/getMsgDetail`, 'get', { msgID });

//将消息内容标记为已读
const signAsRead = msgID => request(`${apiPrefix}/signAsRead`, 'post', { msgID });

//删除消息
const deleteMsg = msgID => request(`${apiPrefix}/deleteMsg`, 'post', { msgID });

//发送消息
const sendMsg = data => request(`${apiPrefix}/sendMsg`, 'post', data);

//获取学生列表用于下拉框
const getStudentSelect = () => request(`${apiPrefix}/getStudentSelect`, 'get');

export default {
    getUnReadMsgNum,
    getMsgList,
    getMsgDetail,
    signAsRead,
    deleteMsg,
    sendMsg,
    getStudentSelect
}