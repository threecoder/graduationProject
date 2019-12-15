import { request } from '../request.js'

//获取还没答题的试卷列表
const getTodoExam = () => request("/campback/student/getTodoExamList", {}, "get", "json");

//获取答过但未完结的试卷列表
const getHalfExam = () => request('/campback/student/getHalfExamList', {}, "get", "json");

//获取一个考试的基本信息
const getExamIndexInfo = examId => request("/campback/student/getExamInfo", { examId }, 'post', 'json');

//获取一个考试的具体问题
const getExamQuestion = examId => request('/campback/student/getExamQuestions', { examId }, 'post', 'json');

//获取已经完结的考试列表
const getDoneExam = () => request("/campback/student/getDoneExamList", {}, 'get', 'json');


//获取考试详情
const getExamDetail = examId => request("/campback/student/getExamDetail", { examId }, 'post', 'json');

//重新报名考试
const rejoinExam = examId => request("/campback/student/rejoinExam", { examId }, 'post', 'json');

//提交考试
const submitExam = par => request('/campback/student/submitExam', par, 'post', 'json');

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