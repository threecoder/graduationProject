<template>
    <div class="notice-container">
        <div class="info-container">
            <ul>
                <li v-for="(item,i) in list" :key="i">
                    <span class="title">
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