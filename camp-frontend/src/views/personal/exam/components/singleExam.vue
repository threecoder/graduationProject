<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">{{examName}}</span>
            </div>
            <div class="info-container">
                <p>日期：<span>{{date}}</span></p>
                <p>开始时间：<span>{{startTime}}</span></p>
                <p>结束时间：<span>{{endTime}}</span></p>
                <p>考试时长：<span>{{Min}}</span></p>
                <p>所属课程：<span>{{belong}}</span></p>
                <p v-if="grade">分数：{{grade}}</p>
                <el-button type="primary" round class="mybutton">开始考试</el-button>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    props: ["examName","date","startTime","endTime","Min","belong","grade","examId"]
}
</script>
<style lang="scss" scoped>
.box-card {
    border-radius: 10px;
    .clearfix {
        text-align: center;
        .title {
            color: rgb(99, 181, 239);
        }
        
    }
    .info-container {
        text-align: center;
        .mybutton {
            margin: 20px auto 10px;
        }
    }
    p {
        text-align: center;
        color: grey;
        span {
            color: #555;
        }
    }
}
</style>