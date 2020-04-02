<template>
    <div class="index">
        <div class="first-node">
            <div class="carousel-container">
                <carousel :clist="clist"></carousel>
            </div>
            <div class="news-container">
                <div class="newscard">
                    <newscard :newslist="newslist" :cardInfo="newscardname"></newscard>
                </div>
            </div>
        </div>

        <div class="second-node">
            <div class="notice">
                <noticecard :noticeList="nolist" />
            </div>
            <div class="news-container">
                <newscard :newslist="dynamiclist" :cardInfo="dynamiccardname"></newscard>
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
            clist: [
                {
                    url: require("../../../assets/images/index1.jpg"),
                    title: "图片1",
                    link: ""
                },
                {
                    url: require("../../../assets/images/index2.jpg"),
                    title: "图片2",
                    link: ""
                },
                {
                    url: require("../../../assets/images/index3.jpg"),
                    title: "图片3",
                    link: ""
                },
                {
                    url: require("../../../assets/images/index4.jpg"),
                    title: "图片4",
                    link: ""
                }
            ],
            nolist: [
                {
                    title: "公告1",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告2",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告3",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告4",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告5",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告6",
                    date: "2019-09-01",
                    link: ""
                },
                {
                    title: "公告7",
                    date: "2019-09-01",
                    link: ""
                }
            ],
            newslist: [
                {
                    title: "新闻新闻新闻新闻新闻新闻新闻新闻新闻",
                    date: "2019-09-01"
                },
                {
                    title: "新闻2",
                    date: "2019-09-01"
                },
                {
                    title: "新闻3",
                    date: "2019-09-01"
                },
                {
                    title: "新闻4",
                    date: "2019-09-01"
                },
                {
                    title: "新闻5",
                    date: "2019-09-01"
                },
                {
                    title: "新闻6",
                    date: "2019-09-01"
                },
                {
                    title: "新闻7",
                    date: "2019-09-01"
                },
                {
                    title: "新闻8",
                    date: "2019-09-01"
                }
            ],
            dynamiclist: [
                {
                    title: "动态1",
                    date: "2019-09-01"
                },
                {
                    title: "动态2",
                    date: "2019-09-01"
                },
                {
                    title: "动态3",
                    date: "2019-09-01"
                },
                {
                    title: "动态4",
                    date: "2019-09-01"
                },
                {
                    title: "动态5",
                    date: "2019-09-01"
                },
                {
                    title: "动态6",
                    date: "2019-09-01"
                },
                {
                    title: "动态6",
                    date: "2019-09-01"
                },
                {
                    title: "动态6",
                    date: "2019-09-01"
                }
            ],
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