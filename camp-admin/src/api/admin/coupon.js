import { request } from "../request";
import { apiPrefix } from "../../const"

//获取现有优惠券列表
const getCouponList = par => request(`${apiPrefix}/getCouponList`, 'get', par);

//删除一张优惠券
const deleteCoupon = couponId => request(`${apiPrefix}/deleteCoupon`, 'post', { couponId });

//发放优惠券给会员
const grantCoupon = data => request(`${apiPrefix}/grantCoupon`, 'post', data);

//发给所有会员
const allGrantCoupon = couponId => request(`${apiPrefix}/allGrantCoupon`, 'post', { couponId });

//新建优惠券
const addCoupon = data => request(`${apiPrefix}/addCoupon`, 'post', data);

//获取会员列表
const getMemberList = par => request(`${apiPrefix}/couponGetMemberList`, 'get', par);


export default {
    getCouponList,
    deleteCoupon,
    grantCoupon,
    allGrantCoupon,
    addCoupon,
    getMemberList
}