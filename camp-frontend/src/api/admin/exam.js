import { request } from '../request';
let commentUrl = "/campback/admin";
//获取试题
export const getQuestionList = par => request(commentUrl + "/getQuestionList", par, 'post', 'json');

//获取训练列表用于确定考试归属
export const getTrainingList = () => request(commentUrl + "/getTrainingList", {}, 'get', 'json');

//修改试题信息
export const modefyQuestionInfo = par => request(commentUrl + "/modifyQuestion", par, 'post', 'json');

//添加考试
export const newExam = par => request(commentUrl + "/addNewExam", par, 'post', 'json');

//发布考试
export const publishExam = examId => request(commentUrl + `/publishExam?examId=${examId}`, {}, 'get', 'json');

//随机填充试题
export const randomFillExam = examId => request(commentUrl + `/randomFillExam?examId=${examId}`, {}, 'get', 'json');

//获取未发布考试列表
export const getNotPostExam = currentPage => request(commentUrl + `/getNotPostExam?pageSize=6&currentPage=${currentPage}`, {}, 'get', 'json');

//获取已经添加的考试信息(根据id)
export const getExamInfo = id => request(commentUrl + `/getExamInfo?id=${id}`, {}, 'get', 'json');

//修改已经添加的考试信息
export const modifyExam = par => request(commentUrl + "/modifyExamInfo", par, 'post', 'json');

//根据试卷id获取试卷题目id列表
export const getExamQuestionList = id => request(commentUrl + `/getExamQuestionList?id=${id}`, {}, 'get', 'json');

//根据题目id获取题目信息
export const getSingleQuestion = id => request(commentUrl + `/getQuestionInfo?id=${id}`, {}, 'get', 'json');

//保存给某个试卷挑选的题目
export const saveQuestionForExam = par => request(commentUrl + "/saveQuestionForExam", par, 'post', 'json');

//获取题目模板
export const getQuestionTemplate = () => request(commentUrl + "/getQuestionTemplate", {}, "get", 'blob');

//管理员批量导入试题 /campback/admin/importQuestionByFile


//获取已发布的考试列表
export const getPublishedList = par => request(commentUrl
    + `/getPublishedList?currentPage=${par.currentPage}&pageSize=${par.pageSize}`,
    {},
    'get',
    'json');

//关闭考试
export const closeExam = examId => request(commentUrl+`/closeExam`, {examId}, 'post', 'json');

//获取已作答学生信息
export const getFinishedStudentList = examId => request(commentUrl+`/getFinishedStudentList?examId=${examId}`, {}, "get", "json");

//下载成绩单模板
export const getGradeTemplate = () => request(commentUrl + "/getGradeTemplate", {}, "get", 'blob');

//上传成绩单模板 "/campback/admin/uploadGradeOfExam?examId=1"

//获取学生作答详情
export const getExamDetail = par => request(`${commentUrl}/getExamDetail?examId=${par.examId}&idNum=${par.idNum}`, {}, 'get', 'json');