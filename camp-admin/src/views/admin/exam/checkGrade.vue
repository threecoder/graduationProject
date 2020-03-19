<template>
    <div>
        <h4>成绩审核</h4>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline></el-form>
        </div>
        <div class="table-container">
            <m-table
                :data="table.data"
                :tableConfig="table.config"
                :loading="table.loading"
                :tableEvent="table.event"
                :tableAttr="table.attr"
            >
                <el-table-column
                    slot-scope="{params}"
                    v-bind="params"
                    align="center"
                    slot="select"
                    type="selection"
                    width="55"
                ></el-table-column>
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="small" @click="detail(row)">作答详情</el-button>
                        <el-button type="primary" size="small" @click="ApprovalRow(row)">通过</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :pageSize="form.pageSize"
                :total="form.total"
                :currentPage="form.currentPage"
                @curChange="handleCurrentChange"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import examApi from "../../../api/admin/exam";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                trainingName: null,
                idNum: null,
                studentName: null
            },
            table: {
                config: [
                    { slot: "select", "reserve-selection": true },
                    { prop: "name", label: "学员姓名" },
                    { prop: "idNum", label: "学员编号" },
                    { prop: "trainingName", label: "所属培训" },
                    { prop: "member", label: "所属会员" },
                    { prop: "times", label: "作答次数" },
                    { prop: "grade", label: "分数" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "200px"
                    }
                ],
                data: [
                    {
                        idNum: "111",
                        name: "张三",
                        member: "BB股份有限公司",
                        grade: 100,
                        times: 3,
                        id: "123",
                        trainingName: "训练名",
                        examId: "宿舍"
                    }
                ],
                event: {
                    "selection-change": this.handleSelectionChange
                },
                attr: {
                    "row-key": "id"
                },
                loading: false
            },
            selected: []
        };
    },
    mounted(){
        this.getCheckList();
    },
    methods: {
        handleCurrentChange(val) {
            this.form.currentPage = val;
            this.getCheckList();
        },
        handleSelectionChange(val) {
            this.selected = val;
            console.log(val);
        },
        async getCheckList() {
            this.table.loading = true;
            try {
                let res = await examApi.getWaitingGradeList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (e) {
                this.$message.error(e.message);
            }
            this.table.loading = false;
        },
        detail(row) {
            this.$router.push({
                path: `/studentExamDetail/${row.name}/${row.examId}/${row.idNum}`
            });
        },
        ApprovalRow(row) {
            this.$confirm("确定要通过该条成绩记录的申请吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                this.table.loading = true;
                try {
                    let res = await examApi.approvalSingleRecord(row.id);
                    this.$message.success("审批成功！");
                    this.getCheckList();
                } catch (e) {
                    this.$message.error(e.message);
                }
                this.table.loading = false;
            });
        },
        ApprovalSelected() {
            this.$confirm("确定要通过这批成绩记录的申请吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                this.table.loading = true;
                try {
                    let ids = this.selected.map(val => val.id);
                    let res = await examApi.approvalManyRecords(ids);
                    this.$message.success("审批成功！");
                    this.getCheckList();
                } catch (e) {
                    this.$message.error(e.message);
                }
                this.table.loading = false;
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>