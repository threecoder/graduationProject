import { request } from '../request';
import { studentPrefix, memberPrefix } from '../../const';
const getJoinableTraining = idType => {
    let str = idType == 0 ? studentPrefix + '/getJoinableTraining' : memberPrefix + '/getJoinableTraining';
    return request(str, 'get');
}
const studentJoinTraining = activityId => request(studentPrefix + '/joinActivity', 'post', { activityId });

const getsignedTraining = idType => {
    let str = idType == 0 ? studentPrefix + '/getSignedTraining' : memberPrefix + '/getSignedTraining';
    return request(str, 'get');
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