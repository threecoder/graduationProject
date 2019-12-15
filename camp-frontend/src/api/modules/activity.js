import { request } from '../request.js'
const getJoinableActivities = idType => {
    let str = idType==0?"/campback/student/getJoinableActivities":"/campback/member/getJoinableActivities"
    return request(str, {}, 'get', 'json');
}
const studentJoinActivties = activityId => request('/campback/student/joinActivity', { activityId }, 'post', 'json');

const getsignedActivities = idType => {
    let str = idType==0?"/campback/student/getSignedActivities":"/campback/member/getSignedActivities"
    return request(str, {}, 'get', 'json');
}
const getSeatNum = activityId => request(`/campback/student/getSeatNum?activityId=${activityId}`, {}, 'get', 'json');

//会员获取学生列表
const getList = () => request("/campback/member/getStudentList", {}, 'get', 'json');

//会员批量报名
const memberJoinActivity = par => request("/campback/member/joinActivity", par, 'post', 'json');

export default {
    getJoinableActivities,
    studentJoinActivties,
    getsignedActivities,
    getSeatNum,
    getList,
    memberJoinActivity
}