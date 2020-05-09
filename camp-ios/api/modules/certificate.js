import {
	request
} from "../request";
import {
	memberPrefix,
	studentPrefix
} from "../../const";

//学员获取证书列表
const getCertificateList = par => request(`${studentPrefix}/getCertificateList`, 'get', par);

//学员获取自己的过期证书
const getOverdueCerList = par => request(`${studentPrefix}/getOverdueCerList`, 'get', par);

//学员发起证书复审
const examineCertificate = cerId => request(`${studentPrefix}/examineCertificate`, 'post', {
	cerId
});

//获取证书相关信息
const getCerInfo =  par => request(`${studentPrefix}/getCerInfo`, 'get', par);

//修改证书信息
const modifyCer = cerId => request(`${studentPrefix}/modifyCer`, 'post', {cerId});

//获取审核记录
const getOperList = (idType, par) => {
	if (idType == 0) {
		return request(`${studentPrefix}/getOperList`, 'get', par);
	} else {
		return request(`${memberPrefix}/getOperList`, 'get', par);
	}
}

export default {
	getCertificateList,
	examineCertificate,
	getCerInfo,
	modifyCer,
	getOperList,
	getOverdueCerList
}
