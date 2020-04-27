<template>
    <view class="vote-container">
        <view class="vote">
            <view class="index">
                <text class="gray">投票序号：</text>
                <text>{{item.id}}</text>
                <view class="type">
                    <text>{{item.type}}</text>
                </view>
            </view>
            <view class="name">
                <text>{{item.name}}</text>
            </view>
            <view class="other">
                <text>截止时间：{{item.time}}</text>
            </view>
        </view>
        <view class="oper">
            <button class="button-small" v-if="status=='vote'" @click="vote">投票</button>
            <button class="button-small" v-else @click="detail">详情</button>
        </view>
    </view>
</template>

<script>
export default {
    props: {
        item: Object,
        status: String
    },
    methods: {
        vote() {
            this.$Router.push({ name: "toVote", query: { id: this.item.id } });
        },
        detail() {
            this.$Router.push({
                name: "voteDetail",
                query: { id: this.item.id }
            });
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
        .index {
            padding-bottom: 0.1rem;
            .gray {
                color: rgb(96, 98, 102);
            }
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