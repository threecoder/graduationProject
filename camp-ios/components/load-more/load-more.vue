<template>
    <view class="uni-load-more" @click="onClick">
        <view
            class="uni-load-more__text"
            :style="{ color: color }"
        >{{ status === 'more' ? contentText.contentdown : status === 'loading' ? contentText.contentrefresh : contentText.contentnomore }}</view>
    </view>
</template>

<script>
/**
 * LoadMore 加载更多
 * @property {String} status = [more|loading|noMore] loading 的状态
 * 	@value more loading前
 * 	@value loading loading中
 * 	@value noMore 没有更多了
 * @property {Object} contentText 各状态文字说明，值为：{contentdown: "上拉显示更多",contentrefresh: "正在加载...",contentnomore: "没有更多数据了"}
 * @event {Function} clickLoadMore 点击加载更多时触发
 */
export default {
    props: {
        status: {
            // 上拉的状态：more-loading前；loading-loading中；noMore-没有更多了
            type: String,
            default: "more"
        },
        color: {
            type: String,
            default: "#777777"
        },
        contentText: {
            type: Object,
            default() {
                return {
                    contentdown: "点击显示更多",
                    contentrefresh: "正在加载...",
                    contentnomore: "没有更多数据了"
                };
            }
        }
    },
    data() {
        return {
            webviewHide: false,
            platform: platform
        };
    },
    methods: {
        onClick() {
            if (this.status == "more") {
                this.$emit("clickLoadMore");
            }
        }
    }
};
</script>

<style scoped>
.uni-load-more {
    height: 0.4rem;
}

.uni-load-more__text {
    display: block;
    text-align: center;
    width: 100%;
    font-size: 0.14rem;
    line-height: 0.4rem;
}
</style>
