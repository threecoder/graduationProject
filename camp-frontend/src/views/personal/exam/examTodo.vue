<template>
    <div>
        <h2>需完成的考试</h2>
        <div class="switcher">
            <el-button :class="{'active':notFinishedFlag}" @click="notFinishedFlag=true">未作答</el-button>
            <el-button :class="{'active':!notFinishedFlag}" @click="notFinishedFlag=false">已作答</el-button>
        </div>
        <div>
            <span v-if="notFinishedFlag" class="panel-title">还未作答的试卷</span>
            <span v-else class="panel-title">已经作答过的试卷</span>
            <div class="all-exam-container" :loading="loading">
                <div class="single-exam-container" v-for="(item,i) in examList" :key="i">
                    <single-exam
                        :examName="item.examName"
                        :date="item.date"
                        :startTime="item.startTime"
                        :endTime="item.endTime"
                        :belong="item.belong"
                        :min="item.min"
                        :grade="item.grade"
                        :examId="item.examId"
                    />
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import examApi from "../../../api/modules/exam";
import singleExam from "./components/singleExam.vue";
export default {
    components: {
        singleExam
    },
    data() {
        return {
            examList: [
                {
                    examId: "1",
                    examName: "考试名称",
                    date: "2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    grade: null
                }
            ],
            notFinishedFlag: true,
            loading: false
        };
    },
    watch: {
        notFinishedFlag() {
            this.examList = [];
            this.getExamList();
        }
    },
    mounted() {
        this.getExamList();
    },
    methods: {
        async getExamList() {
            this.loading = true;
            try {
                let res = null;
                if (this.notFinishedFlag) {
                    res = await examApi.getTodoExam();
                } else {
                    res = await examApi.getHalfExam();
                }
                if (res) {
                    this.examList = res.data;
                }
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.all-exam-container {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: flex-start;
    flex-wrap: wrap;
    .single-exam-container {
        margin: 10px;
        width: 30%;
    }
}
.switcher {
    overflow: hidden;
    float: right;
    .active {
        background-color: rgb(58, 180, 255);
        color: #fff;
    }
}
</style>