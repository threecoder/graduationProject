import { request } from '../request';
import { apiPrefix } from '../../const';
//获取试题
const getQuestionList = par => request(apiPrefix + "/getQuestionList", 'get', par);

//获取训练列表用于确定考试归属
const getTrainingList = () => request(apiPrefix + "/getTrainingList", 'get');

//修改试题信息
const modefyQuestionInfo = data => request(apiPrefix + "/modifyQuestion", 'post', data);

//添加考试
const newExam = data => request(apiPrefix + "/addNewExam", 'post', data);

//发布考试
const publishExam = examId => request(apiPrefix + `/publishExam`, 'post', { examId });

//随机填充试题
const randomFillExam = examId => request(apiPrefix + `/randomFillExam`, 'post', { examId });

//获取未发布考试列表
const getNotPostExam = currentPage => request(apiPrefix + `/getNotPostExam`, 'get', { currentPage, pageSize: 6 });

//获取已经添加的考试信息(根据id)
const getExamInfo = examId => request(apiPrefix + `/getExamInfo`, 'get', { examId });

//修改已经添加的考试信息
const modifyExam = data => request(apiPrefix + "/modifyExamInfo", 'post', data);

//根据试卷id获取试卷题目id列表
const getExamQuestionList = examId => request(apiPrefix + `/getExamQuestionList`, 'get', { examId });

//根据题目id获取题目信息
const getSingleQuestion = examId => request(apiPrefix + `/getQuestionInfo`, 'get', { examId });

//保存给某个试卷挑选的题目
const saveQuestionForExam = par => request(apiPrefix + "/saveQuestionForExam", 'post', par);

//获取题目模板
const getQuestionTemplate = () => request(apiPrefix + "/getQuestionTemplate", "get", {}, 'blob');

//管理员批量导入试题 /campback/admin/importQuestionByFile


//获取已发布的考试列表
const getPublishedList = par => request(apiPrefix + `/getPublishedList`, 'get', par);

//关闭考试
const closeExam = examId => request(apiPrefix + `/closeExam`, 'post', { examId });

//获取已作答学生信息
const getFinishedStudentList = examId => request(apiPrefix + `/getFinishedStudentList`, "get", { examId });

//修改考试成绩
const modifyGrade = data => request(apiPrefix + '/modifyGrade', 'post', data);

//下载成绩单模板
const getGradeTemplate = () => request(apiPrefix + "/getGradeTemplate", "get", {}, 'blob');

//上传成绩单模板 "/campback/admin/uploadGradeOfExam?examId=1"

//获取学生作答详情
const getExamDetail = par => request(`${apiPrefix}/getExamDetail`, 'get', par);

//获取成绩审核员名单
const getCheckerList = () => request(`${apiPrefix}/getCheckerList`, 'get');

//获取待审核学生成绩列表
const getGradeList = examId => request(`${apiPrefix}/getGradeList`, 'get', { examId });

//提交成绩审核
const submitGradeList = data => request(`${apiPrefix}/submitGradeList`, 'post', data);

//获取本账号待审核成绩记录
const getWaitingGradeList = par => request(`${apiPrefix}/getWaitingGradeList`, 'get', par);

//通过某条记录
const approvalSingleRecord = recordId => request(`${apiPrefix}/approvalSingleRecord`, 'post', { recordId });

//通过一批记录
const approvalManyRecords = ids => request(`${apiPrefix}/approvalManyRecords`, 'post', { ids });

//拒绝某条记录
const refuseSingleRecord = data => request(`${apiPrefix}/refuseSingleRecord`, 'post', data);

//拒绝一批记录
const refuseManyRecords = data => request(`${apiPrefix}/refuseManyRecords`, 'post', data);

//获取某次考试的审核记录
const getCheckRecordList = par => request(`${apiPrefix}/getCheckRecordList`, 'get', par);

export default {
    getQuestionList,
    getTrainingList,
    modefyQuestionInfo,
    newExam,
    publishExam,
    randomFillExam,
    getNotPostExam,
    getExamInfo,
    modifyExam,
    getExamQuestionList,
    getSingleQuestion,
    saveQuestionForExam,
    getQuestionTemplate,
    getPublishedList,
    closeExam,
    getFinishedStudentList,
    getGradeTemplate,
    getExamDetail,
    getCheckerList,
    getGradeList,
    submitGradeList,
    getWaitingGradeList,
    approvalSingleRecord,
    approvalManyRecords,
    refuseSingleRecord,
    refuseManyRecords,
    getCheckRecordList,
    modifyGrade
}