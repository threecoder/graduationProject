<template>
    <div>
        <h3>学员列表</h3>
        <div class="divider"></div>

        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="手机号码" label-position="left">
                    <el-input placeholder="输入手机号码" v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="身份证号码" label-position="top">
                    <el-input placeholder="输入身份证号码" v-model="form.idNum"></el-input>
                </el-form-item>
                <el-form-item label="姓名" label-position="top">
                    <el-input placeholder="输入姓名" v-model="form.name"></el-input>
                </el-form-item>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="tableData" :tableConfig="tableConfig" :loading="loading">
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button size="small" type="primary" @click="showCertificate(row)">查看证书</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div>
            <page
                @curChange="curChange"
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
            />
        </div>

        <!-- 学员证书列表弹框弹框 -->
        <el-dialog
            title="现有证书"
            :visible.sync="cerList.flag"
            v-if="cerList.flag"
            width="70%"
            :close-on-click-modal="false"
            :append-to-body="true"
        >
            <cer-list :idNum="cerList.idNum" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import relaApi from "../../../api/modules/relationship";
import cerList from "./components/studentCerList.vue";
import { download } from "../../../api/request";
export default {
    components: {
        mTable,
        page,
        cerList
    },
    data() {
        return {
            form: {
                phone: null,
                idNum: null,
                name: null,
                currentPage: 1,
                total: 100,
                pageSize: 10
            },
            tableData: [
                {
                    idNum: 22222222222,
                    phone: 13534625262,
                    name: "张三",
                    email: "101@qq.com",
                    position: "经理",
                    province: "广东省",
                    city: "广州市",
                    area: "番禺区",
                    zone: "小谷围街道华南理工大学"
                }
            ],
            tableConfig: [
                { prop: "idNum", label: "身份证号码" },
                { prop: "phone", label: "手机号码" },
                { prop: "name", label: "姓名" },
                { prop: "email", label: "邮箱" },
                { prop: "position", label: "职位" },
                { prop: "province", label: "省份" },
                { prop: "city", label: "城市" },
                { prop: "area", label: "区/县" },
                { prop: "zone", label: "详细地址" },
                {
                    prop: "oper",
                    label: "操作",
                    slot: "oper",
                    fixed: "right",
                    width: "220px"
                }
            ],
            loading: false,
            dialogFlag: false,
            cerList: {
                idNum: null,
                flag: false
            }
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        curChange(val) {
            this.form.curChange = val;
            this.search();
        },
        async search() {
            try {
                this.loading = true;
                let res = await relaApi.getStudentList(this.form);
                this.tableData = res.data.list;
                this.form.total = res.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        showCertificate(row) {
            this.cerList.idNum = row.idNum;
            this.cerList.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
.form-container {
    padding: 20px;
    margin: 30px 0 10px;
}
.modifyButton {
    position: absolute;
    top: 70px;
    right: 5%;
}
.el-dialog {
    .dialog-footer {
        text-align: center;
        margin-bottom: 20px;
    }
}
</style>