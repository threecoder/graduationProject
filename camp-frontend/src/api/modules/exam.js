import {request} from '../request.js'
export const getTodoExam = () => {
    return request("/campback/getTodoExamList", {}, "get", "json");
}
export const getHalfExam = () => {
    return request('/campback/getHalfExamList', {}, "get", "json");
}