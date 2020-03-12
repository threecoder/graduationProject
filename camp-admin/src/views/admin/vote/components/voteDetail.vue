<template>
    <div v-loading="loading">
        <h2 class="tac">{{voteInfo.name}}</h2>
        <div class="info-container">
            <p>
                <span v-if="voteInfo.type" class="span-tip">类型：</span>
                <span class="span-content">{{voteInfo.type}}</span>
                <span v-if="voteInfo.num" class="span-tip">最大可选数目：</span>
                <span class="span-content">{{voteInfo.num}}</span>
            </p>
        </div>
        <div class="option-container">
            <p class="option" v-for="(item,i) in voteInfo.options" :key="i">
                <span class="span-tip">选项{{i+1}}：</span>
                <span class="span-content">{{item.text}}</span>
            </p>
        </div>
    </div>
</template>
<script>
import voteApi from "../../../../api/admin/vote";
export default {
    props: {
        voteId: {
            require: true,
            type: String
        }
    },
    data() {
        return {
            voteInfo: {},
            loading: true
        };
    },
    mounted() {
        this.getVoteDetail();
    },
    methods: {
        async getVoteDetail() {
            this.loading = true;
            try {
                let res = await voteApi.getVoteDetail(this.voteId);
                this.voteInfo = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.info-container,
.option-container {
    text-align: center;
    margin: 20px auto;
    font-size: 16px;
    .span-tip {
        margin-right: 10px;
        margin-left: 30px;
        color: rgb(96, 98, 102);
    }
    .span-content {
        color: rgb(64, 158, 255);
    }
}
.option-container {
    text-align-last: center;
    font-size: 16px;
    // margin-left: 50px;
    .option {
        margin-bottom: 15px;
    }
}
</style>