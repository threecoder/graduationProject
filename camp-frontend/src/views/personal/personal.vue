<template>
    <el-container>
        <el-header>
            <div class="container">
                <h3 @click="toHomePage">协会首页</h3>
                <div class="infoContainer">
                    <span>admin</span>
                    <span>退出登录</span>
                </div>
            </div>
        </el-header>
        <el-container>
            <aside>
                <el-menu
                    mode="vertical"
                    default-active="/personal"
                    :router="true"
                    :unique-opened="true"
                    text-color="#fff">
                    <navmenu :data="menuList"></navmenu>
                </el-menu>
            </aside>
            <el-main></el-main>
        </el-container>
    </el-container>
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
                    index: "/personal",
                    title: "协会介绍",
                    children: [
                        { index: "/personal", title: "发展历史" },
                        { index: "/personal", title: "师资力量" },
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
.el-header {
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
        position: relative;
        left: 70%;
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
aside {
    // padding-top: 20px;
    font-size: 16px;
    flex: 0 0 250px;
    width: 250px;
    text-align: center;
    background-color: #2b2b2b;
    overflow-y: auto;
    ::v-deep .el-menu {
        height: 100%;
        background: none;
        border:0;
    }
    ::v-deep .el-menu-item {
        background-color: #2b2b2b;
    }
}
</style>
