import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import Axios from 'axios'
import store from './store'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { getCanGoPath } from '@/assets/js/util.js';

Vue.config.productionTip = false
Vue.prototype.$message = element.Message;
Vue.prototype.$msbox = element.MessageBox;
Vue.prototype.confirm = element.MessageBox.confirm;

Vue.use(element);
Vue.config.productionTip = false
//配置路由拦截
// router.beforeEach((to, from, next) => {
//     let token = window.localStorage.getItem("token");
//     let canGoPath = getCanGoPath();
//     if (!token && canGoPath.indexOf(to.path) == -1) {
//         let url = escape(to.fullPath);
//         next({
//             path: `/login?redirect=${url}`
//         })
//     } else {
//         next();
//     }
// })

//http response 拦截器
Axios.interceptors.response.use(
    response => {
        if(response.headers['content-type'] == 'application/json;charset=UTF-') {
            if(response.code == 'success') {
                return response;
            }else if(response.code == 'error'){
                router.push({path:'/login'});
                return Promise.reject(response);
            }else{
                element.Message.error(response.msg);
                return Promise.reject(response);
            }
        }else{
            return response;
        }
    },
    error => {
        if(error.code == 'ECONNABORTED' && error.message.indexOf('timeout')!= -1){
            element.Message.error("网络异常，请求超时");
            return Promise.reject();
        }
        if(!error.response){
            element.Message.error("服务器异常");
        }
        return Promise.reject(error);
    }
)
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
