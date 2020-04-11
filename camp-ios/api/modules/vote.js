import { request } from '../request.js'
import { studentPrefix, memberPrefix } from '../../const';

//会员获取可投票列表
const getMemberCanVoteList = par => request(`${memberPrefix}/getMemberCanVoteList`, 'get', par);

//学员获取可投票列表
const getStudentCanVoteList = par => request(`${studentPrefix}/getStudentCanVoteList`, 'get', par);

//会员获取投票详情
const memberGetVoteDetail = voteId => request(`${memberPrefix}/getVoteDetail`, 'get', { voteId });

//学员获取投票详情
const studentGetVoteDetail = voteId => request(`${studentPrefix}/getVoteDetail`, 'get', { voteId });

//会员投票
const memberVote = data => request(`${memberPrefix}/memberVote`, 'post', data);

//学员投票
const studentVote = data => request(`${studentPrefix}/studentVote`, 'post', data);

//学员获取已投票列表
const getStudentHasVotedList = par => request(`${studentPrefix}/getStudentHasVotedList`, 'get', par);

//会员获取已投票列表
const getMemberHasVotedList = par => request(`${memberPrefix}/getMemberHasVotedList`, 'get', par);

//学员获取投票统计数据
const studentGetVoteStatic = voteId => request(`${studentPrefix}/studentGetVoteStatic`, 'get', { voteId });

//会员获取投票统计数据
const memberGetVoteStatic = voteId => request(`${memberPrefix}/memberGetVoteStatic`, 'get', { voteId });

export default {
    getMemberCanVoteList,
    getStudentCanVoteList,
    memberGetVoteDetail,
    studentGetVoteDetail,
    memberVote,
    studentVote,
    getStudentHasVotedList,
    getMemberHasVotedList,
    studentGetVoteStatic,
    memberGetVoteStatic
}