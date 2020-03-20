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
import msgApi from "../../api/admin/message";
import { getLocalStorage } from "../../assets/js/util";
import event from "../../assets/js/eventBus";
export default {
    data() {
        return {
            menuList: [
                {
                    index: "1",
                    title: "会员管理",
                    children: [
                        { index: "/managerMember", title: "我的会员" },
                        { index: "", title: "会员统计" }
                    ]
                },
                {
                    index: "2",
                    title: "学员管理",
                    children: [
                        { index: "/studentInfo", title: "学员信息及挂靠管理" },
                        { index: "/studentRecord", title: "学员培训记录以及证书" }
                    ]
                },
                {
                    index: "3",
                    title: "培训管理",
                    children: [
                        { index: "/publishTraining", title: "发布培训" },
                        { index: "/publishedTraining", title: "已发布的培训" }
                    ]
                },
                {
                    index: "4",
                    title: "考试管理",
                    children: [
                        { index: "/question", title: "试题管理" },
                        { index: "/toPublic", title: "发布考试" },
                        { index: "/published", title: "已发布的考试" },
                        { index: "/checkGrade", title: "成绩审核" }
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
                        { index: "/publishVote", title: "发起投票" },
                        { index: "/publishedVote", title: "已发起的投票" }
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
                    children: [
                        { index: "", title: "功能设置" },
                        { index: "", title: "管理员账号管理" }
                    ]
                },
                {
                    index: "10",
                    title: "管理员权限管理",
                    children: [{ index: "", title: "参与投票" }]
                }
            ],
            name: getLocalStorage("user").name,
            unReadNum: 0,
            defaulUrl: null
            // name: 'sasds'
        };
    },
    components: {
        navmenu
    },
    watch: {
        "$route.path"(newVal) {
            this.defaulUrl = newVal;
            if (newVal != "/message") {
                this.getMessageNum();
            }
        }
    },
    mounted() {
        this.getMessageNum();
    },
    methods: {
        toHomePage() {
            this.$router.push("/");
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
        },
        logout() {
            window.localStorage.removeItem("token");
            this.$router.push("/adminLogin");
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
    // min-width: 1700px;
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
            background-color: rgb(54, 65, 80);
            padding-top: 30px;
            ::v-deep .el-submenu__title {
                font-size: 16px;
                color: #fff;
                &:hover {
                    background: none;
                }
                i {
                    color: #fff;
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
                background-color: rgb(28, 175, 157) !important;
            }
            ::v-deep .el-submenu__title:hover {
                background-color: rgb(28, 175, 157) !important;
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
}
</style>
