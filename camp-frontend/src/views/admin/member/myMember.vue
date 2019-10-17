<template>
    <div>
        <h3>我的会员</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="会员名称" label-position="left">
                    <el-input placeholder="输入名称" v-model="form.name" clearable></el-input>
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
                <el-button type="primary" round @click="search">搜索</el-button>
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
                        <el-button @click="checkStudent(row)">查看成员</el-button>
                        <el-button>提醒续费</el-button>
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
        <div>
            <new-person :type="1" />
        </div>
        <el-dialog v-if="studentTable.flag" :visible.sync="studentTable.flag">
            <h3>{{dialogInfo.company}}</h3>
            <div class="button-container">
                <el-button type="primary" size="small">导出模板</el-button>
                <div style="margin-left:8px;">
                    <el-upload
                        class="upload-demo"
                        ref="upload"
                        action="/campback/admin/importStudentByFile"
                        :before-upload="beforeUpload"
                        :on-remove="handleRemove"
                        :on-exceed="handleExceed"
                        :on-success="handleSuccess"
                        :on-error="handleError"
                        :file-list="fileList"
                        :auto-upload="false"
                        :limit="1"
                    >
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        <el-button
                            style="margin-left: 10px;"
                            size="small"
                            type="success"
                            @click="submitUpload"
                        >上传名单</el-button>
                    </el-upload>
                </div>
            </div>
            <div class="divider"></div>
            <list
                :tableConfig="studentTable.tableConfig"
                :tableData="studentTable.tableData"
                @dele="deleteOneStudent"
            />
        </el-dialog>

        <div class="new-container">
            <el-button type="primary">添加会员</el-button>
        </div>
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import page from "@/components/page.vue";
import list from "@/components/studentList.vue";
import newPerson from "@/components/newPerson.vue";
import {
    getMemberList,
    getStudentList,
    deleteStudentFromMember
} from "@/api/admin/member.js";
import { download } from "@/api/request.js";
export default {
    components: {
        mTable,
        page,
        list,
        newPerson
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
                        id: 1,
                        name: "BB股份有限公司",
                        phone: "020-88888888",
                        email: "101@qq.com",
                        enterData: "2019-10-10",
                        vip: "是",
                        vipBegin: "2019-12-12",
                        vipEnd: "2019-12-30",
                        deadline: "是",
                        province: "广东省",
                        city: "广州市",
                        area: "番禺区",
                        zone: "华南理工大学C12-415"
                    }
                ],
                tableConfig: [
                    { prop: "id", label: "会员ID" },
                    { prop: "name", label: "公司名称" },
                    { prop: "phone", label: "联系电话" },
                    { prop: "email", label: "邮箱" },
                    { prop: "enterData", label: "加入时间" },
                    { prop: "vip", label: "是否会员" },
                    { prop: "vipBegin", label: "会员开始时间" },
                    { prop: "vipEnd", label: "会员结束时间" },
                    { prop: "deadline", label: "会员是否快到期" },
                    { prop: "province", label: "省份" },
                    { prop: "city", label: "城市" },
                    { prop: "area", label: "区/县" },
                    { prop: "zone", label: "详细地址" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300"
                    }
                ],
                loading: false
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
                    { slot: "oper", label: "操作", width: "200" }
                ],
                tableData: [
                    {
                        idNum: "44512122",
                        name: "张三",
                        phone: 15555,
                        email: "101@qq.com",
                        position: "经理",
                        province: "广东",
                        city: "广州",
                        area: "番禺",
                        zone: "华南理工大学"
                    }
                ],
                flag: false
            },
            dialogInfo: {
                id: null,
                company: null
            },
            fileList: []
        };
    },
    mounted() {
        this.search();
    },
    methods: {
        async search() {
            try {
                let res = await getMemberList(this.form);
                this.memberTable.tableData = res.data.list;
                let t = memberTable.tableData;
                t.forEach((val, i) => {
                    t[i].vip = t[i].vip == 0 ? "否" : "是";
                    t[i].deadline = t[i].deadline == 0 ? "否" : "是";
                });
                this.form.total = res.data.total;
            } catch (error) {}
        },
        async checkStudent(row) {
            this.dialogInfo.company = row.name;
            this.dialogInfo.id = row.id;
            try {
                let res = await getStudentList(this.dialogInfo.id);
                this.studentTable.tableData = res.data;
            } catch (error) {}
            this.studentTable.flag = true;
        },
        async deleteOneStudent(val) {
            let par = {
                idNum: val,
                memberId: this.dialogInfo.id
            };
            try {
                let res = await deleteStudentFromMember(par);
                this.$message.success("解除挂靠成功！");
                res = await getStudentList(this.dialogInfo.id);
                this.studentTable.tableData = res.data;
            } catch (error) {}
        },
        curChange(val) {
            this.form.currentPage = val;
            this.search();
        },

        async getListTemplate() {
            try {
                let res = await getTemplate();
                download(res);
            } catch (error) {}
        },
        handleRemove(file) {
            this.$message.warning(`移除文件：${file.name}`);
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
        },
        handleExceed() {
            this.$message.error("一次只能添加一个文件");
        },
        handleError() {
            this.$message.error("上传文件失败，请重试");
        },
        submitUpload() {
            this.$refs.upload.action = `/campback/admin/importStudentByFile?memberId=${this.dialogInfo.id}`;
            console.log(this.$refs.upload.action);
            setTimeout(() => {
                this.$refs.upload.submit();
            }, 400);
        },
        beforeUpload() {}
    }
};
</script>
<style lang="scss" scoped>
.button-container {
    display: flex;
    position: absolute;
    right: 5%;
    top: 60px;
}
.new-container {
    position: absolute;
    right: 5%;
    top: 70px;
}
</style>