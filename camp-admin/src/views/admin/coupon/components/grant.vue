<template>
    <div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="会员名称">
                    <el-input placeholder="输入会员名称" v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="会员ID">
                    <el-input placeholder="输入会员ID" v-model="form.memberId" clearable></el-input>
                </el-form-item>
                <el-button type="primary" @click="getMemberList">搜索</el-button>
            </el-form>
        </div>
        <div class="table-container">
            <m-table
                :data="tableData"
                :tableConfig="tableConfig"
                :loading="loading"
                :tableEvent="tableEvent"
                :tableAttr="tableAttr"
            >
                <el-table-column
                    slot-scope="{params}"
                    v-bind="params"
                    align="center"
                    slot="select"
                    type="selection"
                    width="55"
                ></el-table-column>
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="small" @click="grantSingle(row)">发放</el-button>
                    </div>
                </el-table-column>
            </m-table>

            <page
                :pageSize="form.pageSize"
                :total="form.total"
                :currentPage="form.currentPage"
                @curChange="handleCurrentChange"
            />
        </div>
        <div class="tac mt30">
            <el-button type="primary" size="small" @click="grantAll">全部发放</el-button>
            <el-button type="primary" size="small" @click="grantMany">发放给选中会员</el-button>
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import couponApi from "../../../../api/admin/coupon";
export default {
    props: {
        couponId: String
    },
    components: {
        mTable,
        page
    },
    data() {
        return {
            tableData: [
                // {
                //     idNum: "111",
                //     name: "张三",
                //     member: "BB股份有限公司",
                //     grade: 100,
                //     times: 3,
                //     id: "123"
                // },
                // {
                //     idNum: "111",
                //     name: "张三",
                //     member: "BB股份有限公司",
                //     grade: 201,
                //     times: 3,
                //     id: "11"
                // }
            ],
            tableConfig: [
                { slot: "select", "reserve-selection": true },
                { prop: "id", label: "会员ID" },
                { prop: "name", label: "公司名称" },
                { prop: "phone", label: "联系电话" },
                { prop: "email", label: "邮箱" },
                { prop: "num", label: "拥有本券数量" },
                {
                    slot: "oper",
                    label: "操作",
                    fixed: "right",
                    width: "150px"
                }
            ],
            tableEvent: {
                "selection-change": this.handleSelectionChange
            },
            tableAttr: {
                "row-key": "id"
            },
            loading: false,
            form: {
                memberId: null,
                name: null,
                total: 100,
                pageSize: 10,
                currentPage: 1
            },
            selected: []
        };
    },
    mounted() {
        this.getMemberList();
    },
    methods: {
        async getMemberList() {
            this.loading = true;
            try {
                let par = { ...this.form };
                par.couponId = this.couponId;
                let res = await couponApi.getMemberList(par);
                this.tableData = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        grantSingle(row) {
            this.$confirm(
                "确定给该用户发放优惠券吗？现有" + row.num + "张",
                "提示",
                {
                    cancelButtonText: "取消",
                    confirmButtonText: "确定",
                    type: "warning"
                }
            ).then(async () => {
                try {
                    let data = {
                        couponId: this.couponId,
                        memberIds: [row.id]
                    };
                    let res = await couponApi.grantCoupon(data);
                    this.$message.success("发放成功");
                    this.getMemberList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        grantMany() {
            if (this.selected.length === 0) {
                this.$message.error("请选择要发放优惠券的会员");
                return false;
            }
            this.$confirm("确定发放优惠券给选中的成员吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let memberIds = this.selected.map(val => val.id);
                    let data = {
                        couponId: this.couponId,
                        memberIds
                    };
                    let res = await couponApi.grantCoupon(data);
                    this.$message.success("发放成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        grantAll() {
            this.$confirm("确定发放优惠券给全部成员吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await couponApi.allGrantCoupon(this.couponId);
                    this.$message.success("发放成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        handleSelectionChange(val) {
            this.selected = val;
            console.log(val);
        },
        handleCurrentChange(val) {
            this.form.currentPage = val;
            this.getMemberList();
        }
    }
};
</script>
<style lang="scss" scoped>
.table-container {
    overflow: hidden;
}
</style>