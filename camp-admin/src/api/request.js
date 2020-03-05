//封装请求的方法
import Axios from 'axios'
import md5 from 'js-md5';
import fileDownload from 'js-file-download';


const SALT = "6e6s4xswqsD25WEWQ3sShLJOK";

Axios.defaults.baseURL = '/';

/**
 * @url             请求地址
 * @data            请求数据
 * @type          请求的类型，如get，post等
 * @responseType    返回类型
 */
export function request(url, type = "post", data = {}, responseType = "json") {
    return new Promise((resolve, reject) => {
        Axios({
            method: type,    //请求类型
            url,    //接收请求的接口
            data: type == 'get' ? {} : data,    //报文主体中携带的数据
            params: type == 'get' ? data : {},   //使用get时，携带在路径的请求参数
            headers: {
                ...getHeaders(),
                'Content-Type': 'application/json;charset=UTF-8'
            },
            responseType    //浏览器返回的数据类型
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err);
        });
    });
}

export function getHeaders() {
    let randomNum = getRandomNum(); //获得随机数
    const time = new Date().getTime();  //时间戳
    const sign = `#${time}#${randomNum}#${SALT}`;
    const signature = md5.hex(sign).toUpperCase();  //加密后大写
    return {
        time,
        signature,
        randomNum
    };
}

export function getRandomNum() {
    const myStr = 'abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    let randomNum = '';
    for (let i = 0; i < 16; i++) {
        let index = Number.parseInt(Math.random() * 61);
        randomNum += myStr[ index ];
    }
    return randomNum;
}

export function Encrypt(value) {
    if (!value) {
        return null;
    }
    return md5.hex(value).toUpperCase();
}

//导出文件
/**
*res: 文件流
**/
export function download(res) {
    // 处理返回的文件流
    let disposition = res.headers[ 'content-disposition' ];
    let filename = decodeURI(disposition.split("filename=")[ 1 ]);
    fileDownload(res.data, filename);
}