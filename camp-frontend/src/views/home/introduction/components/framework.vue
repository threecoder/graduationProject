<template>
    <div class="framework">
        <div class="header">
            <p class="el-icon-caret-right">组织架构</p>
        </div>
        <div class="msg" v-html="msgList"></div>
    </div>
</template>
<script>
import intrApi from "../../../../api/index/introduction";
export default {
    data() {
        return {
            msgList: [
                "这里是组织架构这里是组织架构这里是组织架构这里是组织架构这里是组织架构这里是组织架构这里是组织架构这里是组织架构这里是组织架构"
            ]
        };
    },
    mounted() {
        this.getFramework();
    },
    methods: {
        async getFramework() {
            try {
                let res = await intrApi.getFramework();
                this.msgList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.framework {
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