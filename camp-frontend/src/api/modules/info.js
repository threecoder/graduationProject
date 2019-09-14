import { request } from "../request.js"
export const setInfo = par => {
    return request("setUserInfo", par, "post", "json");
}
export const getInfo = ()=> {
    return request('getUserInfo',{},'get','json');
}
export const setPassword = par => {
    return request('setNewPassword', 'post', 'json');
}