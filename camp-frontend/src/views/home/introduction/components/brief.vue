<template>
    <div class="brief">
        <div class="header">
            <p class="el-icon-caret-right">协会简介</p>
        </div>
        <div class="msg">
            <p v-for="(item,i) in msgList" :key="i">{{item}}</p>
        </div>
    </div>
</template>
<script>
import intrApi from "../../../../api/index/introduction";
export default {
    data() {
        return {
            msgList: [
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介这里是协会简介",
                "这里是协会简介这里是协会简介"
            ]
        };
    },
    mounted() {
        this.getBrief();
    },
    methods: {
        async getBrief() {
            try {
                let res = await intrApi.getBrief();
                this.msgList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.brief {
    width: 100%;
    .header {
        font-family: "PingFang SC";
        font-size: 18px;
        background-image: linear-gradient(#fff, #ddd);
        border: 1px solid rgb(217, 217, 217);
        height: 40px;
        p {
            margin-left: 20px;
            line-height: 40px;
        }
    }
    .msg {
        background-color: #fff;
        min-height: 640px;
        p {
            line-height: 30px;
            color: rgb(96, 98, 102);
            text-indent: 2rem;
        }
    }
}
</style>