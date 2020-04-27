import { request } from "../request";
import { indexPrefix } from '../../const';

//获取协会架构
const getFramework = () => request(`${indexPrefix}/getFramework`, 'get');

//获取协会章程
const getConstitution = () => request(`${indexPrefix}/getConstitution`, 'get');

//获取协会简介
const getBrief = () => request(`${indexPrefix}/getBrief`, 'get');

export default {
    getBrief,
    getFramework,
    getConstitution
}