import { request } from "../request.js"

export const getCourses = (par) => {
    let url = `/campback/index/getCourses`;
    return request(url, par, "post", "json");
}