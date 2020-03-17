import { request } from '../request.js'
import { studentPrefix, memberPrefix } from '../../const';

const getMemberCanVoteList = () => request(`${memberPrefix}/getMemberCanVoteList`, 'get');
const getStudentCanVoteList = () => request(`${memberPrefix}/getStudentCanVoteList`, 'get');

export default {
    getMemberCanVoteList,
    getStudentCanVoteList
}