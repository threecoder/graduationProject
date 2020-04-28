<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content" :class="{ mt: isFixed }">
				<view v-show="contentIndex === 0">
					<single v-for="(item, i) in joinableList" :key="i" :item="item" status="可报名" @refresh="refresh" trainingDetail="trainingDetail(item)" />
				</view>
				<view v-show="contentIndex === 1">
					<single v-for="(item, i) in signedList" :key="i" :item="item" status="已报名" @refresh="refresh" trainingDetail="trainingDetail(item)" />
				</view>
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
import single from './components/singleTraining.vue';
import uniPopup from '../../../components/uni-popup/uni-popup.vue';
import loadMore from '../../../components/load-more/load-more.vue';
import trainingApi from '../../../api/modules/training.js';
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
			res: null,
			contentIndex: 0,
			segItem: ['可报名培训', '已报名培训'],
			isFixed: false,
			joinableList: [],
			signedList: [],
			par: {
				currentPage: 1,
				pageSize: 10
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
		async getSignedList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await trainingApi.getsignedTraining(this.par);
					this.signedList.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await trainingApi.getsignedTraining(this.par);
					this.signedList = res.data.list;
				}
				if (res.data.list.length == 0) {
					this.hasMore = 'noMore';
				} else {
					this.hasMore = 'more';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		async getJoinableList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await trainingApi.getJoinableTraining(this.par);
					this.joinableList.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await trainingApi.getJoinableTraining(this.par);
					this.joinableList = res.data.list;
				}
				if (res.data.list.length == 0) {
					this.hasMore = 'noMore';
				} else {
					this.hasMore = 'more';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		onClickItem(index) {
			this.contentIndex = index;
		},
		async refresh() {
			if (this.contentIndex == 0) {
				await this.getJoinableList('new');
			} else {
				await this.getSignedList('new');
			}
		},
		trainingDetail(item) {
			this.introduction = item.introduction;
			this.$refs.popup.open();
		},
		loadMore() {
			this.hasMore = 'loading';
			if (this.contentIndex == 0) {
				this.getJoinableList('add');
			} else {
				this.getSignedList('add');
			}
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
