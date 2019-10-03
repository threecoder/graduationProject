import { request } from '../request.js'
export const getJoinableActivities = () => {
    return request("/campback/student/getJoinableActivities", {}, 'get', 'json');
}
export const studentJoinActivties = activityId => {
    return request('/campback/student/joinActivity', { activityId }, 'post', 'json');
}
export const getsignedActivities = () => {
    return request("/campback/student/getSignedActivities", {}, 'get', 'json');
}
export const getSeatNum = activityId => {
    return request("/campback/student/getSeatNum", { activityId }, 'post', 'json');
}

//会员获取学生列表
export const getList = () => request("/campback/member/getStudentList", {}, 'get', 'json');

//会员批量报名
export const memberJoinActivity = () => request("/campback/member/joinActivity", par, 'post', 'json');