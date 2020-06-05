export const indexPrefix = "/index";
export const studentPrefix = "/student";
export const baseUrl = "http://112.126.67.240:3000";
// export const baseUrl = "http://192.168.188.44:3000";
// export const baseUrl = "http://192.168.1.173:3000";
// export const baseUrl = "http://192.168.43.124:3000";

export const canGoModule = [
	"dynamic",
	"notice",
	"campTraining",
	"dynamicDetail",
	"login",
	"personal"
]

//站内信的消息类型
export const msgTypeList = [{
		name: "activity",
		label: "活动发布",
		key: "newActivity"
	},
	{
		name: "activity",
		label: "活动报名",
		key: "signedActivity"
	},
	{
		name: "training",
		label: "培训发布",
		key: "newTraining"
	},
	{
		name: "training",
		label: "培训报名",
		key: "signedTraining"
	},
	{
		name: "exam",
		label: "成绩审核",
		key: "gradeExamine"
	},
	{
		name: "certificate",
		label: "证书复审",
		key: "cerRecheck"
	},
	{
		name: "certificate",
		label: "证书校正",
		key: "cerChange"
	}
]
