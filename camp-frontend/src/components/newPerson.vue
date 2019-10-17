<template>
    <el-dialog title="添加学员" :visible.sync="dialogFormVisible" width="40%">
        <h6 v-if="type==0" style="font-size: 16px">方式一：输入信息添加一个学员</h6>
        <h6 v-else style="font-size: 16px">方式一：输入信息添加一个学员</h6>
        <el-form v-if="type==0" :model="newOne">
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
        <el-form v-else :model="newOne">
            <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="newOne.name" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="newOne.email" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="联系方式" :label-width="formLabelWidth">
                <el-input v-model="newOne.phone" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label-position="top" label="地址">
                <el-input
                    id="zone-input"
                    v-model="newOne.zone"
                    class="input-with-select"
                    placeholder="请输入具体地址"
                    :readonly="readOnly"
                >
                    <v-distpicker
                        v-on:province="changeProvince"
                        v-on:city="changeCity"
                        v-on:area="changeArea"
                        :disabled="readOnly"
                        :province="newOne.province"
                        :city="newOne.city"
                        :area="newOne.area"
                        slot="prepend"
                    ></v-distpicker>
                </el-input>
            </el-form-item>
        </el-form>
        <div class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSingleStudent">确 定</el-button>
        </div>
        <div class="divider"></div>
        <h6 style="font-size: 16px">
            方式二：
            <el-button type="primary" round @click="getListTemplate">导出模板</el-button>填写完信息后上传文件批量添加
        </h6>
        <div class="upload-container">
            <el-upload
                class="upload-demo"
                ref="upload"
                :action="uploadUrl"
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
    </el-dialog>
</template>
<script>
import { download } from "@/api/request.js";
export default {
    props: ["type", "temUrl", "uploadUrl"],
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
                zone:null
            },
            fileList: []
        };
    },
    methods: {
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
            this.$refs.upload.action = this.uploadUrl;
            setTimeout(() => {
                this.$refs.upload.submit();
            }, 400);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>