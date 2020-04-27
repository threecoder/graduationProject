<template>
    <div>
        <el-row>
            <el-col :span="22">
                <h3>管理员账号管理</h3>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" size="medium" @click="addDiaFlag=true">添加管理员</el-button>
            </el-col>
        </el-row>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="账号">
                    <el-input v-model="form.account" placeholder="管理员账号" clearable></el-input>
                </el-form-item>
                <el-form-item label="名字">
                    <el-input v-model="form.name" placeholder="管理员名字" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="medium" @click="getAdminList">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" v-bind="params" slot-scope="{params}" align="center">
                    <div slot-scope="{row}">
                        <el-button
                            v-if="row.lock=='否'"
                            type="primary"
                            size="mini"
                            @click="lock(row)"
                        >冻结</el-button>
                        <el-button
                            v-if="row.lock=='是'"
                            type="primary"
                            size="mini"
                            @click="unLock(row)"
                        >解冻</el-button>
                        <el-button type="primary" size="mini" @click="authority(row)">权限</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>

        <!-- 添加管理员弹框 -->
        <el-dialog title="添加管理员" :visible.sync="addDiaFlag" v-if="addDiaFlag">
            <new-admin @cancel="addDiaFlag=false;getAdminList()" />
        </el-dialog>

        <!-- 管理员权限弹框 -->
        <el-dialog title="修改权限" :visible.sync="auth.flag" v-if="auth.flag">
            <authority :account="auth.account" @cancel="auth.flag=false" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import newAdmin from "./components/newAdmin.vue";
import authority from "./components/authority.vue";
import systemApi from "../../../api/admin/system";
export default {
    components: {
        mTable,
        page,
        newAdmin,
        authority
    },
    data() {
        return {
            form: {
                name: null,
                account: null,
                pageSize: 10,
                currentPage: 1,
                total: 10
            },
            table: {
                config: [
                    { prop: "account", label: "账号" },
                    { prop: "name", label: "名称" },
                    { prop: "lock", label: "冻结" },
                    { slot: "oper", label: "操作" }
                ],
                data: [
                    {
                        account: "111111",
                        name: "模拟管理员",
                        lock: "否"
                    }
                ],
                loading: false
            },
            addDiaFlag: false,
            auth: {
                flag: false,
                account: null
            }
        };
    },
    mounted() {
        this.getAdminList();
    },
    methods: {
        async getAdminList() {
            this.table.loading = true;
            try {
                let res = await systemApi.getAdminList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getAdminList();
        },
        lock(row) {
            this.$confirm(
                "确定要将此账号冻结吗？冻结后该账号将无法登录",
                "提示",
                {
                    cancelButtonText: "取消",
                    confirmButtonText: "确定",
                    type: "warning"
                }
            ).then(async () => {
                try {
                    let res = await systemApi.lockAccount(row.account);
                    this.$message.success("冻结成功");
                    this.getAdminList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        unLock(row) {
            this.$confirm(
                "确定要解除冻结吗？解除后该账号将可以正常管理系统",
                "提示",
                {
                    cancelButtonText: "取消",
                    confirmButtonText: "确定",
                    type: "warning"
                }
            ).then(async () => {
                try {
                    let res = await systemApi.unLockAccount(row.account);
                    this.$message.success("解除冻结成功");
                    this.getAdminList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        authority(row) {
            this.auth.account = row.account;
            this.auth.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>