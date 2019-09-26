import { request } from '../request.js'
export const getJoinableActivities = () => {
    return request("/campback/student/getJoinableActivities", {}, 'get', 'json');
}
export const joinActivties = activityId => {
    return request('/campback/student/joinActivity', { activityId }, 'post', 'json');
}
export const getsignedActivities = () => {
    return request("/campback/student/getsignedActivities", {}, 'get', 'json');
}
export const getSeatNum = activityId => {
    return request("/campback/student/getSeatNum", { activityId }, 'post', 'json');
}