import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import Axios from 'axios'
import store from './store'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { getCanGoPath} from '@/assets/js/util.js';

Vue.config.productionTip = false
Vue.prototype.$message = element.Message;
Vue.prototype.$msbox = element.MessageBox;
Vue.prototype.confirm = element.MessageBox.confirm;

Vue.use(element);

//配置路由拦截
// router.beforeEach( (to,from,next) => {
//   let token = window.localStorage.getItem("token");
//   let canGoPath = getCanGoPath();
//   if(!token && canGoPath.indexOf(to.path)==-1){
//     let url = escape(to.fullPath);
//     next({
//       path:`/login?redirect=${url}`
//     })
//   }else{
//     next();
//   }
// })

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
