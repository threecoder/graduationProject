import { request } from '../request.js';
const getJoinableTraining = idType => {
    let str = idType == 0 ? "/campback/student/getJoinableTraining" : "/campback/member/getJoinableTraining";
    return request(str, {}, 'get', 'json');
}
const studentJoinTraining = activityId => request('/campback/student/joinActivity', { activityId }, 'post', 'json');

const getsignedTraining = idType => {
    let str = idType == 0 ? "/campback/student/getSignedTraining" : "/campback/member/getSignedTraining";
    return request(str, {}, 'get', 'json');
}
const getSeatNum = activityId =>  request("/campback/student/getSeatNum", { activityId }, 'post', 'json');

//会员批量报名
const memberJoinTraining = par => request("/campback/member/joinTraining", par, 'post', 'json');

export default {
    getJoinableTraining,
    studentJoinTraining,
    getsignedTraining,
    getSeatNum,
    memberJoinTraining
}