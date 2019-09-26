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
  props: ["data"],
  mounted() {},
  data() {
    return {
      menuList: [
        {
          index: "",
          title: "协会介绍",
          children: [
            { index: "", title: "协会简介" },
            { index: "", title: "协会章程" },
            { index: "1.3", title: "组织结构" }
          ]
        },
        {
          index: "2",
          title: "培训课程介绍",
          children: [
            { index: "2.1", title: "往期课程" },
            { index: "2.2", title: "正在上课" },
            { index: "2.3", title: "近期开课" }
          ]
        },
        {
          index: "3",
          title: "协会动态",
          children: [
            { index: "3.1", title: "新闻" },
            { index: "3.1", title: "活动" },
            { index: "3.1", title: "培训" }
          ]
        }
      ]
    };
  }
};
</script>
<style lang="scss" scoped>
::v-deep .el-menu-item.is-active {
  background-color: #c43938!important;
  color: #ffffff!important;
  border-radius: 5px;
  margin: 0 10px;
  &:after {
      background: transparent!important;
    }
}
::v-deep .el-menu-item,
::v-deep .el-submenu__title {
    &:hover {
      background-color: #c43938!important;
      color: #fff!important;
      border-radius: 6px;
      margin: 0 10px;
      &:after {
        background: transparent!important;
      }
    }
    &:active,
    &:visited {
      background-color: #c43938!important;
    }
}
</style>
