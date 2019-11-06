import { request } from "../request"
const commomUrl = "/campback/admin"

//发布新的活动
export const addNewTraining = par => request(`${commomUrl}/addNewTraining`, par, 'json');