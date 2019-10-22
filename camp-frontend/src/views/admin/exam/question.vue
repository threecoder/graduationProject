<template>
    <div class="all-container">
        <h3>试题管理</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="题目" label-position="left">
                    <el-input placeholder="输入题目关键字" v-model="form.keyword"></el-input>
                </el-form-item>
                <el-form-item label="题目类型" label-position="top">
                    <el-select v-model="form.type">
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
                </el-form-item> -->
                <el-button type="primary" round @click="search">搜索</el-button>
            </el-form>
        </div>

        <div class="table-container">
            <m-table
                :data="table.tableData"
                :tableConfig="table.tableConfig"
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
                            round
                            @click="checkDetail(row)"
                            :loading="buttonLoading"
                        >详情</el-button>
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

        <div class="dialog-container">
            <el-dialog title="修改试题" :visible.sync="flag">
                <h3>题目内容：{{dialog.state}}</h3>
                <div class="question-type">
                    <el-select v-model="dialog.type">
                        <el-option value="0" label="单选题"></el-option>
                        <el-option value="1" label="多选题"></el-option>
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
import mTable from "@/components/mTable.vue";
import page from "@/components/page.vue";
import { getQuestionList, modefyQuestionInfo,getTrainingList} from '@/api/admin/exam.js';
export default {
    components: {
        mTable,
        page
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
                    { prop: "questionId", label: "题目ID" },
                    { prop: "state", label: "试题题干" },
                    { prop: "type", label: "题目类型" },
                    { prop: "answer", label: "正确答案" },
                    { prop: "choiceA", label: "选项A" },
                    { prop: "choiceB", label: "选项B" },
                    { prop: "choiceC", label: "选项C" },
                    { prop: "choiceD", label: "选项D" },
                    { slot: "oper", label: "操作" }
                ],
                tableData: [
                    {
                        questionId: 1,
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
            }
        };
    },
    watch: {
        currentPage() {
            this.search();
        }
    },
    mounted(){
        this.search();
    },
    methods: {
        async search() {
            try {
                let res = await getQuestionList(this.form);
                this.table.tableData = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                
            }
        },
        async getTraining() {
            try {
                let res = await getTrainingList();
                this.trainingList = res.data; 
            } catch (error) {
                
            }
        },
        curChange(val) {
            this.form.currentPage = val;
        },
        checkDetail(row) {
            this.flag = true;
            this.dialog = row;
        },
        async modefyQuestion(){

        }
    }
};
</script>

<style lang="scss" scoped>
.all-container {
    
    .dialog-container {
        .question-type {
            position: absolute;
            right: 10%;
            top: 75px;
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