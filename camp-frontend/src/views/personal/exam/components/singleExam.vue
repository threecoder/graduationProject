<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">{{examName}}</span>
            </div>
            <div class="info-container">
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
                    <span>{{min}}分钟</span>
                </p>
                <p>
                    所属课程：
                    <span>{{belong}}</span>
                </p>
                <p v-if="grade">分数：{{grade}}</p>
                <div>
                    <el-button
                        v-if="!isDone"
                        type="primary"
                        class="mybutton"
                        @click="toExamIndex"
                    >开始考试</el-button>
                    <el-button
                        v-if="isDone=='done'"
                        type="primary"
                        class="mybutton"
                        @click="checkExam"
                    >查看考试</el-button>
                    <el-button
                        v-if="isDone=='done'&&grade<60"
                        type="primary"
                        class="mybutton"
                        @click="rejoin"
                        :loading="loading"
                    >重新报名</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
import examApi from "@/api/modules/exam.js";
export default {
    props: [
        "isDone", //学生是否已经考过试，done表示考过
        "examName", //考试名称
        "startTime", //考试开始时间
        "endTime", //考试结束时间
        "min", //考试时长（单位：分钟）
        "belong", //所属课程/培训
        "grade", //考试分数
        "examId", //考试id
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
                let res = await examApi.rejoinExam(this.examId);
                if (res) {
                    this.$message.success(res.msg);
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
.exam-button-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 100%;
    .items {
        flex-shrink: 1;
    }
}
</style>