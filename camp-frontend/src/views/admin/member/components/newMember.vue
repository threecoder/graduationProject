<template>
    <div>
        <h6 style="font-size: 16px">方式一：输入信息添加一个会员</h6>
        <el-form :model="newOne">
            <el-form-item label="会员名称" :label-width="formLabelWidth">
                <el-input v-model="newOne.name" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="newOne.email" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="联系方式" :label-width="formLabelWidth">
                <el-input v-model="newOne.phone" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
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
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="addSingleONe">确 定</el-button>
        </div>
        <div class="divider"></div>
        <h6 style="font-size: 16px">
            方式二：
            <el-button type="primary" size="mini" @click="getListTemplate">导出模板</el-button>填写完信息后上传文件批量添加
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
import adminMemberApi from "@/api/admin/member.js";
import adminStudentApi from "@/api/admin/student.js";
import { download } from "@/api/request.js";
import upload from "@/components/upload.vue";
export default {
    //uploadUrl：上传模板的地址
    props: ["temUrl", "uploadUrl"],
    components: {
        upload
    },
    data() {
        return {
            newOne: {
                phone: null,
                name: null,
                email: null,
                province: null,
                city: null,
                area: null,
                zone: null
            },
            fileList: [],
            formLabelWidth: "100px",
            readOnly: false
        };
    },
    methods: {
        async getListTemplate() {
            try {
                let res = await adminMemberApi.getMemberTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async addSingleONe() {
            try {
                let res = await adminMemberApi.addSingleMember(this.newOne);
                this.$message.success("添加新的会员成功");
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