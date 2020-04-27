<template>
    <div>
        <m-table :data="table.data" :tableConfig="table.config" :tableAttr="table.attr" />
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
export default {
    props: {
        seatList: Array
    },
    components: {
        mTable
    },
    data() {
        return {
            table: {
                config: [
                    { prop: "name", label: "姓名" },
                    { prop: "phone", label: "电话号码" },
                    { prop: "applyNumber", label: "报名编号" },
                    { prop: "seatNumber", label: "座位编号" },
                    { prop: "applyTime", label: "报名时间" }
                ],
                data: this.seatList,
                attr: {
                    stripe: true
                }
            }
        };
    }
};
</script>
<style lang="scss" scoped>
</style>