<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title">协会公告</span>
                <el-button class="fr more-button" type="text" @click="getMore">更多>></el-button>
            </div>
            <ul v-loading="loading" v-if="list.length!=0">
                <li v-for="(item,key) in list" :key="key" class="notice-item">
                    <div class="date-container">
                        <p class="day">{{item.day}}</p>
                        <p class="year-month">{{item.yearAndMonth}}</p>
                    </div>
                    <div class="info-container">
                        <p class="news-title" @click="detail(item)">{{shortStr(item.title)}}</p>
                    </div>
                </li>
            </ul>
            <p v-else class="tac">暂无数据</p>
        </el-card>
    </div>
</template>
<script>
import noticeApi from "../../../../api/index/notice";
export default {
    data() {
        return {
            list: [],
            loading: false
        };
    },
    mounted() {
        // this.handleData(this.list);
        this.getNoticeList();
    },
    methods: {
        getMore() {
            this.$router.push("/notice");
        },
        handleData(data) {
            data.forEach(val => {
                let obj = { ...val };
                let temArr = obj.date.split(" ")[0];
                temArr = temArr.split("-");
                obj.day = temArr.pop();
                obj.yearAndMonth = temArr.join("-");
                delete obj.date;
                this.list.push(obj);
            });
        },
        async getNoticeList() {
            this.loading = true;
            let par = { currentPage: 1, pageSize: 6 };
            try {
                this.list = [];
                let res = await noticeApi.getNoticeList(par);
                this.handleData(res.data.list);
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        detail(item) {
            this.$router.push(`/dynamicDetail?type=notice&id=${item.id}`);
        },
        shortStr(str) {
            if (str.length > 16) {
                return str.substring(0, 16) + "......";
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
        // color: rgb(64, 158, 255);
        color: rgb(86, 88, 92);
        font-weight: 700;
    }
    ul {
        list-style: none;
        li.notice-item {
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
                width: calc(100% - 60px);
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