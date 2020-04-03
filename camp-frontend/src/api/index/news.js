import { request } from "../request";
import { indexPrefix } from '../../const';

//获取协会新闻
const getNewsList = par => request(`${indexPrefix}/getNewsList`, 'get', par);

//获取新闻详情
const getNewsDetail = id => request(`${indexPrefix}/getNewsDetail`, 'get', { id });

export default {
    getNewsList,
    getNewsDetail
}