<template>
    <div class="all-container">
        <m-table
            :data="tableData"
            :tableConfig="tableConfig"
            :loading="loading"
            :tableEvent="tableEvent"
        >
            <el-table-column fixed slot-scope="{params}" v-bind="params" align="center" slot="select" type="selection" width="55"></el-table-column>
            <el-table-column fixed="right" slot-scope="{params}" v-bind="params" align="center" slot="oper">
                <div slot-scope="{ row }">
                    <el-button size="small" type="primary" @click="deleteOneStudent(row)">解除挂靠</el-button>
                </div>
            </el-table-column>
        </m-table>
    </div>
</template>
<script>
import mTable from "./mTable.vue";
export default {
    components: {
        mTable
    },
    props:["tableData","tableConfig"],
    data() {
        return {
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
        },
        deleteOneStudent(row) {
            this.$emit("dele", row.idNum);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>