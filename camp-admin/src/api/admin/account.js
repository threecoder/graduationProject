import { request } from "../request";
import { apiPrefix } from "../../const";

//登录
const login = data => request(`${apiPrefix}/login`, 'post', data);

//注销
const logout = data => request(`${apiPrefix}/logout`, 'post', data);

//重设密码
const resetPassword = data => request(`${apiPrefix}/setNewPassword`, 'post', data);

export default {
    login,
    logout,
    resetPassword
}