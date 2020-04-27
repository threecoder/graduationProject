<template>
    <div class="dialog-container">
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data"></m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSioze"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import relaApi from "../../../../api/modules/relationship";
export default {
    props: {
        idNum: {
            type: String,
            required: true
        }
    },
    components: {
        page,
        mTable
    },
    data() {
        return {
            form: {
                total: 10,
                pageSize: 10,
                currentPage: 1
            },
            table: {
                config: [
                    { prop: "id", label: "培训序号" },
                    { prop: "name", label: "培训名称" },
                    { prop: "startTime", label: "开始时间" },
                    { prop: "endTime", label: "结束时间" },
                    { prop: "address", label: "培训地点" },
                    { prop: "status", label: "状态" }
                ],
                data: []
            }
        };
    },
    mounted() {
        this.getTrainingHistory();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getTrainingHistory();
        },
        async getTrainingHistory() {
            try {
                let par = { ...this.form };
                par.idNum = this.idNum;
                let res = await relaApi.getStudentTrainingHistory(par);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
            } catch (e) {
                this.$message.error(e.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.dialog-container {
    overflow: hidden;
}
</style>