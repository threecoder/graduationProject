import Vue from 'vue'
import App from './App'
import router from './router/index';
import store from "./store.js";

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue({
	...App,
	router,
	store
})
app.$mount()

export default app;