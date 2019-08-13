<template>
    <el-row class="allContainer">
        <el-col :span="24" class="header">
            <div style="margin-left:250px;width:100%">
                <h3 @click="toHomePage">协会首页</h3>
                <div class="infoContainer fr">
                    <span>admin</span>
                    <span>退出登录</span>
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
                    text-color="#fff">
                    <navmenu :data="menuList"></navmenu>
                </el-menu>
            </aside>
            <section class="content-container">
                <el-row>
                    <el-col :span="24" class="">
                        <transition name="fade" mode="out-in">
                            <router-view />
                        </transition>
                    </el-col>
                </el-row>
            </section>
        </el-col>
    </el-row>
</template>
<script>
import navmenu from '../../components/navmenu.vue'
export default {
    mounted(){
        // document.getElementsByTagName("aside")[0].style.height = window.innerHeight+'px';
    },
    data(){
        return{
            menuList: [
                {
                    index: "",
                    title: "协会介绍",
                    children: [
                        { index: "/hello", title: "发展历史" },
                        { index: "/login", title: "师资力量" },
                        { index: "/personal", title: "品牌价值" }
                    ]
                },
                {
                    index: "/personal/2",
                    title: "培训课程介绍",
                    children: [
                        { index: "/personal", title: "往期课程" },
                        { index: "/personal", title: "正在上课" },
                        { index: "/personal", title: "近期开课" }
                    ]
                },
                {
                    index: "/personal/3",
                    title: "协会动态",
                    children: [
                        { index: "/personal", title: "新闻" },
                        { index: "/personal", title: "活动" },
                        { index: "/personal", title: "培训" }
                    ]
                }
            ]
        }
    },
    components:{
        navmenu
    },
    methods:{
        toHomePage(){
            this.$router.push("/");
        }
    }
}
</script>
<style lang="scss" scoped>
.allContainer {
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    overflow-x: auto;
    // min-width: 1280px;

    &>.el-col:nth-child(1) {
        height:60px;
    }
}
.header {
    display: flex;
    border-bottom: 1px solid #ccc;
    h3 {
        display: inline-block;
        height: 60px;
        line-height: 60px;
        margin: 0!important;
    }
    .infoContainer {
        display: inline-block;
        height: 60px;
        text-align: center;
        margin-right: 60px;
        line-height: 60px;
        span {
            padding: 0 10px;
            &:first-child::after{
                content: "|";
                height: 60px;
                color: gray;
                margin-left: 15px;
            }
        }
        &>:nth-child(2) {
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
        width: 250px!important;
        background-color: #2d2d2d;
        padding-top: 30px;
        ::v-deep .el-submenu__title{
            font-size: 16px;
            color:#fff;
            &:hover{
                background: none;
            }
        }
        ::v-deep .el-menu {
            height: 100%;
            border:0;
            background: none;
        }
        ::v-deep .el-submenu .el-menu-item{
            padding-left:70px!important;
        }
        ::v-deep .el-submenu .el-menu .el-submenu .el-submenu__title span{
            padding-left:30px!important;
        }
        /*导航icon*/
        ::v-deep .el-menu-item{
            color:#fff;
            position: relative;
            font-size: 16px;
        }
    }
    section.content-container {
        flex: 1;
        overflow-y: auto;
    }
}

</style>
