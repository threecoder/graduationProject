<template>
    <div>
        <h3>已发布的培训</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="searchForm">
                <el-form-item label="培训名称">
                    <el-input v-model="searchForm.name" clearable placeholder="活动名称"></el-input>
                </el-form-item>
                <el-form-item label="开始时间">
                    <el-date-picker
                        v-model="searchForm.startTime"
                        clearable
                        type="date"
                        placeholder="选择开始日期"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间">
                    <el-date-picker
                        v-model="searchForm.endTime"
                        clearable
                        type="date"
                        placeholder="选择结束日期"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getPublishedTraining">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="trainingDetail(row)">查看 / 修改</el-button>
                        <el-button size="small" type="primary" @click="situation(row)">报名情况</el-button>
                        <el-button size="small" type="primary" @click="order(row)">订单</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                @curChange="curChange"
                :currentPage.sync="searchForm.currentPage"
                :total="searchForm.total"
                :pageSize="searchForm.pageSize"
            />
        </div>

        <el-dialog
            v-if="detailDialog.flag"
            :visible.sync="detailDialog.flag"
            :title="detailDialog.title"
            width="50%"
        >
            <training-detail :trainingInfo="detailDialog.info" @cancel="detailDialog.flag=false" />
        </el-dialog>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import mTable from "../../../components/mTable.vue";
import trainingDetail from "./components/trainingDetail.vue";
import { formatDateAndTime } from "../../../assets/js/util";
import trainingApi from "../../../api/admin/training";
export default {
    components: {
        page,
        mTable,
        trainingDetail
    },
    data() {
        return {
            searchForm: {
                name: null,
                startTime: null,
                endTime: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "id", label: "培训序号" },
                    { prop: "name", label: "培训名称" },
                    { prop: "startTime", label: "开始时间" },
                    { prop: "endTime", label: "结束时间" },
                    { prop: "address", label: "培训地点" },
                    { prop: "fee", label: "普通价格" },
                    { prop: "vipFee", label: "会员价格" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300px"
                    }
                ],
                data: [
                    {
                        id: 1,
                        name: "模拟数据",
                        startTime: "2020-03-05 21:55:00",
                        endTime: "2020-03-05 21:55:00",
                        address: "小谷围街道",
                        fee: 100,
                        vipFee: 90,
                        level: "困难",
                        phone: "121212",
                        contacts: "联系人",
                        introduction: ["第一段", "第二段"]
                    }
                ],
                loading: false
            },
            detailDialog: {
                flag: false,
                title: "",
                info: {}
            }
        };
    },
    mounted() {
        this.getPublishedTraining();
    },
    methods: {
        async getPublishedTraining() {
            let par = { ...this.searchForm };
            par.startTime = formatDateAndTime(par.startTime);
            par.endTime = formatDateAndTime(par.endTime);
            this.table.loading = true;
            try {
                let res = await trainingApi.getPublishedTraining(par);
                this.table.data = res.data.data;
                this.searchForm.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        curChange(newVal) {
            this.searchForm.currentPage = newVal;
            this.getPublishedTraining();
            console.log(newVal);
        },
        trainingDetail(row) {
            this.detailDialog.info = row;
            this.detailDialog.flag = true;
            this.detailDialog.title = row.name;
        },
        situation(row) {
            let path = "/trainingEnrollSituation?trainingId=";
            path = path + row.id + "&name=" + row.name;
            this.$router.push(path);
        },
        order(row) {
            let path = "/trainingOrder?trainingId=";
            path = path + row.id + "&name=" + row.name;
            this.$router.push(path);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>