<template>
	<view class="dynamic-container" @click="toDetail" hover-class="dynamic-container-hover">
		<view class="img">
			<image src="../../../assets/images/index1.jpg" mode="aspectFill"></image>
			<!-- <image :src="item.src" mode="aspectFill"></image> -->
		</view>
		<view class="info">
			<view class="title">
				<text>{{ item.title }}</text>
			</view>
			<view class="date">
				<text>{{ item.date }}</text>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	props: {
		item: {
			required: true,
			type: Object
		}
	},
	methods: {
		toDetail() {
            let {id,type} = this.item;
			this.$Router.push({ name: 'dynamicDetail', query: { id,type} });
		}
	}
};
</script>

<style lang="scss">
.dynamic-container {
    &-hover {
        background-color: #ddd;
    }
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
