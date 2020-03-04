<template>
    <div>
        <h6 style="font-size: 16px">方式一：输入信息添加一个学员</h6>
        <el-form :model="newOne">
            <el-form-item label="身份证号码" :label-width="formLabelWidth">
                <el-input v-model="newOne.idNum" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" :label-width="formLabelWidth">
                <el-input v-model="newOne.phone" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="newOne.name" autocomplete="off" type="text"></el-input>
            </el-form-item>
        </el-form>

        <div class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="addSingleONe">确 定</el-button>
        </div>
        <div class="divider"></div>
        <h6 style="font-size: 16px">
            方式二：
            <el-button type="primary" round @click="getListTemplate">导出模板</el-button>填写完信息后上传文件批量添加
        </h6>
        <div class="upload-container">
            <upload
                :limit="1"
                :autoUpload="false"
                :uploadUrl="uploadUrl"
                :tip="'请先下载模板，填写数据后将模板上传'"
                @uploadSuccess="handleSuccess"
            />
        </div>
    </div>
</template>
<script>
import adminStudentApi from "../../../../api/admin/student";
import { download } from "../../../../api/request";
import upload from "../../../../components/upload.vue";
export default {
    //type：1表示添加会员，0表示添加学生
    //uploadUrl：上传模板的地址
    props: ["temUrl", "uploadUrl"],
    components: {
        upload
    },
    data() {
        return {
            newOne: {
                idNum: null,
                phone: null,
                name: null,
                email: null,
                province: null,
                city: null,
                area: null,
                zone: null
            },
            fileList: [],
            dialogFormVisible: false,
            formLabelWidth: "100px",
            readOnly: false
        };
    },
    methods: {
        async getListTemplate() {
            try {
                let res = await adminStudentApi.getStudentTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async addSingleONe() {
            let addSingle = adminStudentApi.addSingleStudent;
            try {
                let res = await addsingle(this.newOne);
                this.$message.success("添加新的学员成功");
                this.close();
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        close() {
            this.$emit("close");
        },
        changeProvince({ value }) {
            this.newOne.province = value;
        },
        changeCity({ value }) {
            this.newOne.city = value;
        },
        changeArea({ value }) {
            this.newOne.area = value;
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
.el-form {
    margin-top: 30px;
}
.dialog-footer,
.upload-container {
    text-align: center;
}
</style>