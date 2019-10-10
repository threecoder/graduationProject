import { request } from '../request';
let commentUrl = "/campback/damin";
//获取试题
export const getQuestionList = par => request(commentUrl+"/getQuestionList", par, 'post', 'json');

//获取训练列表
export const getTrainingList = () => request(commentUrl+"/getTraingingList", par, 'get', 'json');

//修改试题信息
export const modefyQuestionInfo = par => request(commentUrl+"/modifyQuestion", par, 'post', 'json');