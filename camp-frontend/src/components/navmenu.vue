<template>
    <div>
        <template v-for="(item,index) in data">
            <el-menu-item :key="index" :index="item.index" v-if="!item.children">{{item.title}}</el-menu-item>
            <el-submenu :key="index" :index="item.index" v-if="item.children">
                <template slot="title">{{item.title}}</template>
                <navmenu :data="item.children"></navmenu>
            </el-submenu>
        </template>
    </div>
</template>


<script>
export default {
    name: "navmenu",
    props: ["data"]
};
</script>
<style lang="scss" scoped>
::v-deep .el-menu-item.is-active {
    background-color: #c43938 !important;
    color: #ffffff !important;
    border-radius: 5px;
    margin: 0 10px;
    &:after {
        background: transparent !important;
    }
}
::v-deep .el-menu-item,
::v-deep .el-submenu__title {
    &:hover {
        background-color: #c43938 !important;
        color: #fff !important;
        border-radius: 6px;
        margin: 0 10px;
        &:after {
            background: transparent !important;
        }
    }
    &:active,
    &:visited {
        background-color: #c43938 !important;
    }
}
</style>
