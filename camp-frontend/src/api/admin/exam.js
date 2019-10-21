import { request } from '../request';
let commentUrl = "/campback/admin";
//获取试题
export const getQuestionList = par => request(commentUrl+"/getQuestionList", par, 'post', 'json');

//获取训练列表用于确定考试归属
export const getTrainingList = () => request(commentUrl+"/getTraingingList", par, 'get', 'json');

//修改试题信息
export const modefyQuestionInfo = par => request(commentUrl+"/modifyQuestion", par, 'post', 'json');

//添加考试
export const newExam = par => request(commentUrl+"/addNewExam", par, 'post', 'json');

//发布考试
export const publishExam = examId => request(commentUrl+`/publishExam?examId=${examId}`, {}, 'get', 'json');

//随机填充试题
export const randomFillExam = examId => request(commentUrl+`/randomFillExam?examId=${examId}`, {}, 'get', 'json');

//获取未发布考试列表
export const getNotPostExam = currentPage => request(commentUrl+`/getNotPostExam?pageSize=6&currentPage=${currentPage}`, {}, 'get', 'json');