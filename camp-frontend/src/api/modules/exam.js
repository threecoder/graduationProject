import { request } from '../request.js'

//获取还没答题的试卷列表
export const getTodoExam = () => {
    return request("/campback/student/getTodoExamList", {}, "get", "json");
}

//获取答过但未完结的试卷列表
export const getHalfExam = () => {
    return request('/campback/student/getHalfExamList', {}, "get", "json");
}

//获取一个考试的基本信息
export const getExamIndexInfo = examId => {
    return request("/campback/student/getExamInfo", { examId }, 'post', 'json');
}

//获取一个考试的具体问题
export const getExamQuestion = examId => {
    return request('/campback/student/getExamQuestions', { examId }, 'post', 'json');
}

//获取已经完结的考试列表
export const getDoneExam = () => {
    return request("/campback/student/getDoneExamList", {}, 'get', 'json');
}

//获取考试详情
export const getExamDetail = examId => {
    return request("/campback/student/getExamDetail", {examId}, 'post', 'json');
}

//重新报名考试
export const rejoinExam = examId => {
    return request("/campback/student/rejoinExam", {examId}, 'post', 'json');
}

//提交考试
export const submitExam = par => request('/campback/student/submitExam', par, 'post', 'json');