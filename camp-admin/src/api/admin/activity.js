import { request } from '../request';
import { apiPrefix } from '../../const';
//添加新的活动
const newActivity = data => request(apiPrefix + '/addNewActivity', 'post', data);

//获取发布的活动列表
const getActivityList = () => request(apiPrefix + '/getActivityList', 'get');

//管理员获取活动座位表
const getSEATInfo = activityId => request(apiPrefix + '/getActivitySEAT', 'get', { activityId });

//管理员导出报名表
const getEntryForm = activityId => request(apiPrefix + `/getEntryForm`, 'get', { activityId }, 'blob');

//管理员导出座位表模板
const getSEATForm = () => request(apiPrefix + "/getSEATForm", 'get', {}, 'blob');

//管理员设置座位信息
const setSEATInfo = data => request(apiPrefix + '/setSEATInfo', 'post', data);

//自动排位
const autoSEAT = activityId => request(apiPrefix + "/autoSEAT", 'post', { activityId });

export default {
    newActivity,
    getActivityList,
    getSEATInfo,
    getEntryForm,
    getSEATForm,
    setSEATInfo,
    autoSEAT
}