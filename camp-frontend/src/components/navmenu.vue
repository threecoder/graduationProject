<template>
  <div>
    <template v-for="(item,index) in data">
      <el-menu-item :key="index" :index="item.index" v-if="!item.children">{{item.title}}</el-menu-item>
      <el-submenu :key="index" v-if="item.children">
        <template :index="item.index" slot="title">{{item.title}}</template>
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
          index: "1",
          title: "协会介绍",
          children: [
            { index: "1.1", title: "发展历史" },
            { index: "1.2", title: "师资力量" },
            { index: "1.3", title: "品牌价值" }
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
div {
  display: inline-block;
}
</style>
