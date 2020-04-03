<template>
    <div class="dynamic-container">
        <div>
            <el-button type="text" @click="back">返回上一层</el-button>
        </div>
        <div class="header">
            <p class="title">{{detail.title}}</p>
            <p class="date">{{detail.date}}</p>
        </div>
        <div class="content">
            <template v-for="(item,i) in detail.content">
                <p class="paragraph" v-if="!isImg(item)" :key="i">{{item}}</p>
                <!-- <img v-else :src="item" :alt="`第${i+1}张图片`" :key="i" /> -->
                <img v-else src="../../../assets/images/index1.jpg" :alt="`第${i+1}张图片`" :key="i" />
            </template>
        </div>
    </div>
</template>

<script>
import { imgStr } from "../../../const";
import dynamicApi from "../../../api/index/dynamic";
import newsApi from "../../../api/index/news";
import noticeApi from "../../../api/index/notice";
export default {
    data() {
        return {
            info: {
                type: "news",
                id: "11"
            },
            detail: {
                title: "深交所严密监控可转债交易 反复警示交易风险",
                date: "2020年4月1日",
                content: [
                    "中证网讯 近期，部分可转债大幅波动，价格走势已严重背离正股，炒作现象突出，受到市场高度关注。以深市为例，3月1日至4月1日，深市128只可转债平均上涨7.84%，其中10只涨幅超过50%，“新天转债”“横河转债”“通光转债”一度涨幅超过150%，主要涉及疫情防控、特高压、燃料电池、特斯拉、网红经济等热门题材，存在较为明显的市场炒作特征。记者获悉，针对近期可转债过度炒作情况，深交所高度重视交易风险防控，严密监控可转债交易情况，切实采取监管措施，维护市场秩序。",
                    `${imgStr}+../ssss`,
                    "分析可见，深市可转债交易主要有以下特点：一是部分可转债涨幅严重偏离正股，转股溢价率大幅上升，其中“横河转债”一度高达187.15%。二是个人投资者是主要买入力量，占比超过九成，其中七成为中小散户，羊群效应明显，机构投资者交易占比不足一成，其中基金、企业年金等专业机构投资者则逢高大量减持。三是主要交易参与者均为超短线投资者，近八成交易为当日买入、当日卖出的“T+0”回转交易。四是部分可转债交易中存在个别投资者利用资金优势，实施盘中拉抬打压、虚假申报等异常交易行为，恶意影响市场价格，误导其他投资者交易。总的来看，部分可转债已经成为高度投机的标的，丧失了下可保本、上可获得股价上涨收益的“可攻可守”属性，波动风险已远超正股。"
                ]
            }
        };
    },
    mounted() {
        this.info = this.$route.query;
        this.getDetail();
    },
    methods: {
        isImg(item) {
            return item.includes(imgStr);
        },
        back() {
            this.$router.go(-1);
        },
        async getDetail() {
            try {
                let res = null,
                    type = this.info.type;
                if (type == "news") {
                    res = await newsApi.getNewsDetail(this.info.id);
                } else if (type == "notice") {
                    res = await noticeApi.getNoticeDetail(this.info.id);
                } else {
                    res = await dynamicApi.getDynamicDetail(this.info.id);
                }
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>

<style lang="scss" scoped>
.dynamic-container {
    background-color: #fff;
    padding: 20px;
    .header {
        text-align: center;
        border-bottom: 1px solid #ccc;
        padding-bottom: 10px;
        p.title {
            color: rgb(23, 65, 142);
            font-size: 24px;
        }
        p.date {
            margin-top: 10px;
            color: rgb(96, 98, 102);
        }
    }
    .content {
        display: flex;
        flex-direction: column;
        // justify-content: center;
        align-items: center;
        p {
            margin: 20px 0;
            text-indent: 2em;
            align-self: flex-start;
            color: rgb(10, 10, 10);
            line-height: 25px;
        }
        img {
            max-width: 100%;
        }
    }
}
</style>>
