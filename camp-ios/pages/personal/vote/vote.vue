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
                    <single v-for="(item, i) in canvoteList" :key="i" :item="item" status="vote" />
                </view>
                <view v-show="contentIndex === 1">
                    <single v-for="(item, i) in votedList" :key="i" :item="item" />
                </view>
                <view class="more" @click="loadMore">
                    <text v-if="hasMore">加载更多</text>
                    <text v-else>没有更多了</text>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
import seg from "../../../components/seg/seg.vue";
import single from "./components/singleVote.vue";
import voteApi from "../../../api/modules/vote.js";
import { toast } from "../../../assets/js/commom.js";
export default {
    components: {
        seg,
        single
    },
    data() {
        return {
            res: null,
            contentIndex: 0,
            segItem: ["可投票", "已投票"],
            isFixed: false,
            canvoteList: [
                {
                    id: "111",
                    name: "去年今日韦德生涯最后一舞",
                    time: "2020-02-02 14:00",
                    type: "多选"
                }
            ],
            votedList: [
                {
                    id: "111",
                    name: "香蕉船兄弟场边庆祝韦德三双",
                    time: "2020-02-02 14:00",
                    type: "单选"
                }
            ],
            par: {
                currentPage: 1,
                pageSize: 10
            },
            hasMore: true
        };
    },
    watch: {
        contentIndex(newVal) {
            this.par.currentPage = 1;
            if (newVal == 0) {
                this.getCanvoteList("new");
            } else {
                this.getVotedList("new");
            }
        }
    },
    onShow() {
        console.log("获取投票信息");
        if (this.contentIndex == 0) {
            this.getCanvoteList("new");
        } else {
            this.getVotedList("new");
        }
    },
    async onPullDownRefresh() {
        console.log("下拉刷新");
        this.getSwiperList();
        this.par.currentPage == 1;
        if (this.contentIndex == 0) {
            await this.getCanvoteList("new");
        } else {
            await this.getVotedList("new");
        }
        uni.stopPullDownRefresh();
    },
    onPageScroll(scroll) {
        this.isFixed = scroll.scrollTop > 0 ? true : false;
    },
    methods: {
        async getVotedList(type = "add") {
            try {
                let res = await voteApi.getStudentCanVoteList(this.par);
                if (type == "add") {
                    this.votedList.push(...res.data.list);
                    if (res.data.list.length == 0) {
                        this.hasMore = false;
                    }
                } else {
                    this.votedList = res.data.list;
                }
            } catch (e) {
                toast(e.message);
            }
        },
        async getCanvoteList(type = "add") {
            try {
                let res = await voteApi.getStudentHasVotedList(this.par);
                if (type == "add") {
                    this.canvoteList.push(...res.data.list);
                    if (res.data.list.length == 0) {
                        this.hasMore = false;
                    }
                } else {
                    this.canvoteList = res.data.list;
                }
            } catch (e) {
                toast(e.message);
            }
        },
        onClickItem(index) {
            this.contentIndex = index;
        },
        loadMore() {
            if (this.hasMore) {
                toast("更多");
                if (this.contentIndex == 0) {
                    this.getCanvoteList("add");
                } else {
                    this.getVotedList("add");
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
}
</style>
