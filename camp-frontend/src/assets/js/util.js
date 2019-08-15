//封装常用函数

//获取localStorage某个值
export function getLocalStorage(key){
    return JSON.parse(localStorage.getItem(key));
}

//保存localStorage
export function setLocalStorage(key,val){
    return localStorage.setItem(key,JSON.stringify(val));
}

//删除localStorage
export function removeLocalStorage(key) {
    return localStorage.removeItem(key);
}

//返回月份，从1开始
export function getMonth(){
    return ['01','02','03','04','05','06','07','08','09','10','11','12'];
}

//对象序列化
export function objToString(obj){
    if(!obj){
        return "";
    }
    let str = '{';
    for(let key in obj){
        if(obj[key]!=null && (typeof obj[key]) == "object"){
            str = str + `"${key}":"` + objToString(obj[key]) + `"`;
        }else{
            str = str + `"${key}":"${obj[key]}",`;
        }
    }
    str = str.substring(0,str.length-1) + '}';
    return str;
}

//根据cook名字和key获取对应的值，没有key返回对象
export function getCookie(name){
    let cookie = document.cookie;
    if(cookie == ""){
        return null;
    }
    let cookies = {},
        arr = cookie.split("; ");
    arr.forEach( val => {
        let nameAndVal = val.split("=");
        cookies[nameAndVal[0]] = JSON.parse(nameAndVal[1]);
    });
    if(!name){
        return cookies;
    }else{
        return cookies[name] == undefined?{}:cookies[name];
    }
}

//使用对象和名字设置cookie
export function setCookie(obj={},name = "camp"){
    if(!obj){
        return;
    }else{
        let cookies = getCookie() || {};
        cookies[name] = obj;
        for(let key in cookies){
            let str = `${key}=` + objToString(cookies[key]);
            document.cookie = str;
        }
    }
}

//删除指定的cookie
export function deleteCookie(name){
    let cookies = getCookie();
    if(name){
        cookies[name] = {};
    }
    
}