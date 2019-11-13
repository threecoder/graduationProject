import { request } from "../request"
const commomUrl = "/campback/admin"

//获取学员列表
export const getStudentList = que => request(`${commomUrl}/getStudentList?${que}`, {}, 'get', 'json');

//获取公司列表
export const getMemSelectList = () => request(`${commomUrl}/getMemSelectList`, {}, "get", "json");

//修改某个学员的挂靠公司
export const modifyRely = par => request(`${commomUrl}/modifyRely`, par, 'post', 'json');

//重置学员密码
export const resetPassword = id => request(`${commomUrl}/resetPassword`, {id}, 'post', 'json');

//修改学员资料
export const modifyInfo = par => request(`${commomUrl}/modifyStudentInfo`, par, 'post', 'json');

//下载学员名单模板
export const getStudentTemplate = () => request(`${commomUrl}/getStudentTemplate`, {}, 'get', 'blob');

//添加单个学员员
export const addSingleStudent = par => request(`${commomUrl}/addSingleStudent`, par, 'post', 'json');