<template>
    <div>
        <h3>已发布的投票</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="form">
                <el-form-item label="投票名称">
                    <el-input v-model="form.name" placeholder="输入投票名称"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-select v-model="form.name" placeholder="选择投票类型">
                        <el-option :value="0" label="仅限会员投票"></el-option>
                        <el-option :value="1" label="仅限学员投票"></el-option>
                        <el-option :value="2" label="学员会员均可"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="getVoteList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <!-- <el-button type="primary" size="small" @click="detail(row)">详情</el-button> -->
                        <el-button type="primary" size="small" @click="statistic(row)">统计</el-button>
                        <el-button type="primary" size="small" @click="deleteVote(row)">删除</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
        <el-dialog title="投票详情" v-if="detailDialog.flag" :visible.sync="detailDialog.flag">
            <vote-detail :voteId="detailDialog.voteId" />
        </el-dialog>
        <el-dialog title="投票统计" v-if="statisticDialog.flag" :visible.sync="statisticDialog.flag">
            <vote-statistic :voteId="detailDialog.voteId" />
        </el-dialog>
    </div>
</template>
<script>
import voteApi from "../../../api/admin/vote";
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import voteDetail from "./components/voteDetail.vue";
import voteStatistic from "./components/voteStatistic.vue";
export default {
    components: {
        page,
        mTable,
        voteDetail,
        voteStatistic
    },
    data() {
        return {
            form: {
                currentPage: 1,
                pageSize: 10,
                total: 100,
                name: null,
                type: null
            },
            table: {
                loading: false,
                config: [
                    { prop: "id", label: "投票ID" },
                    { prop: "name", label: "投票名字" },
                    { prop: "type", label: "投票类型" },
                    { prop: "sum", label: "选项总数" },
                    { prop: "num", label: "最大可选数" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "230px"
                    }
                ],
                data: [
                    {
                        id: "111",
                        name: "模拟投票",
                        type: "学员投票",
                        sum: 10,
                        num: 2
                    }
                ]
            },
            detailDialog: {
                flag: false,
                voteId: null
            },
            statisticDialog: {
                flag: false,
                voteId: null
            }
        };
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getVoteList();
        },
        async getVoteList() {
            this.table.loading = true;
            try {
                let res = await voteApi.getVoteList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        statistic(row) {
            this.statisticDialog.voteId = row.id;
            this.statisticDialog.flag = true;
        },
        deleteVote(row) {
            this.$confirm("删除后将无法恢复投票数据", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await voteApi.deleteVote(row.id);
                    this.$message.success("删除投票成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        detail(row) {
            this.detailDialog.voteId = row.id;
            this.detailDialog.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>