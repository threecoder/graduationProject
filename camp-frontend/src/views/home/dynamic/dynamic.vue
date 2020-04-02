<template>
    <div class="new-notice-container">
        <div class="info-container">
            <ul>
                <li v-for="(item,i) in list" :key="i">
                    <span class="title">{{item.title}}</span>
                    <div class="content">
                        <img :src="item.src" alt="新闻图片" :width="item.src?'130px':'0px'" />
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
export default {
    components: {
        page
    },
    data() {
        return {
            type: this.$route.path,
            form: {
                currentPage: 1,
                pageSize: 10,
                total: 100
            },
            list: [
                {
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
            ]
        };
    },
    methods: {
        curChange(val) {
            this.form.currentPage = val;
            this.getList();
        },
        async getList() {
            try {
                let res = null;
                if (this.type == "news") {
                } else {
                }
            } catch (error) {
                this.$message.error(error.message);
            }
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
                span.title {
                    line-height: 40px;
                    font-size: 18px;
                    color: rgb(50, 50, 50);
                }
                .content {
                    margin-top: 10px;
                    margin-left: 20px;
                    overflow: hidden;
                    img {
                        float: left;
                        // width: 130px;
                        height: 90px;
                    }
                    p {
                        float: left;
                        margin-left: 20px;
                        width: 540px;
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