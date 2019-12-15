<template>
    <div>
        <h3>{{title}}</h3>
        <search-form @searchInfo="searchCourses" />
        <div class="divider"></div>
        <list v-if="list.length!=0" :list="list" />
        <h3 v-else class="tip">暂无课程</h3>
        <div class="divider"></div>
        <page :total="pagination.total" :pageSize="pagination.pageSize" :currentPage="pagination.currentPage" @curChange="curChange" />
    </div>
</template>
<script>
import searchForm from "./components/searchForm.vue";
import list from "./components/list.vue";
import page from "@/components/page.vue";
import coursesApi from "@/api/modules/courses.js";
export default {
    data() {
        return {
            type: this.$route.params.type,
            pagination: {
                total: 0,
                pageSize: 10,
                currentPage: 1
            },
            searchPar: {
                keyWord:"",
                date:null,
                startDate:"",
                endDate:""
            },
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
            this.searchCourses();
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
        }
    },
    mounted() {
        this.searchCourses();
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
            let params = {
                ...this.searchPar,
                ...this.pagination,
                type: this.type
            };
            let res = await coursesApi.getCourses(params);
            console.log(res);
            this.list = res.data.list;
            this.pagination.total = res.data.allNum;
            this.pagination.currentPage = res.data.currentPage;
        }
    }
};
</script>
<style lang="scss" scoped>
h3 {
    margin: 20px 0 30px;
}
.tip {
    text-align: center;
    width: 100%;
    display: block;
    margin: 100px 0;
}
</style>