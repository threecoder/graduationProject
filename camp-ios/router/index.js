import modules from "./modules/index";
import Vue from "vue";
import Router from "uni-simple-router";

import {
	canGoModule
} from '../const.js';
import app from "../main.js";
import accountApi from "../api/modules/account.js";

Vue.use(Router);

const router = new Router({
	holdTabbar: true, //是否开启底部菜单拦截
	rewriteFun: true, //是否对uni-app 下的chooseLocation/openLocation 两个方法重写 目的是隐藏和显示拦截tabbar
	loddingPageStyle: () => JSON.parse('{"backgroundColor":"#FFF"}'), //当前等待页面的样式 必须返回一个json
	loddingPageHook: () => {
		plus.navigator.closeSplashscreen()
	}, //刚刚打开页面处于等待状态,会触发此事件
	holdTabbarStyle: () => JSON.parse('{}'),
	animation: {
		animationType: 'pop-in',
		animationDuration: 300
	}, //页面切换动画
	switchPageOutTime: 1000, //最高能忍耐的页面切换时间 达到此时间 不管切换有没有完成都会显示页面出来 这对启动页帮助很大
	routes: [...modules]
});

router.beforeEach(async (to, from, next) => {
	console.log("拦截")
	app.$store.commit("init");
	console.log(to.name, canGoModule.indexOf(to.name));
	if (canGoModule.indexOf(to.name) !== -1) {
		next();
	} else {
		//验证cookie是否到期，到期重新登录，否则直接跳转
		let expire = app.$store.getters.expire;
		let time = new Date().getTime();
		if (time > expire) {
			console.log("cookie过期")
			let account = app.$store.getters.account;
			if (account.userName) {
				console.log("有账号")
				let res = await accountApi.login(account);
				let user = {
					userName: res.data.name,
					expire: res.data.expire,
					userAccount: account
				}
				app.$store.commit("setUser", user);
				next();
			} else {
				console.log("没账号")
				app.$Router.push({
					name: "login",
					query: {
						name: to.name
					}
				});
			}
		} else {
			next();
		}
	}
});

export default router;
