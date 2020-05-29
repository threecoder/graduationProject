<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">{{cardInfo.name}}</span>
                <el-button class="fr more-button" type="text" @click="getMore">更多>></el-button>
            </div>
            <ul v-if="list.length!=0" v-loading="loading">
                <li v-for="(newsitem,key) in list" :key="key" class="newsitem">
                    <span class="news-title" @click="detail(newsitem)">{{shortStr(newsitem.title)}}</span>
                    <span class="newsdate">{{newsitem.date}}</span>
                </li>
            </ul>
            <p v-else class="tac">暂无数据</p>
        </el-card>
    </div>
</template>
<script>
import newsApi from "../../../../api/index/news";
import dynamicApi from "../../../../api/index/dynamic";
export default {
    props: ["cardInfo"],
    data() {
        return {
            list: [
                // {
                //     title: "新闻新闻新闻新闻新闻新闻新闻新闻新闻",
                //     date: "2019-09-01",
                //     id: "111"
                // }
            ],
            loading: false
        };
    },
    mounted() {
        this.getList();
    },
    methods: {
        async getList() {
            this.loading = true;
            try {
                let res = null;
                let par = {
                    currentPage: 1,
                    pageSize: 7
                };
                if (this.cardInfo.name == "协会新闻") {
                    res = await newsApi.getNewsList(par);
                } else {
                    res = await dynamicApi.getDynamicList(par);
                }
                this.list = res.data.list;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        getMore() {
            let path = null;
            if (this.cardInfo.name == "协会新闻") {
                path = "/news";
            } else {
                path = "/activities";
            }
            this.$router.push(path);
        },
        detail(newsitem) {
            if (this.cardInfo.name == "协会新闻") {
                this.$router.push(`/dynamicDetail?type=news&id=${newsitem.id}`);
            } else {
                this.$router.push(
                    `/dynamicDetail?type=dynamic&id=${newsitem.id}`
                );
            }
        },
        shortStr(str) {
            if(str.length>18) {
                return str.substring(0,12) + "......"
            }
            return str;
        }
    }
};
</script>
<style lang="scss" scoped>
.box-card {
    width: 100%;
    min-height: 370px;
    ::v-deep.el-card__header {
        background-image: linear-gradient(
            rgb(187, 221, 230),
            rgb(255, 255, 255)
        );
    }
    .more-button {
        padding: 3px 0;
        color: rgb(96, 98, 102);
        &:hover {
            color: rgb(64, 158, 255);
        }
    }
    .title {
        color: rgb(96, 98, 102);
        font-weight: 700;
    }
    ul {
        list-style: none;
        li.newsitem {
            height: 40px;
            line-height: 40px;
            position: relative;
            padding-left: 10px;
            &::after {
                content: "";
                width: 5px;
                height: 5px;
                background-color: #cca870;
                display: block;
                position: absolute;
                left: 0;
                top: 15px;
            }
            .news-title {
                font-size: 17px;
                color: #000;
                &:hover {
                    cursor: pointer;
                    color: rgb(64, 158, 255);
                }
            }
            .newsdate {
                font-size: 16px;
                color: #999;
                float: right;
            }
        }
    }
}
</style>