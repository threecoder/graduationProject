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
            <div v-if="examList.length>0" class="all-exam-container" :loading="loading">
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
            <div v-else>
                <h4 class="tac">暂无内容</h4>
            </div>
        </div>
        <div class="page-container">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import examApi from "../../../api/modules/exam";
import singleExam from "./components/singleExam.vue";
import page from "../../../components/page.vue";
export default {
    components: {
        singleExam,
        page
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
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
            this.form.currentPage = 1;
            this.form.total = 0;
            this.getExamList();
        }
    },
    mounted() {
        this.getExamList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getExamList();
        },
        async getExamList() {
            this.loading = true;
            try {
                let res = null;
                if (this.notFinishedFlag) {
                    res = await examApi.getTodoExam(this.form);
                } else {
                    res = await examApi.getHalfExam(this.form);
                }
                this.examList = res.data.list;
                this.form.total = res.data.total;
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