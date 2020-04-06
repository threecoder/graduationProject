<template>
    <div>
        <h3>编辑动态</h3>
        <div class="divider"></div>
        <div class="all-container" v-loading="loading">
            <div class="title-container">
                <label>标题</label>
                <el-input v-model="form.title" placeholder="请输入标题"></el-input>
            </div>
            <div class="desc-container">
                <label>简要内容</label>
                <el-input
                    type="textarea"
                    v-model="form.desc"
                    :maxlength="50"
                    show-word-limit
                    placeholder="请输入简要内容"
                ></el-input>
            </div>
            <div class="edit-container">
                <label>内容</label>
                <editor
                    v-model="form.content"
                    :imgSize="imgSize"
                    :content="initContent"
                    @contentChange="saveContent"
                />
            </div>
            <div class="tac">
                <el-button @click="cancel">取消</el-button>
                <el-button type="primary" @click="submit">提交</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import editor from "../../../components/editor.vue";
import indexApi from "../../../api/admin/index";
import { imgStr } from "../../../const";
export default {
    components: {
        editor
    },
    data() {
        return {
            quill: null,
            loading: false,
            imgSize: 1024 * 1024 * 2,
            imgList: [],
            backupContent: null,
            form: {
                title: null,
                content: null,
                desc: null
            },
            //编辑功能相关变量
            dynamicId: null,
            initContent: null,
            imgInfo: [], //编辑后图片顺序
            imgIds: {
                "http://www.baidu.com/img/baidu_resultlogo@2.png": "self"
            } //编辑前图片与其id的对应
        };
    },
    beforeMount() {
        if (this.$route.query.dynamicId !== undefined) {
            this.dynamicId = this.$route.query.dynamicId;
            this.getDynamicDetail();
        }
        this.initContent =
            "<img src='http://www.baidu.com/img/baidu_resultlogo@2.png'>";
    },
    methods: {
        //新增和编辑功能通用方法
        saveContent(val) {
            if (val == "<p><br></p>") {
                this.form.content = null;
                this.backupContent = null;
            } else {
                this.form.content = val;
                this.backupContent = val;
            }
            console.log(this.form.content);
        },

        $_base64ToFile(imgHtml, index) {
            let start = imgHtml.indexOf('"') + 1,
                end = imgHtml.lastIndexOf('"');
            let src = imgHtml.substring(start, end);
            let arr = src.split(","),
                mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]),
                n = bstr.length,
                u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            let blob = new Blob([u8arr], { type: mime });
            let date = new Date(),
                fileName = `${index}-${date.getTime()}`;
            blob.lastModifiedDate = date;
            blob.name = fileName;
            return blob;
        },

        $_handleContent() {
            this.imgList = [];
            //针对base64图片进行处理
            let reg1 = /<img[\s\S]*?>/g;
            let imgArr = this.form.content.match(reg1);
            if (imgArr) {
                imgArr.forEach((val, i) => {
                    if (val.indexOf("data:") !== -1) {
                        let file = this.$_base64ToFile(val, i + 1);
                        this.imgList.push(file);
                        this.imgInfo.push(
                            JSON.stringify({
                                imgId: null,
                                order: i + 1,
                                name: file.name
                            })
                        );
                    } else {
                        let start = imgArr[i].indexOf('"'),
                            end = imgArr[i].lastIndexOf('"'),
                            key = imgArr[i].substring(start + 1, end);
                        let id = this.imgIds[key];
                        this.imgInfo.push(
                            JSON.stringify({
                                imgId: id,
                                order: i + 1,
                                name: null
                            })
                        );
                    }
                    this.form.content = this.form.content.replace(val, imgStr);
                });
            }
        },

        cancel() {
            this.$confirm("确定离开吗？请确保已经保存编辑内容", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(() => {
                this.$router.go(-1);
            });
        },

        async submit() {
            let form = this.form;
            console.log(this.form);
            if (!form.title || !form.desc || !form.content) {
                this.$message.error("必须填写所有内容");
                return false;
            }
            this.loading = true;
            try {
                this.$_handleContent();
                let data = new FormData();
                data.append("placeholder", imgStr);
                data.append("title", this.form.title);
                data.append("desc", this.form.desc);
                data.append("content", this.form.content);
                this.imgList.forEach(val => {
                    data.append("imgList", val, val.name);
                });

                if (this.dynamicId) {
                    this.imgInfo.forEach(val => {
                        data.append("imgInfo", val);
                    });
                    data.append("dynamicId", this.dynamicId);
                    await indexApi.modifyDynamic(data);
                } else {
                    await indexApi.addDynamic(data);
                }
                this.$message.success("编辑动态成功");
            } catch (error) {
                this.form.content = this.backupContent;
                this.$message.error(error.message);
            }
            this.loading = false;
        },

        //编辑功能相关

        async getDynamicDetail() {
            try {
                let res = await indexApi.getDynamicDetail(this.dynamicId);
                this.form = res.data.info;
                this.initContent = res.data.info.content;
                let reg1 = /<img[\s\S]*?>/g;
                let imgArr = this.form.content.match(reg1);
                let obj = {};
                for (let i = 0; i < imgArr.length; i++) {
                    let start = imgArr[i].indexOf('"'),
                        end = imgArr[i].lastIndexOf('"'),
                        key = imgArr[i].substring(start + 1, end);
                    obj[key] = res.data.ids[i];
                }
                this.imgIds = obj;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>

<style lang="scss" scoped>
::v-deep .ql-snow {
    background-color: #fff;
}
.all-container {
    width: 70%;
    margin: 0 auto;
    .title-container {
        label {
            display: block;
            margin-left: 20px;
            margin-bottom: 10px;
        }
    }
    .desc-container,
    .edit-container {
        margin-top: 30px;
        label {
            display: block;
            margin-left: 20px;
            margin-bottom: 10px;
        }
    }
    .tac {
        margin: 30px auto;
    }
}
</style>