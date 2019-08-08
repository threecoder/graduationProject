import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import Axios from 'axios'
import store from './store'
import element from 'element-ui'

Vue.config.productionTip = false
Vue.prototype.$message = element.Message;
Vue.prototype.$msbox = element.MessageBox;
Vue.prototype.confirm = element.MessageBox.confirm;

Vue.use(element);

//配置路由拦截
// router.beforeEach( (to,from,nex) => {

// })

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
