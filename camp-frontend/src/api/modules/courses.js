import { request } from "../request.js";
import { indexPrefix } from '../../const';

const getCourses = par => request(`${indexPrefix}/getCourses`, "get", par);

export default {
    getCourses
}