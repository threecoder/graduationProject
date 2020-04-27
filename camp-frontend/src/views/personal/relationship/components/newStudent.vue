<template>
    <div>
        <h6 style="font-size: 16px">方式一：输入信息添加一个学员</h6>
        <el-form :model="newStudent">
            <el-form-item label="身份证号码" label-width="50">
                <el-input v-model="newStudent.idNum" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" label-width="50">
                <el-input v-model="newStudent.phone" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="姓名" label-width="50">
                <el-input v-model="newStudent.name" autocomplete="off" type="text"></el-input>
            </el-form-item>
        </el-form>
        <div class="tac">
            <el-button @click="hideDialog">取 消</el-button>
            <el-button type="primary" @click="addSingleStudent">确 定</el-button>
        </div>
        <div class="divider"></div>
        <h6 style="font-size: 16px">
            方式二：
            <el-button type="primary" size="small" @click="getListTemplate">导出模板</el-button>填写完信息后上传文件批量添加
        </h6>
        <div class="upload-container tac">
            <el-upload
                class="upload-demo"
                ref="upload"
                action="/campback/member/importStudentByFile"
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
                <div slot="tip" class="el-upload__tip">只能上传Excel文件，且不超过500kb</div>
            </el-upload>
        </div>
    </div>
</template>
<script>
import relaApi from "../../../../api/modules/relationship";
export default {
    data() {
        return {
            newStudent: {
                idNum: null,
                phone: null,
                name: null
            },
            fileList: []
        };
    },
    methods: {
        hideDialog() {
            this.$emit("hideDialog");
        },
        async addSingleStudent() {
            let t = this.newStudent;
            if (t.idNum == null || t.name == null || t.phone == null) {
                this.$message.error("身份证、手机号码及姓名必填");
                return false;
            }
            try {
                let res = await relaApi.addOneStudent(this.newStudent);
                this.$message.success(res.msg);
                this.dialogFormVisible = false;
                this.search();
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        //获取模板
        async getListTemplate() {
            try {
                let res = await relaApi.getTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        handleRemove(file) {
            this.$message.warning(`移除文件：${file.name}`);
        },
        handleSuccess(response) {
            this.$alert(response.msg, "上传文件成功", {
                confirmButtonText: "确定"
            });
            this.hideDialog();
        },
        handleExceed() {
            this.$message.error("一次只能添加一个文件");
        },
        handleError() {
            this.$message.error("上传文件失败，请重试");
        },
        submitUpload() {
            this.$refs.upload.submit();
        }
    }
};
</script>
<style lang="scss" scoped>
.upload-container {
    text-align: center;
    margin-top: 20px;
}
</style>