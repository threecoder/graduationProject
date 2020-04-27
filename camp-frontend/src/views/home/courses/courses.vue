<template>
    <div>
        <courses-menu @switch="changeContent" />
        <div v-loading="loading" class="course-container">
            <div>
                <el-form :model="searchParams" inline>
                    <el-form-item label="培训名称">
                        <el-input v-model="searchParams.keyWord" placeholder="课程关键字"></el-input>
                    </el-form-item>
                    <el-form-item label="培训时间">
                        <el-date-picker
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            v-model="searchParams.date"
                        ></el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" round @click="searchCourses">搜索</el-button>
                    </el-form-item>
                </el-form>
            </div>

            <div class="divider"></div>
            <list v-if="list.length!=0" :list="list" />
            <h3 v-else class="tip">暂无课程</h3>
            <page
                :total="pagination.total"
                :pageSize="pagination.pageSize"
                :currentPage="pagination.currentPage"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import list from "./components/list.vue";
import page from "../../../components/page.vue";
import coursesMenu from "./components/coursesMenu.vue";
import coursesApi from "../../../api/modules/courses.js";
export default {
    components: {
        list,
        page,
        coursesMenu
    },
    data() {
        return {
            type: "now",
            pagination: {
                total: 0,
                pageSize: 10,
                currentPage: 1
            },
            searchParams: {
                keyWord: "",
                date: null,
                startDate: "",
                endDate: ""
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
            ],
            loading: false
        };
    },
    watch: {
        currentPage() {
            //调用api更新数据
            this.searchCourses();
        }
    },
    mounted() {
        this.searchCourses();
    },
    methods: {
        changeContent(val) {
            this.type = val;
            this.searchCourses();
        },
        curChange(val) {
            this.pagination.currentPage = val;
            this.searchCourses();
        },
        async searchCourses() {
            this.loading = true;
            try {
                let params = {
                    ...this.searchParams,
                    ...this.pagination,
                    type: this.type
                };
                let res = await coursesApi.getCourses(params);
                this.list = res.data.list;
                this.pagination.total = res.data.allNum;
                this.pagination.currentPage = res.data.currentPage;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.course-container {
    padding: 10px;
    background-color: #fff;
    overflow: hidden;
}
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