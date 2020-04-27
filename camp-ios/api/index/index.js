import { request } from "../request";
import { indexPrefix } from '../../const';

//获取首页轮播图信息
const getCarouselList = () => request(`${indexPrefix}/getCarouselList`, 'get');


export default {
    getCarouselList
}
