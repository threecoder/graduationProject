import { request } from '../request.js'
import { studentPrefix, memberPrefix } from '../../const';
const getJoinableActivities = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getJoinableActivities`, 'get', par);
    } else {
        return request(`${memberPrefix}/getJoinableActivities`, 'get', par);
    }
}
const studentJoinActivties = activityId => request(`${studentPrefix}/joinActivity`, 'post', { activityId });

const getsignedActivities = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getSignedActivities`, 'get', par);
    } else {
        return request(`${memberPrefix}/getSignedActivities`, 'get', par);
    }
}
const getSeatNum = (idType, activityId) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getSeatNum`, 'get', { activityId });
    } else {
        return request(`${memberPrefix}/getSeatNum`, 'get', { activityId });
    }
}

//会员获取学生列表
const getList = () => request(memberPrefix + "/getStudentList", 'get');

//会员批量报名
const memberJoinActivity = data => request(memberPrefix + "/joinActivity", 'post', data);

export default {
    getJoinableActivities,
    studentJoinActivties,
    getsignedActivities,
    getSeatNum,
    getList,
    memberJoinActivity
}