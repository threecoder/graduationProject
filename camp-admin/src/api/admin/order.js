import { request } from "../request";
import { apiPrefix } from "../../const";

//获取订单列表
const getOrderList = par => request(`${apiPrefix}/getOrderList`, 'get', par);

//修改订单金额
const modifyOrderPrice = data => request(`${apiPrefix}/modifyOrderPrice`, 'post', data);

//将订单状态修改为已支付
const confirmPay = orderNum => request(`${apiPrefix}/confirmPay`, 'post', { orderNum });

export default {
    getOrderList,
    modifyOrderPrice,
    confirmPay
}