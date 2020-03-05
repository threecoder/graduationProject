import { request } from "../request";
import { apiPrefix } from '../../const';

//发布新的活动
const addNewTraining = data => request(`${apiPrefix}/addNewTraining`, 'post', data);

export default {
    addNewTraining
}