import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

let store = new Vuex.Store({
	state: {
		user: {
			userName: null,
			cookie: "",
			expire: 0,
			userAccount: {
				username: "",
				password: ""
			}
		},
		messageTime: 0,
		msgNum: 0
	},
	getters: {
		userName: state => state.user.userName,
		account: state => state.user.userAccount,
		cookie: state => state.user.cookie,
		expire: state => state.user.expire,
		msgTime: state => state.messageTime,
		msgNum: state => state.msgNum,
		isLogin: state => {
			if (state.user.cookie && state.user.expire > new Date().getTime()) {
				return true
			}
			return false;
		}
	},
	mutations: {
		init: (state) => {
			let stateObj = uni.getStorageSync("stateObj");
			if (stateObj) {
				stateObj = JSON.parse(stateObj);
				state.user = stateObj.user;
				state.messageTime = stateObj.messageTime;
				state.msgNum = stateObj.msgNum;
			} else {
				console.log("本地无缓存");
				state.user = {
					userName: null,
					expire: 0,
					cookie: "",
					userAccount: {
						username: "",
						password: ""
					}
				}
				state.messageTime = 0;
				state.msgNum = 0;
			}
		},
		setUser: (state, user) => {
			user.cookie = state.user.cookie;
			state.user = user;
			uni.setStorageSync("stateObj", JSON.stringify(state));
		},
		setCookie: (state, info) => {
			state.user.cookie = info.cookie;
			uni.setStorageSync("stateObj", JSON.stringify(state));
		},
		removeUser: (state) => {
			state.user = {
				userName: null,
				cookie: "",
				userAccount: {
					username: "",
					password: ""
				}
			}
			state.msgNum = 0;
			state.messageTime = 0;
			uni.removeStorageSync("stateObj");
		},
		setMsgTime: (state, obj) => {
			state.messageTime = obj.time;
			uni.setStorageSync("stateObj", JSON.stringify(state));
		},
		setMsgNum: (state, obj) => {
			state.msgNum = obj.msgNum;
			uni.setStorageSync("stateObj", JSON.stringify(state));
		}
	}
});

export default store;
