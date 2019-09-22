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
                    <span>{{min}}</span>
                </p>
                <p>
                    所属课程：
                    <span>{{belong}}</span>
                </p>
                <p v-if="grade">分数：{{grade}}</p>
                <el-button type="primary" round class="mybutton" @click="toExamIndex">开始考试</el-button>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    props: [
        "examName",
        "date",
        "startTime",
        "endTime",
        "min",
        "belong",
        "grade",
        "examId"
    ],
    methods: {
        toExamIndex(){
            if(this.examId !== null && this.examId!== undefined) {
                this.$router.push({path:`examIndex/${this.examId}`})
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.box-card {
    border-radius: 10px;
    .clearfix {
        text-align: center;
        .title {
            color: rgb(99, 181, 239);
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
            margin: 20px auto 10px;
        }
    }
}
</style>