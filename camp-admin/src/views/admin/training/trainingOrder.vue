<template>
    <div>
        <h3>{{$route.query.name}}——订单</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="订单号">
                    <el-input v-model="form.orderNum" placeholder="订单号码"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="form.userName" placeholder="创建订单的用户名"></el-input>
                </el-form-item>
                <el-form-item label="用户ID">
                    <el-input v-model="form.userId" placeholder="创建订单的用户ID"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="getOrderList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button
                            v-if="row.status=='未支付'"
                            type="primary"
                            size="small"
                            @click="modifyPrice(row)"
                        >修改金额</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :pageSize="form.pageSize"
                :currentPage="form.currentPage"
                :total="form.total"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import trainingApi from "../../../api/admin/training";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                trainingId: this.$route.query.trainingId,
                orderNum: null,
                userId: null,
                userName: null,
                currentPage: 1,
                pageSize: 10,
                total: 100
            },
            table: {
                config: [
                    { prop: "orderNum", label: "订单号" },
                    { prop: "orderType", label: "订单类型" },
                    { prop: "builderId", label: "用户id" },
                    { prop: "builderName", label: "用户名" },
                    { prop: "buildTime", label: "创建时间" },
                    { prop: "price", label: "订单金额" },
                    { prop: "status", label: "订单状态" },
                    { slot: "oper", label: "操作", fixed: "right" }
                ],
                data: [
                    {
                        orderNum: "111",
                        orderType: "学员",
                        builderId: "123",
                        builderName: "用户名",
                        buildTime: "2020-10-10",
                        price: "111",
                        status: "未支付"
                    }
                ]
            }
        };
    },
    mounted() {
        this.getOrderList();
    },
    methods: {
        async getOrderList() {
            try {
                let res = await trainingApi.getTrainingOrderList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        modifyPrice(row) {
            this.$prompt("请输入目标金额", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                inputPattern: /^[1-9]\d*$|^\d+(\.\d+)?$/,
                inputErrorMessage: "请输入正确的金额",
                type: "info"
            }).then(async ({ value }) => {
                if (value === null) {
                    this.$message.error("请输入金额");
                    return false;
                }
                try {
                    let data = {
                        orderNum: row.orderNum,
                        price: Number(value)
                    };
                    console.log(value);
                    let res = await trainingApi.modifyTrainingOrderPrice(data);
                    this.$message.success("修改金额成功！");
                    this.getOrderList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getOrderList();
        }
    }
};
</script>
<style lang="scss" scoped>
::v-deep .el-message-box__content {
    ::v-deep .el-message-box__status {
        top: 20%;
    }
}
</style>