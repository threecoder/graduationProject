<template>
    <div>
        <el-menu
            mode="horizontal"
            :default-active="defaultPath"
            :router="true"
            :unique-opened="true"
            text-color="rgb(96,98,102)"
            active-text-color="rgb(25,80,139)"
        >
            <el-menu-item class="one" index="/index">首页</el-menu-item>
            <el-menu-item class="one" index="/introduction">协会介绍</el-menu-item>
            <el-menu-item class="one" index="/courses">协会介绍</el-menu-item>
            <el-menu-item index="/news">新闻</el-menu-item>
            <el-menu-item index="/activities">活动</el-menu-item>
            <el-menu-item index="/notice">协会公告</el-menu-item>
            <el-menu-item index="/contact">联系我们</el-menu-item>
            <el-menu-item index="/personal" class="fr">个人中心</el-menu-item>
        </el-menu>
    </div>
</template>
<script>
export default {
    data() {
        return {
            defaultPath: "/index"
        };
    },
    mounted() {
        this.defaultPath = this.$route.path;
    },
    watch: {
        "$route.path"(newVal) {
            this.defaultPath = newVal;
        }
    }
};
</script>
<style lang="scss" scoped>
.el-menu {
    z-index: 999;
    width: 62.5%;
    margin: 0 auto;
    min-width: 1030px;
    background-image: linear-gradient(rgb(64, 158, 255), rgb(255, 255, 255));
    // .is-active {
    //     background-image: linear-gradient(#409eff, rgb(255, 255, 255));
    // }
    .el-menu-item {
        &:hover,
        &:focus {
            background-image: linear-gradient(#409eff, rgb(255, 255, 255));
        }
    }
    ::v-deep .el-submenu__title {
        &:hover,
        &:focus {
            background-image: linear-gradient(#409eff, rgb(255, 255, 255));
        }
    }
}
// .is-opened {
//     background-color: rgb(25, 80, 139);
// }
</style>