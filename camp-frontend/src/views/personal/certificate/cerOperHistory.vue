<template>
    <div>
        <h3>证书操作记录</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="证书名字">
                    <el-input v-model="form.cerName" placeholder="操作的目标证书名字" clearable></el-input>
                </el-form-item>
                <el-form-item label="操作人名字">
                    <el-input v-model="form.userName" placeholder="操作人的名字" clearable></el-input>
                </el-form-item>
                <el-form-item label="操作类型">
                    <el-select v-model="form.type" placeholder="复审或者修改操作">
                        <el-option label="复审" value="recheck"></el-option>
                        <el-option label="修改" value="modify"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getOperList" type="primary" size="medium">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="mini"></el-button>
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
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import cerApi from "../../../api/modules/certificate";
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
                cerName: null,
                userName: null,
                type: "recheck"
            },
            table: {
                config: [
                    { prop: "userName", label: "操作人" },
                    { prop: "userType", label: "操作人身份" },
                    { prop: "cerName", label: "对应证书" },
                    { prop: "desc", label: "操作描述" },
                    { prop: "operTime", label: "时间" },
                    { prop: "success", label: "是否成功" }
                ],
                data: [],
                loading: false
            }
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.getOperList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getOperList();
        },
        async getOperList() {
            try {
                let res = await cerApi.getOperList(this.idType, this.form);
                this.table.data = res.data.list;
                this.form.total = res.data.data;
                this.table.data.forEach(val => {
                    val.userType = val.userType == "student" ? "学员" : "会员";
                    val.success =
                        val.success == "success"
                            ? "通过"
                            : val.success == "fail"
                            ? "失败"
                            : "未处理";
                });
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
</style>