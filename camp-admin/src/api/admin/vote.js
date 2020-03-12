import { request } from '../request';
import { apiPrefix } from '../../const';

//管理员添加新的投票
const addNewVote = data => request(`${apiPrefix}/addNewVote`, 'post', data);

//获取投票列表
const getVoteList = par => request(`${apiPrefix}/getVoteList`, 'get', par);

//删除投票
const deleteVote = voteId => request(`${apiPrefix}/deleteVote`, 'post', { voteId });

//获取投票详情
const getVoteDetail = voteId => request(`${apiPrefix}/getVoteDetail`, 'get', { voteId });

//统计投票结果
const getVoteStatistic = voteId => request(`${apiPrefix}/getVoteStatistic`, 'get', { voteId })

export default {
    addNewVote,
    getVoteList,
    deleteVote,
    getVoteDetail,
    getVoteStatistic
}