<template>
    <div>
        <div>
            <h5 class="panel-title">考试信息</h5>
            <div class="examInfo-container">
                <span class="info-items">
                    考试名称：
                    <span class="blue">{{examInfo.name}}</span>
                </span>
                <span class="info-items">
                    所属培训：
                    <span class="blue">{{examInfo.training}}</span>
                </span>
                <span class="info-items">
                    题目数量：
                    <span class="blue">{{examInfo.num}}</span>
                </span>
                <span class="info-items">
                    考试时长：
                    <span class="blue">{{examInfo.len}}分钟</span>
                </span>
                <span class="info-items">
                    开始时间：
                    <span class="blue">{{examInfo.startTime}}</span>
                </span>
                <span class="info-items">
                    结束时间：
                    <span class="blue">{{examInfo.endTime}}</span>
                </span>
                <span class="info-items">
                    通过成绩：
                    <span class="blue">{{examInfo.pass}}</span>
                </span>
            </div>
            <div class="divider"></div>
            <h5 class="panel-title">挑选试题</h5>
            <div class="question-container">
                <div class="index-list">
                    <div
                        :class="{'index-items': true, 'index-items-fill': i<=len}"
                        v-for="i in examInfo.num"
                        :key="i"
                        @click="checkDetail(i,'picked')"
                    >{{i}}</div>
                </div>
                <div class="table-container">
                    <el-form :model="form" inline>
                        <el-form-item label="题目" label-position="left">
                            <el-input clearable placeholder="输入题目关键字" v-model="form.keyword"></el-input>
                        </el-form-item>
                        <el-form-item label="题目类型" label-position="top">
                            <el-select clearable v-model="form.type">
                                <el-option value="0" label="单选题"></el-option>
                                <el-option value="1" label="多选题"></el-option>
                                <!-- <el-option value="2" label="判断题"></el-option> -->
                            </el-select>
                        </el-form-item>
                        <el-button type="primary" round @click="search">搜索</el-button>
                    </el-form>
                    <m-table
                        :data="table.data"
                        :tableConfig="table.config"
                        :loading="table.loading"
                    >
                        <el-table-column
                            align="center"
                            slot="oper"
                            slot-scope="{ params }"
                            v-bind="params"
                            class="myoper"
                        >
                            <div slot-scope="{ row }">
                                <el-button
                                    size="small"
                                    type="primary"
                                    @click="checkDetail(row,'detail')"
                                >详情</el-button>
                                <el-button size="small" type="primary" @click="pickOne(row)">加入试卷</el-button>
                            </div>
                        </el-table-column>
                    </m-table>
                    <page
                        @curChange="curChange"
                        :currentPage="form.currentPage"
                        :total="form.total"
                        :pageSize="form.pageSize"
                    />
                </div>
                <div class="button-container">
                    <el-button @click="cancel">取消</el-button>
                    <el-button type="primary" @click="save">保存</el-button>
                </div>
            </div>
        </div>

        <div class="dialog-container">
            <el-dialog title="试题详情" :visible.sync="flag">
                <h3>题目内容：{{dialog.state}}</h3>
                <div class="question-type">
                    <el-select disabled v-model="dialog.type">
                        <el-option value="0" label="单选题"></el-option>
                        <el-option value="1" label="多选题"></el-option>
                    </el-select>
                </div>
                <div class="option">
                    <span class="tip">正确答案：</span>
                    <el-input disabled v-model="dialog.answer"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项A：</span>
                    <el-input disabled v-model="dialog.choiceA"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项B：</span>
                    <el-input disabled v-model="dialog.choiceB"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项C：</span>
                    <el-input disabled v-model="dialog.choiceC"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项D：</span>
                    <el-input disabled v-model="dialog.choiceD"></el-input>
                </div>

                <div class="button-container" v-if="dialog.detailType=='picked'">
                    <el-button @click="remove" type="primary">移除此题目</el-button>
                    <el-button @click="flag=false">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import page from "@/components/page.vue";
import adminExamApi from "@/api/admin/exam.js";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            examId: this.$route.params.id,
            examInfo: {
                num: 10,
                name: "考试名称",
                startTime: "2016-10-10 16:00:00",
                endTime: "2016-10-10 16:00:00",
                len: 120,
                pass: 60,
                training: "所属培训名",
                hasQue: true
            },
            questionIdList: [],
            len: 0,
            table: {
                config: [
                    { prop: "questionId", label: "题目ID", width: "100px" },
                    { prop: "stateMin", label: "题目" },
                    { prop: "type", label: "题目类型", width: "100px" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "200px"
                    }
                ],
                data: [],
                loading: false
            },
            form: {
                keyword: null,
                type: null,
                trainingId: null,
                currentPage: 1,
                total: 100,
                pageSize: 10
            },
            flag: false,
            dialog: {
                questionId: 1,
                state: "以下属于物理层的设备是",
                type: "单选题",
                answer: "A",
                choiceA: "中继器",
                choiceB: "以太网交换机",
                choiceC: "桥",
                choiceD: "网关",
                detailType: "detail"
            }
        };
    },
    computed: {
        itemFill() {
            return this.len;
        }
    },
    mounted() {
        this.search();
    },
    methods: {
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },
        async getInfo() {
            try {
                let res = await adminExamApi.getExamInfo(this.examId);
                this.examInfo = res.data;
                if (this.examInfo.hasQue == true) {
                    this.getQueList();
                }
            } catch (error) {}
        },
        async getQueList() {
            try {
                let res = await adminExamApi.getExamQuestionList(this.examId);
                this.questionIdList = res.data.list;
                this.len = res.data.list.length;
            } catch (error) {}
        },
        async search() {
            try {
                let res = await adminExamApi.getQuestionList(this.form);
                this.table.data = res.data.list;
                let arr = this.table.data;
                arr.forEach((val, i) => {
                    if (val.type == 0) {
                        arr[i].type = "单选题";
                    } else if (val.type == 1) {
                        arr[i].type = "多选题";
                    } else if (val.type == 2) {
                        arr[i].type = "判断题";
                    }
                    arr[i].stateMin =
                        val.state.length <= 30
                            ? val.state
                            : val.state.substring(0, 27) + ".....";
                });
                this.form.total = res.data.total;
            } catch (error) {
                console.log(error);
            }
        },
        async checkDetail(row, type) {
            if (type == "picked") {
                if (row - 1 < 0 || row > this.questionIdList.length) {
                    this.$message.error(`第${row}题还是空的`);
                    return false;
                }
                try {
                    let id = this.questionIdList[row - 1];
                    let res = await adminExamApi.getSingleQuestion(id);
                    this.dialog = { ...res.data };
                } catch (error) {}
            } else {
                this.dialog = { ...row };
                this.dialog.detailType = type;
            }
            this.dialog.detailType = type;
            this.flag = true;
        },
        pickOne(row) {
            if (this.questionIdList.indexOf(row.questionId) != -1) {
                this.$message.error("该题目已经加入到试卷中了，不可再次加入！");
                return false;
            } else {
                this.questionIdList.push(row.questionId);
                console.log(this.questionIdList);
                this.len++;
            }
        },
        remove() {
            let index = this.questionIdList.indexOf(this.dialog.questionId);
            if (index != -1) {
                this.questionIdList.splice(index, 1);
                this.len--;
            }
        },
        cancel() {
            this.$router.go(-1);
        },
        async save() {
            if (this.questionIdList.length < this.examInfo.num) {
                this.$message.error("选择题目数量还未达到设定数量");
                return false;
            }
            let par = {};
            par.id = this.examId;
            par.queList = this.questionIdList;
            try {
                let res = await adminExamApi.saveQuestionForExam(par);
                this.$message.success("保存题目成功");
                this.$router.push({ path: "/toPublic" });
            } catch (error) {}
        }
    }
};
</script>
<style lang="scss" scoped>
.examInfo-container {
    width: 100%;
    // background-color: #fff;
    height: auto;
    .info-items {
        line-height: 50px;
        padding: 5px 10px;
        margin: 0 10px;
    }
    .blue {
        color: rgb(64, 158, 255);
    }
}
.question-container {
    // background-color: #fff;
    overflow: hidden;
    .index-list {
        margin-bottom: 20px;
        display: flex;
        justify-content: flex-start;
        flex-wrap: wrap;
        .index-items {
            flex-shrink: 0.5;
            padding: 3px 8px;
            margin: 5px 10px;
            text-align: center;
            line-height: 25px;
            box-sizing: content-box;
            width: 5%;
            height: 25px;
            cursor: pointer;
            color: rgb(64, 158, 255);
            border: 1px solid rgb(64, 158, 255);
        }
        .index-items-fill {
            color: #fff;
            background-color: rgb(64, 158, 255);
        }
    }
    .table-container {
        ::v-deep .el-table {
            width: 99%;
        }
        overflow: hidden;
    }
    .button-container {
        text-align: center;
        margin-top: 40px;
    }
}
.dialog-container {
    .question-type {
        position: absolute;
        right: 10%;
        top: 25px;
    }
    .option {
        margin-top: 20px;
        .tip {
            margin-bottom: 8px;
            display: block;
        }
    }
    .button-container {
        margin-top: 20px;
        text-align: center;
    }
}
</style>