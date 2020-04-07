<template>
    <div class="all-container">
        <h3>试题管理</h3>
        <div class="divider"></div>
        <div>
            <p class="panel-title">添加试题</p>
            <div class="button-container">
                <el-button
                    size="medium"
                    style="margin-right:10px;"
                    type="primary"
                    @click="getQueTem"
                >下载模板</el-button>
                <upload
                    :autoUpload="uploadAttr.autoUpload"
                    :uploadUrl="uploadAttr.uploadUrl"
                    :formData="uploadAttr.data"
                    :size="'medium'"
                    @uploadSuccess="success"
                />
            </div>
        </div>

        <p class="panel-title">试题列表</p>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="题目" label-position="left">
                    <el-input clearable placeholder="输入题目关键字" v-model="form.keyword"></el-input>
                </el-form-item>
                <el-form-item label="题目类型" label-position="top">
                    <el-select clearable v-model="form.type">
                        <el-option value="0" label="单选题"></el-option>
                        <el-option value="1" label="多选题"></el-option>
                        <el-option value="2" label="判断题"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="所属培训" label-position="top">
                    <el-select v-model="form.trainingId">
                        <template v-for="(item,i) in trainingList">
                            <el-option :label="item.label" :value="item.value" :key="i"></el-option>
                        </template>
                    </el-select>
                </el-form-item>-->
                <el-button size="medium" type="primary" @click="search">搜索</el-button>
            </el-form>
        </div>

        <div class="table-container">
            <m-table
                :data="table.tableData"
                :tableConfig="table.tableConfig"
                :tableAttr="table.tableAttr"
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
                            @click="checkDetail(row)"
                            :loading="buttonLoading"
                        >详 情</el-button>
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

        <!-- 题目详情弹框 -->
        <div class="dialog-container">
            <el-dialog title="修改试题" :visible.sync="flag">
                <h3>题目内容：{{dialog.state}}</h3>
                <el-input v-model="dialog.state" type="textarea"></el-input>
                <div class="question-type">
                    <el-select v-model="dialog.type">
                        <el-option value="单选题" label="单选题"></el-option>
                        <el-option value="多选题" label="多选题"></el-option>
                        <el-option value="判断题" label="判断题"></el-option>
                    </el-select>
                </div>
                <div class="option">
                    <span class="tip">正确答案：</span>
                    <el-input v-model="dialog.answer"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项A：</span>
                    <el-input v-model="dialog.choiceA"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项B：</span>
                    <el-input v-model="dialog.choiceB"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项C：</span>
                    <el-input v-model="dialog.choiceC"></el-input>
                </div>
                <div class="option">
                    <span class="tip">选项D：</span>
                    <el-input v-model="dialog.choiceD"></el-input>
                </div>

                <div class="button-container">
                    <el-button @click="flag=false">取 消</el-button>
                    <el-button type="primary" @click="modefyQuestion">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import upload from "../../../components/upload.vue";
import adminExamApi from "../../../api/admin/exam";
import { download } from "../../../api/request";
export default {
    components: {
        mTable,
        page,
        upload
    },
    data() {
        return {
            form: {
                keyword: null,
                type: null,
                trainingId: null,
                currentPage: 1,
                total: 100,
                pageSize: 10
            },
            trainingList: [],
            table: {
                tableConfig: [
                    { prop: "questionId", label: "题目ID", fixed: "left" },
                    { prop: "stateMin", label: "试题题干", width: "250px" },
                    { prop: "type", label: "题目类型" },
                    { prop: "answer", label: "正确答案" },
                    { prop: "choiceAMin", label: "选项A" },
                    { prop: "choiceBMin", label: "选项B" },
                    { prop: "choiceCMin", label: "选项C" },
                    { prop: "choiceDMin", label: "选项D" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "150px"
                    }
                ],
                tableData: [
                    {
                        questionId: 1,
                        stateMin: "简略",
                        state: "以下属于物理层的设备是",
                        type: "单选题",
                        answer: "A",
                        choiceA: "中继器",
                        choiceB: "以太网交换机",
                        choiceC: "桥",
                        choiceD: "网关"
                    },
                    {
                        questionId: 2,
                        state: " 在以太网中，是根据___地址来区分不同的设备的.",
                        type: "单选题",
                        answer: "A",
                        choiceA: "LLC地址",
                        choiceB: "IP地址",
                        choiceC: "MAC地址",
                        choiceD: "IPX地址"
                    }
                ],
                tableAttr: {
                    stripe: true
                },
                tableLoading: false
            },
            buttonLoading: false,
            flag: false,
            dialog: {
                questionId: 1,
                state: "以下属于物理层的设备是",
                type: "单选题",
                answer: "A",
                choiceA: "中继器",
                choiceB: "以太网交换机",
                choiceC: "桥",
                choiceD: "网关"
            },
            uploadAttr: {
                uploadUrl: "/campback/admin/importQuestionByFile",
                autoUpload: false,
                data: {}
            }
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        async search() {
            try {
                let res = await adminExamApi.getQuestionList(this.form);
                this.table.tableData = res.data.list;
                let arr = this.table.tableData;
                let t = ["choiceA", "choiceB", "choiceC", "choiceD"],
                    max = 4;
                arr.forEach((val, i) => {
                    if (val.type == 0) {
                        arr[i].type = "单选题";
                    } else if (val.type == 1) {
                        arr[i].type = "多选题";
                    } else if (val.type == 2) {
                        arr[i].type = "判断题";
                        max = 2;
                    }
                    arr[i].stateMin =
                        val.state.length <= 30
                            ? val.state
                            : val.state.substring(0, 24) + ".....";
                    for (let j = 0; j < max; j++) {
                        arr[i][t[j] + "Min"] =
                            val[t[j]].length <= 20
                                ? val[t[j]]
                                : val[t[j]].substring(0, 10) + ".....";
                    }
                });
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getTraining() {
            try {
                let res = await adminExamApi.getTrainingList();
                this.trainingList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },
        checkDetail(row) {
            this.flag = true;
            this.dialog = { ...row };
        },
        beforeModify() {
            let data = { ...this.dialog };
            let ans = data.answer,
                tem = [];
            for (let i = 0; i < ans.length; i++) {
                switch (ans[i].toUpperCase()) {
                    case "A":
                        tem.push(1);
                        break;
                    case "B":
                        tem.push(2);
                        break;
                    case "C":
                        tem.push(3);
                        break;
                    case "D":
                        tem.push(4);
                        break;

                    default:
                        break;
                }
            }
            data.answer = tem;
            return data;
        },
        async modefyQuestion() {
            try {
                let data = this.beforeModify();
                let res = await adminExamApi.modefyQuestionInfo(data);
                this.$message.success("修改试题成功");
                this.search();
                this.flag = false;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getQueTem() {
            try {
                let res = await adminExamApi.getQuestionTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async success(res) {
            console.log(res);
        }
    }
};
</script>

<style lang="scss" scoped>
.all-container {
    .button-container {
        width: 400px;
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
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
}
</style>