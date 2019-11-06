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
                <div v-if="idtype!=1">
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
                <div class="exam-button-container" v-if="idtype==1">
                    <el-button class="items" v-if="status==0" type="primary" round @click="pick">挑选试题</el-button>
                    <el-button class="items" v-if="status==0" type="primary" round @click="fillExam">随机出题</el-button>
                    <el-button class="items" v-if="status==1" type="primary" round >查看试题</el-button>
                    <el-button class="items" v-if="status==1" type="primary" round @click="publish">发布考试</el-button>
                    <el-button class="items" v-if="status==0||status==1" type="primary" round @click="modify">修改信息</el-button>
                    <el-button class="items" v-if="status==2" type="primary" round>停止发布</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
import { rejoinExam } from "@/api/modules/exam.js";
import { publishExam, randomFillExam } from "@/api/admin/exam.js";
export default {
    props: [
        "type",     //学生是否已经考过试，done表示考过
        "examName", //考试名称
        "startTime",//考试开始时间
        "endTime",  //考试结束时间
        "min",      //考试时长（单位：分钟）
        "belong",   //所属课程/培训
        "grade",    //考试分数（学生使用）
        "examId",   //考试id
        "idtype",   //使用这个组件的身份状态，0学生，1管理员
        "status"    //管理员使用，考试的状态，0未出题，1已经出题，2已经发布
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
        pick() {
            this.$emit("pick");
        },
        modify(){
            this.$emit("modify")
        },
        async fillExam() {
            try {
                let res = await randomFillExam(this.examId);
                this.$message.success("随机出题成功");
                this.$emit("refresh");
            } catch (error) {}
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
        },
        async publish() {
            try {
                let res = await publishExam(this.examId);
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