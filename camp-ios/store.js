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
		}
	},
	getters: {
		userName: state => state.user.userName,
		account: state => state.user.userAccount,
		cookie: state => state.user.cookie,
		expire: state => state.user.expire
	},
	mutations: {
		init: (state) => {
			let user = uni.getStorageSync("user");
			if (user) {
				state.user = JSON.parse(user);
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
			}
		},
		setUser: (state, user) => {
			user.cookie = state.user.cookie;
			state.user = user;
			uni.setStorageSync("user", JSON.stringify(user));
		},
		setCookie: (state, info) => {
			state.user.cookie = info.cookie;
		},
		removeUser: (state) => {
			this.user = {
				userName: null,
				cookie: "",
				userAccount: {
					username: "",
					password: ""
				}
			}
			uni.removeStorageSync("user");
		}
	}
});

export default store;
