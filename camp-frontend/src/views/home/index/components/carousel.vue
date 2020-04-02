<template>
    <div class="carousel">
        <el-carousel height="366px" width="515px">
            <el-carousel-item v-for="(item,index) in clist" :key="index">
                <div class="list">
                    <img :src="item.url" />
                    <p class="title">{{item.title}}</p>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<script>
export default {
    props: ["clist"],
    data() {
        return {};
    },
    methods: {}
};
</script>
<style lang="scss" scoped>
.carousel {
    width: 100%;
    margin: auto;
    .list {
        height: 100%;
        width: 100%;
        position: relative;
        .title {
            height: 38px;
            width: 100%;
            padding-left: 10px;
            background-color: rgb(10, 6, 2);
            color: rgb(242, 242, 242);
            opacity: 0.6;
            position: absolute;
            bottom: 0px;
            font-size: 22px;
            line-height: 38px;
        }
        img {
            width: 100%;
        }
    }
}
</style>