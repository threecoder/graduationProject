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


//根据cook名字获取对应的值，返回对象,对象就是cookie
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
export function setCookie(obj={},name){
    let cookies = getCookie() || {};
    if(!name){
        for(let key in cookies){
            document.cookie = `${key}={}`;
        }
    }else{
        let str = `${name}=` + JSON.stringify(obj);
        document.cookie = str;
    }
}

//删除指定的cookie
export function deleteCookie(name){
    if(!name){
        setCookie();
    }else {
        setCookie({},name);
    }
}