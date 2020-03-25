import { request } from "../request";
import { apiPrefix } from "../../const";

//获取管理员账号
const getAdminList = par => request(`${apiPrefix}/getAdminList`, 'get', par);

//冻结某个账号
const lockAccount = account => request(`${apiPrefix}/lockAccount`, 'post', { account });

//解冻某个账号
const unLockAccount = account => request(`${apiPrefix}/unLockAccount`, 'post', { account });

//添加一个管理员账号
const addNewAdmin = data => request(`${apiPrefix}/addNewAdmin`, 'post', data);

//获取某个账号的权限列表
const getAuthority = account => request(`${apiPrefix}/getAuthority`, 'get', { account });

//修改某个账号权限
const modifyAuthority = data => request(`${apiPrefix}/modifyAuthority`, 'post', data);

//获取系统参数
const getSystemParams = () => request(`${apiPrefix}/getSystemParams`, 'get');

//修改系统参数
const modifySystemParams = data => request(`${apiPrefix}/modifySystemParams`, 'post', data);

export default {
    getAdminList,
    lockAccount,
    unLockAccount,
    addNewAdmin,
    getAuthority,
    modifyAuthority,
    getSystemParams,
    modifySystemParams
}