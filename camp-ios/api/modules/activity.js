import { request } from '../request.js'
import { studentPrefix, memberPrefix } from '../../const';
const getJoinableActivities = idType => {
    if (idType == 0) {
        return request(`${studentPrefix}/getJoinableActivities`, 'get');
    } else {
        return request(`${memberPrefix}/getJoinableActivities`, 'get');
    }
}
const studentJoinActivties = activityId => request(`${studentPrefix}/joinActivity`, 'post', { activityId });

const getsignedActivities = idType => {
    if (idType == 0) {
        return request(`${studentPrefix}/getSignedActivities`, 'get');
    } else {
        return request(`${memberPrefix}/getSignedActivities`, 'get');
    }
}
const getSeatNum = activityId => request(`${studentPrefix}/getSeatNum`, 'get', { activityId });

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