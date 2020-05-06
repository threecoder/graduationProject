<template>
	<view class="vote-container">
		<view class="vote">
			<view class="index">
				<view class="type">
					<text>难度：{{ item.level }}</text>
				</view>
			</view>
			<view class="name">
				<text class="gray">培训名称：</text>
				<text>{{ item.title }}</text>
			</view>
			<view class="other">
				<view class="other-item">
					<text class="tip">培训描述：</text>
					<text class="tip-text">{{ item.abstract }}</text>
				</view>
				<view class="other-item">
					<text class="tip">参与人数：</text>
					<text class="tip-text">{{ item.viewers }}</text>
				</view>
			</view>
		</view>
		<view class="oper"><button class="button-small" @click="detail">详情</button></view>
	</view>
</template>

<script>
// import trainingApi from '../../../../api/modules/training.js';
// import { toast } from '../.././../../assets/js/commom';
export default {
	props: {
		item: Object,
		status: String
	},
	mounted() {
		console.log(this.item);
	},
	methods: {
		detail() {
			this.$Router.push({ name: 'training' });
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
