<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">{{cardInfo.name}}</span>
                <el-button class="fr more-button" type="text">更多>></el-button>
            </div>
            <ul>
                <li v-for="(newsitem,key) in newslist" :key="key" class="newsitem">
                    <span class="news-title">{{newsitem.title}}</span>
                    <span class="newsdate">{{newsitem.date}}</span>
                </li>
            </ul>
        </el-card>
    </div>
</template>
<script>
export default {
    props: ["newslist", "cardInfo"],
    data() {
        return {};
    },
    methods: {
        getmore(str) {}
    }
};
</script>
<style lang="scss" scoped>
.box-card {
    width: 100%;
    .more-button {
        padding: 3px 0;
        color: rgb(96, 98, 102);
        &:hover {
            color: rgb(64, 158, 255);
        }
    }
    .title {
        color: rgb(64, 158, 255);
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