import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import Axios from 'axios'
import store from './store'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import { canGoPath } from './const';

Vue.config.productionTip = false
Vue.prototype.$message = element.Message;
Vue.prototype.$msbox = element.MessageBox;
Vue.prototype.confirm = element.MessageBox.confirm;

Vue.use(element);

import "./assets/css/quill.core.css";
import "./assets/css/quill.snow.css";

// 配置路由拦截
router.beforeEach((to, from, next) => {
    let token = document.cookie.indexOf("token");
    if (!store.getters.isLogin) {
        console.log(store.getters.isLogin)
        store.commit("initState");
    }
    if ((token == -1 || !store.getters.isLogin) && canGoPath.indexOf(to.path) == -1) {
        let url = escape(to.fullPath);
        next({
            path: `/login?redirect=${url}`
        })
    } else {
        next();
    }
});

//http response 拦截器
Axios.interceptors.response.use(
    response => {
        if (response.headers[ "content-type" ] == 'application/json;charset=UTF-8') {
            if (response.data.code == 'success') {
                return response.data;
            } else if (response.data.code == 'error') {
                router.push({ path: '/login' });
                throw new Error("请先登录您的账号");
            } else if (response.data.code == 'fail') {
                throw new Error(response.data.msg);
            } else {
                throw new Error("未知错误");
            }
        } else {
            return response;
        }
    },
    error => {
        if (error.code == 'ECONNABORTED' && error.message.indexOf('timeout') != -1) {
            throw new Error("网络异常，请求超时");
        }
        if (!error.response || error.response.status == 500) {
            throw new Error("服务器异常");
        }
        throw new Error(error);
    }
)
const mainProcess = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
