//封装常用函数

//获取localStorage某个值
export function getLocalStorage(key) {
    return JSON.parse(window.localStorage.getItem(key));
}
//保存localStorage
export function setLocalStorage(key, val) {
    return window.localStorage.setItem(key, JSON.stringify(val));
}

//删除localStorage
export function removeLocalStorage(key) {
    return window.localStorage.removeItem(key);
}

//返回月份，从1开始
export function getMonth() {
    return [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ];
}


//根据cook名字获取对应的值，返回对象,对象就是cookie
export function getCookie(name) {
    let cookie = document.cookie;
    if (cookie == "") {
        return null;
    }
    let cookies = {},
        arr = cookie.split("; ");
    arr.forEach(val => {
        let nameAndVal = val.split("=");
        cookies[ nameAndVal[ 0 ] ] = JSON.parse(nameAndVal[ 1 ]);
    });
    if (!name) {
        return cookies;
    } else {
        return cookies[ name ] == undefined ? {} : cookies[ name ];
    }
}

//使用对象和名字设置cookie
export function setCookie(obj = {}, name) {
    let cookies = getCookie() || {};
    if (!name) {
        for (let key in cookies) {
            document.cookie = `${key}={}`;
        }
    } else {
        let str = `${name}=` + JSON.stringify(obj);
        document.cookie = str;
    }
}

//删除指定的cookie
export function deleteCookie(name) {
    if (!name) {
        setCookie();
    } else {
        setCookie({}, name);
    }
}

//格式化日期
export function formatDate(date) {
    let y = date.getYear() + 1900,
        m = (date.getMonth() + 1).toString().padStart(2, "0"),
        d = date.getDate().toString().padStart(2, "0");
    return `${y}-${m}-${d}`;
}
//格式化时间
export function formatTime(date) {
    let h = date.getHours().toString().padStart(2, "0"),
        min = date.getMinutes().toString().padStart(2, "0"),
        s = date.getSeconds().toString().padStart(2, "0")
    return `${h}:${min}:${s}`;
}
export function formatDateAndTime(date) {
    if (!date) {
        return null;
    }
    return `${formatDate(date)} ${formatTime(date)}`;
}

export function getCanGoPath() {
    return [
        '/adminLogin',
        '/404',
        '/',
    ]
}