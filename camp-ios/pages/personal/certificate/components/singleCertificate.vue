<template>
	<view class="vote-container">
		<view class="vote">
			<view class="index">
				<text class="gray">证书序号：</text>
				<text>{{ item.cerId }}</text>
				<view class="type">
					<text>{{ status }}</text>
				</view>
			</view>
			<view class="name">
				<text class="gray">证书名称：</text>
				<text>{{ item.cerName }}</text>
			</view>
			<view class="other">
				<view class="other-item">
					<text class="tip">所属会员：</text>
					<text class="tip-text">{{ item.memberName ? item.memberName : '无' }}</text>
				</view>
				<view class="other-item">
					<text class="tip">生效时间：</text>
					<text class="tip-text">{{ item.beginTime }}</text>
				</view>
				<view class="other-item">
					<text class="tip">到期时间：</text>
					<text class="tip-text">{{ item.endTime }}</text>
				</view>
			</view>
		</view>
		<view class="oper">
			<button class="button-small" v-if="status == '有效'" @click="modify">校正</button>
			<button class="button-small" v-if="status == '过期' || item.isEndding" @click="recheck">复审</button>
		</view>
	</view>
</template>

<script>
import cerApi from '../../../../api/modules/certificate.js';
import { toast } from '../.././../../assets/js/commom';
export default {
	props: {
		item: Object,
		status: String
	},
	methods: {
		modify() {
			uni.showModal({
				title: '提示',
				content: '确定要校正证书信息吗？',
				success: async res => {
					if (res.confirm) {
						console.log('用户点击确定');
						try {
							let res = await cerApi.modifyCer(this.item.cerId);
							toast('发起校正成功，请等待审核');
						} catch (e) {
							toast(e.message);
						}
					} else if (res.cancel) {
						console.log('用户点击取消');
					}
				}
			});
		},
		recheck() {
			uni.showModal({
				title: '提示',
				content: '确定要延长证书有效期限吗？',
				success: async res => {
					uni.showLoading({
						title: '处理中'
					});
					if (res.confirm) {
						console.log('用户点击确定');
						try {
							let res = await cerApi.examineCertificate(this.item.cerId);
							toast('发起校正成功，请等待审核');
						} catch (e) {
							toast(e.message);
						}
					} else if (res.cancel) {
						console.log('用户点击取消');
					}
					uni.hideLoading();
				}
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
