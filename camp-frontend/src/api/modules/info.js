import { request } from "../request.js"
export const setInfo = par => {
    return request("/campback/setUserInfo", par, "post", "json");
}
export const getInfo = ()=> {
    return request('/campback/getUserInfo',{},'get','json');
}
export const setPassword = par => {
    return request('/campback/setNewPassword', par, 'post', 'json');
}