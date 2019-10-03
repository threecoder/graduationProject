import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/login.vue'

Vue.use(Router)

export default new Router({
        
    routes: [
        {
            path: '/',
            name: 'home',
            component: resolve => require(["../views/home/home.vue"], resolve),
            children: [
                {
                  path:'/index',
                  name:'index',
                  component: resolve => require(['../views/index/index.vue'],resolve)
                },
                {
                    path: '/courses/:type',
                    name: 'courses',
                    component: resolve => require(["../views/courses/courses.vue"], resolve)
                },
                {
                    path: '/contact',
                    name: 'contact',
                    component: resolve => require(['../views/contact.vue'], resolve)
                },
                {
                    path:'/introduction',
                    name:'introduction',
                    component: resolve => require(["../views/introduction/introduction.vue"],resolve),
                    children:[
                      {
                        path:'/brief',
                        name:'brief',
                        component: resolve =>require(["../views/introduction/components/brief.vue"],resolve)
                      },
                      {
                        path:'/constitution',
                        name:'constitution',
                        component: resolve =>require(["../views/introduction/components/constitution.vue"],resolve)
                      },
                      {
                        path:'/framework',
                        name:'framework',
                        component: resolve =>require(["../views/introduction/components/framework.vue"],resolve)
                      }
                    ]
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
            component: resolve => require(["../views/personal/personal.vue"], resolve),
            children: [
                {
                    path: '/info',
                    name: 'info',
                    component: resolve => require(['../views/personal/info/info.vue'], resolve)
                },
                {
                    path: '/examTodo',
                    name: 'examTodo',
                    component: resolve => require(['../views/personal/exam/examTodo.vue'], resolve)
                },
                {
                    path: '/examDone',
                    name: 'examDone',
                    component: resolve => require(['../views/personal/exam/examDone.vue'], resolve)
                },
                {
                    path: '/activities/:id',
                    name: 'activities',
                    component: resolve => require(['../views/personal/activities/joinable.vue'], resolve)
                },
                {
                    path: '/management',
                    name: 'management',
                    component: resolve => require(['../views/personal/relationship/management.vue'], resolve)
                }
            ]
        },
        {
            path: "/examIndex/:id",
            component: resolve => require(['@/views/personal/exam/examIndex.vue'], resolve)
        },
        {
            path: "/exam/:id",
            component: resolve => require(['@/views/personal/exam/exam.vue'], resolve)
        },
        {
            path: '/examDetail/:id',
            component: resolve => require(['@/views/personal/exam/examDetail.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['@/views/404.vue'], resolve),
            hidden: true
        },
        {
            path: "*",
            redirect: { path: '/404' }
        }
    ]
})
