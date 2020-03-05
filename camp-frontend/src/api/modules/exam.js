import { request } from '../request';
import { studentPrefix } from '../../const';

//获取还没答题的试卷列表
const getTodoExam = () => request(studentPrefix + "/getTodoExamList", "get");

//获取答过但未完结的试卷列表
const getHalfExam = () => request(studentPrefix + '/getHalfExamList', "get");

//获取一个考试的基本信息
const getExamIndexInfo = examId => request(studentPrefix + "/getExamInfo", 'get', { examId });

//获取一个考试的具体问题
const getExamQuestion = examId => request(studentPrefix + '/getExamQuestions', 'get', { examId });

//获取已经完结的考试列表
const getDoneExam = () => request(studentPrefix + "/getDoneExamList", 'get');


//获取考试详情
const getExamDetail = examId => request(studentPrefix + "/getExamDetail", 'get', { examId });

//重新报名考试
const rejoinExam = examId => request(studentPrefix + "/rejoinExam", 'post', { examId });

//提交考试
const submitExam = data => request(studentPrefix + '/submitExam', 'post', data);

export default {
    getTodoExam,
    getHalfExam,
    getExamIndexInfo,
    getExamQuestion,
    getDoneExam,
    getExamDetail,
    rejoinExam,
    submitExam
}