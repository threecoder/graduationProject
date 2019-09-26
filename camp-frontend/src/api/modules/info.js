import { request } from "../request.js"
export const setInfo = par => {
    return request("/campback/student/setUserInfo", par, "post", "json");
}
export const getInfo = ()=> {
    return request('/campback/student/getUserInfo',{},'get','json');
}
export const setPassword = par => {
    return request('/campback/student/setNewPassword', par, 'post', 'json');
}