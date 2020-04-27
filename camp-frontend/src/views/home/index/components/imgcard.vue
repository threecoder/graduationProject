<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>{{cardname.name}}</span>
                <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <div v-for="(item,index) in imglist" :key="index" class="item">
                <div class="list">
                    <div class="img-list">
                        <el-link type="info" href="item.link">
                            <img :src="item.url" width="300px" height="163px" alt="item.title"/>
                            <p class="title">{{item.title}}</p>
                        </el-link>
                    </div>
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    props:["imglist","cardname"],
    data(){
        return{

        }
    }
}
</script>
<style lang="scss" scoped>
.boxcard{
    width: 320px;
    .list{
        position: relative;
        .img-list{
            float: left;
            margin-left: 10px;
            width: 320px;
            .title{
                height: 8%;
                font-size: 12px;                
            }
        }
    }
}
</style>