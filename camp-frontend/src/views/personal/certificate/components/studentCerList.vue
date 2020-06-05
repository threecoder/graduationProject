<template>
    <div class="studentCerList-container">
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="证书名称">
                    <el-input v-model="form.cerName" placeholder="要搜索的证书名称" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属培训">
                    <el-input v-model="form.trainingName" placeholder="证书对应的培训" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="medium" @click="getStudentCerList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
                <el-table-column slot="oper" slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button size="mini" type="primary" @click="modify(row)">修改</el-button>
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

        <!-- 会员修改证书弹窗 -->
        <el-dialog
            title="修改证书"
            :visible.sync="cerInfo.flag"
            v-if="cerInfo.flag"
            append-to-body
            top="10%"
        >
            <modify-cer
                :idNum="cerInfo.idNum"
                :cerId="cerInfo.cerId"
                type="member"
                @cancel="cerInfo.flag=false"
            />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import modifyCer from "./modifyCertificate.vue";
import cerApi from "../../../../api/modules/certificate";
export default {
    props: {
        idNum: String
    },
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
                cerName: null,
                trainingName: null
            },
            table: {
                config: [
                    { prop: "cerName", label: "证书名称" },
                    { prop: "trainingName", label: "所属培训" },
                    { prop: "memberName", label: "所属组织" },
                    { prop: "effectiveLength", label: "有效时长" },
                    { prop: "beginTime", label: "生效时间" },
                    { prop: "endTime", label: "失效时间" }
                    // {
                    //     slot: "oper",
                    //     label: "操作",
                    //     fixed: "right"
                    // }
                ],
                data: [
                    {
                        cerId: "111",
                        cerName: "模拟证书",
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
        this.getStudentCerList();
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getStudentCerList();
        },
        async getStudentCerList() {
            try {
                let res = await cerApi.getStudentCerList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        modify(row) {
            this.cerInfo.cerId = row.cerId;
            this.cerInfo.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
.studentCerList-container {
    overflow: hidden;
}
</style>