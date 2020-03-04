<template>
    <div>
        <div class="table-container">
            <m-table
                :data="tableData"
                :tableConfig="tableConfig"
                :loading="loading"
                :tableEvent="tableEvent"
            >
                <el-table-column
                    fixed
                    slot-scope="{params}"
                    v-bind="params"
                    align="center"
                    slot="select"
                    type="selection"
                    width="55"
                ></el-table-column>
            </m-table>

            <page
                :pageSize="form.pageSize"
                :total="form.total"
                :currentPage="form.currentPage"
                @curChange="handleCurrentChange"
            />
        </div>

        <div class="tac mt30">
            <el-row>
                <el-col :span="6">
                    <el-select placeholder="选择审核员" :value="form.checker">
                        <el-option
                            v-for="(item,i) in checkers"
                            :key="i"
                            :label="item.name"
                            :value="item.value"
                        ></el-option>
                    </el-select>
                </el-col>
                <el-col :span="18">
                    <div class="tac">
                        <el-button type="primary" @click="submit">提交审核</el-button>
                        <el-button @click="cancel">取消</el-button>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import adminExamApi from "../../../../api/admin/exam";
export default {
    props: {
        examId: {
            type: String,
            default: ""
        }
    },
    components: {
        mTable,
        page
    },
    data() {
        return {
            tableData: [
                {
                    idNum: "111",
                    name: "张三",
                    member: "BB股份有限公司",
                    grade: 100,
                    times: 3,
                    id: "123"
                }
            ],
            // 试卷编号、学生编号、学员姓名、学员挂靠的会员名称，分数
            tableConfig: [
                { slot: "select" },
                { prop: "name", label: "学员姓名" },
                { prop: "idNum", label: "学员编号" },
                { prop: "member", label: "所属会员" },
                { prop: "times", label: "作答次数" },
                { prop: "grade", label: "分数" }
            ],
            loading: false,
            tableEvent: {
                "selection-change": this.handleSelectionChange
            },
            form: {
                selected: [],
                checker: null,
                total: 100,
                pageSize: 10,
                currentPage: 1
            },
            checkers: []
        };
    },
    mounted() {
        this.getGradeList();
        this.getCheckers();
    },
    methods: {
        async getGradeList() {
            this.loading = true;
            try {
                let res = adminExamApi.getGradeList(this.examId);
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        async getCheckers() {
            try {
                let res = await adminExamApi.getCheckerList();
                this.checkers = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        cancel() {
            this.$emit("submitClose");
        },
        async submit() {
            if (this.form.selected.length === 0) {
                this.$message.error("请选择要提交的成绩列表");
                return false;
            }
            try {
                let res = await adminExamApi.submitGradeList(this.form);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        handleSelectionChange(val) {
            this.form.selected = val;
            console.log(val);
        },
        handleCurrentChange(val) {
            this.form.currentPage = val;
            this.getGradeList();
        }
    }
};
</script>
<style lang="scss" scoped>
.table-container {
    overflow: hidden;
}
</style>