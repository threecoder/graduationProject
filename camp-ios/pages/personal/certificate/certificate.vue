<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content" :class="{ mt: isFixed }">
				<view v-show="contentIndex === 0"><single v-for="(item, i) in nowList" :key="i" :item="item" status="有效" @refresh="refresh" /></view>
				<view v-show="contentIndex === 1"><single v-for="(item, i) in overList" :key="i" :item="item" status="过期" @refresh="refresh" /></view>
				<load-more :status="hasMore" @clickLoadMore="loadMore"></load-more>
			</view>
		</view>
	</view>
</template>

<script>
import seg from '../../../components/seg/seg.vue';
import single from './components/singleCertificate.vue';
import loadMore from '../../../components/load-more/load-more.vue';
import cerApi from '../../../api/modules/certificate.js';
import { toast } from '../../../assets/js/commom.js';
export default {
	components: {
		seg,
		single,
		loadMore
	},
	data() {
		return {
			res: null,
			contentIndex: 0,
			segItem: ['现有证书', '过期证书'],
			isFixed: false,
			nowList: [],
			overList: [],
			par: {
				currentPage: 1,
				pageSize: 10
			},
			hasMore: 'more'
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
		async getOverCerList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await cerApi.getOverdueCerList(this.par);
					this.overList.push(...res.data.data);
				} else {
					this.par.currentPage = 1;
					res = await cerApi.getOverdueCerList(this.par);
					this.overList = res.data.data;
				}
				if (res.data.data.length == 0) {
					this.hasMore = 'noMore';
				} else {
					this.hasMore = 'more';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		async getNowCerList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await cerApi.getCertificateList(this.par);
					this.nowList.push(...res.data.data);
				} else {
					this.par.currentPage = 1;
					res = await cerApi.getCertificateList(this.par);
					console.log('证书', res);
					this.nowList = res.data.data;
				}
				if (res.data.data.length == 0) {
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
				await this.getNowCerList('new');
			} else {
				await this.getOverCerList('new');
			}
		},
		loadMore() {
			this.hasMore = 'loading';
			if (this.contentIndex == 0) {
				this.getNowCerList('add');
			} else {
				this.getOverCerList('add');
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
