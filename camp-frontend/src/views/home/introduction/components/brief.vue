<template>
    <div class="brief">
        <div class="header">
            <p class="el-icon-caret-right">协会简介</p>
        </div>
        <div class="msg" v-html="msgList"></div>
    </div>
</template>
<script>
import intrApi from "../../../../api/index/introduction";
export default {
    data() {
        return {
            msgList:
                '<p class="ql-indent-1"><span style="color: rgb(206, 145, 120);" class="ql-size-large">"中证网讯&nbsp;近期，部分可转债大幅波动，价格走势已严重背离正股，炒作现象突出，受到市场高度关注。以深市为例，3月1日至4月1日</span><span style="color: rgb(206, 145, 120);"><span class="ql-cursor">﻿</span></span><span style="color: rgb(206, 145, 120);" class="ql-size-large">，深市128只可转债平均上涨7.84%，其中10只涨幅超过50%，“新天转债”“横河转债”“通光转债”一度涨幅超过150%，主要涉及疫情防控、特高压、燃料电池、特斯拉、网红经济等热门题材，存在较为明显的市场炒作特征。记者获悉，针对近期可转债过度炒作情况，深交所高度重视交易风险防控，严密监控可转债交易情况，切实采取监管措施，维护市场秩序。"</span><span class="ql-size-large">,</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: rgb(206, 145, 120);" class="ql-size-large">"分析可见，深市可转债交易主要有以下特点：一是部分可转债涨幅严重偏离正股，转股溢价率大幅上升，其中“横河转债”一度高达187.15%。二是个人投资者是主要买入力量，占比超过九成，其中七成为中小散户，羊群效应明显，机构投资者交易占比不足一成，其中基金、企业年金等专业机构投资者则逢高大量减持。三是主要交易参与者均为超短线投资者，近八成交易为当日买入、当日卖出的“T+0”回转交易。四是部分可转债交易中存在个别投资者利用资金优势，实施盘中拉抬打压、虚假申报等异常交易行为，恶意影响市场价格，误导其他投资者交易。总的来看，部分可转债已经成为高度投机的标的，丧失了下可保本、上可获得股价上涨收益的“可攻可守”属性，波动风险已远超正股。"</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]</p>'
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