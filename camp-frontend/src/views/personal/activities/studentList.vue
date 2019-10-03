<template>
    <div class="all-container">
        <m-table
            :data="tableData"
            :tableConfig="tableConfig"
            :loading="loading"
            :tableEvent="tableEvent"
        >
            <el-table-column fixed slot-scope="{params}" v-bind="params" align="center" slot="select" type="selection" width="55"></el-table-column>
        </m-table>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
export default {
    components: {
        mTable
    },
    props:["tableData"],
    data() {
        return {
            tableConfig: [
                { slot: "select"},
                { prop: "name", label: "姓名" },
                { prop: "idNum", label: "身份证号码" },
                { prop: "phone", label: "手机号码" },
                { prop: "position", label: "职务" }
            ],
            tableEvent: {
                "selection-change":this.handleSelectionChange
            },
            loading: false
        };
    },
    mounted(){

    },
    methods: {
        handleSelectionChange(val){
            this.$emit("selectChange",val);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>