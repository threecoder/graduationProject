import { request } from "../request";
import { studentPrefix } from "../../const";

//查询未读消息数量
const getUnReadMsgNum = () => request(`${studentPrefix}/getUnReadMsgNum`, 'get');

//获取消息列表
const getMsgList =  par => request(`${studentPrefix}/getMsgList`, 'get', par);

//获取某条消息详情
const getMsgDetail = msgID => request(`${studentPrefix}/getMsgDetail`, 'get', { msgID });

//将消息内容标记为已读
const signAsRead = msgID => request(`${studentPrefix}/signAsRead`, 'post', { msgID });

//删除消息
const deleteMsg = msgID => request(`${studentPrefix}/deleteMsg`, 'post', { msgID });

export default {
    getUnReadMsgNum,
    getMsgList,
    getMsgDetail,
    signAsRead,
    deleteMsg
}