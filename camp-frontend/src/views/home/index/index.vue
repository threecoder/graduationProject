<template>
    <div class="index">
        <div class="first-node">
            <div class="carousel-container">
                <carousel />
            </div>
            <div class="news-container">
                <div class="newscard">
                    <newscard :cardInfo="newscardname" />
                </div>
            </div>
        </div>

        <div class="second-node">
            <div class="notice">
                <noticecard />
            </div>
            <div class="news-container">
                <newscard :cardInfo="dynamiccardname"></newscard>
            </div>
        </div>
    </div>
</template>

<script>
import carousel from "./components/carousel.vue";
import newscard from "./components/newscard.vue";
import noticecard from "./components/notice.vue";
import imgcard from "./components/imgcard.vue";
export default {
    components: {
        carousel,
        newscard,
        imgcard,
        noticecard
    },
    data() {
        return {
            msg: "这里是首页",
            newscardname: {
                name: "协会新闻",
                link: ""
            },
            dynamiccardname: {
                name: "协会动态",
                link: ""
            },
            imgcardname: {
                name: "协会优秀成果展示",
                link: ""
            },
            imglist: [
                {
                    url: require("../../../assets/images/index1.jpg"),
                    title: "图片1",
                    link: ""
                },
                {
                    url: require("../../../assets/images/index1.jpg"),
                    title: "图片2",
                    link: ""
                },
                {
                    url: require("../../../assets/images/index1.jpg"),
                    title: "图片3",
                    link: ""
                }
            ]
        };
    },
    methods: {}
};
</script>
<style lang="scss" scoped>
.index {
    width: 100%;
    overflow: hidden;
    padding-bottom: 100px;
    .first-node {
        overflow: hidden;
        .carousel-container {
            margin-top: 10px;
            width: 50%;
            float: left;
        }
        .news-container {
            float: left;
            width: 48%;
            margin-top: 10px;
            margin-left: 2%;
            height: 336px;
        }
    }

    .second-node {
        margin-top: 30px;
        .notice {
            width: 50%;
            float: left;
        }
        .news-container {
            float: left;
            width: 48%;
            margin-left: 2%;
            height: 336px;
        }
    }

    .imgcard {
        float: left;
        width: 350px;
        margin-right: 30px;
    }
}
</style>