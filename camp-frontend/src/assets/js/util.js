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


//根据cook名字和key获取对应的值，没有key返回对象
export function getCookie(name){
    let cookie = document.cookie;
    let cookies = {},
        arr = cookie.split("; ");
    arr.forEach( val => {
        let nameAndVal = val.split("=");
        cookies[nameAndVal[0]] = JSON.parse(nameAndVal[1]);
    });
    if(!name){
        return cookies;
    }else{
        return cookies[name];
    }
}

//使用对象和名字设置cookie
export function setCookie(obj={},name = "camp",expires = "noExpires",domain = "nodomain"){
    let cookie = "";
    if(!obj){
        return;
    }else{
        let str = '{';
        for(let key in obj){
            str = str + `"${key}":"${obj[key]}",`;
        }
        str = str.substring(0,str.length-1) + '}';
        cookie = `${name}=${str}`;
        if(expires!="noExpires"){
            cookie += `;expires=${expires}`;
        }
        if(domain!="nodomain"){
            cookie += `;domain=${domain}`
        }
        document.cookie = cookie;
    }
}

//删除指定的cookie
export function deleteCookie(name){
    let cookie = document.cookie;
    if(name){
        let start = cookie.indexOf(cookie);
        if( start !=-1){
            let end = cookie.indexOf(";",start);
            if(end = -1){
                end = cookie.length;
            }
        }
    }
}