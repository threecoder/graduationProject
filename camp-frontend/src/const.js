//配置接口前缀
export const studentPrefix = '/campback/student';
export const memberPrefix = '/campback/member';
export const indexPrefix = '/campback/index';

//配置无需路由拦截的页面
export const canGoPath = [
    '/login',
    '/404',
    '/introduction',
    '/courses/previous',
    '/courses/now',
    '/courses/future',
    '/contact',
    '/index',
    '/brief',
    '/constitution',
    '/framework',
    '/',
]

//图片占位符
export const imgStr = "@图#片$占#位@";

//站内信的消息类型
export const msgTypeList = [
    { path: "/enrolableActivities", label: "活动发布", key: "newActivity" },
    { path: "/enrolledActivities", label: "活动报名", key: "signedActivity" },
    { path: "/enrolableTraining", label: "培训发布", key: "newTraining" },
    { path: "/enrolledTraining", label: "培训报名", key: "signedTraining" },
    { path: "/examTodo", label: "考试发布", key: "examPublish" },
    { path: "/examDone", label: "成绩审核", key: "gradeExamine" },
    { path: "/cerOperHistory", label: "证书复审", key: "cerRecheck" },
    { path: "/cerOperHistory", label: "证书校正", key: "cerChange" },
    { path: "/order", label: '会员续费', key: 'memberSubscription' }
]

//订单类别
export const orderTypeList = [
    { label: "活动", key: "activity" },
    { label: "培训", key: "training" },
    { label: "证书变更", key: "cerChange" },
    { label: "证书复审", key: "cerRecheck" },
    { label: "会员续费", key: "member" }
]