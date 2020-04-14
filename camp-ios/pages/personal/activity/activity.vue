<template>
    <view class="container">
        <view>
            <seg
                :isFixed="isFixed"
                :current="contentIndex"
                :segItem="segItem"
                @clickItem="onClickItem"
            ></seg>
            <view class="content">
                <view v-show="contentIndex === 0">
                    <single
                        v-for="(item, i) in joinableList"
                        :key="i"
                        :item="item"
                        status="vote"
                        @refresh="refresh"
                        @activityDetail="activityDetail(item)"
                    />
                </view>
                <view v-show="contentIndex === 1">
                    <single
                        v-for="(item, i) in signedList"
                        :key="i"
                        :item="item"
                        @refresh="refresh"
                        @activityDetail="activityDetail(item)"
                    />
                </view>
                <view class="more" @click="loadMore">
                    <text v-if="hasMore">加载更多</text>
                    <text v-else>没有更多了</text>
                </view>
            </view>
        </view>

        <uni-popup ref="popup" type="top">
            <view class="introduce">
                <view class="title">活动介绍</view>
                <view class="introduce-item" v-for="(item,i) in introduce" :key="i">{{item}}</view>
            </view>
        </uni-popup>
    </view>
</template>

<script>
import seg from "../../../components/seg/seg.vue";
import single from "./components/singleActivity.vue";
import uniPopup from "../../../components/uni-popup/uni-popup.vue";
import voteApi from "../../../api/modules/vote.js";
import { toast } from "../../../assets/js/commom.js";
export default {
    components: {
        seg,
        single,
        uniPopup
    },
    data() {
        return {
            res: null,
            contentIndex: 0,
            segItem: ["可报名活动", "已报名活动"],
            isFixed: false,
            joinableList: [
                {
                    id: "111",
                    name: "质量检测",
                    date: "2020-02-02 14:00",
                    phone: "15183650703",
                    fee: 2222,
                    contacts: "糖好酸",
                    address:
                        "广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学",
                    introduce: ["广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学广东省广州市番禺区小谷围街道华南理工大学", "222"],
                    status: "可报名"
                }
            ],
            signedList: [
                {
                    id: "111",
                    name: "质量检测",
                    date: "2020-02-02 14:00",
                    phone: "15183650703",
                    fee: 2222,
                    contacts: "糖好酸",
                    address: "广东省广州市番禺区小谷围街道华南理工大学",
                    introduce: ["111", "222"],
                    status: "已支付"
                }
            ],
            par: {
                currentPage: 1,
                pageSize: 10
            },
            hasMore: true,
            introduce: []
        };
    },
    watch: {
        contentIndex(newVal) {
            this.par.currentPage = 1;
            if (newVal == 0) {
                this.getJoinableList("new");
            } else {
                this.getSignedList("new");
            }
        }
    },
    onShow() {
        if (this.contentIndex == 0) {
            this.getJoinableList("new");
        } else {
            this.getSignedList("new");
        }
    },
    async onPullDownRefresh() {
        await this.refresh();
        uni.stopPullDownRefresh();
    },
    onPageScroll(scroll) {
        this.isFixed = scroll.scrollTop > 0 ? true : false;
    },
    methods: {
        async getSignedList(type = "add") {
            try {
                let res = await voteApi.getStudentCanVoteList(this.par);
                if (type == "add") {
                    this.signedList.push(...res.data.list);
                    if (res.data.list.length == 0) {
                        this.hasMore = false;
                    }
                } else {
                    this.signedList = res.data.list;
                }
            } catch (e) {
                toast(e.message);
            }
        },
        async getJoinableList(type = "add") {
            try {
                let res = await voteApi.getStudentHasVotedList(this.par);
                if (type == "add") {
                    this.joinableList.push(...res.data.list);
                    if (res.data.list.length == 0) {
                        this.hasMore = false;
                    }
                } else {
                    this.joinableList = res.data.list;
                }
            } catch (e) {
                toast(e.message);
            }
        },
        onClickItem(index) {
            this.contentIndex = index;
        },
        async refresh() {
            toast("刷新");
            this.par.currentPage == 1;
            if (this.contentIndex == 0) {
                await this.getJoinableList("new");
            } else {
                await this.getSignedList("new");
            }
        },
        activityDetail(item) {
            this.introduce = item.introduce;
            this.$refs.popup.open();
        },
        loadMore() {
            if (this.hasMore) {
                toast("更多");
                if (this.contentIndex == 0) {
                    this.getJoinableList("add");
                } else {
                    this.getSignedList("add");
                }
            }
        }
    }
};
</script>

<style lang="scss" scoped>
.container {
    background-color: #eee;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    .content {
        background-color: #fff;
        .caroulse {
            height: 300rpx;
            .swiper {
                height: 300rpx;
                text {
                    width: 100%;
                    position: absolute;
                    top: 0;
                    left: 0;
                    z-index: 998;
                    font-size: 0.16rem;
                    line-height: 0.2rem;
                    color: #fff;
                    background-color: black;
                    opacity: 0.5;
                }
            }
            image {
                width: 100%;
                height: 100%;
            }
        }
    }
    .more {
        width: 100%;
        height: 0.2rem;
        font-size: 0.12rem;
        line-height: 0.2rem;
        color: rgb(96, 98, 102);
        padding: 0.1rem 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    ::v-deep .uni-popup {
        .uni-popup__wrapper-box {
            width: 100%;
            top: 1rem;
            position: absolute;
        }
    }
    .introduce {
        width: 80%;
        margin: 0 auto;
        box-sizing: border-box;
        padding: 0.1rem 0.2rem;
        background-color: #fff;
        .title {
            border-bottom: 1px solid #ccc;
            font-size: 0.18rem;
            padding-bottom: 0.05rem;
            font-weight: 800;
            color: rgb(96, 98, 102);
        }
        .introduce-item {
            font-size: 0.16rem;
            text-indent: 2em;
            padding-top: 0.1rem;
        }
    }
}
</style>
