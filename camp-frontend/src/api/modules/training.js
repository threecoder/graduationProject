import { request } from '../request.js';
export const getJoinableTraining = () => {
    return request("/campback/student/getJoinableTraining", {}, 'get', 'json');
}
export const studentJoinActivties = activityId => {
    return request('/campback/student/joinActivity', { activityId }, 'post', 'json');
}
export const getsignedTraining = () => {
    return request("/campback/student/getSignedTraining", {}, 'get', 'json');
}
export const getSeatNum = activityId => {
    return request("/campback/student/getSeatNum", { activityId }, 'post', 'json');
}

//会员批量报名
export const memberJoinTraining = () => request("/campback/member/joinTraining", par, 'post', 'json');