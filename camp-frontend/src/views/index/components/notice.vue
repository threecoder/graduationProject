<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">协会公告</span>
                <el-button class="fr more-button" type="text">更多>></el-button>
            </div>
            <ul>
                <li v-for="(newsitem,key) in list" :key="key" class="newsitem">
                    <div class="date-container">
                        <p class="day">{{newsitem.day}}</p>
                        <p class="year-month">{{newsitem.yearAndMonth}}</p>
                    </div>
                    <div class="info-container">
                        <p class="news-title">{{newsitem.title}}</p>
                    </div>
                </li>
            </ul>
        </el-card>
    </div>
</template>
<script>
export default {
    props: ["noticeList"],
    data() {
        return {
            list: []
        };
    },
    mounted() {
        this.handleData();
    },
    methods: {
        getmore(str) {},
        handleData() {
            this.noticeList.forEach(val => {
                let obj = { ...val };
                let temArr = obj.date.split("-");
                obj.day = temArr.pop();
                obj.yearAndMonth = temArr.join("-");
                delete obj.date;
                this.list.push(obj);
            });
        }
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
            height: 66px;
            line-height: 66px;
            padding-left: 1%;
            width: 49%;
            float: left;
            margin-bottom: 20px;
            &:hover {
                .news-title {
                    cursor: pointer;
                    color: rgb(64, 158, 255) !important;
                }
            }
            .date-container {
                width: 60px;
                text-align: center;
                height: 66px;
                float: left;
                p {
                    border: 1px solid rgba(64, 158, 255, 0.5);
                }
                p.day {
                    height: 40px;
                    line-height: 40px;
                    color: #007cda;
                    font-weight: bold;
                    border-bottom: none;
                }
                p.year-month {
                    line-height: 22px;
                    height: 22px;
                    color: #a19d97;
                    font-size: 13px;
                }
            }
            .info-container {
                float: left;
                height: auto;
                .news-title {
                    font-size: 17px;
                    color: rgb(68, 68, 68);
                    line-height: 30px;
                    padding-left: 10px;
                }
            }
        }
    }
}
</style>