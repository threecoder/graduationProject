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

//获取协会简介   /campback/admin/
const getBrief = () => request(`${apiPrefix}/getBrief`, 'get');

//获取协会架构
const getFrameWork = () => request(`${apiPrefix}/getFrameWork`, 'get');

//获取协会章程
const getConsitiution = () => request(`${apiPrefix}/getConsitiution`, 'get');

//修改协会章程
const modifyConstitution = content => request(`${apiPrefix}/modifyConstitution`, 'post', { content });

//修改协会简介
const modifyBrief = content => request(`${apiPrefix}/modifyBrief`, 'post', { content });

//修改协会架构
const modifyFramework = content => request(`${apiPrefix}/modifyFramework`, 'post', { content });

//获取轮播图上的动态
const getCarouselList = () => request(`${apiPrefix}/getCarouselList`, 'get');

//添加一条轮播图
const addCarousel = dynamicId => request(`${apiPrefix}/addCarousel`, 'post', { dynamicId });

//删除一条轮播图
const removeCarousel = dynamicId => request(`${apiPrefix}/removeCarousel`, 'post', { dynamicId });


export default {
    getNewsAndDynamicList,
    deleteDynamic,
    getDynamicDetail,
    modifyDynamic,
    addDynamic,
    getBrief,
    getFrameWork,
    getConsitiution,
    modifyConstitution,
    modifyBrief,
    modifyFramework,
    getCarouselList,
    addCarousel,
    removeCarousel
}