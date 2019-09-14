<template>
    <div>
        <h3>{{title}}</h3>
        <search-form @searchInfo="searchCourses" />
        <div class="divider"></div>
        <list :list="list" />
        <page :total="100" :pageSize="10" :currentPage="1" @curChange="curChange" />
    </div>
</template>
<script>
import searchForm from "./components/searchForm.vue";
import list from "./components/list.vue";
import page from "@/components/page.vue";
import { getCourses } from "@/api/modules/courses.js";
export default {
    data() {
        return {
            type: this.$route.params.type,
            pagination: {
                total: null,
                pageSize: 10,
                currentPage: 1
            },
            searchPar: null,
            list: [
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                },
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                },
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                },
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                },
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                },
                {
                    url:
                        "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                    title: "初试HTML+CSS",
                    level: "入门",
                    viewers: 1063251,
                    abstract:
                        "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                }
            ]
        };
    },
    components: {
        searchForm,
        list,
        page
    },
    watch: {
        currentPage() {
            //调用api更新数据
            console.log("ss");
        }
    },
    computed: {
        title() {
            if (this.type == "previous") {
                return "往期课程";
            } else if (this.type == "now") {
                return "正在上课";
            } else if (this.type == "future") {
                return "近期开课";
            }
            let obj = {
                code: 200,
                data: [
                    {
                        url:
                            "https://img4.mukewang.com/529dc3380001379906000338-240-135.jpg",
                        title: "初试HTML+CSS",
                        level: "入门",
                        viewers: 1063251,
                        abstract:
                            "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义"
                    }
                ]
            };
            console.log(JSON.stringify(obj));
        }
    },
    mounted() {
        console.log(this.type);
    },
    methods: {
        curChange(val) {
            this.pagination.currentPage = val;
            this.searchCourses();
        },
        async searchCourses(par) {
            if (par) {
                this.searchPar = par;
            }
            this.searchPar = par;
            let params = {
                ...par,
                ...this.pagination,
                type: this.type
            };
            let res = await getCourses(params);
            this.list = res.data;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>