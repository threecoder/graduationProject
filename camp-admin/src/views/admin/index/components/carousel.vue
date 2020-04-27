<template>
    <div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary" size="mini" @click="remove(row)">去除</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="tac mt30">
            <el-button type="primary" @click="add">添加</el-button>
        </div>
    </div>
</template>

<script>
import mTable from "../../../../components/mTable.vue";
import indexApi from "../../../../api/admin/index";
export default {
    components: {
        mTable
    },
    data() {
        return {
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
            }
        };
    },
    mounted() {
        this.getList();
    },
    methods: {
        async getList() {
            this.table.loading = true;
            try {
                let res = await indexApi.getCarouselList();
                this.table.data = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        add() {
            this.$prompt("请输入要加入轮播图的动态的序号", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定"
            }).then(async ({value}) => {
                try {
                    let res = await indexApi.addCarousel(value);
                    this.$message.success("操作成功");
                    this.getList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        remove(row) {
            this.$confirm("确定将该条数据从轮播图中撤下吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await indexApi.removeCarousel(row.id);
                    this.$message.success("操作成功");
                    this.getList();
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        }
    }
};
</script>

<style>
</style>