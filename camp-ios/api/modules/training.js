import {
	request
} from '../request';
import {
	studentPrefix,
	memberPrefix
} from '../../const';
//获取可以报名的培训
const getJoinableTraining = par => request(`${studentPrefix}/getJoinableTraining`, 'get', par);

const studentJoinTraining = trainingId => request(`${studentPrefix}/joinTraining`, 'post', {
	trainingId
});

//获取已经报名的培训
const getsignedTraining = par => request(`${studentPrefix}/getSignedTraining`, 'get', par);

const getSeatNum = activityId => request(studentPrefix + '/getSeatNum', 'post', {
	activityId
});

//会员批量报名
const memberJoinTraining = par => request(memberPrefix + '/joinTraining', 'post', par);

export default {
	getJoinableTraining,
	studentJoinTraining,
	getsignedTraining,
	getSeatNum,
	memberJoinTraining
}
