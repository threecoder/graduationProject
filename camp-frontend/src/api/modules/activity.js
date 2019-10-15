import { request } from '../request.js'
export const getJoinableActivities = idType => {
    let str = idType==0?"/campback/student/getJoinableActivities":"/campback/member/getJoinableActivities"
    return request(str, {}, 'get', 'json');
}
export const studentJoinActivties = activityId => {
    return request('/campback/student/joinActivity', { activityId }, 'post', 'json');
}
export const getsignedActivities = idType => {
    let str = idType==0?"/campback/student/getSignedActivities":"/campback/member/getSignedActivities"
    return request(str, {}, 'get', 'json');
}
export const getSeatNum = activityId => {
    return request(`/campback/student/getSeatNum?activityId=${activityId}`, {}, 'get', 'json');
}

//会员获取学生列表
export const getList = () => request("/campback/member/getStudentList", {}, 'get', 'json');

//会员批量报名
export const memberJoinActivity = par => request("/campback/member/joinActivity", par, 'post', 'json');