import {
	request
} from "../request.js";
import {
	studentPrefix
} from "../../const.js";

//登录
const login = data => request(`/login`, 'post', data);

const logout = () => request('/logout', 'get');

//修改密码
const setPassword = data => request(studentPrefix + '/setNewPassword', 'post', data);

export default {
	login,
	logout,
	setPassword
}
