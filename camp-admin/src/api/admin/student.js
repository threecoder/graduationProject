import { request } from "../request";
import { apiPrefix } from '../../const';

//获取学员列表
const getStudentList = par => request(`${apiPrefix}/getStudentList`, 'get', par);

//获取公司列表
const getMemSelectList = () => request(`${apiPrefix}/getMemSelectList`, "get");

//修改某个学员的挂靠公司
const modifyRely = data => request(`${apiPrefix}/modifyRely`, 'post', data);

//重置学员密码
const resetPassword = id => request(`${apiPrefix}/resetPassword`, 'post', { id });

//修改学员资料
const modifyInfo = data => request(`${apiPrefix}/modifyStudentInfo`, 'post', data);

//下载学员名单模板
const getStudentTemplate = () => request(`${apiPrefix}/getStudentTemplate`, 'get', {}, 'blob');

//添加单个学员员
const addSingleStudent = data => request(`${apiPrefix}/addSingleStudent`, 'post', data);

//获取某个学员参加过的培训记录
const getStudentTrainingHistory = par => request(`${apiPrefix}/getStudentTrainingHistory`, 'get', par);

//获取某个学员所拥有的证书
const getStudentCertificate = par => request(`${apiPrefix}/getStudentCertificate`, 'get', par);

export default {
    getStudentList,
    getMemSelectList,
    modifyRely,
    resetPassword,
    modifyInfo,
    getStudentTemplate,
    addSingleStudent,
    getStudentTrainingHistory,
    getStudentCertificate
}