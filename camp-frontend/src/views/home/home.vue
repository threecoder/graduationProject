<template>
    <div>
        <el-menu 
            :class="{'fixed':fixed}"
            mode="horizontal"
            default-active="/"
            :router="true"
            :unique-opened="true">
            <el-submenu index="1">
                <template slot="title">协会介绍</template>
                <el-menu-item index="/hello">发展历史</el-menu-item>
                <el-menu-item index="">师资力量</el-menu-item>
                <el-menu-item index="">品牌价值</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">培训课程介绍</template>
                <el-menu-item index="/login">往期课程</el-menu-item>
                <el-menu-item index="">正在上课</el-menu-item>
                <el-menu-item index="">近期开课</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">协会动态</template>
                <el-menu-item index="">新闻</el-menu-item>
                <el-menu-item index="">活动</el-menu-item>
                <el-menu-item index="">培训</el-menu-item>
            </el-submenu>
            <el-menu-item index="">协会公告</el-menu-item>
            <el-menu-item index="">联系我们</el-menu-item>
            <el-submenu index="" class="fr">
                <template slot="title">个人中心</template>
                <el-menu-item index="">注销登陆</el-menu-item>
            </el-submenu>
        </el-menu>
        <div style="margin-top:60px" v-show="fixed"></div>
        <router-view />
    </div>
</template>

<script>
export default {
    data(){
        return{
           fixed:false
        }
    },
    mounted(){
        this.setNavFixed();
    },
    methods:{
        mouseenter(){
            
        },
        setNavFixed(){
            window.onscroll = () => {
                let top = document.documentElement.scrollTop || document.body.scrollTop;
                if(top>=60) {
                    this.fixed = true;
                }else{
                    this.fixed = false;
                }
            }
        }
    }
}
</script>

<style lang="scss" scoped>
.el-menu {
    padding-left: 100px;
    .el-submenu {
        margin: 0 30px;
    }
    .el-menu-item {
        margin: 0 30px;
    }
}
.fixed {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
}
</style>
