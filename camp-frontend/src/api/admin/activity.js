import { request } from '../request';
//添加新的活动
export const newActivity = par => request('/campback/admin/addNewActivity', par, 'post', 'json');

//获取发布的活动列表
export const getActivityList = () => request('/campback/admin/getActivityList', {}, 'get', 'json');

//管理员获取活动座位表
export const getSEATInfo = activityId => request('/campback/admin/getActivitySEAT', {activityId}, 'post', 'json');

//管理员导出报名表
export const getEntryForm = activityId => request(`/campback/admin/getEntryForm?activityId=${activityId}`, {}, 'get', 'blob');

//管理员导出座位表模板
export const getSEATForm = () => request("/campback/admin/getSEATForm", {}, 'get', 'blob');

//管理员设置座位信息
export const setSEATInfo = par => request('/campback/admin/setSEATInfo', par, 'post', 'json');