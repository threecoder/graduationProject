import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({

    routes: [
        {
            path: '/',
            redirect: { path: '/admin' }
        },
        {
            path: '/adminLogin',
            name: 'adminLogin',
            component: resolve => require([ "../views/admin/login.vue" ], resolve),
        },
        {
            path: '/admin',
            name: 'admin',
            component: resolve => require([ '../views/admin/mainView.vue' ], resolve),
            children: [
                //会员管理
                {
                    path: '/managerMember',
                    name: 'managerMember',
                    component: resolve => require([ '../views/admin/member/myMember.vue' ], resolve)
                },
                //学员管理
                {
                    path: '/studentInfo',
                    name: 'studentInfo',
                    component: resolve => require([ '../views/admin/student/infoManagement.vue' ], resolve)
                },
                //培训
                {
                    path: '/publicTraining',
                    name: 'publicTraining',
                    component: resolve => require([ '../views/admin/training/trainingPublish.vue' ], resolve)
                },
                //考试管理
                {
                    path: '/question',
                    name: 'question',
                    component: resolve => require([ '../views/admin/exam/question.vue' ], resolve)
                },
                {
                    path: '/toPublic',
                    name: 'toPublic',
                    component: resolve => require([ '../views/admin/exam/toPublic.vue' ], resolve)
                },
                {
                    path: '/published',
                    name: 'published',
                    component: resolve => require([ '../views/admin/exam/published.vue' ], resolve)
                },
                {
                    path: '/checkGrade',
                    name: 'checkGrade',
                    component: resolve => require([ '../views/admin/exam/checkGrade.vue' ], resolve)
                },
                {
                    path: '/pickQuestion/:id',
                    name: 'pickQuestion',
                    component: resolve => require([ '../views/admin/exam/pickQuestion.vue' ], resolve)
                },
                //活动管理
                {
                    path: '/publicActivity',
                    name: 'publicActivity',
                    component: resolve => require([ '../views/admin/activity/activityPublish.vue' ], resolve)
                },
                {
                    path: '/activitiesList',
                    name: 'activitiesList',
                    component: resolve => require([ '../views/admin/activity/activitiesList.vue' ], resolve)
                }
            ]
        },
        {
            path: '/studentExamDetail/:name/:examId/:idNum',
            name: 'studentExamDetail',
            component: resolve => require([ '../views/admin/exam/examDetail.vue' ], resolve)
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