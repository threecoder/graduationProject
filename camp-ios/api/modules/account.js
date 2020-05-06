import {
	request
} from "../request.js";
import {
	studentPrefix
} from "../../const.js";
const login = data => request(`/login`, 'post', data);

export default {
	login
}
