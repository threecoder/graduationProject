<template>
    <div class="coupon-container">
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="small" @click="pay(row)">使用</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container" style="overflow:hidden">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
        <div class="tac mt30">
            <el-button size="medium" @click="cancel">取消</el-button>
            <el-button size="medium" type="primary" @click="fullPay">直接支付</el-button>
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import orderApi from "../../../../api/modules/order";
export default {
    components: {
        mTable,
        page
    },
    props: {
        orderId: {
            required: true,
            type: String
        }
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "code", label: "优惠券券码" },
                    { prop: "name", label: "优惠券名称" },
                    { prop: "startTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "value", label: "价值" },
                    { slot: "oper", label: "操作" }
                ],
                data: [],
                loading: false
            }
        };
    },
    mounted() {
        this.getCouponList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getCouponList();
        },
        async getCouponList() {
            this.table.loading = true;
            try {
                let res = await orderApi.getCouponList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        async pay(row) {
            let data = {
                orderId: this.orderId,
                returnUrl: "/order",
                serverPort: "8888",
                couponCode: row.code
            };
            try {
                let res = await orderApi.aliPay(1, data);
                this.$_putForm(res.data);
                console.log("支付返回", res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        fullPay() {
            this.$confirm("确定不适用优惠券吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                let data = {
                    orderId: this.orderId,
                    returnUrl: "/order",
                    serverPort: "8888"
                };
                try {
                    let res = await orderApi.aliPay(1, data);
                    this.$_putForm(res.data);
                    console.log("支付返回", res);
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        $_putForm(str) {
            let div = document.createElement("form-container");
            div.innerHTML = str;
            let divs = document.getElementsByTagName("form-container");
            if (divs.length != 0) {
                document.body.removeChild(divs[0]);
            }
            document.body.appendChild(div);
            let myForm = document.forms[document.forms.length - 1];
            myForm.setAttribute("target", "_blank"); // 新开窗口跳转
            myForm.submit();
        },
        cancel() {
            this.$emit("cancel");
        }
    }
};
</script>
<style lang="scss" scoped>
.coupon-container {
    overflow: hidden;
}
</style>