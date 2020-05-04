<template>
    <div>
        <div v-if="tip" class="tip-container">
            <p>
                会费即将到期的会员有：
                <span>{{form.total}}</span> 个
            </p>
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
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                deadline: 1,
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
        event.$on("huifei", this.getMemberList);
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getMemberList();
        },
        async getMemberList() {
            this.table.loading = true;
            try {
                let res = await memberApi.getMemberList(this.form);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
                this.tip = true;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.tip-container {
    color: rgb(96, 98, 102);
    margin: 10px auto 20px;
    span {
        color: rgb(64, 158, 255);
    }
}
</style>