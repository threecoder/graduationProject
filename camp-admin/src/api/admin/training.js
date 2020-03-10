import { request } from "../request";
import { apiPrefix } from '../../const';

//发布新的训练
const addNewTraining = data => request(`${apiPrefix}/addNewTraining`, 'post', data);

//获取已经发布的训练列表
const getPublishedTraining = par => request(`${apiPrefix}/getPublishedTraining`, 'get', par);

//根据id修改培训
const modifyTraining = data => request(`${apiPrefix}/modifyTraining`, 'post', data);

//根据id获取已经报名的学生列表
const getEnrolledStudent = trainingId => request(`${apiPrefix}/getEnrolledStudent`, 'get', { trainingId });

//提醒部分会员报名
const promptMemberEnrollTraining = data => request(`${apiPrefix}/promptMemberEnrollTraining`, 'post', data);

//提醒所有会员员报名
const promptAllMemberEnrollTraining = trainingId => request(`${apiPrefix}/promptAllMemberEnrollTraining`, 'post', { trainingId });

//提醒部分学员报名
const promptStudentEnrollTraining = data => request(`${apiPrefix}/promptStudentEnrollTraining`, 'post', data);

//提醒所有未报名的学员报名
const promptAllStudentEnrollTraining = trainingId => request(`${apiPrefix}/promptAllStudentEnrollTraining`, 'post', { trainingId });

//获取未报名学员列表
const getNotEnrollStudentList = par => request(`${apiPrefix}/getNotEnrollStudentList`, 'get', par);

//获取已报名学员列表
const getEnrolledStudentList = par => request(`${apiPrefix}/getEnrolledStudentList`, 'get', par);

//获取某个培训的订单列表
const getTrainingOrderList = par => request(`${apiPrefix}/getTrainingOrderList`, 'get', par);

//修改某个订单金额
const modifyTrainingOrderPrice = data => request(`${apiPrefix}/modifyTrainingOrderPrice`, 'post', data);

export default {
    addNewTraining,
    getPublishedTraining,
    modifyTraining,
    getEnrolledStudent,
    promptMemberEnrollTraining,
    promptAllMemberEnrollTraining,
    promptStudentEnrollTraining,
    promptAllStudentEnrollTraining,
    getNotEnrollStudentList,
    getEnrolledStudentList,
    getTrainingOrderList,
    modifyTrainingOrderPrice
}