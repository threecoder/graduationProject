import {
	request
} from '../request';
import {
	studentPrefix,
	memberPrefix
} from '../../const';
//获取可以报名的培训
const getJoinableTraining = () => request(`${studentPrefix}/getJoinableTraining`, 'get');

const studentJoinTraining = trainingId => request(`${studentPrefix}/joinTraining`, 'post', {
	trainingId
});

//获取已经报名的培训
const getsignedTraining = () => request(`${studentPrefix}/getSignedTraining`, 'get');

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
