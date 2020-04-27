import { request } from '../request';
import { studentPrefix, memberPrefix } from '../../const';
const getJoinableTraining = (idType, par) => {
    let str = idType == 0 ? studentPrefix : memberPrefix;
    str += '/getJoinableTraining'
    return request(str, 'get', par);
}
const studentJoinTraining = activityId => request(studentPrefix + '/joinActivity', 'post', { activityId });

const getsignedTraining = (idType, par) => {
    let str = idType == 0 ? studentPrefix : memberPrefix;
    str += '/getSignedTraining'
    return request(str, 'get', par);
}
const getSeatNum = activityId => request(studentPrefix + '/getSeatNum', 'post', { activityId });

//会员批量报名
const memberJoinTraining = par => request(memberPrefix + '/joinTraining', 'post', par);

export default {
    getJoinableTraining,
    studentJoinTraining,
    getsignedTraining,
    getSeatNum,
    memberJoinTraining
}