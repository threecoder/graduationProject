<template>
    <div class="notice-container">
        <div class="info-container">
            <ul>
                <li v-for="(item,i) in list" :key="i">
                    <span class="title" @click="detail(item.id)">
                        <i class="el-icon-s-flag"></i>
                        {{item.title}}
                    </span>
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
import noticeApi from "../../../api/index/notice";
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
                    id: "111",
                    title: "科威特新增25例新冠肺炎确诊病例",
                    date: "2020-04-02 21:05"
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
            try {
                let res = await noticeApi.getNoticeList(this.form);
                this.form.total = res.data.total;
                this.list = res.data.list;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        detail(id) {
            this.$router.push(`/dynamicDetail?type=notice&id=${id}`);
        }
    }
};
</script>

<style lang="scss" scoped>
.notice-container {
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
                height: 60px;
                padding: 10px 5px;
                &:last-of-type {
                    border-bottom: none;
                }
                span.title {
                    line-height: 40px;
                    font-size: 18px;
                    color: rgb(50, 50, 50);
                    &:hover {
                        color: red;
                        cursor: pointer;
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