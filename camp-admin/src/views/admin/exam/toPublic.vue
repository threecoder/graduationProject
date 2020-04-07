<template>
    <div>
        <el-row>
            <el-col :span="22">
                <h3>发布考试</h3>
            </el-col>
            <el-col :span="2">
                <div class="switcher">
                    <el-button
                        type="primary"
                        @click="examForm.newFlag=true;examForm.formType=true"
                    >新建考试</el-button>
                </div>
            </el-col>
        </el-row>
        <div class="divider"></div>

        <div class="panel-contaner">
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
                        @refresh="getNotPostExamList"
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
            <exam-publish
                :id="examForm.id"
                :type.sync="examForm.formType"
                @close="examForm.newFlag=false;getNotPostExamList()"
            />
        </el-dialog>
    </div>
</template>
<script>
import singleExam from "../../../components/singleExam.vue";
import page from "../../../components/page.vue";
import examPublish from "./components/examPublish.vue";
import adminExamApi from "../../../api/admin/exam.js";
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
            total: 10,
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
        this.getNotPostExamList();
    },
    methods: {
        async getNotPostExamList() {
            try {
                this.loading = true;
                let res = await adminExamApi.getNotPostExam(this.currentPage);
                console.log(res);
                this.examList = res.data.list;
                this.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        curChange(val) {
            this.currentPage = val;
            this.getNotPostExamList();
        },
        pickQuestion(id) {
            this.$router.push({ path: `/pickQuestion/${id}` });
        },
        modify(id) {
            this.examForm.id = id;
            this.examForm.newFlag = true;
            this.examForm.formType = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.panel-contaner {
    padding: 10px;
    .all-exam-container {
        min-height: 200px;
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