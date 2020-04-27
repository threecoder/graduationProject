<template>
    <div v-loading="loading">
        <div class="examInfo fixed">
            <span class="title">{{examInfo.examName}}</span>
            <span>姓名:{{sName}}</span>
            <span>考试时间：{{examInfo.date}} {{examInfo.startTime}}-{{examInfo.endTime}}</span>
            <span>考试时长：{{examInfo.min}}分钟</span>
            <span>总得分：{{examInfo.grade}}</span>
            <span class="cursor" style="color:black" @click="goBack">返回</span>
        </div>
        <div class="container">
            <template v-for="(item,i) in list">
                <single-one :key="i" :item="item" />
            </template>
        </div>
    </div>
</template>
<script>
import singleOne from "./components/singleChoiceDetail.vue";
import adminExamApi from "../../../api/admin/exam";
export default {
    data() {
        return {
            examId: this.$route.params.examId,
            sName: this.$route.params.name,
            idNum: this.$route.params.idNum,
            examInfo: {
                examName: "考试名",
                date: "2016-10-10",
                startTime: "14:00:00",
                endTime: "16:00:00",
                min: 120,
                grade: 60
            },
            list: [
                {
                    index: 1,
                    title: "食醋是什么味道1",
                    score: 8,
                    arr: ["发发发", "备选项", "备选", "备选1"],
                    type: 2, //1是单选和判断，2是多选
                    rightAnswer: [1, 2],
                    studentAnswer: [2]
                },
                {
                    index: 2,
                    title: "食醋是什么味道1",
                    score: 8,
                    arr: ["发发发", "备选项", "备选", "备选1"],
                    type: 1, //1是单选和判断，2是多选
                    rightAnswer: [1],
                    studentAnswer: [1]
                },
                {
                    index: 2,
                    title: "食醋是什么味道1",
                    score: 8,
                    arr: ["发发发", "备选项", "备选", "备选1"],
                    type: 1, //1是单选和判断，2是多选
                    rightAnswer: [1],
                    studentAnswer: [1]
                }
            ],
            fixed: false,
            loading: false
        };
    },
    components: {
        singleOne
    },
    mounted() {
        this.init();
    },
    methods: {
        async init() {
            this.loading = true;
            try {
                let par = {
                    examId: this.examId,
                    idNum: this.idNum
                };
                let res = await adminExamApi.getExamDetail(par);
                this.list = res.data.list;
                this.examInfo = res.data.examInfo;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        goBack() {
            this.$router.go(-1);
        }
    }
};
</script>

<style lang="scss" scoped>
.examInfo {
    width: 100%;
    background-color: rgb(64, 158, 255);
    margin: 0;
    padding: 0;
    height: 60px;
    line-height: 60px;
    text-align: center;
    span {
        margin: 20px;
        color: #fff;
    }
}
.container {
    margin-top: 60px;
}
.fixed {
    position: fixed;
    top: 0;
    z-index: 99;
}
</style>