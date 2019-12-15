import { request } from "../request.js"

const getCourses = (par) => request(`/campback/index/getCourses`, par, "post", "json");

export default {
    getCourses
}