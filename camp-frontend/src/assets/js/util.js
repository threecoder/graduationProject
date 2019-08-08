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
