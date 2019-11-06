import { request } from "../request"
const commomUrl = "/campback/admin"

//下载学员名单模板
export const getStudentTemplate = () => request(`${commomUrl}/getStudentTemplate`, {}, 'get', 'blob');

//添加单个学员员
export const addSingleStudent = par => request(`${commomUrl}/addSingleStudent`, par, 'post', 'json');