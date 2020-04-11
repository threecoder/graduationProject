import { request } from "../request";
import { indexPrefix } from '../../const';

//获取协会公告
const getNoticeList = par => request(`${indexPrefix}/getNoticeList`, 'get', par);

//获取公告详情
const getNoticeDetail = id => request(`${indexPrefix}/getNoticeDetail`, 'get', { id });

export default {
    getNoticeList,
    getNoticeDetail
}