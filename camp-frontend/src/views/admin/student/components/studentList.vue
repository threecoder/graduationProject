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
                    <el-select v-model="form.company" clearable></el-select>
                </el-form-item>
                <el-form-item label="挂靠组织" label-position="top">
                    <el-select v-model="form.hasOrg" clearable>
                        <el-option value="0" label="无"></el-option>
                        <el-option value="1" label="有"></el-option>
                    </el-select>
                </el-form-item>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-form>
        </div>

        <div class="table-container">
            <m-table
                :data="table.tableData"
                :tableConfig="table.tableConfig"
                :loading="table.loading"
            >
                <el-table-column slot="oper" align="center" slot-scope="{ params }" v-bind="params">
                    <div slot-scope=" {row}">
                        <el-button v-if="type==0" type="primary" size="small" @click="modifyInfo(row)">修改信息</el-button>
                        <el-button v-if="type==0" type="primary" size="small" @click="deleteOneStudent(row)">解除挂靠</el-button>
                        <el-button v-if="type==0" type="primary" size="small" @click="resetPass(row)">重置密码</el-button>
                        
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
import mTable from "@/components/mTable.vue";
import page from "@/components/page.vue";
import { getStudentList } from "@/api/admin/student.js";

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
            }
        };
    },
    mounted() {
        this.search();
        console.log(this.$parent);
    },
    methods: {
        async search() {
            try {
                let que = "";
                let res = await getStudentList(que);
                console.log(res);
                this.table.tableData = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                console.log(error);
            }
        },
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },
        modifyInfo(row){
            this.$emit("modifyInfo",row)
        },
        deleteOneStudent(row){
            this.$emit("delete",row)
        },
        resetPass(row){
            this.$emit("resetPass",row)
        }
    }
};
</script>
<style lang="scss" scoped>
</style>