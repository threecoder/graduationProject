import Vue from 'vue'
import App from './App'
import router from './router/index';

Vue.config.productionTip = false
console.log(App);
App.mpType = 'app'

const app = new Vue({
	...App,
	router
})
app.$mount()
