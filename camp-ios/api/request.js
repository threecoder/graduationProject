//封装请求的方法
import Axios from 'axios'
import md5 from 'js-md5';
// import fileDownload from 'js-file-download';
import {
	baseUrl
} from '../const';


const SALT = "6e6s4xswqsD25WEWQ3sShLJOK";

Axios.defaults.baseURL = 'http://112.126.67.240:3000';

/**
 * @url             请求地址
 * @data            请求数据
 * @type          请求的类型，如get，post等
 * @responseType    返回类型
 */
export function request(url, type, data = {}, responseType = "json") {
	return new Promise((resolve, reject) => {
		uni.request({
			method: type, //请求类型
			url: baseUrl + url, //接收请求的接口
			data: type == 'get' ? {} : data, //报文主体中携带的数据
			params: type == 'get' ? data : {}, //使用get时，携带在路径的请求参数
			header: {
				...getHeaders(),
				'Content-Type': 'application/json;charset=UTF-8'
			},
			responseType //浏览器返回的数据类型
		}).then(res => {
			let response = res[1];
			if (response) {
				if (response.header['Content-Type'] == 'application/json;charset=UTF-8') {
					if (response.data.code == 'success') {
						resolve(response.data.data);
					} else if (response.data.code = 'error') {
						//处理未登录
					} else if (response.data.code == 'fail') {
						throw new Error(response.data.msg);
					} else {
						throw new Error('未知错误');
					}
				} else {
					resolve(response);
				}
			} else {
				throw new Error("你当前网络有问题哦")
			}
		}).catch( e => {
			reject(e);
		})
	});
}

export function getHeaders() {
	let randomNum = getRandomNum(); //获得随机数
	const time = new Date().getTime(); //时间戳
	const sign = `#${time}#${randomNum}#${SALT}`;
	const signature = md5.hex(sign).toUpperCase(); //加密后大写
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
		randomNum += myStr[index];
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
// export function download(res) {
//     // 处理返回的文件流
//     let disposition = res.headers[ 'content-disposition' ];
//     let filename = decodeURI(disposition.split("filename=")[ 1 ]);
//     fileDownload(res.data, filename);
// }
