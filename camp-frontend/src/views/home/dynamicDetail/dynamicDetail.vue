<template>
    <div class="dynamic-container">
        <div>
            <el-button type="text" @click="back">返回上一层</el-button>
        </div>
        <div class="header">
            <p class="title">{{detail.title}}</p>
            <p class="date">{{detail.date}}</p>
        </div>
        <!-- <div v-html="detail.content" class="content"></div> -->
        <div id="content" class="content"></div>
    </div>
</template>

<script>
import Vue from "vue/dist/vue.js";
import elImage from "element-ui/packages/image/index";
import { imgStr } from "../../../const";
import dynamicApi from "../../../api/index/dynamic";
import newsApi from "../../../api/index/news";
import noticeApi from "../../../api/index/notice";
export default {
    data() {
        return {
            info: {
                type: "news",
                id: "11"
            },
            detail: {
                urlList: [
                    "http://localhost:3000/image/news/1/1-1587398897307.jpg",
                    "http://localhost:3000/image/news/2/2-1587887856960.jpg",
                    "http://localhost:3000/image/news/1/1-1587398827582.jpg",
                    "http://localhost:3000/image/news/1/3-1587398897308.jpg"
                ],
                title: null,
                date: null,
                placeholder: "ffff",
                content: "<p>ssffffsssffffffffffff</p>"
            }
        };
    },
    mounted() {
        this.info = this.$route.query;
        // this.getDetail();
        this.$_handleData();
    },
    methods: {
        isImg(item) {
            return item.includes(imgStr);
        },
        back() {
            this.$router.go(-1);
        },
        $_handleData() {
            let count = 0,
                index;
            while (true) {
                index = this.detail.content.indexOf(this.detail.placeholder);
                if (index == -1) {
                    break;
                } else {
                    let img = `<el-image :src="getSrc(${count})" :preview-src-list="urlList"></el-image>`;
                    count++;
                    let str = this.detail.content.replace(
                        this.detail.placeholder,
                        img
                    );
                    this.detail.content = str;
                }
            }
            let urlList = this.detail.urlList;
            let com = Vue.extend({
                template: "<div>" + this.detail.content + "</div>",
                components: {
                    elImage
                },
                data() {
                    return {
                        urlList: urlList
                    };
                },
                methods: {
                    getSrc(index) {
                        return this.urlList[index];
                    }
                }
            });
            com = new com().$mount();
            console.log(this.detail.content);
            document.getElementById("content").appendChild(com.$el);
        },
        async getDetail() {
            try {
                let res = null,
                    type = this.info.type;
                if (type == "news") {
                    res = await newsApi.getNewsDetail(this.info.id);
                } else if (type == "notice") {
                    res = await noticeApi.getNoticeDetail(this.info.id);
                } else {
                    res = await dynamicApi.getDynamicDetail(this.info.id);
                }
                this.detail = res.data.info;
                console.log(this.detail.content);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        click() {
            console.log("ccc");
        }
    }
};
</script>

<style lang="scss" scoped>
.dynamic-container {
    background-color: #fff;
    padding: 20px;
    .header {
        text-align: center;
        border-bottom: 1px solid #ccc;
        padding-bottom: 10px;
        p.title {
            color: rgb(23, 65, 142);
            font-size: 24px;
        }
        p.date {
            margin-top: 10px;
            color: rgb(96, 98, 102);
        }
    }
    .content {
        display: flex;
        flex-direction: column;
        // justify-content: center;
        align-items: center;

        ::v-deep .my-img {
            max-width: 100%;
            object-fit: scale-down;
        }
    }
}
</style>>
