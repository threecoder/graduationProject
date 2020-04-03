<template>
    <div class="carousel" v-loading="loading">
        <el-carousel height="366px" width="515px">
            <el-carousel-item v-for="(item,index) in list" :key="index">
                <div class="list" @click="detail(item)">
                    <img :src="item.url" />
                    <p class="title">{{item.title}}</p>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<script>
import indexApi from "../../../../api/index/index";
export default {
    data() {
        return {
            loading: false,
            list: [
                {
                    url: require("../../../../assets/images/index1.jpg"),
                    title: "图片1",
                    type: "news",
                    id: "111"
                },
                {
                    url: require("../../../../assets/images/index2.jpg"),
                    title: "图片2",
                    type: "news",
                    id: "111"
                },
                {
                    url: require("../../../../assets/images/index3.jpg"),
                    title: "图片3",
                    type: "news",
                    id: "111"
                },
                {
                    url: require("../../../../assets/images/index4.jpg"),
                    title: "图片4",
                    type: "news",
                    id: "111"
                }
            ]
        };
    },
    mounted() {
        this.getCarouselList();
    },
    methods: {
        async getCarouselList() {
            this.loading = true;
            try {
                let res = await indexApi.getCarouselList();
                this.list = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        detail(item) {
            this.$router.push(`/dynamicDetail?type=${item.type}&id=${item.id}`);
        }
    }
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
        cursor: pointer;
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