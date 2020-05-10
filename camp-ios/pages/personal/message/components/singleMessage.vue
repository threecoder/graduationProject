<template>
	<view class="vote-container">
		<view class="vote" @click="detail">
			<view class="index">
				<view class="type">
					<text>{{ item.read }}</text>
				</view>
			</view>
			<view class="name">
				<text>{{ item.title }}</text>
			</view>
			<view class="other">
				<view class="other-item">
					<text class="tip">内容：</text>
					<text class="tip-text">{{ item.shortMsg }}</text>
				</view>
				<view class="other-item">
					<text class="tip">时间：</text>
					<text class="tip-text">{{ item.time }}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
// import trainingApi from '../../../../api/modules/training.js';
// import { toast } from '../.././../../assets/js/commom';
export default {
	props: {
		item: Object
	},
	mounted() {
		console.log(this.item);
	},
	methods: {
		detail() {
			let { id, type } = this.item;
			this.$Router.push({ name: 'msgDetail', query: { id, type } });
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
		.gray {
			color: rgb(96, 98, 102);
		}
		.index {
			padding-bottom: 0.1rem;

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
			.other-item {
				display: flex;
				justify-content: flex-start;
				margin-top: 0.1rem;
				.tip {
					width: 0.7rem;
				}
				.tip-text {
					width: calc(100% - 0.7rem);
				}
			}
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
