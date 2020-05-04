<template>
    <div v-loading="loading">
        <h2 class="tac">{{voteInfo.name}}</h2>
        <div class="info-container">
            <p>
                <span class="span-tip">类型：</span>
                <span class="span-content">{{voteInfo.type}}</span>
                <span class="span-tip">最大可选数目：</span>
                <span class="span-content">{{voteInfo.num}}</span>
            </p>
        </div>
        <div class="option-container">
            <div class="option" v-for="(item,i) in voteInfo.options" :key="i">
                <p>
                    <span class="span-tip">选项{{i+1}}：</span>
                    <span class="span-content">{{item.text}}</span>
                </p>
                <p>
                    <span class="span-tip">票数：</span>
                    <span class="span-content">{{item.quantity}}</span>
                </p>

                <el-progress :stroke-width="20" :percentage="item.per" :text-inside="true"></el-progress>
            </div>
        </div>
    </div>
</template>
<script>
import voteApi from "../../../../api/modules/vote";
import vote from "../../../../api/modules/vote";
export default {
    props: {
        voteId: {
            require: true,
            type: String | Number
        }
    },
    data() {
        return {
            voteInfo: {
                name: "投票名称",
                type: "多选",
                num: 10,
                options: [
                    { text: "发生发生发发睡沙发", per: 10 },
                    { text: "2爱的撒旦啥多啊阿达爱思的啊12", per: 20 }
                ]
            },
            loading: true
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.getVoteDetail();
    },
    methods: {
        async getVoteDetail() {
            this.loading = true;
            try {
                let res = null;
                if (this.idType == 0) {
                    res = await voteApi.studentGetVoteStatic(this.voteId);
                } else {
                    res = await voteApi.memberGetVoteStatic(this.voteId);
                }
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
    text-align: left;
    font-size: 16px;
    // margin-left: 50px;
    .option {
        margin-bottom: 20px;
    }
}
</style>