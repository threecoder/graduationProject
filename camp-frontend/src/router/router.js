import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/login.vue'

Vue.use(Router)

export default new Router({

    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/',
            name: 'home',
            component: resolve => require([ "../views/home/home.vue" ], resolve),
            children: [
                {
                    path: '/index',
                    name: 'index',
                    component: resolve => require([ '../views/home/index/index.vue' ], resolve)
                },
                {
                    path: '/introduction',
                    name: 'introduction',
                    component: resolve => require([ '../views/home/introduction/introduction.vue' ], resolve)
                },
                {
                    path: '/courses',
                    name: 'courses',
                    component: resolve => require([ "../views/home/courses/courses.vue" ], resolve)
                },
                {
                    path: '/contact',
                    name: 'contact',
                    component: resolve => require([ '../views/home/contact.vue' ], resolve)
                },
                {
                    path: '/notice',
                    name: 'notice',
                    component: resolve => require([ '../views/home/notice/notice.vue' ], resolve)
                },
                {
                    path: '/news',
                    name: 'news',
                    component: resolve => require([ '../views/home/dynamic/dynamic.vue' ], resolve)
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
            component: resolve => require([ "../views/personal/personal.vue" ], resolve),
            children: [
                //我的资料
                {
                    path: '/info',
                    name: 'info',
                    component: resolve => require([ '../views/personal/info/info.vue' ], resolve)
                },
                //证书相关
                {
                    path: '/certificate',
                    name: 'certificate',
                    component: resolve => require([ '../views/personal/certificate/index.vue' ], resolve)
                },
                {
                    path: '/overdueCertificate',
                    name: 'overdueCertificate',
                    component: resolve => require([ '../views/personal/certificate/index.vue' ], resolve)
                },
                {
                    path: '/cerOperHistory',
                    name: 'cerOperHistory',
                    component: resolve => require([ '../views/personal/certificate/cerOperHistory.vue' ], resolve)
                },
                //考试相关
                {
                    path: '/examTodo',
                    name: 'examTodo',
                    component: resolve => require([ '../views/personal/exam/examTodo.vue' ], resolve)
                },
                {
                    path: '/examDone',
                    name: 'examDone',
                    component: resolve => require([ '../views/personal/exam/examDone.vue' ], resolve)
                },
                //活动页面
                {
                    path: '/enrolableActivities',
                    name: 'enrolableActivities',
                    component: resolve => require([ '../views/personal/activities/enrolableActivities.vue' ], resolve)
                },
                {
                    path: '/enrolledActivities',
                    name: 'enrolledActivities',
                    component: resolve => require([ '../views/personal/activities/enrolledActivities.vue' ], resolve)
                },
                //学员管理
                {
                    path: '/management',
                    name: 'management',
                    component: resolve => require([ '../views/personal/relationship/management.vue' ], resolve)
                },
                //培训页面
                {
                    path: '/enrolableTraining',
                    name: 'enrolableTraining',
                    component: resolve => require([ '../views/personal/training/enrolableTraining.vue' ], resolve)
                },
                {
                    path: '/enrolledTraining',
                    name: 'enrolledTraining',
                    component: resolve => require([ '../views/personal/training/enrolledTraining.vue' ], resolve)
                },
                //投票页面
                {
                    path: '/canVote',
                    name: 'canVote',
                    component: resolve => require([ '../views/personal/vote/canVote.vue' ], resolve)
                },
                {
                    path: '/hasVoted',
                    name: 'hasVoted',
                    component: resolve => require([ '../views/personal/vote/hasVoted.vue' ], resolve)
                },
                //站内信页面
                {
                    path: '/message',
                    name: 'message',
                    component: resolve => require([ '../views/personal/message/message.vue' ], resolve)
                },
                //订单页面
                {
                    path: '/order',
                    name: 'order',
                    component: resolve => require([ '../views/personal/order/order.vue' ], resolve)
                }
            ]
        },
        //考试页面
        {
            path: "/examIndex/:id",
            component: resolve => require([ '@/views/personal/exam/examIndex.vue' ], resolve)
        },
        {
            path: "/exam/:id",
            component: resolve => require([ '@/views/personal/exam/exam.vue' ], resolve)
        },
        {
            path: '/examDetail/:id',
            component: resolve => require([ '@/views/personal/exam/examDetail.vue' ], resolve)
        },

        {
            path: '/404',
            component: resolve => require([ '@/views/404.vue' ], resolve),
            hidden: true
        },
        {
            path: "*",
            redirect: { path: '/404' }
        }
    ]
})
