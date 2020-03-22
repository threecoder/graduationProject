<template>
    <div>
        <h3>我的订单</h3>
        <div class="divider"></div>
        <div class="form-item">
            <el-form :model="form" inline>
                <el-form-item label="订单号">
                    <el-input v-model="form.orderNum" placeholder="订单号" clearable></el-input>
                </el-form-item>
                <el-form-item label="活动/培训名称">
                    <el-input v-model="form.name" placeholder="订单所属的培训/活动名称" clearable></el-input>
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
                            v-if="row.status=='未支付'"
                            size="small"
                            type="primary"
                            @click="pay(row)"
                        >支付</el-button>
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
import payment from "./components/payment.vue";
import orderAPi from "../../../api/modules/order";
export default {
    components: {
        mTable,
        page,
        payment
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                orderNum: null,
                name: null
            },
            table: {
                config: [
                    { prop: "orderNum", label: "订单号" },
                    { prop: "orderType", label: "订单类型" },
                    { prop: "name", label: "培训/活动名称" },
                    { prop: "businessType", label: "属培训/活动" },
                    { prop: "builder", label: "下单人" },
                    { prop: "buildTime", label: "创建时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "status", label: "状态" },
                    { prop: "price", label: "订单金额" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right"
                    }
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
            },
            payment: {
                flag: false,
                orderId: null
            }
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
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
                let res = await orderAPi.getOrderList(this.idType, this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        pay(row) {
            this.payment.orderId = row.orderNum;
            this.payment.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>