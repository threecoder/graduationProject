<template>
    <div>
        <div id="qu"></div>
        <!-- 借助自己写的input读取图片 -->
        <input
            id="file"
            type="file"
            accept="image/png, image/gif, image/jpeg, image/bmp, image/x-icon"
            @change="onFileChange"
        />
    </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";

import Quill from "quill";

export default {
    props: {
        imgSize: Number,
        content: String,
        imgFlag: {
            type: Boolean,
            default: true
        }
    },
    data() {
        return {
            quill: null,
            editorOption: {
                theme: "snow",
                placeholder: "请在这里输入",
                modules: {
                    toolbar: [
                        ["bold", "italic", "underline", "strike"], //加粗，斜体，下划线，删除线
                        ["blockquote", "code-block"], //引用，代码块
                        [{ header: 1 }, { header: 2 }], // 标题，键值对的形式；1、2表示字体大小
                        [{ list: "ordered" }, { list: "bullet" }], //列表
                        [{ script: "sub" }, { script: "super" }], // 上下标
                        [{ indent: "-1" }, { indent: "+1" }], // 缩进
                        [{ direction: "rtl" }], // 文本方向
                        [{ size: ["small", false, "large", "huge"] }], // 字体大小
                        [{ header: [1, 2, 3, 4, 5, 6, false] }], //几级标题
                        [{ color: [] }, { background: [] }], // 字体颜色，字体背景颜色
                        [{ font: [] }], //字体
                        [{ align: [] }], //对齐方式
                        ["clean"], //清除字体样式
                        ["image"] //上传图片、上传视频
                    ]
                }
            }
        };
    },
    watch: {
        content(val) {
            this.quill.root.innerHTML = this.content;
        }
    },
    mounted() {
        if (this.imgFlag == false) {
            this.editorOption.modules.toolbar.pop();
        }
        this.init();
    },
    methods: {
        init() {
            this.quill = new Quill("#qu", this.editorOption); //初始化编辑器
            let toolbar = this.quill.getModule("toolbar"); //获取工具栏模块

            //对工具栏的image添加回调函数覆盖原本的方法
            toolbar.addHandler("image", () => {
                document.getElementById("file").click();
            });
            this.quill.on("text-change", () => {
                this.$emit("contentChange", this.quill.root.innerHTML);
            });
            this.quill.root.innerHTML = this.content;
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

            //使用FileReader读取图片成base64编码，再插入到编辑框中
            let reader = new FileReader();
            reader.onload = e => {
                let url = e.target.result;

                //将图片插入需要获取当前光标的位置，参数为是否要求焦点在编辑器上
                let selection = this.quill.getSelection(true);
                //调用insertEmbed 将图片插入到编辑器
                this.quill.insertEmbed(selection.index, "image", url);
            };
            reader.readAsDataURL(files[0]);
        }
    }
};
</script>

<style lang="scss" scoped>
@import "../assets/css/quillCn.css";
::v-deep .ql-snow {
    background-color: #fff;
}

#file {
    display: none;
}
</style>