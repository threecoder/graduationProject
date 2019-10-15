import { request } from '../request.js';


//获取学生列表
export const getStudentList = par => request( '/campback/member/getStudentListByCondition', par, "post", 'json');

//删除一个学生的挂靠关系
export const deleteOneStudent = par => request('/campback/member/deleteOneStudent', par, "post", 'json');

//添加一个学生
export const addOneStudent = par => request("/campback/member/importSingleStudent", par, 'post', 'json');

//获取学生模板
export const getTemplate = () => request('/campback/member/getTemplate', {}, 'get', 'blob');

//上传学生账号Excel文件，upload的action写了路径