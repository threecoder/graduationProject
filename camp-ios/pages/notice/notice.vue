<template>
    <view class="container">
        <view>
            <view class="content">
                <view>
                    <notice v-for="(item, i) in noticeList" :key="i" :item="item" />
                </view>
                <load-more :status="hasMore" @clickLoadMore="loadMore" />
            </view>
        </view>
    </view>
</template>

<script>
import notice from "./components/singleNotice.vue";
import loadMore from "../../components/load-more/load-more.vue";
import noticeApi from "../../api/index/notice.js";
import { toast } from "../../assets/js/commom.js";
export default {
    components: {
        notice,
        loadMore
    },
    data() {
        return {
            noticeList: [
                // {
                // 	id: '111',
                // 	title: '去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
                // 	date: '2016-10-10',
                // 	url: '',
                // 	type: 'news'
                // }
            ],
            par: {
                currentPage: 1,
                pageSize: 10
            },
            hasMore: "more"
        };
    },
    mounted() {
        this.getNoticeList();
    },
    async onPullDownRefresh() {
        console.log("下拉刷新");
        this.par.currentPage = 1;
        await this.getNoticeList("new");
        uni.stopPullDownRefresh();
    },
    onPageScroll(scroll) {
        this.isFixed = scroll.scrollTop > 0 ? true : false;
    },
    methods: {
        async getNoticeList(type = "add") {
            try {
                let res = null;
                if (type == "add") {
                    this.par.currentPage++;
                    res = await noticeApi.getNoticeList(this.par);
                    this.noticeList.push(...res.data.list);
                } else {
                    this.par.currentPage = 1;
                    res = await noticeApi.getNoticeList(this.par);
                    this.noticeList = res.data.list;
                }
                if (res.data.list.length == 0) {
                    this.hasMore = "noMore";
                } else {
                    this.hasMore = "more";
                }
            } catch (e) {
                toast(e.message);
                this.hasMore = "more";
            }
        },

        toDetail(item) {
            this.$Router.push({
                name: "dynamicDetail",
                query: { id: item.id, type: "notice" }
            });
        },
        async loadMore() {
            this.hasMore = "loading";
            await this.getNoticeList();
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
