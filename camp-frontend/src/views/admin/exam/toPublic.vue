<template>
    <div>
        <h3>发布考试</h3>
        <div class="divider"></div>
        <div class="switcher">
            <el-button type="primary" @click="examForm.newFlag=true;examForm.formType=true">新建考试</el-button>
        </div>
        <div>
            <span class="panel-title">已有考试</span>
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
                        :status="item.status"
                        :idtype="1"
                        @pick="pickQuestion(item.examId)"
                        @modify="modify(item.examId)"
                        @refresh="fresh"
                    />
                </div>
                <page
                    @curChange="curChange"
                    :currentPage="currentPage"
                    :total="total"
                    :pageSize="6"
                />
            </div>
        </div>

        <el-dialog v-if="examForm.newFlag" :visible.sync="examForm.newFlag">
            <exam-publish :id="examForm.id" :type.sync="examForm.formType" @close="examForm.newFlag=false;fresh()" />
        </el-dialog>
    </div>
</template>
<script>
import singleExam from "@/components/singleExam.vue";
import page from "@/components/page.vue";
import examPublish from "./components/examPublish";
import adminExamApi from "@/api/admin/exam.js";
export default {
    components: {
        singleExam,
        page,
        examPublish
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
                    status: 0,
                    grade: null
                },
                {
                    examId: "1",
                    examName: "考试名称",
                    date: "2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    status: 0,
                    grade: null
                },
                {
                    examId: "1",
                    examName: "考试名称",
                    startTime: "2016-10-10 14:00:00",
                    endTime: "2016-10-10 16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    status: 1,
                    grade: null
                },
                {
                    examId: "1",
                    examName: "考试名称",
                    date: "2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    status: 0,
                    grade: null
                },
                {
                    examId: "1",
                    examName: "考试名称",
                    date: "2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    status: 0,
                    grade: null
                }
            ],
            currentPage: 1,
            total:10,
            examForm: {
                id: null,
                newFlag: false,
                formType: true
            },
            pickFlag: false,
            loading: false
        };
    },

    mounted() {
        // this.fresh();
    },
    methods: {
        fresh() {
            this.getNotPostExamList();
        },
        async getNotPostExamList() {
            try {
                this.loading = true;
                let res = await adminExamApi.getNotPostExam(this.currentPage);
                console.log(res);
                this.examList = res.data.list;
                this.total = res.data.total;
                this.loading = false;
            } catch (error) {
                this.loading = true;
            }
        },
        curChange(val){
            this.currentPage = val;
            this.getNotPostExamList();
        },
        pickQuestion(id){
            console.log(id);
            this.$router.push({path:`/pickQuestion/${id}`})
        },
        modify(id){
            this.examForm.id = id;
            this.examForm.newFlag = true;
            this.examForm.formType = false;
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