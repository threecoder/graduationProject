<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>{{cardname.name}}</span>
                <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <div v-for="(newsitem,key) in newslist " :key="key" class="newsitem">
                <div class="newstitle">
                    <a href="newsitem.link">{{newsitem.title}}</a>
                </div>
                <div class="newsdate">
                    {{newsitem.date}}
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    props:["newslist","cardname"],
    data(){
        return{

        }
    },
    methods:{
        getmore(str){}
    }
}
</script>
<style lang="scss" scoped>
.box-card{
    width: 550px;
    .newsitem{
        height: 44px;
        position: relative;
        .newstitle{
            margin-top: 7px;
            font-size: 19px;
            width: 78%;
            float: left;
        }
        .newsdate{
            font-size: 12px;
            float: left;
            right: 0px;
            bottom: 0px;
            position: absolute;
        }
    }
}
</style>