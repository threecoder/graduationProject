import { request } from "../request";
import { apiPrefix } from '../../const';

//发布新的训练
const addNewTraining = data => request(`${apiPrefix}/addNewTraining`, 'post', data);

//获取已经发布的训练列表
const getPublishedTraining = par => request(`${apiPrefix}/getPublishedTraining`, 'get', par);

//根据id修改培训
const modifyTraining = data => request(`${apiPrefix}/modifyTraining`, 'post', data);

export default {
    addNewTraining,
    getPublishedTraining,
    modifyTraining
}