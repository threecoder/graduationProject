import { request } from "../request";
import { studentPrefix, memberPrefix } from "../../const";

//查询未读消息数量
const getUnReadMsgNum = idType => {
    if (idType == 0) {
        return request(`${studentPrefix}/getUnReadMsgNum`, 'get');
    } else {
        return request(`${memberPrefix}/getUnReadMsgNum`, 'get');
    }

}

//获取消息列表
const getMsgList = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getMsgList`, 'get', par);
    } else {
        return request(`${memberPrefix}/getMsgList`, 'get', par);
    }
}

//获取某条消息详情
const getMsgDetail = (idType, msgID) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getMsgDetail`, 'get', { msgID });
    } else {
        return request(`${memberPrefix}/getMsgDetail`, 'get', { msgID });
    }
}

//将消息内容标记为已读
const signAsRead = (idType, msgID) => {
    if (idType == 0) {
        return request(`${studentPrefix}/signAsRead`, 'post', { msgID });
    } else {
        return request(`${memberPrefix}/signAsRead`, 'post', { msgID });
    }
}

//删除消息
const deleteMsg = (idType, msgID) => {
    if (idType == 0) {
        return request(`${studentPrefix}/deleteMsg`, 'post', { msgID });
    } else {
        return request(`${memberPrefix}/deleteMsg`, 'post', { msgID });
    }
}

export default {
    getUnReadMsgNum,
    getMsgList,
    getMsgDetail,
    signAsRead,
    deleteMsg
}