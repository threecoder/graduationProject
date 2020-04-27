<template>
    <view class="vote-container">
        <view class="vote">
            <view class="index">
                <text class="gray">活动序号：</text>
                <text>{{item.id}}</text>
                <view class="type">
                    <text>{{item.status}}</text>
                </view>
            </view>
            <view class="name">
                <text class="gray">活动名称：</text>
                <text>{{item.name}}</text>
            </view>
            <view class="other">
                <view class="other-item">
                    <text class="tip">活动时间：</text>
                    <text class="tip-text">{{item.date}}</text>
                </view>
                <view class="other-item">
                    <text class="tip">活动地址：</text>
                    <text class="tip-text">{{item.address}}</text>
                </view>
            </view>
        </view>
        <view class="oper">
            <button class="button-small" v-if="status=='可报名'" @click="join">报名</button>
            <button class="button-small" v-if="status=='未支付'" @click="pay">支付</button>
            <!-- <button class="button-small" v-if="status=='已支付'" @click="seatNum">座位</button> -->
            <button class="button-small" @click="detail">详情</button>
        </view>
    </view>
</template>

<script>
import trainingApi from "../../../../api/modules/training.js";
import { toast } from "../.././../../assets/js/commom";
export default {
    props: {
        item: Object,
        status: String
    },
    methods: {
        vote() {
            this.$Router.push({ name: "toVote", query: { id: this.item.id } });
        },
        async join() {
            try {
                let res = await trainingApi.studentJoinTraining(this.item.id);
                toast("报名成功");
                this.$emit("refresh");
            } catch (error) {
                toast(error.message);
            }
        },
        pay() {},
        async seatNum() {
            try {
                let res = await trainingApi.getSeatNum(this.item.id);
                toast("你的座位号是" + res.data);
            } catch (error) {
                toast(error.message);
            }
        },
        detail() {
            this.$emit("activityDetail");
        }
    }
};
</script>

<style lang="scss" scoped>
.vote-container {
    margin-top: 0.1rem;
    padding: 0.1rem;
    width: 100%;
    overflow: hidden;
    box-sizing: border-box;
    border-bottom: 1rpx solid #eee;

    .vote {
        width: 100%;
        padding-left: 0.2rem;
        font-size: 0.14rem;
        box-sizing: border-box;
        // &-hover {
        //     background-color: #ddd;
        // }
        .gray {
            color: rgb(96, 98, 102);
        }
        .index {
            padding-bottom: 0.1rem;

            .type {
                float: right;
                padding: 0 0.1rem;
                color: rgb(64, 158, 255);
                font-size: 0.12rem;
            }
        }

        .name {
            font-size: 0.14rem;
        }
        .other {
            margin-top: 0.02rem;
            font-size: 0.12rem;
            color: #ccc;
            .other-item {
                display: flex;
                justify-content: flex-start;
                margin-top: 0.1rem;
                .tip {
                    width: 0.7rem;
                }
                .tip-text {
                    width: calc(100% - 0.7rem);
                }
            }
        }
    }
    .oper {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        align-items: center;
    }
}
</style>