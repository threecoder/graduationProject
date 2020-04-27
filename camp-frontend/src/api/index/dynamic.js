import { request } from "../request";
import { indexPrefix } from '../../const';

//获取协会动态列表
const getDynamicList = par => request(`${indexPrefix}/getDynamicList`, 'get', par);

//获取动态详情
const getDynamicDetail = id => request(`${indexPrefix}/getDynamicDetail`, 'get', { id });

export default {
    getDynamicList,
    getDynamicDetail
}