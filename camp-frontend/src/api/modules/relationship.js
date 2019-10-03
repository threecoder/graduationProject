import { request } from '../request.js';
export const getStudentList = () => request( '/campback/member/getStudentList', {}, "get", 'json');
export const deleteOneStudent = par => request('/campback/member/deleteOneStudent', par, "post", 'json');