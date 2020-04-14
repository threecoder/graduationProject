<template>
    <view class="toVote-container">
        <view class="info">
            <view class="name">{{info.name}}</view>
            <view class="other">
                <text>{{info.type}}</text>
                <text>截止日期：{{info.time}}</text>
            </view>
        </view>
        <view class="content">
            <view class="uni-list">
                <checkbox-group v-if="info.type=='多选'" @change="checkboxChange">
                    <label
                        class="uni-list-cell uni-list-cell-pd"
                        v-for="item in info.options"
                        :key="item"
                    >
                        <view class="cbox">
                            <checkbox :value="item" />
                        </view>
                        <view class="clabel">{{item}}</view>
                    </label>
                </checkbox-group>
                <radio-group v-else @change="radioChange">
                    <label
                        class="uni-list-cell uni-list-cell-pd"
                        v-for="(item) in info.options"
                        :key="item"
                    >
                        <view class="cbox">
                            <radio :value="item" />
                        </view>
                        <view class="clabel">{{item}}</view>
                    </label>
                </radio-group>
            </view>
        </view>
        <view class="oper">
            <button class="button-big" @click="vote">投票</button>
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
                    "去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞去年今日韦德生涯最后一舞",
                    "222",
                    "333"
                ]
            },
            selected: []
        };
    },
    mounted() {
        this.getVote();
    },
    methods: {
        async getVote() {
            try {
                let res = await voteApi.studentGetVoteDetail(this.id);
                this.info = res.data;
            } catch (error) {
                toast(error.message);
            }
        },
        async vote() {
            let data = { voteId: this.id };
            let arr = [];
            this.selected.forEach(text => {
                let index = this.info.options.indexOf(text);
                arr.push({ index, text });
            });
            data.data = arr;
            try {
                let res = await voteApi.studentVote(data);
                toast({ title: "投票成功" });
                uni.navigateBack();
            } catch (error) {
                toast(error.message);
            }
        },
        checkboxChange(e) {
            this.selected = e.detail.value;
        },
        radioChange(e) {
            this.selected = [e.detail.value];
        }
    }
};
</script>

<style lang="scss" scoped>
.toVote-container {
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
        .uni-list {
            font-size: 0.16rem;
            label {
                padding: 0.1rem 0.2rem;
                box-sizing: border-box;
                border-bottom: 1px solid #ccc;
                display: flex;
                .cbox {
                    height: 100%;
                    width: 0.3rem;
                }
                .clabel {
                    width: calc(100% - 0.3rem);
                }
            }
        }
    }
}
</style>