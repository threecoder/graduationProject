<template>
    <div>
        <h3>发放证书</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="学员名字">
                    <el-input v-model="form.userName" placeholder="学员名字" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属考试">
                    <el-input v-model="form.examName" placeholder="成绩记录所属的考试" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属培训">
                    <el-input v-model="form.trainingName" placeholder="成绩记录所属的培训" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getPassGradeCheckLIst" type="primary" size="medium">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="mini" @click="grant(row)">发放证书</el-button>
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
import cerApi from "../../../api/admin/certificate";
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
                userName: null,
                trainingName: null,
                examName: null
            },
            table: {
                config: [
                    { prop: "userName", label: "学员姓名" },
                    { prop: "memberName", label: "所属会员" },
                    { prop: "trainingName", label: "培训名称" },
                    { prop: "examName", label: "考试名称" },
                    { prop: "grade", label: "考试成绩" },
                    { prop: "passTime", label: "通过审核时间" },
                    { slot: "oper", label: "操作", fixed: "right" }
                ],
                data: [
                    {
                        idNum: "!11",
                        trainingId: 22112
                    }
                ],
                loading: false
            }
        };
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getPassGradeCheckLIst();
        },
        async getPassGradeCheckLIst() {
            this.table.loading = true;
            try {
                let res = await cerApi.getPassGradeCheckLIst(this.form);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        grant(row) {
            this.$confirm("发放证书将马上开始证书有效时间计时", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let data = {
                        idNum: row.idNum,
                        trainingId: row.trainingId
                    };
                    let res = await cerApi.grantCer(data);
                    this.$message.success("发放证书成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>