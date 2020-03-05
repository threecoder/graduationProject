<template>
    <div class="index">
        <div class="carousel">
        <carousel :clist="clist"></carousel>
        </div>
        <div class="notice">
            <el-card class="boxcard">
                <div v-for="(notice,no) in nolist" :key="no" class="noitem">
                    <div class="notitle">
                        <el-link type="info" href="notitle.link">{{notice.title}}</el-link>
                    </div>
                    <div class="nodate">
                        {{notice.date}}
                    </div>
                </div>
            </el-card>
        </div>
        <div class="textcard">
            <div class="newscard">
                <newscard :newslist="newslist" :cardname="newscardname"></newscard>
            </div>
            <div class="dynamiccard">
                <newscard :newslist="dynamiclist" :cardname="dynamiccardname"></newscard>
            </div>
        </div>
        <div class="imgcard">
            <imgcard :imglist="imglist" :cardname="imgcardname"></imgcard>
        </div>
    </div>
    
</template>

<script>
import carousel from "./components/carousel.vue";
import newscard from "./components/newscard.vue";
import imgcard from "./components/imgcard.vue";
export default {
    data(){
        return{
            msg:"这里是首页",
            newscardname:{
                name:"协会新闻",
                link:""
            },
            dynamiccardname:{
                name:"协会动态",
                link:""
            },
            imgcardname:{
                name:"协会优秀成果展示",
                link:""
            },
            clist:[
                {
                    url:require('../../assets/images/index1.jpg'),
                    title:"图片1",
                    link:""
                },
                {
                    url: require('../../assets/images/index2.jpg'),
                    title:"图片2",
                    link:""
                },
                {
                    url:require('../../assets/images/index3.jpg'),
                    title:"图片3",
                    link:""
                },
                {
                    url:require('../../assets/images/index4.jpg'),
                    title:"图片4",
                    link:""
                }
            ],
            nolist:[
                {
                    title:"公告1",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告2",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告3",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告4",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告5",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告6",
                    date:"2019-09-01",
                    link:""
                },
                {
                    title:"公告7",
                    date:"2019-09-01",
                    link:""
                }
            ],
            newslist:[
                {
                    title:"新闻1",
                    date:"2019-09-01"
                },
                {
                    title:"新闻2",
                    date:"2019-09-01"
                },
                {
                    title:"新闻3",
                    date:"2019-09-01"
                },
                {
                    title:"新闻4",
                    date:"2019-09-01"
                },
                {
                    title:"新闻5",
                    date:"2019-09-01"
                },
                {
                    title:"新闻6",
                    date:"2019-09-01"
                }
            ],
            dynamiclist:[
                {
                    title:"动态1",
                    date:"2019-09-01"
                },
                {
                    title:"动态2",
                    date:"2019-09-01"
                },
                {
                    title:"动态3",
                    date:"2019-09-01"
                },
                {
                    title:"动态4",
                    date:"2019-09-01"
                },
                {
                    title:"动态5",
                    date:"2019-09-01"
                },
                {
                    title:"动态6",
                    date:"2019-09-01"
                }
            ],
            imglist:[
                {
                    url:require('../../assets/images/index1.jpg'),
                    title:"图片1",
                    link:""
                },
                {
                    url:require('../../assets/images/index1.jpg'),
                    title:"图片2",
                    link:""
                },
                {
                    url:require('../../assets/images/index1.jpg'),
                    title:"图片3",
                    link:""
                }
            ]
        }
    },
    methods:{

    },
    components:{
        carousel,
        newscard,
        imgcard
    }
}
</script>
<style lang="scss">
.index{
    margin-left: 30px;
    margin-top: 30px;
    margin-right: 50px;
    .carousel{
        margin-top: 2px;
    }
    .notice{
        width: 450px;
        height: 390px;
        float: left;
        .el-card__body{
            height: 310px;
        }
        .noitem{
            height: 44px;
            position: relative;
            .notitle{
                margin-top: 7px;
                width: 78%;
                float: left;
                .el-link--inner{
                    font-size: 17px;
                }
            }
            .nodate{
                font-size: 12px;
                float: left;
                right: 0px;
                bottom: 0px;
                position: absolute;
            }
        }
    }
    .textcard{
        float: left;
        .newscard{
            margin-top: 10px;

        }
        .dynamiccard{
            margin-top: 20px;
        }
    }
    .imgcard{
        float: left;
        width: 350px;
        margin-right: 30px;
    }
}
</style>