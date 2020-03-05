import { request } from '../request.js'
import { studentPrefix, memberPrefix } from '../../const';
const getJoinableActivities = idType => {
    let str = idType == 0 ? studentPrefix + "/getJoinableActivities" : memberPrefix + "/getJoinableActivities"
    return request(str, 'get');
}
const studentJoinActivties = activityId => request(memberPrefix + '/joinActivity', 'post', { activityId });

const getsignedActivities = idType => {
    let str = idType == 0 ? memberPrefix + "/getSignedActivities" : memberPrefix + "/getSignedActivities"
    return request(str, 'get');
}
const getSeatNum = activityId => request(`/getSeatNum`, 'get', { activityId });

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