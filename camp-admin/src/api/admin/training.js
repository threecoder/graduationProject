import { request } from "../request";
import { apiPrefix } from '../../const';

//发布新的训练
const addNewTraining = data => request(`${apiPrefix}/addNewTraining`, 'post', data);

//获取已经发布的训练列表
const getPublishedTraining = par => request(`${apiPrefix}/getPublishedTraining`, 'get', par);

//根据id修改培训
const modifyTraining = data => request(`${apiPrefix}/modifyTraining`, 'post', data);

//根据id获取已经报名的学生列表
const getEnrolledStudent = trainingId => request(`${apiPrefix}/getEnrolledStudent`, 'get', {trainingId});

//获取会员列表
const getAllMemberList = () => request(`${apiPrefix}/getAllMemberList`, 'get');

//提醒学员支付


export default {
    addNewTraining,
    getPublishedTraining,
    modifyTraining,
    getEnrolledStudent,
    getAllMemberList
}