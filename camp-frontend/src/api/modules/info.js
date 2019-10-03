import { request } from "../request.js"
export const setInfo = (par,type) => {
    let url = null;
    if(type==0){
        url = '/campback/student/setUserInfo';
    }else{
        url = '/campback/member/setUserInfo';
    }
    return request(url, par, "post", "json");
}
export const getInfo = (type)=> {
    let url = null;
    if(type==0){
        url = '/campback/student/getUserInfo';
    }else{
        url = '/campback/member/getUserInfo';
    }
    return request(url, {},'get','json');
}
export const setPassword = (par,type) => {
    let url = null;
    if(type==0){
        url = '/campback/student/setNewPassword';
    }else{
        url = '/campback/member/setNewPassword';
    }
    return request(url, par, 'post', 'json');
}