<template>
    <div>
        <h3>已参与的投票</h3>
        <div class="divider"></div>
        <div class="form-item">
            <el-form :model="form" inline>
                <el-form-item label="名称">
                    <el-input v-model="form.name" placeholder="投票名字"></el-input>
                </el-form-item>
                <el-form-item label="投票结束">
                    <el-select v-model="form.isFinish" placeholder="是否结束">
                        <el-option label="是" :value="true"></el-option>
                        <el-option label="否" :value="false"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getHasVotedList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div class="table-container" v-loading="table.loading">
            <m-table :data="table.data" :tableConfig="table.config">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="analysis(row)">投票情况</el-button>
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
        <el-dialog title="投票详情" :visible.sync="dialog.flag" v-if="dialog.flag" width="40%">
            <vote-detail :voteId="dialog.voteId" @cancel="dialog.flag = false" />
        </el-dialog>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import mTable from "../../../components/mTable.vue";
import voteDetail from "./components/voteDetail.vue";
import voteApi from "../../../api/modules/vote";
export default {
    components: {
        page,
        mTable,
        voteDetail
    },
    data() {
        return {
            form: {
                name: null,
                isFinish: null,
                total: 100,
                currentPage: 1,
                pageSize: 10
            },
            table: {
                config: [
                    { prop: "id", label: "投票序号" },
                    { prop: "name", label: "投票名称" },
                    { prop: "type", label: "投票类型" },
                    { prop: "time", label: "截止时间" },
                    { slot: "oper", label: "操作" }
                ],
                data: [
                    // {
                    //     id: "1",
                    //     name: "模拟投票",
                    //     type: "多选",
                    //     time: "2016-10-12 16:00:00"
                    // }
                ],
                loading: false
            },
            dialog: {
                flag: false,
                voteId: null
            }
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.getHasVotedList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getHasVotedList();
        },
        async getHasVotedList() {
            try {
                let res = null;
                if (this.idType == 0) {
                    res = await voteApi.getStudentHasVotedList(this.form);
                } else {
                    res = await voteApi.getMemberHasVotedList(this.form);
                }
                this.table.data = res.data.list;
                this.form.total = res.data.total;
            } catch (e) {
                this.$message.error(e.message);
            }
        },
        analysis(row) {
            this.dialog.flag = true;
            this.dialog.voteId = row.id;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>