<template>
    <div class="all-container">
        <div class="exam-container">
            <single-choice
                :type="item.type"
                :title="item.title"
                :arr="item.arr"
                :score="item.score"
                :index="item.index"
                :choice.sync="answer"
            />
            <div class="button-container">
                <el-button :disabled="preFlag" type="primary" @click="pre">上 一 题</el-button>
                <el-button v-if="index+1==list.length" type="primary" @click="beforeSubmit">提 交 试 卷</el-button>
                <el-button v-else :disabled="nextFlag" type="primary" @click="next">下 一 题</el-button>
            </div>
        </div>
        <div class="menu">
            <div class="examName">
                <h3>{{examInfo.name}}</h3>
            </div>
            <div class="time">
                <p>{{remainTime}}</p>
                <p class="time-icon">
                    <i class="el-icon-timer"></i>剩余时间
                </p>
            </div>
            <div class="tika">
                <h4>进度</h4>
                <div class="box-container">
                    <template v-for="i in list.length">
                        <span
                            :key="i"
                            :class="{'done': hadDone(i)}"
                            @click="switchQuestion(i)"
                        >{{i}}</span>
                    </template>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import singleChoice from "./components/singChoice.vue";
import examApi from "@/api/modules/exam.js";
export default {
    data() {
        return {
            examId: this.$route.params.id,
            index: 0,
            nextFlag: false,
            preFlag: true,
            item: {
                index: 0,
                title: null,
                score: -1,
                arr: ["", "", "", ""],
                type: 0
            },
            list: [],
            answer: [],
            examInfo: {
                name: "考试名",
                timeLength: 30
            },
            remainTime: "30:11"
        };
    },
    components: {
        singleChoice
    },
    mounted() {
        this.getExamById();
        console.log("考试页面", this.examId);
    },
    watch: {
        index() {
            if (this.index == 0) {
                this.preFlag = true;
            } else if (this.index > 0) {
                this.preFlag = false;
            }
            if (this.index < this.list.length - 1) {
                this.nextFlag = false;
            } else {
                this.nextFlag = true;
            }
            this.item = this.list[this.index];
        }
    },
    methods: {
        async getExamById() {
            try {
                let res = await examApi.getExamQuestion(this.examId);
                console.log(res);
                this.list = res.data.list;
                this.item = this.list[0];
                this.examInfo = res.data.examInfo;
                let s = this.examInfo.timeLength * 60;
                this.initAnswer();
                this.remainTime = `${parseInt(s / 60)
                    .toString()
                    .padStart(2, "0")}:${(s % 60).toString().padStart(2, "0")}`;
                let id = setInterval(() => {
                    let arr = this.remainTime.split(":");
                    let s = +arr[0] * 60 + +arr[1] - 1;
                    if (s == -1) {
                        clearInterval(id);
                        this.runOutOfTime();
                        console.log("时间到");
                        return false;
                    }
                    this.remainTime = `${parseInt(s / 60)
                        .toString()
                        .padStart(2, "0")}:${(s % 60)
                        .toString()
                        .padStart(2, "0")}`;
                }, 1000);
            } catch (error) {}
        },
        initAnswer() {
            this.list.forEach((val, i) => {
                if (val.type == 1) {
                    this.answer[i] = [];
                } else {
                    this.answer[i] = null;
                }
            });
        },
        hadDone(i) {
            if (Array.isArray(this.answer[i - 1])) {
                return this.answer[i - 1].length > 0;
            } else {
                return this.answer[i - 1] != null;
            }
        },
        next() {
            this.index++;
            console.log(this.answer);
        },
        pre() {
            this.index--;
        },
        beforeSubmit() {
            let flag = true;
            for (let i = 0; i < this.answer.length; i++) {
                let t = this.answer[i];
                if ((Array.isArray(t) && t.length == 0) || t == null) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //提交试卷
                this.submit();
            } else {
                this.$message.error("还有问题没有作答，不能提交");
            }
        },
        runOutOfTime() {
            this.submit();
        },
        async submit() {
            //提交试卷代码
            try {
                let par = {
                    examId: this.examId,
                    answer: this.answer
                }
                console.log(par)
                let res = await examApi.submitExam(par);
                this.$message.success("提交试卷成功！");
                this.$router.push({ path: "/examTodo" });
            } catch (error) {}
        },
        switchQuestion(i) {
            this.index = i - 1;
        }
    }
};
</script>
<style lang="scss" scoped>
.all-container {
    overflow: hidden;
    background-color: rgb(240, 240, 240);
    height: 100%;
    .menu {
        border-radius: 10px;
        width: 10%;
        min-width: 200px;
        padding: 10px;
        height: 700px;
        background-color: #fff;
        position: absolute;
        top: 100px;
        left: calc(67% + 10px);
        .examName {
            height: 55px;
            border-bottom: 1px solid #ccc;
            h3 {
                color: orange;
                text-align: center;
                line-height: 55px;
            }
        }
        .time {
            height: 90px;
            border-bottom: 1px solid #ccc;
            padding: 10px;
            p {
                font-size: 30px;
                color: rgb(27, 157, 253);
                text-align: center;
            }
            .time-icon {
                font-size: 26px;
                margin-top: 10px;
            }
        }
        .tika {
            margin-top: 100px;
            h4 {
                text-align: center;
                margin-top: 20px;
            }
            .box-container {
                width: 100%;
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                justify-content: flex-start;
                align-items: flex-start;
                span {
                    border-radius: 50%;
                    margin: 10px 13px 0 0;
                    color: rgb(100, 100, 100);
                    width: 25px;
                    height: 25px;
                    text-align: center;
                    line-height: 25px;
                    border: 1px solid grey;
                    cursor: pointer;
                }
                .done {
                    color: #fff;
                    background-color: orange;
                    border: 1px solid orange;
                }
            }
        }
    }
    .exam-container {
        border-radius: 10px;
        margin: 100px 35% 100px 20%;
        background-color: #fff;
        height: 700px;
        width: 45%;
        padding: 10px;
        .button-container {
            margin-top: 150px;
            text-align: center;
            .el-button {
                width: 25%;
            }
        }
    }
}
</style>