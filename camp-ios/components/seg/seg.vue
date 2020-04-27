<template>
    <view class="seg-container" :class="{fixed:isFixed}">
        <text
            class="item"
            :class="{active: i==index}"
            v-for="(item,i) in segItem"
            :key="i"
            @click="onClickItem(i)"
        >{{item}}</text>
    </view>
</template>

<script>
/**
 * 分段器
 * @description 用作不同视图的显示
 * @property {Number} current 当前选中的tab索引值，从0计数
 * @property {Array} segItem 选项数组
 * @event {Function} clickItem 组件触发点击事件时触发
 */

export default {
    props: {
        current: {
            type: Number,
            default: 0
        },
        segItem: {
            type: Array,
            default() {
                return [];
            }
        },
        isFixed: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            index: 0
        };
    },
    watch: {
        current(val) {
            if (val !== this.currentIndex) {
                this.currentIndex = val;
            }
        }
    },
    created() {
        this.index = this.current;
    },
    methods: {
        onClickItem(index) {
            if (this.index !== index) {
                this.index = index;
                this.$emit("clickItem", index);
            }
        }
    }
};
</script>

<style lang="scss" scoped>
.fixed {
    position: fixed;
    top: 0;
    left: 0;
}
.seg-container {
    width: 100%;
    overflow: hidden;
    background-color: #fff;
    z-index: 998;
    border-bottom: 1px solid #ccc;
    .item {
        float: left;
        padding: 0.1rem 0.2rem;
        font-size: 0.14rem;
        color: #ccc;
    }
    .active {
        color: #000;
        border-bottom: 1px solid rgb(64, 158, 255);
    }
}
</style>