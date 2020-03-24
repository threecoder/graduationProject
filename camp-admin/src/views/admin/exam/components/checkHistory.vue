<template>
    <div class="history-container">
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="学员名字">
                    <el-input placeholder="输入学员名字" v-model="form.studentName" clearable></el-input>
                </el-form-item>
                <el-form-item label="审查员">
                    <el-input placeholder="审查员名字" v-model="form.checker" clearable></el-input>
                </el-form-item>
                <el-form-item label="是否通过">
                    <el-select v-model="form.isPass" clearable>
                        <el-option value="0" label="否"></el-option>
                        <el-option value="1" label="是"></el-option>
                    </el-select>
                </el-form-item>
                <el-button type="primary" @click="getRecordList">搜索</el-button>
            </el-form>
        </div>
        <div class="table-container" v-loading="table.loading">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading"></m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import page from "../../../../components/page.vue";
import mTable from "../../../../components/mTable.vue";
import examApi from "../../../../api/admin/exam";
export default {
    props: {
        examId: {
            require: true,
            type: String
        }
    },
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
                checker: null,
                studentName: null,
                isPass: null
            },
            table: {
                config: [
                    { prop: "name", label: "学员姓名" },
                    { prop: "idNum", label: "学员ID" },
                    { prop: "member", label: "所属会员" },
                    { prop: "grade", label: "分数" },
                    { prop: "checker", label: "审核人" },
                    { prop: "pass", label: "是否通过" },
                    { prop: "tip", label: "不通过理由" },
                    { prop: "checkTime", label: "审核时间" }
                ],
                data: [
                    {
                        orderNum: "20191023111733112724",
                        orderType: "学员订单",
                        name: "英语角",
                        businessType: "培训",
                        builder: "张三",
                        buildTime: "2020-02-02 20:20:30",
                        endTime: "2020-03-02 20:20:20",
                        price: "9500",
                        status: "未支付"
                    }
                ],
                loading: false
            }
        };
    },
    mounted() {
        this.getRecordList();
    },
    methods: {
        async getRecordList() {
            try {
                let par = { ...this.form };
                par.examId = this.examId;
                let res = await examApi.getCheckRecordList(par);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.history-container {
    overflow: hidden;
}
</style>