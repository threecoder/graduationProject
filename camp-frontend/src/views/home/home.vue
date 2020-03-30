<template>
    <div class="all-container">
        <el-menu
            mode="horizontal"
            default-active="/"
            :router="true"
            :unique-opened="true"
            background-color="rgb(64,158,255)"
            text-color="#fff"
            active-text-color="rgb(25,80,139)"
        >
            <el-menu-item class="one" index="/index">首页</el-menu-item>
            <el-submenu index="1">
                <template slot="title">协会介绍</template>
                <el-menu-item index="/brief">协会简介</el-menu-item>
                <el-menu-item index="/constitution">协会章程</el-menu-item>
                <el-menu-item index="/framework">组织架构</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">培训课程</template>
                <el-menu-item index="/courses/previous">往期课程</el-menu-item>
                <el-menu-item index="/courses/now">正在上课</el-menu-item>
                <el-menu-item index="/courses/future">近期开课</el-menu-item>
            </el-submenu>
            <el-menu-item index="/index">新闻</el-menu-item>
            <el-menu-item index="/index">活动</el-menu-item>
            <!-- 
            <el-submenu index="3">
                <template slot="title">协会动态</template>
                <el-menu-item index>新闻</el-menu-item>
                <el-menu-item index>活动</el-menu-item>
                <el-menu-item index>培训</el-menu-item>
            </el-submenu>-->
            <el-menu-item index>协会公告</el-menu-item>
            <el-menu-item index="/contact">联系我们</el-menu-item>
            <el-menu-item index="/personal" class="fr">个人中心</el-menu-item>
        </el-menu>
        <div class="container">
            <router-view :key="$route.path" />
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.all-container {
    background-color: rgb(242, 243, 244);
    height: 100%;
    .el-menu {
        z-index: 999;
        width: 62.5%;
        margin: 0 auto;
        min-width: 1030px;
    }
    .is-opened {
        background-color: rgb(25, 80, 139);
    }
    .container {
        min-width: 1030px;
    }
}
</style>
