<template>
    <div class="all-container">
        <el-menu
            :class="{'fixed':fixed}"
            mode="horizontal"
            default-active="/"
            :router="true"
            :unique-opened="true"
            background-color="rgb(64,158,255)"
            text-color="#fff"
            active-text-color="rgb(25,80,139)"
        >
            <el-menu-item class="one" index>首页</el-menu-item>
            <el-submenu index="1">
                <template slot="title">协会介绍</template>
                <el-menu-item index>发展历史</el-menu-item>
                <el-menu-item index>师资力量</el-menu-item>
                <el-menu-item index>品牌价值</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">培训课程</template>
                <el-menu-item index="/courses/previous">往期课程</el-menu-item>
                <el-menu-item index="/courses/now">正在上课</el-menu-item>
                <el-menu-item index="/courses/future">近期开课</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">协会动态</template>
                <el-menu-item index>新闻</el-menu-item>
                <el-menu-item index>活动</el-menu-item>
                <el-menu-item index>培训</el-menu-item>
            </el-submenu>
            <el-menu-item index>协会公告</el-menu-item>
            <el-menu-item index="/contact">联系我们</el-menu-item>
            <el-menu-item index="/personal" class="fr">个人中心</el-menu-item>
        </el-menu>
        <div style="margin-top:60px" v-show="fixed"></div>
        <div class="container">
            <router-view :key="$route.path" />
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            fixed: false
        };
    },
    mounted() {
        this.setNavFixed();
    },
    methods: {
        mouseenter() {},
        setNavFixed() {
            window.onscroll = () => {
                let top =
                    document.documentElement.scrollTop ||
                    document.body.scrollTop;
                if (top >= 60) {
                    this.fixed = true;
                } else {
                    this.fixed = false;
                }
            };
        }
    }
};
</script>

<style lang="scss" scoped>
.el-menu {
    padding-left: 5%;
    // background-color: black;
    .one {
        margin-left: 16%!important;
    }
    .el-submenu {
        margin: 0 1.5%;
    }
    .el-menu-item {
        margin: 0 1.5%;
    }
    z-index: 999999;
}
.fixed {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
}
.is-opened {
    background-color: rgb(25,80,139);
}
</style>
