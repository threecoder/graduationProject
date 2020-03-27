<template>
    <div>
        <h3>证书操作审核</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="form">
                <el-form-item label="证书名称">
                    <el-input v-model="form.cerName" clearable placeholder="证书名称"></el-input>
                </el-form-item>
                <el-form-item label="操作人名称">
                    <el-input v-model="form.userName" clearable placeholder="操作人名称"></el-input>
                </el-form-item>
                <el-form-item label="操作类型">
                    <el-select v-model="form.type" clearable placeholder="证书操作的类型">
                        <el-option label="证书变更" value="change"></el-option>
                        <el-option label="证书复审" value="recheck"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getExamineLog">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="pass(row)">通过</el-button>
                        <el-button size="small" type="primary" @click="reject(row)">拒绝</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                @curChange="curChange"
                :currentPage.sync="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page";
import cerApi from "../../../api/admin/certificate";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                cerName: null,
                userName: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "userName", label: "操作人" },
                    { prop: "userType", label: "操作人身份" },
                    { prop: "cerName", label: "对应证书" },
                    { prop: "desc", label: "操作描述" },
                    { prop: "operType", label: "操作类型" },
                    { prop: "operTime", label: "时间" },
                    { prop: "success", label: "是否成功" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "200px"
                    }
                ],
                data: [
                    {
                        operId: "111",
                        userName: "12344"
                    }
                ],
                loading: false
            }
        };
    },
    methods: {
        curChange(val) {
            this.form.currentPage = val;
            this.getExamineLog();
        },
        async getExamineLog() {
            this.table.loading = true;
            try {
                let res = cerApi.getOperLogList(this.form);
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        pass(row) {
            this.$confirm("确定要通过这条申请吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                this.table.loading = true;
                try {
                    let res = await cerApi.passOperLog({ operId: row.operId });
                    this.$message.success("通过申请成功");
                    await this.getExamineLog();
                } catch (error) {
                    this.$message.error(error.message);
                }
                this.table.loading = false;
            });
        },
        reject(row) {
            this.$confirm("确定要拒绝这条申请吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                this.table.loading = true;
                try {
                    let res = await cerApi.rejectOperLog({
                        operId: row.operId
                    });
                    this.$message.success("拒绝申请成功");
                    await this.getExamineLog();
                } catch (error) {
                    this.$message.error(error.message);
                }
                this.table.loading = false;
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>