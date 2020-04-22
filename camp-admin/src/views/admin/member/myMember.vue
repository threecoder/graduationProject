<template>
    <div>
        <el-row>
            <el-col :span="22">
                <h3>我的会员</h3>
            </el-col>
            <el-col :span="2">
                <el-button @click="newFlag=true" type="primary">添加会员</el-button>
            </el-col>
        </el-row>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="名称" label-position="left">
                    <el-input placeholder="输入会员名称" v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="是否会员" label-position="top">
                    <el-select v-model="form.type" clearable>
                        <el-option value="0" label="否"></el-option>
                        <el-option value="1" label="是"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="会员是否快到期" label-position="top">
                    <el-select v-model="form.deadline" clearable>
                        <el-option value="0" label="否"></el-option>
                        <el-option value="1" label="是"></el-option>
                    </el-select>
                </el-form-item>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-form>
        </div>

        <div class="table-container">
            <m-table
                :data="memberTable.tableData"
                :tableConfig="memberTable.tableConfig"
                :loading="memberTable.loading"
            >
                <el-table-column slot="oper" align="center" slot-scope="{ params }" v-bind="params">
                    <div slot-scope=" {row}">
                        <el-button type="primary" size="small" @click="checkInfo(row)">修改信息</el-button>
                        <el-button type="primary" size="small" @click="checkStudent(row)">查看成员</el-button>
                        <el-button
                            v-if="row.vip=='是'"
                            type="primary"
                            size="small"
                            @click="remind(row)"
                        >提醒续费</el-button>
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

        <!-- 查看会员信息弹窗 -->
        <el-dialog
            title="会员信息"
            v-if="memberTable.flag"
            :visible.sync="memberTable.flag"
            width="70%"
        >
            <info :idType="1" :infor="temRow" />
        </el-dialog>

        <!-- 添加会员弹窗 -->
        <el-dialog title="添加会员" v-if="newFlag" :visible.sync="newFlag">
            <new-member :uploadUrl="`/campback/admin/importMemberByFile`" @close="closeMember" />
        </el-dialog>

        <!-- 查看学员弹窗 -->
        <el-dialog v-if="studentTable.flag" :visible.sync="studentTable.flag">
            <h3>{{dialogInfo.company}}</h3>
            <div class="button-container">
                <el-button type="primary" size="small" @click="getStuTemp">导出模板</el-button>
                <div style="margin-left:8px;">
                    <upload
                        :uploadUrl="'/campback/admin/importStudentByFile'"
                        :limit="1"
                        :autoUpload="false"
                        :formData="formData"
                        @uploadSuccess="handleSuccess"
                    />
                </div>
            </div>
            <div class="divider"></div>
            <list
                :tableConfig="studentTable.tableConfig"
                :tableData="studentTable.tableData"
                @dele="deleteOneStudent"
            />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import info from "../components/info.vue";
import list from "../../../components/studentList.vue";
import newMember from "./components/newMember.vue";
import upload from "../../../components/upload.vue";
import adminMemberApi from "../../../api/admin/member";
import { download } from "../../../api/request";
export default {
    components: {
        mTable,
        page,
        list,
        newMember,
        upload,
        info
    },
    data() {
        return {
            form: {
                name: null,
                type: null,
                deadline: null,
                currentPage: 1,
                total: 100,
                pageSize: 10
            },
            memberTable: {
                tableData: [
                    {
                        area: "鼓楼区",
                        city: "南京市",
                        deadline: 1,
                        email: "12345@163.com",
                        enterDate: "2019-09-19 00:00:00",
                        id: "mb_ca33eae307ae4",
                        name: "BB股份有限公司",
                        phone: "15521065463",
                        province: "江苏省",
                        vip: 1,
                        vipBegin: "2019-09-20 00:00:00",
                        vipEnd: "2019-12-22 00:00:00",
                        zone: "南京大学"
                    }
                ],
                tableConfig: [
                    { prop: "id", label: "会员ID" },
                    { prop: "name", label: "公司名称", width: "150px" },
                    { prop: "phone", label: "联系电话", width: "150px" },
                    { prop: "email", label: "邮箱", width: "150px" },
                    { prop: "enterDate", label: "加入时间", width: "150px" },
                    { prop: "vip", label: "是否会员" },
                    { prop: "vipBegin", label: "会员开始时间", width: "150px" },
                    { prop: "vipEnd", label: "会员结束时间", width: "150px" },
                    { prop: "deadline", label: "会员是否快到期" },
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
                loading: false,
                flag: false
            },
            studentTable: {
                tableConfig: [
                    { prop: "name", label: "姓名" },
                    { prop: "idNum", label: "身份证号码" },
                    { prop: "phone", label: "电话号码" },
                    { prop: "email", label: "邮箱" },
                    { prop: "position", label: "公司职位" },
                    { prop: "province", label: "省份" },
                    { prop: "city", label: "城市" },
                    { prop: "area", label: "区/县" },
                    { prop: "zone", label: "详细地址" },
                    { slot: "oper", label: "操作", width: "100px" }
                ],
                tableData: [
                    // {
                    //     idNum: "44512122",
                    //     name: "张三",
                    //     phone: 15555,
                    //     email: "101@qq.com",
                    //     position: "经理",
                    //     province: "广东",
                    //     city: "广州",
                    //     area: "番禺",
                    //     zone: "华南理工大学"
                    // }
                ],
                flag: false
            },
            dialogInfo: {
                id: null,
                company: null
            },
            newFlag: false,
            formData: {},
            temRow: {}
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        async search() {
            try {
                let res = await adminMemberApi.getMemberList(this.form);
                console.log(res);
                this.memberTable.tableData = res.data.list;
                let t = this.memberTable.tableData;
                t.forEach((val, i) => {
                    t[i].vip = t[i].vip == 0 ? "否" : "是";
                    t[i].deadline = t[i].deadline == 0 ? "否" : "是";
                });
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },
        checkInfo(row) {
            this.temRow = row;
            this.memberTable.flag = true;
        },
        async checkStudent(row) {
            this.dialogInfo.company = row.name;
            this.dialogInfo.id = row.id;
            this.formData = { memberId: this.dialogInfo.id };
            console.log(this.formData);
            try {
                let res = await adminMemberApi.getStudentList(
                    this.dialogInfo.id
                );
                this.studentTable.tableData = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.studentTable.flag = true;
        },
        async remind(row) {
            try {
                let res = await adminMemberApi.remindRenew(row.id);
                this.$message.success(`提醒会员${row.name}续费成功`);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async deleteOneStudent(val) {
            let par = {
                idNum: val,
                memberId: this.dialogInfo.id
            };
            try {
                let res = await adminMemberApi.deleteStudentFromMember(par);
                this.$message.success("解除挂靠成功！");
                res = await adminMemberApi.getStudentList(this.dialogInfo.id);
                this.studentTable.tableData = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getStuTemp() {
            try {
                let res = await adminMemberApi.getStudentTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },

        //会员弹窗相关
        closeMember() {
            this.newFlag = false;
        },
        handleSuccess(response) {
            this.$alert(response.msg, "上传文件成功", {
                confirmButtonText: "确定"
                // callback: action => {
                //     this.$message({
                //         type: "info",
                //         message: `action: ${action}`
                //     });
                // }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
.button-container {
    display: flex;
    position: absolute;
    align-items: flex-start;
    right: 5%;
    top: 40px;
}
.new-container {
    position: absolute;
    right: 5%;
    top: 70px;
}
</style>