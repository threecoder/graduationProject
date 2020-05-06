import { request } from "../request";
import { apiPrefix } from "../../const";

//获取审核记录列表
const getOperLogList = par => request(`${apiPrefix}/getOperLogList`, 'get', par);

//通过某条记录申请
const passOperLog = data => request(`${apiPrefix}/passOperLog`, 'post', data);

//拒绝某条记录申请
const rejectOperLog = data => request(`${apiPrefix}/rejectOperLog`, 'post', data);

//获取某个培训的证书信息
const getCerInfo = trainingId => request(`${apiPrefix}/getCerInfo`, 'get', { trainingId });

//提交某个培训的证书信息
const modifyCerInfo = data => request(`${apiPrefix}/modifyCerInfo`, 'post', data);

//获取已经通过审核的成绩记录
const getPassGradeCheckLIst = par => request(`${apiPrefix}/getPassGradeCheckLIst`, 'get', par);

//针对某个记录发放证书
const grantCer = data => request(`${apiPrefix}/grantCer`, 'post', data);

//获取证书背景图信息
const getCerBackgroundImg = () => request(`${apiPrefix}/getCerBackgroundImg`, 'get');

//上传证书背景图片
const uploadCerBackground = data => request(`${apiPrefix}/uploadCerBackground`, 'post', data);

export default {
    getOperLogList,
    passOperLog,
    rejectOperLog,
    getCerInfo,
    modifyCerInfo,
    getPassGradeCheckLIst,
    grantCer,
    getCerBackgroundImg,
    uploadCerBackground
}