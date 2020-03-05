import { request } from '../request.js';
import { memberPrefix } from '../../const';


//获取学生列表
const getStudentList = par => request(memberPrefix + '/getStudentListByCondition', 'get', par);

//删除一个学生的挂靠关系
const deleteOneStudent = data => request(memberPrefix + '/deleteOneStudent', 'post', data);

//添加一个学生
const addOneStudent = data => request(memberPrefix + '/importSingleStudent', 'post', data);

//获取学生模板
const getTemplate = () => request(memberPrefix + '/getTemplate', 'get', {}, 'blob');

//上传学生账号Excel文件，upload的action写了路径

export default {
    getStudentList,
    deleteOneStudent,
    addOneStudent,
    getTemplate
}