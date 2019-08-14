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

function parseCookie(str){
    username=1;id=1;
}