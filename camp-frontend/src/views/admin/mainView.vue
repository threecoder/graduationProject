<template>
    <el-row class="allContainer">
        <el-col :span="24" class="header">
            <div style="margin-left:250px;width:100%">
                <h3 class="cursor" @click="toHomePage">协会首页</h3>
                <div class="infoContainer fr">
                    <span>{{name}}</span>
                    <span class="cursor" @click="logout">退出登录</span>
                </div>
            </div>
        </el-col>

        <el-col :span="24" class="mainContainer">
            <aside>
                <el-menu
                    mode="vertical"
                    default-active="/login"
                    :router="true"
                    :unique-opened="true"
                    text-color="#fff"
                    active-text-color="#fff"
                >
                    <navmenu :data="menuList"></navmenu>
                </el-menu>
            </aside>
            <section class="content-container">
                <el-row>
                    <el-col :span="24" class>
                        <transition name="fade" mode="out-in">
                            <router-view :key="$route.path" />
                        </transition>
                    </el-col>
                </el-row>
            </section>
        </el-col>
    </el-row>
</template>
<script>
import navmenu from "../../components/navmenu.vue";
import { getLocalStorage } from "@/assets/js/util.js";
export default {
    data() {
        return {
            menuList: [
                {
                    index: "1",
                    title: "会员管理",
                    children: [
                        { index: "", title: "我的会员" },
                        { index: "", title: "会员统计" },
                    ]
                },
                {
                    index: "2",
                    title: "学员管理",
                    children: [
                        { index: "", title: "挂靠管理" },
                        { index: "", title: "学员信息" }
                    ]
                },
                {
                    index: "3",
                    title: "培训管理",
                    children: [
                        { index: "", title: "发布培训" },
                        { index: "", title: "已发布的培训" }
                    ]
                },
                {
                    index: "4",
                    title: "考试管理",
                    children: [
                        { index: "/question", title: "试题管理" },
                        { index: "", title: "发布考试" },
                        { index: "", title: "已发布的考试" },
                        { index: "", title: "成绩审核" }
                    ]
                },
                {
                    index: "5",
                    title: "证书管理",
                    children: [
                        { index: "", title: "设置证书" },
                        { index: "", title: "发放证书" },
                        { index: "", title: "证书审核" }
                    ]
                },
                {
                    index: "6",
                    title: "活动管理",
                    children: [
                        { index: "/publicActivity", title: "发布活动" },
                        { index: "/activitiesList", title: "已发布活动" }
                    ]
                },
                {
                    index: "7",
                    title: "投票管理",
                    children: [
                        { index: "", title: "发起投票" },
                        { index: "", title: "已发起的投票" },
                    ]
                },
                {
                    index: "8",
                    title: "前台网站管理",
                    children: [
                        { index: "", title: "协会新闻" },
                        { index: "", title: "协会资料" }
                    ]
                },
                {
                    index: "9",
                    title: "系统设置",
                    children: [{ index: "", title: "参与投票" }]
                },
                {
                    index: "10",
                    title: "管理员权限管理",
                    children: [{ index: "", title: "参与投票" }]
                }
            ],
            name: getLocalStorage("user").name
        };
    },
    components: {
        navmenu
    },
    methods: {
        toHomePage() {
            this.$router.push("/");
        },
        logout() {
            window.localStorage.removeItem("token");
            this.$router.push("/login");
        }
    }
};
</script>
<style lang="scss" scoped>
.allContainer {
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    overflow-x: auto;

    & > .el-col:nth-child(1) {
        height: 60px;
    }
    .header {
        display: flex;
        border-bottom: 1px solid #ccc;
        h3 {
            display: inline-block;
            height: 60px;
            line-height: 60px;
            margin: 0 !important;
        }
        .infoContainer {
            display: inline-block;
            height: 60px;
            text-align: center;
            margin-right: 60px;
            line-height: 60px;
            span {
                padding: 0 10px;
                &:first-child::after {
                    content: "|";
                    height: 60px;
                    color: gray;
                    margin-left: 15px;
                }
            }
            & > :nth-child(2) {
                color: red;
            }
        }
    }
    .mainContainer {
        display: flex;
        position: absolute;
        top: 61px;
        bottom: 0px;
        overflow: hidden;
        aside {
            flex: 0 0 250px;
            padding-top: 20px;
            font-size: 16px;
            overflow-y: auto;
            width: 250px !important;
            background-color: rgb(54, 65, 80);
            padding-top: 30px;
            ::v-deep .el-submenu__title {
                font-size: 16px;
                color: #fff;
                &:hover {
                    background: none;
                }
            }
            ::v-deep .el-menu {
                height: 100%;
                border: 0;
                background: none;
            }
            ::v-deep .el-submenu .el-menu-item {
                padding-left: 70px !important;
            }
            ::v-deep .el-menu-item:hover {
                background-color: rgb(28,175, 157)!important;
            }
            ::v-deep .el-submenu__title:hover {
                background-color: rgb(28,175, 157)!important;
            }
            ::v-deep .el-submenu .el-menu .el-submenu .el-submenu__title span {
                padding-left: 30px !important;
            }
            /*导航icon*/
            ::v-deep .el-menu-item {
                color: #fff;
                position: relative;
                font-size: 16px;
            }
        }
        section.content-container {
            flex: 1;
            overflow-y: auto;
            padding: 30px 40px;
            width: auto;
            background-color: rgb(244, 244, 244);
        }
    }
}
</style>
