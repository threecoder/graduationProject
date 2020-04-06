import { request } from "../request";
import { apiPrefix } from "../../const";

//获取动态列表
const getNewsAndDynamicList = par => request(`${apiPrefix}/getNewsAndDynamicList`, 'get', par);

//删除一条动态
const deleteDynamic = dynamicId => request(`${apiPrefix}/deleteDynamic`, 'post', { dynamicId });

//获取一条动态的详情
const getDynamicDetail = dynamicId => request(`${apiPrefix}/getDynamicDetail`, 'get', { dynamicId });

//修改动态
const modifyDynamic = data => request(`${apiPrefix}/modifyDynamic`, 'post', data);

//新增动态
const addDynamic = data => request(`${apiPrefix}/addDynamic`, 'post', data);

export default {
    getNewsAndDynamicList,
    deleteDynamic,
    getDynamicDetail,
    modifyDynamic,
    addDynamic
}