import {
	request
} from "../request";
import {
	studentPrefix
} from "../../const";

//获取订单列表
const getOrderList = par => request(`${studentPrefix}/getOrderList`, 'get', par);


export default {
	getOrderList
}
