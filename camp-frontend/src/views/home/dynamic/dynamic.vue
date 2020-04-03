<template>
    <div class="new-notice-container">
        <div class="info-container">
            <ul v-loading="loading">
                <li v-for="(item,i) in list" :key="i">
                    <span class="title" @click="detail(item.id)">{{item.title}}</span>
                    <div class="content">
                        <!-- <img :src="item.src" alt="新闻图片" :width="item.src?'130px':'0px'" /> -->
                        <img src="../../../assets/images/index1.jpg" alt="新闻图片" width="130px" />
                        <p>{{item.desc}}</p>
                    </div>
                    <p class="date">{{item.date}}</p>
                </li>
            </ul>
        </div>
        <div class="page-container">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
    </div>
</template>

<script>
import page from "../../../components/page.vue";
import newsApi from "../../../api/index/news";
import dynamicApi from "../../../api/index/dynamic";
export default {
    components: {
        page
    },
    data() {
        return {
            path: this.$route.path,
            form: {
                currentPage: 1,
                pageSize: 10,
                total: 100
            },
            list: [
                {
                    id: 111,
                    title: "科威特新增25例新冠肺炎确诊病例",
                    desc:
                        "原标题：钟南山：疫情防控关键是保持距离、戴口罩 新京报快讯（记者 徐美慧）4月2日，新冠疫情防控经验国际分享会暨健康中国国际公共卫生管理培训项目启动会以线上直播的方...",
                    date: "2020-04-02 21:05",
                    src: "../../../assets/images/index1.jpg"
                },
                {
                    title: "科威特新增25例新冠肺炎确诊病例",
                    date: "2020-04-02 21:05"
                },
                {
                    title: "科威特新增25例新冠肺炎确诊病例",
                    date: "2020-04-02 21:05"
                }
            ],
            loading: false
        };
    },
    mounted() {
        let dynamic = this.$store.getters.dynamic;
        if (dynamic.path == this.path) {
            this.form.currentPage = dynamic.currentPage;
        } else {
            this.form.currentPage = 1;
            this.$store.commit("setDynamic", {
                path: this.path,
                currentPage: 1
            });
        }
        this.getList();
    },
    methods: {
        curChange(val) {
            this.form.currentPage = val;
            this.$store.commit("setDynamic", {
                currentPage: val,
                path: this.path
            });
            this.getList();
        },
        async getList() {
            this.loading = true;
            try {
                let res = null;
                if (this.type == "news") {
                    res = await newsApi.getNewsList(this.form);
                } else {
                    res = await dynamicApi.getDynamicList(this.form);
                }
                this.form.total = res.data.total;
                this.list = res.data.list;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        detail(id) {
            this.$router.push(`/dynamicDetail?type=${this.path}&id=${id}`);
        }
    }
};
</script>

<style lang="scss" scoped>
.new-notice-container {
    background-color: #fff;
    overflow: hidden;
    padding: 20px 0;
    .info-container {
        ul {
            width: 60%;
            margin: 0 auto;
            list-style: none;
            padding: 10px;
            border: 1px solid rgba(0, 0, 0, 0.2);
            li {
                border-bottom: 1px solid rgba(0, 0, 0, 0.1);
                // height: 80px;
                padding-bottom: 10px;
                &:hover {
                    span.title {
                        color: red;
                        cursor: pointer;
                    }
                }
                &:last-of-type {
                    border-bottom: none;
                }
                span.title {
                    line-height: 40px;
                    font-size: 18px;
                    color: rgb(50, 50, 50);
                }
                .content {
                    margin-top: 10px;
                    margin-left: 20px;
                    overflow: hidden;
                    display: flex;
                    justify-content: space-around;
                    img {
                        float: left;
                        // width: 130px;
                        height: 90px;
                    }
                    p {
                        float: left;
                        margin-left: 20px;
                        // width: 540px;
                        color: rgba(96, 98, 102, 0.8);
                    }
                }
                p.date {
                    line-height: 15px;
                    margin-top: 5px;
                    color: rgba(96, 98, 102, 0.8);
                }
            }
        }
    }
}
</style>>