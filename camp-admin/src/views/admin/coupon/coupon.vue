<template>
    <div>
        <el-row>
            <el-col :span="18">
                <h3>现有的优惠券</h3>
            </el-col>
            <el-col :offset="4" :span="2">
                <el-button type="primary" @click="newCoupon.flag=true">添加优惠券</el-button>
            </el-col>
        </el-row>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="searchForm">
                <el-form-item label="优惠券名称">
                    <el-input v-model="searchForm.name" clearable placeholder="优惠券名称"></el-input>
                </el-form-item>
                <el-form-item label="优惠券价值">
                    <el-input v-model="searchForm.value" placeholder="请输入优惠券价值"></el-input>
                </el-form-item>
                <el-form-item label="可用范围（开始）">
                    <el-date-picker
                        v-model="searchForm.startTime"
                        clearable
                        type="date"
                        placeholder="范围开始时间"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item label="可用范围（结束）">
                    <el-date-picker
                        v-model="searchForm.endTime"
                        clearable
                        type="date"
                        placeholder="范围结束时间"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getCouponList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}" v-if="isOverdue(row)">
                        <el-button size="small" type="primary" @click="grant(row)">发放</el-button>
                        <el-button size="small" type="primary" @click="deleteCoupon(row)">删除</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                @curChange="curChange"
                :currentPage.sync="searchForm.currentPage"
                :total="searchForm.total"
                :pageSize="searchForm.pageSize"
            />
        </div>

        <!-- 发放优惠券 -->
        <el-dialog
            v-if="detailDialog.flag"
            :visible.sync="detailDialog.flag"
            :title="detailDialog.title"
            :close-on-click-modal="false"
            width="60%"
        >
            <grant :couponId="detailDialog.couponId" />
        </el-dialog>

        <!-- 添加优惠券 -->
        <el-dialog
            v-if="newCoupon.flag"
            :visible.sync="newCoupon.flag"
            title="添加优惠券"
            :close-on-click-modal="false"
            width="50%"
        >
            <add-coupon @close="newCoupon.flag=false;getCouponList()" />
        </el-dialog>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import mTable from "../../../components/mTable.vue";
import grant from "./components/grant.vue";
import addCoupon from "./components/addCoupon.vue";
import { formatDateAndTime } from "../../../assets/js/util";
import couponApi from "../../../api/admin/coupon";
export default {
    components: {
        page,
        mTable,
        grant,
        addCoupon
    },
    data() {
        return {
            searchForm: {
                name: null,
                value: null,
                startTime: null,
                endTime: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "name", label: "名称" },
                    { prop: "buildTime", label: "创建时间" },
                    { prop: "startTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "value", label: "价值" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300px"
                    }
                ],
                data: [
                    // {
                    //     id: 1,
                    //     name: "模拟数据",
                    //     startTime: "2020-03-05 21:55:00",
                    //     endTime: "2020-04-05 21:55:00",
                    //     value: "100元"
                    // }
                ],
                loading: false
            },
            detailDialog: {
                flag: false,
                title: "",
                couponId: null
            },
            newCoupon: {
                flag: false
            }
        };
    },
    mounted() {
        this.getCouponList();
    },
    methods: {
        async getCouponList() {
            let par = { ...this.searchForm };
            par.startTime = formatDateAndTime(par.startTime);
            par.endTime = formatDateAndTime(par.endTime);
            this.table.loading = true;
            try {
                let res = await couponApi.getCouponList(par);
                this.table.data = res.data.data;
                this.searchForm.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        isOverdue(row) {
            let targetTime = new Date(row.endTime);
            return new Date().getTime() - targetTime.getTime() < 0;
        },
        curChange(newVal) {
            this.searchForm.currentPage = newVal;
            this.getCouponList();
        },
        grant(row) {
            console.log(row);
            this.detailDialog.couponId = row.id;
            this.detailDialog.flag = true;
            this.detailDialog.title = row.name;
        },
        deleteCoupon(row) {
            this.$confirm("确定删除该优惠券吗？删除后将无法恢复", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await couponApi.deleteCoupon(row.id);
                    this.$message.success("删除优惠券成功");
                    this.getCouponList();
                } catch (e) {
                    this.$message.error(e.message);
                }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>