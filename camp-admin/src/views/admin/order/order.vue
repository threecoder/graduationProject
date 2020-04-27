<template>
    <div>
        <h3>订单管理</h3>
        <div class="divider"></div>
        <div class="form-item">
            <el-form :model="form" inline>
                <el-form-item label="订单号">
                    <el-input v-model="form.orderNum" placeholder="订单号" clearable></el-input>
                </el-form-item>
                <el-form-item label="订单类别">
                    <el-select v-model="form.type" placeholder="订单类别">
                        <el-option
                            v-for="(item,i) in orderTypeList"
                            :key="i"
                            :value="item.key"
                            :label="item.label"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="关联名称">
                    <el-input v-model="form.businessName" placeholder="订单所属的培训/活动/证书等名称" clearable></el-input>
                </el-form-item>
                <el-form-item label="下单人/单位">
                    <el-input v-model="form.name" placeholder="下单人/单位" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getOrderList" :loading="table.loading">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div class="table-container" v-loading="table.loading">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button
                            type="primary"
                            size="small"
                            v-if="row.status=='未支付'"
                            @click="modify(row)"
                        >修改金额</el-button>
                        <el-button
                            type="primary"
                            size="small"
                            v-if="row.status=='未支付'"
                            @click="confirmPay(row)"
                        >确认支付</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>

        <el-dialog title="订单支付" :visible.sync="payment.flag" v-if="payment.flag">
            <payment :orderId="payment.orderId" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import orderAPi from "../../../api/admin/order";
import { orderTypeList } from "../../../const";
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
                businessName: null,
                name: null,
                orderNum: null,
                type: "activity"
            },
            table: {
                config: [
                    { prop: "orderNum", label: "订单号" },
                    { prop: "orderType", label: "订单类型" },
                    { prop: "businessName", label: "关联名称" },
                    { prop: "businessType", label: "属培训/活动" },
                    { prop: "builder", label: "下单人" },
                    { prop: "buildTime", label: "创建时间" },
                    { prop: "startTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "status", label: "状态" },
                    { prop: "price", label: "订单金额" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "220px"
                    }
                ],
                data: [
                    {
                        orderNum: "20191023111733112724",
                        orderType: "学员订单",
                        type: "新闻",
                        businessName: "英语角",
                        businessType: "培训",
                        builder: "张三",
                        buildTime: "2020-02-02 20:20:30",
                        endTime: "2020-03-02 20:20:20",
                        price: "9500",
                        status: "未支付"
                    }
                ],
                loading: false
            },
            payment: {
                flag: false,
                orderId: null
            },
            orderTypeList
        };
    },
    mounted() {
        this.getOrderList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getOrderList();
        },
        async getOrderList() {
            this.table.loading = true;
            try {
                let res = await orderAPi.getOrderList();
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        modify(row) {
            this.$prompt("请输入新的订单金额", "提示", {
                type: "warning",
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                inputPattern: /^[1-9]\d*$|^\d+(\.\d+)?$/,
                inputErrorMessage: "请输入正确的金额"
            }).then(async ({ value }) => {
                try {
                    let data = {
                        orderNum: row.orderNum,
                        price: Number(value)
                    };
                    let res = await orderAPi.modifyOrderPrice(data);
                    this.$message.success("修改订单金额成功");
                    this.getOrderList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        confirmPay(row) {
            this.$confirm("确定将订单状态修改为已支付吗？将无法恢复", "提示", {
                type: "warning",
                cancelButtonText: "取消",
                confirmButtonText: "确定"
            }).then(async () => {
                try {
                    let res = await orderAPi.confirmPay(row.orderNum);
                    this.$message.success("修改订单状态为已支付");
                    this.getOrderList();
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