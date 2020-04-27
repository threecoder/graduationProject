<template>
    <div>
        <el-upload
            class="upload-demo"
            ref="upload"
            :action="uploadUrl"
            :data.sync="uploadData"
            :limit="limit"
            :on-exceed="handleExceed"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="fileList"
            :auto-upload="autoUpload"
        >
            <el-button slot="trigger" type="success" :size="size">选取文件</el-button>
            <el-button
                v-if="!autoUpload"
                style="margin-left: 10px;"
                type="primary"
                @click="submitUpload"
                :size="size"
            >上传文件</el-button>
            <div v-if="tip" slot="tip" class="el-upload__tip">{{tip}}</div>
        </el-upload>
    </div>
</template>
<script>
export default {
    props: {
        //上传文件的路径
        uploadUrl: {
            type: String,
            default: ""
        },
        //上传文件限制数量
        limit: {
            type: Number,
            default: 1
        },
        //提示
        tip: {
            type: String,
            default: ""
        },
        //文件名，用于模拟已有文件
        fileName: {
            type: String,
            default: ""
        },
        //是否自动上传，false为否
        autoUpload: {
            type: Boolean,
            default: true
        },
        //上传附带的数据
        formData: {
            type: Object,
            default: () => {}
        },
        //上传成功后跳转到的路径
        path: {
            type: String,
            default: ""
        },
        //按钮尺寸
        size: {
            type: String,
            default: "small"
        }
    },
    data() {
        return {
            fileList: this.fileName ? [{ uid: 1111, name: this.fileName }] : [],
            uploadData: null
        };
    },
    watch: {
        formData: {
            handler: function(val, oldVal) {
                console.log(val, oldVal);
                console.log("change");
                this.uploadData = this.formData;
            },
            deep: true
        }
    },
    methods: {
        submitUpload() {
            this.uploadData = this.formData;
            setTimeout(() => {
                this.$refs.upload.submit();
            }, 400);
        },
        handleRemove(file, fileList) {
            let t = -1;
            for (let i = 0; i < this.fileList.length; i++) {
                if (this.fileList[i].uid == file.uid) {
                    t = i;
                    break;
                }
            }
            this.fileList.splice(t, 1);
            this.hasFile = this.fileList.length == 0 ? false : true;
            this.$emit("removeFile");
            this.$message.info(`移除文件 ${file.name}`);
        },
        handleExceed() {
            this.$message.error(`最多只能选择${this.limit}个文件`);
        },
        handleSuccess(response) {
            this.$emit("uploadSuccess", response);
            if (this.path) {
                this.$router.push({ path: this.path });
            }
        },
        handleError() {
            this.$emit("removeFile");
            this.$message.error("文件上传失败，请选择文件重试");
        },
        handleChange() {
            this.$emit("addFile");
        }
    }
};
</script>
<style lang="scss" scoped>
</style>