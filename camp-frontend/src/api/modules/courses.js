import { request } from "../request.js"

export const getCourses = (par,type) => {
    let url = `camp/get${type}Courses`;
    return request(url, par, "post", "json");
}