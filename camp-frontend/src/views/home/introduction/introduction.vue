<template>
    <div class="introduction-container">
        <div class="menu-container">
            <introductionMenu @switch="switchContent"></introductionMenu>
        </div>
        <div class="content-container">
            <brief v-show="content=='简介'" />
            <framework v-show="content=='架构'" />
            <constitution v-show="content=='章程'" />
        </div>
    </div>
</template>
<script>
import introductionMenu from "./components/introductionMenu.vue";
import brief from "./components/brief.vue";
import framework from "./components/framework.vue";
import constitution from "./components/constitution.vue";

export default {
    components: {
        introductionMenu,
        brief,
        framework,
        constitution
    },
    data() {
        return {
            content: "简介"
        };
    },
    methods: {
        switchContent(val) {
            this.content = val;
        }
    }
};
</script>
<style lang="scss" scoped>
.introduction-container {
    width: 100%;
    margin-top: 5px;
    position: relative;
    .menu-container {
        width: 250px;
        text-align: center;
        float: left;
    }
    .content-container {
        position: absolute;
        right: 0;
        top: 0;
        left: 254px;
    }
}
</style>