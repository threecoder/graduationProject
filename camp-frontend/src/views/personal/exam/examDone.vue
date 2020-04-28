<template>
    <div>
        <h2>已完成的考试</h2>
        <div>
            <span class="panel-title">已经完成的试卷</span>
            <div v-if="examList.length>0" class="all-exam-container">
                <div class="single-exam-container" v-for="(item,i) in examList" :key="i">
                    <single-exam
                        isDone="done"
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
                    min: "120",
                    belong: "HTML入门",
                    grade: 59
                }
            ],
            flag: true
        };
    },
    mounted() {
        this.getExamList();
    },
    methods: {
        async getExamList() {
            try {
                let res = await examApi.getDoneExam();
                this.examList = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
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
</style>