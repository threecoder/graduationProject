<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content" :class="{ mt: isFixed }">
				<view><single v-for="(item, i) in orderList" :key="i" :item="item" status="vote" @refresh="refresh" @activityDetail="activityDetail(item)" /></view>
				<load-more :status="hasMore" @clickLoadMore="loadMore"></load-more>
			</view>
		</view>

		<uni-popup ref="popup" type="top">
			<view class="introduction">
				<view class="title">培训介绍</view>
				<view class="introduction-item" v-for="(item, i) in introduction" :key="i">{{ item }}</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
import seg from '../../../components/seg/seg.vue';
import single from './components/singleOrder.vue';
import uniPopup from '../../../components/uni-popup/uni-popup.vue';
import loadMore from '../../../components/load-more/load-more.vue';
import orderApi from '../../../api/modules/order.js';
import { toast } from '../../../assets/js/commom.js';
export default {
	components: {
		seg,
		single,
		uniPopup,
		loadMore
	},
	data() {
		return {
			contentIndex: 0,
			segItem: ['活动', '培训', '证书变更', '证书复审'],
			isFixed: false,
			orderList: [
				{
					orderNum: "11111",
					orderType: 'activity',
					businessName: '关联活动',
					builder: "李四",
					buildTime: '2020-02-02 20:00:00',
					endTime: '2020-02-02 21:00:00',
					payTime: '2020-02-02 20:30:00',
					status: '未支付',
					price: 100
				}
			],
			par: {
				currentPage: 1,
				pageSize: 10,
				type: 'activity'
			},
			hasMore: 'more',
			introduction: []
		};
	},
	watch: {
		contentIndex() {
			this.refresh();
		}
	},
	onShow() {
		this.refresh();
	},
	async onPullDownRefresh() {
		await this.refresh();
		uni.stopPullDownRefresh();
	},
	onPageScroll(scroll) {
		this.isFixed = scroll.scrollTop > 0 ? true : false;
	},
	methods: {
		async getOrderList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await orderApi.getOrderList(this.par);
					this.orderList.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await orderApi.getOrderList(this.par);
					this.orderList = res.data ? res.data.list : [];
				}
				console.log("订单",res);
				if (res.data.list.length == 0) {
					this.hasMore = 'more';
				} else {
					this.hasMore = 'noMore';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		onClickItem(index) {
			this.contentIndex = index;
		},
		$_setType() {
			switch (this.contentIndex) {
				case 0:
					this.par.type = 'activity';
					break;
				case 1:
					this.par.type = 'training';
					break;
				case 2:
					this.par.type = 'cerChange';
					break;
				case 3:
					this.par.type = 'cerRecheck';
					break;
				default:
					this.par.type = 'activity';
			}
		},
		async refresh() {
			this.$_setType();
			await this.getOrderList('new');
			this.hasMore = 'more';
		},
		activityDetail(item) {
			this.introduction = item.introduction;
			this.$refs.popup.open();
		},
		loadMore() {
			this.hasMore = 'loading';
			this.getOrderList();
		}
	}
};
</script>

<style lang="scss" scoped>
.container {
	background-color: #eee;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	.mt {
		margin-top: 0.5rem;
	}
	.content {
		background-color: #fff;
		.caroulse {
			height: 300rpx;
			.swiper {
				height: 300rpx;
				text {
					width: 100%;
					position: absolute;
					top: 0;
					left: 0;
					z-index: 998;
					font-size: 0.16rem;
					line-height: 0.2rem;
					color: #fff;
					background-color: black;
					opacity: 0.5;
				}
			}
			image {
				width: 100%;
				height: 100%;
			}
		}
	}
	.more {
		width: 100%;
		height: 0.2rem;
		font-size: 0.12rem;
		line-height: 0.2rem;
		color: rgb(96, 98, 102);
		padding: 0.1rem 0;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	::v-deep .uni-popup {
		.uni-popup__wrapper-box {
			width: 100%;
			top: 1rem;
			position: absolute;
		}
	}
	.introduction {
		width: 80%;
		margin: 0 auto;
		box-sizing: border-box;
		padding: 0.1rem 0.2rem;
		background-color: #fff;
		.title {
			border-bottom: 1px solid #ccc;
			font-size: 0.18rem;
			padding-bottom: 0.05rem;
			font-weight: 800;
			color: rgb(96, 98, 102);
		}
		.introduction-item {
			font-size: 0.16rem;
			text-indent: 2em;
			padding-top: 0.1rem;
		}
	}
}
</style>
