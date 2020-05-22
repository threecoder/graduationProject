<template>
    <div>
        <el-row>
            <el-col :span="22">
                <h3>我的订单</h3>
            </el-col>
            <el-col :span="2">
                <el-button v-if="idType==1" type="primary" size="medium" @click="couponList">我的优惠券</el-button>
            </el-col>
        </el-row>
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
                    <el-input v-model="form.businessName" placeholder="订单所属的培训/活动/证书名称" clearable></el-input>
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
                            v-if="row.status=='未支付' && idType==0"
                            size="small"
                            type="primary"
                            @click="studentPay(row)"
                        >支付</el-button>
                        <el-button
                            v-if="row.status=='未支付' && idType==1"
                            size="small"
                            type="primary"
                            @click="beforePay(row)"
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

        <el-dialog title="请选择优惠券" :visible.sync="payment.flag" v-if="payment.flag">
            <payment :orderId="payment.orderId" @cancel="payment.flag=false" @fresh="getOrderList" />
        </el-dialog>

        <el-dialog title="我的优惠券" :visible.sync="couponFlag" v-if="couponFlag">
            <coupon-list />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import payment from "./components/payment.vue";
import couponList from "./components/couponList.vue";
import orderApi from "../../../api/modules/order";
import { orderTypeList } from "../../../const";
export default {
    components: {
        mTable,
        page,
        payment,
        couponList
    },
    data() {
        let tArr = [...orderTypeList];
        if (this.$store.getters.idType == 0) {
            tArr.forEach((val, i) => {
                if (val.key == "member") {
                    tArr.splice(i, 1);
                }
            });
        }
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                orderNum: null,
                businessName: null,
                type: "activity"
            },
            table: {
                config: [
                    { prop: "orderNum", label: "订单号" },
                    { prop: "orderType", label: "订单类型" },
                    { prop: "businessName", label: "培训/活动名称" },
                    { prop: "builder", label: "下单人" },
                    { prop: "buildTime", label: "创建时间" },
                    { prop: "payTime", label: "支付时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "status", label: "状态" },
                    { prop: "price", label: "订单金额" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right"
                    }
                ],
                data: [],
                loading: false
            },
            payment: {
                flag: false,
                orderId: null
            },
            couponFlag: false,
            orderTypeList: tArr
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
        couponList() {
            this.couponFlag = true;
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getOrderList();
        },
        async getOrderList() {
            this.table.loading = true;
            try {
                let res = await orderApi.getOrderList(this.idType, this.form);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
                let typeStr = "";
                for(let i = 0; i<orderTypeList.length; i++ ){
                    if(this.form.type == orderTypeList[i].key) {
                        typeStr = orderTypeList[i].label;
                        break;
                    }
                }
                this.table.data.forEach(val=> {
                    val.orderType = typeStr;
                })
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        async studentPay(row) {
            let data = {
                orderId: row.orderNum,
                returnUrl: "/#/order",
                serverPort: "8888"
            };
            try {
                let res = await orderApi.aliPay(this.idType, data);
                this.$_putForm(res.data);
                console.log("支付返回", res);
            } catch (error) {
                this.$message.error(error.message);
            }
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
        beforePay(row) {
            this.payment.orderId = row.orderNum;
            this.payment.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>