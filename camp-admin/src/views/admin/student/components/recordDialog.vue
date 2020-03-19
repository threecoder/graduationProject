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
import studentApi from "../../../../api/admin/student";
export default {
    props: {
        idNum: {
            type: String,
            required: true
        },
        type: {
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
                config: [],
                data: []
            }
        };
    },
    mounted() {
        this.init();
        this.getData();
    },
    methods: {
        init() {
            //（报名时间、培训的参与状态（完成、参与中、未通过考试））
            //查看已获得的证件以及证件到期时间
            if (this.type == "training") {
                this.table.config = [
                    { prop: "id", label: "培训序号" },
                    { prop: "name", label: "培训名称" },
                    { prop: "startTime", label: "开始时间" },
                    { prop: "endTime", label: "结束时间" },
                    { prop: "address", label: "培训地点" },
                    { prop: "status", label: "状态" }
                ];
                // this.table.data = [
                //     {
                //         id: "123",
                //         name: "培训名",
                //         startTime: "2020-02-02 18:00:11",
                //         endTime: "2020-02-02 18:00:11",
                //         address: "广东省广州市番禺区小谷围街道",
                //         status: "完成"
                //     }
                // ];
            } else {
                this.table.config = [
                    { prop: "id", label: "证书序号" },
                    { prop: "name", label: "证书名称" },
                    { prop: "training", label: "所属培训" },
                    { prop: "deadline", label: "到期时间" }
                ];
            }
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getTrainingHistory();
        },
        async getData() {
            try {
                let res = null;
                let par = { ...this.form };
                par.idNum = this.idNum;
                if (this.type == "training") {
                    res = await studentApi.getStudentTrainingHistory(par);
                } else {
                    res = await studentApi.getStudentCertificate(par);
                }
                this.table.data = res.data.data;
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