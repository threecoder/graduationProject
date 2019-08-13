import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: resolve => require(["../views/home/home.vue"],resolve),
      children: [
        {
          path: '/hello',
          name: 'helloWorld',
          component: resolve => require(["../components/HelloWorld.vue"],resolve)
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/personal',
      name: 'personal',
      component: resolve => require(["../views/personal/personal.vue"],resolve),
      children:[
        
      ]
    },
    {
      path: '/404',
      component: resolve => require(['@/views/404.vue'],resolve),
      hidden:true
    },
    {
      path: "*",
      redirect:{ path: '/404'}
    }
  ]
})
