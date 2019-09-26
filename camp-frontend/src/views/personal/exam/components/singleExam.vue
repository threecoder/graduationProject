<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">{{examName}}</span>
            </div>
            <div class="info-container">
                <p>
                    日期：
                    <span>{{date}}</span>
                </p>
                <p>
                    开始时间：
                    <span>{{startTime}}</span>
                </p>
                <p>
                    结束时间：
                    <span>{{endTime}}</span>
                </p>
                <p>
                    考试时长：
                    <span>{{min}}分</span>
                </p>
                <p>
                    所属课程：
                    <span>{{belong}}</span>
                </p>
                <p v-if="grade">分数：{{grade}}</p>
                <el-button
                    v-if="!type"
                    type="primary"
                    round
                    class="mybutton"
                    @click="toExamIndex"
                >开始考试</el-button>
                <el-button
                    v-if="type=='done'"
                    type="primary"
                    round
                    class="mybutton"
                    @click="checkExam"
                >查看考试</el-button>
                <el-button
                    v-if="type=='done'&&grade<60"
                    type="primary"
                    round
                    class="mybutton"
                    @click="rejoin"
                    :loading="loading"
                >重新报名</el-button>
            </div>
        </el-card>
    </div>
</template>
<script>
import { rejoinExam } from "@/api/modules/exam.js";
export default {
    props: [
        "type",
        "examName",
        "date",
        "startTime",
        "endTime",
        "min",
        "belong",
        "grade",
        "examId"
    ],
    data() {
        return {
            loading: false
        };
    },
    methods: {
        toExamIndex() {
            if (this.examId !== null && this.examId !== undefined) {
                this.$router.push({ path: `examIndex/${this.examId}` });
            }
        },
        checkExam() {
            if (this.examId !== null && this.examId !== undefined) {
                this.$router.push({ path: `examDetail/${this.examId}` });
            }
        },
        async rejoin() {
            this.loading = true;
            try {
                let res = await rejoinExam(this.examId);
                if (res) {
                    this.$message.success(res.msg);
                }
            } catch (error) {}

            this.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.box-card {
    border-radius: 10px;
    ::v-deep .el-card__header {
        background-color: rgb(99, 181, 239);
    }
    .clearfix {
        text-align: center;
        .title {
            color: #fff;
            font-size: 20px;
        }
    }
    .info-container {
        text-align: center;
        p {
            display: block;
            width: 100%;
            margin: 10px 0;
            text-align: center;
            color: grey;
            span {
                color: #555;
            }
        }
        .mybutton {
            margin: 20px 10px 10px;
        }
    }
}
</style>