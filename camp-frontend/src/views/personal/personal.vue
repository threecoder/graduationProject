<template>
    <el-row class="allContainer">
        <el-col :span="24" class="header">
            <div style="margin-left:250px;width:100%">
                <h3 class="cursor" @click="toHomePage">协会首页</h3>
                <div class="infoContainer fr">
                    <span>{{name}}</span>
                    <span class="message" @click="toMessage">
                        <i class="el-icon-message-solid"></i>
                        {{unReadNum}}
                    </span>
                    <span class="cursor" @click="logout">退出登录</span>
                </div>
            </div>
        </el-col>

        <el-col :span="24" class="mainContainer">
            <aside>
                <el-menu
                    mode="vertical"
                    :default-active="defaulUrl"
                    :router="true"
                    :unique-opened="true"
                    text-color="#fff"
                >
                    <navmenu v-if="type==1" :data="memberList"></navmenu>
                    <navmenu v-if="type==0" :data="studentList"></navmenu>
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
import { request } from "@/api/request.js";
export default {
    components: {
        navmenu
    },
    data() {
        return {
            studentList: [
                {
                    index: "1",
                    title: "我的信息",
                    children: [{ index: "/info", title: "我的资料" }]
                },
                {
                    index: "",
                    title: "我的证书"
                    // children: [
                    //     { index: "/personal", title: "个人证书" },
                    //     { index: "/personal", title: "证书复审" },
                    //     { index: "/personal", title: "证书变更" }
                    // ]
                },
                {
                    index: "2",
                    title: "我的培训",
                    children: [
                        { index: "/enrolableTraining", title: "可报名的培训" },
                        { index: "/enrolledTraining", title: "已报名的培训" }
                    ]
                },
                {
                    index: "3",
                    title: "我的考试",
                    children: [
                        { index: "/examTodo", title: "需要参加的考试" },
                        { index: "/examDone", title: "已完成的考试" }
                    ]
                },
                {
                    index: "4",
                    title: "我的活动",
                    children: [
                        { index: "/enrolableActivities", title: "可报名活动" },
                        { index: "/enrolledActivities", title: "已报名活动" }
                    ]
                },
                {
                    index: "5",
                    title: "投票",
                    children: [
                        { index: "/canVote", title: "可参与投票" },
                        { index: "/hasVoted", title: "已参与投票" }
                    ]
                }
            ],
            //会员菜单
            memberList: [
                {
                    index: "1",
                    title: "我的信息",
                    children: [{ index: "/info", title: "我的资料" }]
                },
                {
                    index: "2",
                    title: "学员管理",
                    children: [{ index: "/management", title: "挂靠关系" }]
                },
                {
                    index: "3",
                    title: "培训管理",
                    children: [
                        { index: "/enrolableTraining", title: "可报名的培训" },
                        { index: "/enrolledTraining", title: "已报名的培训" }
                    ]
                },
                {
                    index: "4",
                    title: "学员证件",
                    children: [{ index: "", title: "管理证件" }]
                },
                {
                    index: "5",
                    title: "活动报名",
                    children: [
                        { index: "/enrolableActivities", title: "可报名活动" },
                        { index: "/enrolledActivities", title: "已报名活动" }
                    ]
                },
                {
                    index: "6",
                    title: "投票",
                    children: [
                        { index: "/canVote", title: "可参与投票" },
                        { index: "/hasVoted", title: "已参与投票" }
                    ]
                }
            ],
            unReadNum: 0,
            defaulUrl: null
        };
    },
    watch: {
        "$route.path"(newVal) {
            this.defaulUrl = newVal;
            if (newVal != "/message") {
                this.getMessageNum();
            }
        }
    },
    computed: {
        name: function() {
            return this.$store.getters.userName;
        },
        type: function() {
            return this.$store.getters.idType;
        }
    },
    methods: {
        toHomePage() {
            this.$router.push("/");
        },
        logout() {
            try {
                request("/campback/logout", "get").then(res => {
                    this.$message.success("注销成功");
                    this.$router.push("/login");
                });
            } catch (error) {
                this.$router.push("/login");
            }
            this.$store.commit("removeState");
        },
        toMessage() {
            this.$router.push("/message");
        },
        async getMessageNum() {
            try {
                let oldTime = Number(window.localStorage.getItem("msgTime"));
                let newTime = new Date().getTime();
                if (oldTime) {
                    if (newTime - oldTime < 2000) {
                        return false;
                    }
                }
                window.localStorage.setItem("msgTime", newTime);
                console.log("更新未读消息");
                let res = await msgApi.getUnReadMsgNum();
                this.unReadNum = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
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

    & > .el-col:nth-child(1) {
        height: 60px;
    }
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
            &:nth-child(1):after,
            &:nth-child(2):after {
                content: "|";
                height: 60px;
                color: gray;
                margin-left: 15px;
            }
        }
        & > :last-child {
            color: red;
        }
        .message {
            cursor: pointer;
            font-size: 18px;
            color: red;
            &:hover {
                color: rgb(64, 158, 255);
            }
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
        background-color: #2d2d2d;
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
        .el-row {
            height: 100%;
            .el-col {
                height: 100%;
            }
        }
    }
}
</style>
