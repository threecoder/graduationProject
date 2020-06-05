<template>
	<view class="vote-container">
		<view class="vote">
			<view class="index">
				<text class="gray">订单号：</text>
				<text>{{ item.orderNum }}</text>
				<view class="type">
					<view>{{ getTypeText(item.orderType) }}</view>
					<view>{{ item.status }}</view>
				</view>
			</view>
			<view class="name">
				<text>
					<text class="gray">名称：</text>
					{{ item.businessName }}
				</text>
				<view>
					<text class="gray">金额：</text>
					{{ item.price }}元
				</view>
			</view>
			<view class="other">
				<view>创建时间：{{ item.buildTime }}</view>
				<view>失效时间：{{ item.endTime }}</view>
				<view>支付时间：{{ item.payTime }}</view>
			</view>
		</view>
		<view class="oper">
			<!-- <button class="button-small" v-if="status == 'vote'" @click="vote">投票</button>
			<button class="button-small" v-else @click="detail">详情</button> -->
		</view>
	</view>
</template>

<script>
export default {
	props: {
		item: Object,
		status: String
	},
	methods: {
		getTypeText(type) {
			if (type == 'activity') {
				return '活动';
			}
			if (type == 'training') {
				return '培训';
			}
			if (type == 'cerChange') {
				return '证书变更';
			}
			if (type == 'cerRecheck') {
				return '证书复审';
			}
		},
		vote() {
			this.$Router.push({ name: 'toVote', query: { id: this.item.id } });
		},
		detail() {
			this.$Router.push({
				name: 'voteDetail',
				query: { id: this.item.id }
			});
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
			margin-top: 0.045rem;
			font-size: 0.12rem;
			color: #ccc;
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
