<template>
    <div class="courses">
        <div class="single-courses" v-for="(item,index) in list" :key="index">
            <div class="img-container">
                <img :src="item.url" alt />
            </div>
            <span class="title">{{item.title}}</span>
            <div class="info-container">
                <p class="tip">
                    难度:{{item.level}}
                    <span class="num">参与者:{{item.viewers}}</span>
                </p>
                <p class="greyWords">{{item.abstract}}</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props:["list"],
    data() {
        return {
            
        };
    },
    methods: {}
};
</script>
<style lang="scss" scoped>
.courses {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    flex-wrap: wrap;
    .single-courses {
        padding: 10px;  
        width: 200px;
        margin-right: 2%;
        margin-top: 30px;
        cursor: pointer;
        box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
        border: 1px solid #ebeef5;
        border-radius: 5px;
        overflow: hidden;
        &:hover .title {
            color: red;
        }
        .img-container {
            img {
                width: 200px;
                height: 120px;
            }
        }
        .title {
            font-weight: 800;
            font-size: 18px;
        }
        .info-container {
            margin-top: 30px;
            p.tip {
                margin: 10px 0;
                color: rgb(96, 98, 102);
            }
            .greyWords {
                color: rgb(181, 181, 181);
                font-size: 13px;
            }
            .num {
                margin-left: 20px;
            }
        }
    }
}
</style>