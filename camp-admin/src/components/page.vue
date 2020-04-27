<template>
    <el-col :span="24" class="mt30">
        <el-pagination
            background
            layout="total, prev, pager, next"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="total"
            :hide-on-single-page="true"
        ></el-pagination>
    </el-col>
</template>
<script>
export default {
    data() {
        return {};
    },
    props: ["total", "pageSize", "currentPage"],
    methods: {
        handleSizeChange(val) {
            
        },
        handleCurrentChange(val) {
            this.$emit("curChange", val, this.pageSize);
        }
    }
};
</script>
<style lang="scss" scoped>
::v-deep .el-pagination {
    display: flex;
    flex-direction: row;
    justify-content: center;
}
</style>