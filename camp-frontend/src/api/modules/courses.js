import { request } from "../request.js"

export const getCourses = (par) => {
    let url = `camp/getCourses`;
    return request(url, par, "post", "json");
}