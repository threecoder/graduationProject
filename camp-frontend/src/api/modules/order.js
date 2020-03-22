import { request } from "../request";
import { memberPrefix, studentPrefix } from "../../const";

//获取订单列表
const getOrderList = (idType, par) => {
    if (idType == 0) {
        return request(`${studentPrefix}/getOrderList`, 'get', par);
    } else {
        return request(`${memberPrefix}/getOrderList`, 'get', par);
    }
}


export default {
    getOrderList
}