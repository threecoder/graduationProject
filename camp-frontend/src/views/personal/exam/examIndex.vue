<template>
    <div class="all-container">
        <div class="index-container">
            <div class="header">
                <h1>{{examInfo.courseName}}</h1>
            </div>
            <div class="divider"></div>
            <div class="content">
                <div>
                    <h3>{{examInfo.examName}}</h3>
                    <h4>考试说明</h4>
                    <ul>
                        <li><p>考试次数：剩余{{examInfo.remainTimes}}次</p></li>
                        <li><p>考试时长：{{examInfo.timeLength}}分钟</p></li>
                        <li><p>及格分数：{{examInfo.pass}}分</p></li>
                        <li><p>如果遇到考试异常，在考试时间内可以重新进入考试</p></li>
                    </ul>
                </div>
                <el-button class="button" type='primary' @click="toExamIndex">进 入 考 试</el-button>
            </div>
        </div>
    </div>
</template>
<script>
import examApi from '../../../api/modules/exam'
export default {
    data(){
        return {
            examId: this.$route.params.id,
            examInfo: {
                courseName: "课程名称",
                examName: "考试名称",
                remainTimes: 3,
                timeLength: 120,
                pass: 60,
            }
        }
    },
    mounted(){
        this.getIndexInfoByExamId();
    },
    methods: {
        async getIndexInfoByExamId(){
            let res = await examApi.getExamIndexInfo(this.examId);
            if(res){
                this.examInfo = res.data;
            }
        },
        toExamIndex(){
            this.$router.push({path:`/exam/${this.examId}`});
        }
    }
};
</script>
<style lang="scss" scoped>
.all-container {
    background-color: rgb(243,244,248);
    height: 100%;
}
.index-container {
    position: absolute;
    background-color: #fff;
    top: 100px;
    left: calc(50% - 300px);
    width: 600px;
    height: 660px;
    padding: 20px;
    text-align: center;
    .header {
        height: 150px;
        h1 {
            color: orange;
            font-weight: 400;
            line-height: 150px;
            font-size: 45px;
        }
    }
    .content {
        font-size: 18px;
        h3 {
            color: rgb(64, 158, 255);
            font-weight: 400;
            margin-top: 30px;
        }
        h4 {
            font-weight: 200;
            margin-top: 40px;
        }
        ul {
            max-width: 388px;
            list-style: none;
            margin: 50px auto 50px;
            li {
                // text-align: left;
                margin: 20px 0;
                border-bottom: 1px solid rgb(240, 240, 240);
                height: 25px;
                line-height: 25px;
                p {
                    color: rgb(100, 100, 100);
                }
            }
        }
        .button {
            min-width: 300px;
            height: 50px;
            font-size: 18px;
        }
    }
}
</style>