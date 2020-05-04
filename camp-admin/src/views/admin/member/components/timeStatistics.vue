<template>
    <div>
        <el-form :model="form" inline>
            <el-form-item label="成为会员时间范围">
                <el-date-picker
                    type="daterange"
                    v-model="form.becomeTime"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                ></el-date-picker>
            </el-form-item>
            <el-form-item label="会员结束时间范围">
                <el-date-picker
                    type="daterange"
                    v-model="form.endTime"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                ></el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" size="medium" @click="getMemberList">查询</el-button>
            </el-form-item>
        </el-form>
        <div v-if="tip" class="tip-container">
            <span>
                成为会员的时间在
                <span class="tip">{{getTimeText(form.becomeTime)}}</span>
            </span>，
            <span>
                会员最后期限在
                <span class="tip">{{getTimeText(form.endTime)}}</span>
            </span>
            <span>
                的会员共有：
                <span class="tip">{{form.total}}</span> 个
            </span>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading"></m-table>
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
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import cityApi from "../../../../assets/js/city";
import memberApi from "../../../../api/admin/member";
import event from "../../../../assets/js/eventBus";
import { formatDate } from "../../../../assets/js/util";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                becomeTime: [],
                endTime: [],
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "id", label: "会员ID", width: "150px" },
                    { prop: "name", label: "公司名称", width: "150px" },
                    { prop: "phone", label: "联系电话", width: "150px" },
                    { prop: "email", label: "邮箱", width: "150px" },
                    { prop: "enterData", label: "加入时间" },
                    { prop: "vip", label: "是否会员" },
                    { prop: "vipBegin", label: "会员开始时间", width: "150px" },
                    { prop: "vipEnd", label: "会员结束时间", width: "150px" },
                    { prop: "deadline", label: "会员是否快到期" },
                    { prop: "province", label: "省份" },
                    { prop: "city", label: "城市" },
                    { prop: "area", label: "区/县" },
                    { prop: "zone", label: "详细地址" }
                ],
                data: [],
                loading: false
            },
            provinceList: [],
            cityList: [],
            areaList: [],
            tip: true
        };
    },
    mounted() {
        event.$on("shijian", this.getMemberList);
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getMemberList();
        },
        async getMemberList() {
            this.table.loading = true;
            try {
                let par = { ...this.form };
                if (this.form.becomeTime) {
                    par.becomeTimeStart = formatDate(this.form.becomeTime[0]);
                    par.becomeTimeEnd = formatDate(this.form.becomeTime[1]);
                }
                if (this.form.endTime) {
                    par.endTimeStart = formatDate(this.form.endTime[0]);
                    par.endTimeEnd = formatDate(this.form.endTime[1]);
                }

                delete par.becomeTime;
                delete par.endTime;
                let res = await memberApi.getMemberList(par);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
                this.tip = true;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        getTimeText(dateArr) {
            let time1 = "未限制",
                time2 = "未限制";
            if (dateArr) {
                if (dateArr[0]) {
                    time1 = formatDate(dateArr[0]);
                }
                if (dateArr[1]) {
                    time2 = formatDate(dateArr[1]);
                }
            }

            return ` ${time1} 到 ${time2} `;
        }
    }
};
</script>
<style lang="scss" scoped>
.tip-container {
    color: rgb(96, 98, 102);
    margin: 10px auto 20px;
    .tip {
        color: rgb(64, 158, 255);
    }
}
</style>