<template>
    <div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="姓名" label-position="left">
                    <el-input placeholder="学员姓名" v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="身份证" label-position="top">
                    <el-input placeholder="学员身份证" v-model="form.idNum" clearable></el-input>
                </el-form-item>
                <el-form-item label="号码" label-position="top">
                    <el-input placeholder="学员号码" v-model="form.phone" clearable></el-input>
                </el-form-item>
                <el-form-item label="公司" label-position="top">
                    <el-select v-model="form.company" clearable>
                        <el-option :value="0" label="无挂靠"></el-option>
                        <el-option
                            v-for="(item,i) in companyList"
                            :value="item.id"
                            :label="item.name"
                            :key="i"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="挂靠组织" label-position="top">
                    <el-select v-model="form.hasOrg" clearable>
                        <el-option value="0" label="无"></el-option>
                        <el-option value="1" label="有"></el-option>
                    </el-select>
                </el-form-item>
                <el-button :loading="table.loading" type="primary" @click="search">搜索</el-button>
            </el-form>
        </div>

        <div class="table-container">
            <m-table
                :data="table.tableData"
                :tableConfig="table.tableConfig"
                :loading="table.loading"
            >
                <el-table-column slot="oper" align="center" slot-scope="{ params }" v-bind="params">
                    <div slot-scope="{row}">
                        <div v-if="type==0">
                            <el-button type="primary" size="small" @click="modifyInfo(row)">修改信息</el-button>
                            <el-button type="primary" size="small" @click="deleteOneStudent(row)">挂靠</el-button>
                            <el-button type="primary" size="small" @click="resetPass(row)">重置密码</el-button>
                        </div>
                        <div v-if="type==1">
                            <el-button
                                type="primary"
                                size="small"
                                @click="trainingHistory(row)"
                            >培训记录</el-button>
                            <el-button type="primary" size="small" @click="certificate(row)">持有证书</el-button>
                        </div>
                    </div>
                </el-table-column>
            </m-table>
            <page
                @curChange="curChange"
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import adminStudentApi from "../../../../api/admin/student";
import event from "../../../../assets/js/eventBus";
export default {
    props: {
        type: {
            type: Number,
            default: 0
        }
    },
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                name: null,
                idNum: null,
                phone: null,
                company: null,
                hasOrg: null,
                currentPage: 1,
                total: 100,
                pageSize: 10
            },
            table: {
                tableData: [
                    {
                        area: "鼓楼区",
                        city: "南京市",
                        email: "12345@163.com",
                        idNum: "451211888888888888",
                        name: "姓名",
                        phone: "15521065463",
                        province: "江苏省",
                        zone: "南京大学",
                        company: "BB股份有限公司",
                        position: "经理",
                        companyId: 11
                    }
                ],
                tableConfig: [
                    { prop: "idNum", label: "学员身份证", width: "150px" },
                    { prop: "name", label: "学员姓名", width: "150px" },
                    { prop: "phone", label: "联系电话", width: "150px" },
                    { prop: "email", label: "邮箱", width: "150px" },
                    { prop: "company", label: "所属公司" },
                    { prop: "position", label: "公司职务" },
                    { prop: "province", label: "省份" },
                    { prop: "city", label: "城市" },
                    { prop: "area", label: "区/县" },
                    { prop: "zone", label: "详细地址" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300px"
                    }
                ],
                loading: false
            },
            companyList: []
        };
    },
    mounted() {
        this.init();
        this.search();
        this.getCompanyList();
        event.$on("refreshStudent", this.search);
    },
    methods: {
        init() {
            if (this.type == 1) {
                this.table.tableConfig = [
                    { prop: "idNum", label: "学员身份证" },
                    { prop: "name", label: "学员姓名" },
                    { prop: "phone", label: "联系电话" },
                    { prop: "email", label: "邮箱" },
                    { prop: "company", label: "所属公司" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300px"
                    }
                ];
            }
        },
        async search() {
            this.table.loading = true;
            try {
                let res = await adminStudentApi.getStudentList(this.form);
                this.table.tableData = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        async getCompanyList() {
            try {
                let res = await adminStudentApi.getMemSelectList();
                this.companyList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },
        modifyInfo(row) {
            this.$emit("modifyInfo", row);
        },
        deleteOneStudent(row) {
            this.$emit("delete", row);
        },
        resetPass(row) {
            this.$emit("resetPass", row);
        },
        trainingHistory(row) {
            this.$emit("trainingHistory", row);
        },
        certificate(row) {
            this.$emit("certificate", row);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>