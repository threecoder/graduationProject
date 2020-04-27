<template>
    <view class="voted-container">
        <view class="info">
            <view class="name">{{info.name}}</view>
            <view class="other">
                <text>{{info.type}}</text>
                <text>截止日期：{{info.time}}</text>
            </view>
        </view>
        <view class="content">
            <view class="option" v-for="(item,i) in info.options" :key="i">
                <text class="text">选项{{i+1}}：{{item.text}}</text>
                <progress show-info active :percent="item.per"></progress>
            </view>
        </view>
        <view>
            <button class="button-big" @click="back">确定</button>
        </view>
    </view>
</template>

<script>
import { toast } from "../../../assets/js/commom";
import voteApi from "../../../api/modules/vote";
export default {
    data() {
        return {
            id: this.$Route.query.id,
            info: {
                id: "111",
                name: "去年今日韦德生涯最后一舞",
                time: "2020-02-02 14:00",
                type: "单选",
                options: [
                    {
                        text: "111",
                        per: 10
                    },
                    {
                        text: "222",
                        per: 20
                    }
                ]
            },
            selected: []
        };
    },
	mounted() {
		this.getVoteDetail();
	},
    methods: {
        async getVoteDetail() {
            try {
                let res = await voteApi.studentGetVoteStatic(this.id);
                this.info = res.data;
            } catch (error) {
                toast(error.message);
            }
        },
        back() {
            uni.navigateBack();
        }
    }
};
</script>

<style lang="scss" scoped>
.voted-container {
    background-color: #fff;
    .info {
        padding: 0.1rem 0;
        border-bottom: 1px solid #ddd;
        .name {
            font-size: 0.2rem;
            text-align: center;
        }
        .other {
            color: rgb(96, 98, 102);
            font-size: 0.12rem;
            text-align: center;
            text {
                margin-right: 0.1rem;
            }
        }
    }
    .content {
        .option {
            width: 100%;
            height: 0.5rem;
            padding: 0.1rem;
            box-sizing: border-box;
            border-bottom: 1px solid #ddd;
            .text {
                display: block;
                font-size: 0.16rem;
                line-height: 0.1rem;
                height: 0.1rem;
            }
        }
    }
}
</style>