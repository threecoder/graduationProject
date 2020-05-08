import { request } from "../request";
import { memberPrefix, studentPrefix } from "../../const";

//学员获取证书列表
const getCertificateList = par => request(`${studentPrefix}/getCertificateList`, 'get', par);

//学员获取自己的过期证书
const getOverdueCerList = par => request(`${studentPrefix}/getOverdueCerList`, 'get', par);

//学员发起证书复审
const examineCertificate = cerId => request(`${studentPrefix}/examineCertificate`, 'post', { cerId });

//会员获取学员现有证书
const getStudentCerList = par => request(`${memberPrefix}/getStudentCerList`, 'get', par);

//获取证书相关信息
const getCerInfo = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getCerInfo`, 'get', par);
    } else {
        return request(`${memberPrefix}/getCerInfo`, 'get', par);
    }
}

//修改证书信息
const modifyCer = (idType, data) => {
    if (idType == 0) {
        return request(`${studentPrefix}/modifyCer`, 'post', data);
    } else {
        return request(`${memberPrefix}/modifyCer`, 'post', data);
    }
}

//获取审核记录
const getOperList = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getOperList`, 'get', par);
    } else {
        return request(`${memberPrefix}/getOperList`, 'get', par);
    }
}

//获取证书文件
const getCerFile = cerId => request(`${studentPrefix}/getCerFile`, 'get', { cerId }, 'blob');

export default {
    getCertificateList,
    examineCertificate,
    getStudentCerList,
    getCerInfo,
    modifyCer,
    getOperList,
    getOverdueCerList,
    getCerFile
}