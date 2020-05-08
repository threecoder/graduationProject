<template>
    <div>
        <div class="upload-container">
            <p>上传新的证书背景图，建议大小为A4纸大小，背景图名称将以图片名称命名</p>
            <!-- <upload
                uploadUrl="/campback/admin/uploadCerBackground"
                :autoUpload="false"
                :formData="info"
                :beforeUpload="beforeUpload"
            />-->
            <input
                style="display: none"
                id="file"
                type="file"
                accept="image/png, image/gif, image/jpeg, image/jpg"
                @change="onFileChange"
            />
            <el-button size="small" type="success" @click="selectFile">选择文件</el-button>
            <el-button size="small" type="primary" @click="beforeUpload">上传文件</el-button>
            <p>{{file?file.name:""}}</p>
        </div>
        <div class="divider"></div>
        <div class="now-container">
            <div v-for="(item,i) in cerList" :key="i" class="single-container">
                <el-image :src="item.url" fit="fit"></el-image>
                <p>{{item.name}}</p>
            </div>
        </div>
    </div>
</template>
<script>
import upload from "../../../../components/upload.vue";
import cerApi from "../../../../api/admin/certificate";
export default {
    components: {
        upload
    },
    data() {
        return {
            img: null,
            file: null,
            cerList: []
        };
    },
    mounted() {
        this.getTemplateImg();
    },
    methods: {
        async getTemplateImg() {
            try {
                let res = await cerApi.getCerBackgroundImg();
                this.cerList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        selectFile() {
            document.getElementById("file").click();
        },
        onFileChange(event) {
            //通过input本身的change钩子，对图片进行处理
            let tar = event.target;
            let files = tar.files;
            if (files[0].size > this.imgSize) {
                this.$message.error("图片大小不能超过2M");
                return false;
            }
            console.log(files[0]);
            this.file = files[0];

            let url = URL.createObjectURL(files[0]);
            let img = new Image();
            img.src = url;
            img.name = files[0].name;
            this.img = img;
        },
        beforeUpload() {
            let { width, height } = this.img;
            let range = 210 / 297;
            let now = width / height;
            if (now > 1.25 * range || range < 0.75 * range) {
                this.$confirm(
                    `当前所选的图片尺寸为${width}*${height}可能不太合适，确定要继续吗？`,
                    "提示",
                    {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }
                ).then(() => {
                    this.upload();
                });
            } else {
                this.upload();
            }
        },
        async upload() {
            try {
                let data = new FormData();
                data.append("file", this.file);
                data.append("name", this.file.name);
                let res = await cerApi.uploadCerBackground(data);
                this.$message.success("上传成功");
                this.getTemplateImg();
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.upload-container {
    p {
        line-height: 20px;
        margin-bottom: 10px;
    }
}
.now-container {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: center;
    overflow: hidden;
    .single-container {
        text-align: center;
        width: 30%;
        margin-right: 20px;
        p {
            text-align: center;
        }
    }
}
</style>