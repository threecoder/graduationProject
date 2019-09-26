<template>
    <div class="carousel">
        <el-carousel indicator-position="outside" height="350px">
            <el-carousel-item v-for="(item,index) in clist" :key="index">
                <div class="list">
                    <el-link href="item.link" type="info">
                        <img :src="item.url" alt/>
                        <p class="title">{{item.title}}</p>
                    </el-link>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<script>
export default {
    props:["clist"],
    data(){
        return{

        }
    },
    methods:{}

}
</script>
<style lang="scss" scoped>
.carousel{
    width: 450px;
    float: left;
    .list{
        position: relative;
        .img{
            display: block;
            width: 100%;
        }
        .title{
            height: 10%;
            width: 100%;
            background-color: gray;
            opacity: 0.7;
            position: absolute;
            bottom:-20px;
            color: rgb(255, 255, 255);
            font-size: 25px;
            
        }
    }
}
</style>