<template>
	<view class="notice-container" hover-class="notice-container-hover" @click="detail">
		<view class="title">{{ item.title }}</view>
		<view class="date">{{ item.date }}</view>
	</view>
</template>

<script>
export default {
	props: {
		item: {
			type: Object,
			required: true
		}
	},
	methods: {
		detail() {
			console.log(this.item);
			let { id } = this.item,
				type = 'notice';
			this.$Router.push({ name: 'dynamicDetail', query: { id, type } });
		}
	}
};
</script>

<style lang="scss" scoped>
.notice-container {
	display: flex;
	flex-direction: column;
	padding: 0.1rem;
	padding-left: 0.2rem;
	width: 100%;
	height: 0.9rem;
	box-sizing: border-box;
	border-bottom: 1rpx solid #eee;
	&-hover {
		background-color: #ddd;
	}
	.title {
		font-size: 0.14rem;
	}
	.date {
		font-size: 0.12rem;
		color: #ccc;
	}
}
</style>
