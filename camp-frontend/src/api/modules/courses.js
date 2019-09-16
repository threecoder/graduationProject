import { request } from "../request.js"

export const getCourses = (par) => {
    let url = `/campback/getCourses`;
    return request(url, par, "post", "json");
}