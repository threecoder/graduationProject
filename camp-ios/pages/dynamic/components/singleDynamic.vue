<template>
    <view class="dynamic-container">
        <view class="img">
            <image src="../../../assets/images/index1.jpg" mode="aspectFill"></image>
        </view>
        <view class="info">
            <view class="title"><text>去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双</text></view>
            <view class="date"><text>2019-04-10 16:00</text></view>
        </view>
    </view>
</template>

<script>
export default {

}
</script>

<style lang="scss">
.dynamic-container {
    padding: 0.1rem;
    width: 100%;
	height: 0.9rem;
	box-sizing: border-box;
    border-bottom: 1rpx solid #eee;
    .img {
        float: left;
        width: 0.94rem;
        height: 0.8rem;
        display: flex;
        justify-content: center;
        align-items: center;
        image {
            width: 0.8rem;
            height: 0.6rem;
        }
    }
    .info {
        width: calc(100% - 1.4rem);
        float: left;
        margin-left: 0.2rem;
        .title {
            font-size: 0.14rem;
        }
        .date {
            font-size: 0.12rem;
            color: #ccc;
        }
    }
}
</style>