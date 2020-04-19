<template>
    <div>
        <el-row>
            <el-col :span="20">
                <h3>新闻与动态管理</h3>
            </el-col>
            <el-col :span="4">
                <el-button size="medium" type="primary" @click="publish">发布</el-button>
                <el-button size="medium" type="primary" @click="dialog.flag = true">轮播</el-button>
            </el-col>
        </el-row>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="标题">
                    <el-input v-model="form.title" placeholder="新闻标题" clearable></el-input>
                </el-form-item>
                <el-form-item label="发布日期">
                    <el-date-picker type="date" v-model="form.date" placeholder="新闻发布时间" clearable></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getList" type="primary" size="medium">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="mini" @click="modify(row)">修改</el-button>
                        <el-button type="primary" size="mini" @click="deleteDynamic(row)">删除</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>

        <!-- 设置轮播情况 -->
        <el-dialog :title="dialog.title" :visible.sync="dialog.flag" v-if="dialog.flag">
            <carousel @cancel="dialog.flag=false" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import carousel from "./components/carousel.vue";
import indexApi from "../../../api/admin/index";
export default {
    components: {
        mTable,
        page,
        carousel
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                title: null,
                date: null
            },
            table: {
                config: [
                    { prop: "id", label: "序号" },
                    { prop: "title", label: "标题" },
                    { prop: "type", label: "类型" },
                    { prop: "desc", label: "简述" },
                    { prop: "time", label: "发布时间" },
                    { slot: "oper", label: "操作", fixed: "right" }
                ],
                data: [
                    {
                        id: 111,
                        title: "深交所严密监控可转债交易 反复警示交易风险",
                        type: "新闻",
                        desc: "d深交所严密监控可转债交易 反复警示交易风险",
                        time: "2020-02-02 12:00"
                    }
                ],
                loading: false
            },
            dialog: {
                title: "设置轮播图",
                flag: false
            }
        };
    },
    mounted() {
        this.getList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getList();
        },
        async getList() {
            this.table.loading = true;
            try {
                let res = await indexApi.getNewsAndDynamicList(this.form);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        publish() {
            this.$router.push(`/dynamicEdit`);
        },
        modify(row) {
            this.$router.push(`/dynamicEdit?dynamicId=${row.id}`);
        },
        deleteDynamic(row) {
            this.$confirm("确定要删除此条数据吗？将无法恢复", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                this.table.loading = true;
                try {
                    let res = await indexApi.deleteDynamic(row.id);
                    this.$message.success("删除该记录成功");
                    this.getList();
                } catch (error) {
                    this.$message.error(error.message);
                }
                // this.table.loading = false;
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>