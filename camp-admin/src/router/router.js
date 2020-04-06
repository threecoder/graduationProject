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
                {
                    path: '/memberStatistics',
                    name: 'memberStatistics',
                    component: resolve => require([ '../views/admin/member/statistics.vue' ], resolve)
                },
                //学员管理
                {
                    path: '/studentInfo',
                    name: 'studentInfo',
                    component: resolve => require([ '../views/admin/student/infoManagement.vue' ], resolve)
                },
                {
                    path: '/studentRecord',
                    name: 'studentRecord',
                    component: resolve => require([ '../views/admin/student/recordManagement.vue' ], resolve)
                },
                //培训
                {
                    path: '/publishTraining',
                    name: 'publishTraining',
                    component: resolve => require([ '../views/admin/training/trainingPublish.vue' ], resolve)
                },
                {
                    path: '/publishedTraining',
                    name: 'publishedTraining',
                    component: resolve => require([ '../views/admin/training/trainingPublished.vue' ], resolve)
                },
                {
                    path: '/trainingEnrollSituation',
                    name: 'trainingEnrollSituation',
                    component: resolve => require([ '../views/admin/training/enrollSituation.vue' ], resolve)
                },
                {
                    path: '/trainingOrder',
                    name: 'trainingOrder',
                    component: resolve => require([ '../views/admin/training/trainingOrder.vue' ], resolve)
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
                //证书管理
                {
                    path: '/examineCertificate',
                    name: 'examineCertificate',
                    component: resolve => require([ '../views/admin/certificate/examine.vue' ], resolve)
                },
                {
                    path: '/setCertificate',
                    name: 'setCertificate',
                    component: resolve => require([ '../views/admin/certificate/setCertificate.vue' ], resolve)
                },
                {
                    path: '/grantCertificate',
                    name: 'grantCertificate',
                    component: resolve => require([ '../views/admin/certificate/grantCertificate.vue' ], resolve)
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
                },
                //投票管理
                {
                    path: '/publishVote',
                    name: 'publishVote',
                    component: resolve => require([ '../views/admin/vote/publishVote.vue' ], resolve)
                },
                {
                    path: '/publishedVote',
                    name: 'publishedVote',
                    component: resolve => require([ '../views/admin/vote/publishedVote.vue' ], resolve)
                },
                //消息页面
                {
                    path: '/message',
                    name: 'message',
                    component: resolve => require([ '../views/admin/message/index.vue' ], resolve)
                },
                //优惠券管理
                {
                    path: '/coupon',
                    name: 'coupon',
                    component: resolve => require([ '../views/admin/coupon/coupon.vue' ], resolve)
                },
                //订单管理
                {
                    path: '/order',
                    name: 'order',
                    component: resolve => require([ '../views/admin/order/order.vue' ], resolve)
                },
                //系统管理
                {
                    path: '/adminUser',
                    name: 'adminUer',
                    component: resolve => require([ '../views/admin/system/adminUser.vue' ], resolve)
                },
                {
                    path: '/paramsSetting',
                    name: 'paramsSetting',
                    component: resolve => require([ '../views/admin/system/paramsSetting.vue' ], resolve)
                },
                //前台管理
                {
                    path: '/dynamic',
                    name: 'dynamic',
                    component: resolve => require([ '../views/admin/index/dynamic.vue' ], resolve)
                },
                {
                    path: '/dynamicEdit',
                    name: 'dynamicEdit',
                    component: resolve => require([ '../views/admin/index/dynamicEdit.vue' ], resolve)
                }, 
                {
                    path: '/introduction',
                    name: 'introduction',
                    component: resolve => require([ '../views/admin/index/introduction.vue' ], resolve)
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
