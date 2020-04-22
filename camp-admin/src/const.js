//api前缀
export const apiPrefix = '/campback/admin';

//权限列表
export const authorityList = [
    { key: "member", label: "会员管理权限" },
    { key: "student", label: "学员管理权限" },
    { key: "training", label: "培训管理权限" },
    { key: "exam", label: "考试管理权限" },
    { key: "grade", label: "成绩审核权限" },
    { key: "certificate", label: "证书管理权限" },
    { key: "activity", label: "活动管理权限" },
    { key: "vote", label: "投票管理权限" },
    { key: "index", label: "前台网站管理权限" },
    { key: "system", label: "系统管理权限" },
    { key: "order", label: "订单管理权限" },
    { key: "coupon", label: "优惠券管理权限" }
]

//系统参数列表
export const systemParams = [
    { key: "studentTrainig", label: "允许学员报名培训", type: "bool" },
    { key: "trainingOrderTime", label: "培训订单超时时间(分钟)", type: "number" },
    { key: "activityOrderTime", label: "活动订单超时时间(分钟)", type: "number" },
    { key: "certificateOrderTime", label: "证书订单超时时间(分钟)", type: "number" },
    { key: "memberOrderTime", label: "会费订单超时时间(分钟)", type: "number" }
]

//图片占位符
export const imgStr = "@图#片$占#位@";

//站内信的消息类型
export const msgTypeList = [
    { label: "活动发布", key: "newActivity" },
    { label: "活动报名", key: "signedActivity" },
    { label: "培训发布", key: "newTraining" },
    { label: "培训报名", key: "signedTraining" },
    { label: "成绩审核", key: "gradeExamine" },
    { label: "证书审核", key: "certificateExamine" },
    { label: "发放证书", key: "grantCertificate" }
]