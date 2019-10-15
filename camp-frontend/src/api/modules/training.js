import { request } from '../request.js';
export const getJoinableTraining = idType => {
    let str = idType == 0 ? "/campback/student/getJoinableTraining" : "/campback/member/getJoinableTraining";
    console.log(idType)
    return request(str, {}, 'get', 'json');
}
export const studentJoinTraining = activityId => {
    return request('/campback/student/joinActivity', { activityId }, 'post', 'json');
}
export const getsignedTraining = idType => {
    let str = idType == 0 ? "/campback/student/getsignedTraining" : "/campback/member/getsignedTraining";
    return request(str, {}, 'get', 'json');
}
export const getSeatNum = activityId => {
    return request("/campback/student/getSeatNum", { activityId }, 'post', 'json');
}


//会员批量报名
export const memberJoinTraining = () => request("/campback/member/joinTraining", par, 'post', 'json');