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
                <div class="exam-button-container">
                    <el-button
                        size="small"
                        class="items"
                        v-if="status==0"
                        type="primary"
                        @click="pick"
                    >挑选试题</el-button>
                    <el-button
                        size="small"
                        class="items"
                        v-if="status==0"
                        type="primary"
                        @click="fillExam"
                    >随机出题</el-button>
                    <el-button size="small" class="items" v-if="status==1" type="primary">查看试题</el-button>
                    <el-button
                        size="small"
                        class="items"
                        v-if="status==1"
                        type="primary"
                        @click="publish"
                    >发布考试</el-button>
                    <el-button
                        size="small"
                        class="items"
                        v-if="status==0||status==1"
                        type="primary"
                        @click="modify"
                    >修改信息</el-button>
                    <el-button size="small" class="items" v-if="status==2" type="primary" round>停止发布</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
import adminExamApi from "../api/admin/exam";
export default {
    props: [
        "examName", //考试名称
        "startTime", //考试开始时间
        "endTime", //考试结束时间
        "min", //考试时长（单位：分钟）
        "belong", //所属课程/培训
        "examId", //考试id
        "status" //管理员使用，考试的状态，0未出题，1已经出题，2已经发布
    ],
    data() {
        return {
            loading: false
        };
    },
    methods: {
        pick() {
            this.$emit("pick");
        },
        modify() {
            this.$emit("modify");
        },
        async fillExam() {
            try {
                let res = await adminExamApi.randomFillExam(this.examId);
                this.$message.success("随机出题成功");
                this.$emit("refresh");
            } catch (error) {}
        },
        async publish() {
            try {
                let res = await adminExamApi.publishExam(this.examId);
                this.$message.success(
                    "发布考试成功，考生可以在规定时间内参与考试"
                );
                this.$emit("refresh");
            } catch (error) {}
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
    text-align: center;
    ::v-deep .el-button {
        margin: 10px 10px;
    }
}
</style>