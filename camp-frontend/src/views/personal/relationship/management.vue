<template>
    <div>
        <h3>管理挂靠关系</h3>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="手机号码" label-position="left">
                    <el-input placeholder="输入手机号码" :model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="身份证号码" label-position="top">
                    <el-input placeholder="输入身份证号码" :model="form.idNum"></el-input>
                </el-form-item>
                <el-form-item label="姓名" label-position="top">
                    <el-input placeholder="输入姓名" :model="form.name"></el-input>
                </el-form-item>
                <el-button type="primary" round @click="search">搜索</el-button>
            </el-form>
        </div>
        <div class="table-container">
            <m-table
                :data="tableData"
                :tableConfig="tableConfig"
                :loading="loading"
                :tableAttr="tableAttr"
            >
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button
                            size="small"
                            type="primary"
                            round
                            @click="deleteRelation(row)"
                            :loading="buttonLoading"
                        >解除挂靠</el-button>
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

        <div class="modifyButton">
            <el-button type="primary" round @click="dialogFormVisible=true">添加学员</el-button>
        </div>

        <el-dialog title="添加学员" :visible.sync="dialogFormVisible" width="40%">
            <h6 style="font-size: 16px">方式一：输入信息添加一个学员</h6>
            <el-form :model="newStudent">
                <el-form-item label="身份证号码" :label-width="formLabelWidth">
                    <el-input v-model="newStudent.idNum" autocomplete="off" type="text"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" :label-width="formLabelWidth">
                    <el-input v-model="newStudent.phone" autocomplete="off" type="text"></el-input>
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="newStudent.name" autocomplete="off" type="text"></el-input>
                </el-form-item>
            </el-form>
            <div class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </div>
            <div class="divider"></div>
            <h6 style="font-size: 16px">
                方式二：
                <el-button type="primary" round>导出模板</el-button>填写完信息后上传文件批量添加
            </h6>
            <div class="upload-container">
                <el-upload
                class="upload-demo"
                ref="upload"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-remove="handleRemove"
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
                <div slot="tip" class="el-upload__tip">只能上传Excel文件，且不超过500kb</div>
            </el-upload>
            </div>
            
        </el-dialog>
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import page from "@/components/page.vue";
import {
    getStudentList,
    deleteOneStudent
} from "@/api/modules/relationship.js";
export default {
    components: {
        mTable,
        page
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
            newStudent: {
                idNum: null,
                phone: null,
                name: null
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
                },
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
                { prop: "idNum", label: "身份证号码", width: 200 },
                { prop: "phone", label: "手机号码", width: 200 },
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
                    width: 100
                }
            ],
            tableAttr: {},
            loading: false,
            buttonLoading: false,
            dialogFormVisible: false,
            formLabelWidth: "50",
            fileList: []
        };
    },
    watch: {
        currentPage() {
            this.search();
        }
    },
    mounted() {
        this.search();
    },
    methods: {
        curChange(val) {
            this.form.curChange = val;
        },
        async search() {
            try {
                this.loading = true;
                let res = await getStudentList();
                this.tableData = res.data;
                this.form.total = res.total;
            } catch (error) {
                this.loading = false;
            }
        },
        async deleteRelation(row) {
            try {
                this.buttonLoading = true;
                let par = {
                    idNum: row.idNum,
                    phone: row.phone
                };
                let res = await deleteOneStudent(par);
                this.$message.success("解除挂靠关系成功");
                this.buttonLoading = false;
            } catch (e) {
                this.buttonLoading = false;
            }
        },
        handleRemove(file){
            this.$message.warning(`移除文件：${file.name}`);
        },
        submitUpload(){
            this.$refs.upload.submit();
        },
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
.upload-container {
    text-align: center;
    margin-top: 20px;
}
</style>