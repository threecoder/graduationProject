<template>
    <div>
        <h3>我的证书</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="证书名称">
                    <el-input v-model="form.name" placeholder="要搜索的证书名称" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属培训">
                    <el-input v-model="form.training" placeholder="证书对应的培训" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="medium" @click="getCertificateList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}" v-if="type">
                        <el-button size="mini" type="primary" @click="show(row)">查看</el-button>
                        <el-button size="mini" type="primary" @click="DLCer(row)">下载</el-button>
                        <el-button size="mini" type="primary" @click="modify(row)">修改</el-button>
                        <el-button
                            v-if="row.isEndding"
                            size="mini"
                            type="primary"
                            @click="examine(row)"
                        >复审</el-button>
                    </div>
                    <div slot-scope="{row}" v-else>
                        <el-button
                            v-if="row.canRecheck"
                            size="mini"
                            type="primary"
                            @click="examine(row)"
                        >复审</el-button>
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

        <!-- 学员修改证书弹窗 -->
        <el-dialog title="修改证书" :visible.sync="cerInfo.flag" v-if="cerInfo.flag" append-to-body>
            <modify-cer :cerId="cerInfo.cerId" @cancel="cerInfo.flag=false" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import modifyCer from "./components/modifyCertificate.vue";
import cerApi from "../../../api/modules/certificate";
export default {
    props: ["type"],
    components: {
        mTable,
        page,
        modifyCer
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                name: null,
                training: null
            },
            table: {
                config: [
                    { prop: "name", label: "证书名称" },
                    { prop: "trainingName", label: "所属培训" },
                    { prop: "memberName", label: "所属组织" },
                    { prop: "effectiveLength", label: "有效时长" },
                    { prop: "beginTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" },
                    {
                        slot: "oper",
                        label: "操作",
                        width: "350px",
                        fixed: "right"
                    }
                ],
                data: [
                    {
                        cerId: "111",
                        name: "模拟证书",
                        trainingName: "模拟培训",
                        effectiveLength: "365天",
                        beginTime: "2020-02-02 20:02",
                        endTime: "2021-02-02 20:20",
                        isEndding: false
                    }
                ],
                loading: false
            },
            cerInfo: {
                flag: false,
                idNum: this.idNum,
                cerId: null
            }
        };
    },
    mounted() {
        this.getCertificateList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getCertificateList();
        },
        async getCertificateList() {
            try {
                let res = null;
                if (this.type) {
                    res = await cerApi.getCertificateList(this.form);
                } else {
                    res = await cerApi.getOverdueCerList(this.form);
                }
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        show(row) {
            window.open("/", "", "", false);
        },
        DLCer(row) {},
        modify(row) {
            this.cerInfo.cerId = row.cerId;
            this.cerInfo.flag = true;
        },
        examine(row) {
            this.$confirm(
                "确定要发起证书复审吗？需要进行缴费并等待管理员审核",
                "提示",
                {
                    cancelButtonText: "取消",
                    confirmButtonText: "确定",
                    type: "warning"
                }
            ).then(async () => {
                try {
                    let res = await cerApi.examineCertificate(row.cerId);
                    this.$alert(
                        "已创建复审订单，请到订单页面中进行支付，付款后管理员才能进行审核",
                        "提示",
                        {
                            type: "warning",
                            confirmButtonText: "我知道了"
                        }
                    );
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>