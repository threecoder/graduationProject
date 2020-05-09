<template>
	<view class="dynamic-container">
		<view class="header">
			<p class="title">{{ detail.title }}</p>
			<p class="title">{{ getTypeText(detail.type) }}</p>
			<p class="date">{{ detail.time }}</p>
		</view>
		<view class="content">
			<p>{{ detail.msg }}</p>
		</view>
		<view class="oper"><button class="button-big" @click="deleteMsg">删除消息</button></view>
	</view>
</template>

<script>
import msgApi from '../../../api/modules/message.js';
import { msgTypeList } from '../../../const.js';
import { toast } from '../../../assets/js/commom.js';
export default {
	data() {
		return {
			info: {
				type: 'news',
				id: '11'
			},
			detail: {
				title: ''
			}
		};
	},
	mounted() {
		this.info = this.$Route.query;
		this.getDetail();
		console.log(this.info);
	},
	methods: {
		async getDetail() {
			try {
				let res = await msgApi.getMsgDetail(this.info.id);
				this.detail = res.data;
				await msgApi.signAsRead(this.info.id);
			} catch (error) {
				toast(error.message);
			}
		},
		getTypeText(type) {
			for (let i = 0; i < msgTypeList.length; i++) {
				if (msgTypeList[i].key == type) {
					return msgTypeList[i].label;
				}
			}
			return type;
		},
		deleteMsg() {
			uni.showModal({
				title: '提示',
				content: '确定要删除本条消息吗？将无法恢复',
				success: async res => {
					if (res.confirm) {
						console.log('用户点击确定');
						try {
							let res = await msgApi.deleteMsg(this.info.id);
							toast(res.msg);
							uni.navigateBack();
						} catch (e) {
							toast(e.message);
						}
					} else if (res.cancel) {
						console.log('用户点击取消');
					}
				}
			});
		}
	}
};
</script>

<style lang="scss">
.dynamic-container {
	background-color: #fff;
	padding: 0.2rem;
	.header {
		text-align: center;
		border-bottom: 1px solid #ccc;
		padding-bottom: 0.1rem;
		p.title {
			color: rgb(23, 65, 142);
			font-size: 0.24rem;
		}
		p.date {
			margin-top: 0.1rem;
			color: rgb(96, 98, 102);
			font-size: 0.12rem;
		}
	}
	.content {
		p {
			margin: 0.2rem 0;
			align-self: flex-start;
			color: rgb(10, 10, 10);
			line-height: 0.25rem;
			font-size: 0.16rem;
		}
		.my-img {
			max-width: 100%;
		}
	}
}
</style>
