<template>
    <div class="coupon-container">
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="loading"></m-table>
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
import orderApi from "../../../../api/modules/order";
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
                total: 100
            },
            table: {
                config: [
                    { prop: "code", label: "优惠券券码" },
                    { prop: "name", label: "优惠券名称" },
                    { prop: "startTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" },
                    { prop: "value", label: "价值" }
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
            try {
                let res = await orderApi.getCouponList(this.form);
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
.coupon-container {
    overflow: hidden;
}
</style>