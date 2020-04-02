<template>
    <div class="all-container">
        <my-header />
        <horizontalMenu />
        <div class="content-container">
            <div class="view-container">
                <router-view :key="$route.path" />
            </div>
        </div>
    </div>
</template>

<script>
import horizontalMenu from "./components/menu.vue";
import myHeader from "./components/myHeader.vue";
export default {
    components: {
        horizontalMenu,
        myHeader
    }
};
</script>

<style lang="scss" scoped>
.all-container {
    background-color: rgb(242, 243, 244);
    height: 100%;
    .content-container {
        min-height: calc(100% - 200px);
        background-color: rgb(242, 243, 244);
        .view-container {
            width: 62.5%;
            margin: 0 auto;
            min-width: 1030px;
        }
    }
}
</style>
