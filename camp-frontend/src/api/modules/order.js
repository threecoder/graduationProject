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

//会员获取自己拥有的优惠券
const getCouponList = par => request(`${memberPrefix}/getCouponList`, 'get', par);

//会员支付宝支付
const aliPay = (idType, data) => {
    if (idType == 0) {
        return request(`${studentPrefix}/aliPay`, 'post', data);
    } else {
        return request(`${memberPrefix}/aliPay`, 'post', data);
    }
}
export default {
    getOrderList,
    getCouponList,
    aliPay
}