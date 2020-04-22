<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content">
				<view class="caroulse">
					<swiper class="swiper" indicator-dots autoplay circular :interval="2000" :duration="500">
						<swiper-item v-for="(item, i) in swiperList" :key="i" @click="toDetail(item)">
							<text>{{ item.title }}</text>
							<image src="../../assets/images/index1.jpg" mode="aspectFill"></image>
							<!-- <image :src="item.url" mode="aspectFill"></image> -->
						</swiper-item>
						<swiper-item><image src="../../assets/images/index1.jpg" mode="aspectFill"></image></swiper-item>
						<swiper-item><image src="../../assets/images/index2.jpg" mode="aspectFill"></image></swiper-item>
						<swiper-item><image src="../../assets/images/index3.jpg" mode="aspectFill"></image></swiper-item>
					</swiper>
				</view>
				<view v-show="contentIndex === 0"><single v-for="(item, i) in newsList" :key="i" :item="item" /></view>
				<view v-show="contentIndex === 1"><single v-for="(item, i) in dynamicList" :key="i" :item="item" /></view>
				<load-more :status="hasMore" @clickLoadMore="loadMore" />
			</view>
		</view>
	</view>
</template>

<script>
import uniNavBar from '../../components/uni-nav-bar/uni-nav-bar.vue';
import seg from '../../components/seg/seg.vue';
import single from './components/singleDynamic.vue';
import loadMore from '../../components/load-more/load-more.vue';
import dynamicApi from '../../api/index/dynamic.js';
import indexApi from '../../api/index/index.js';
import newsApi from '../../api/index/news.js';
import { toast } from '../../assets/js/commom.js';
import { getNetwork } from '../../network.js';
import { baseUrl } from '../../const';
export default {
	components: {
		uniNavBar,
		seg,
		single,
		loadMore
	},
	data() {
		return {
			res: null,
			contentIndex: 0,
			segItem: ['新闻', '动态'],
			isFixed: false,
			swiperList: [
				{
					id: '111',
					title: '去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
					date: '2016-10-10',
					url: '',
					type: 'news'
				}
			],
			newsList: [
				{
					id: '111',
					title: '新闻：去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
					date: '2020-02-02 14:00',
					url: '11',
					type: 'news'
				}
			],
			dynamicList: [
				{
					title: '动态：去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
					date: '2020-02-02 14:00',
					url: '11',
					id: '222',
					type: 'dynamic'
				}
			],
			par: {
				currentPage: 1,
				pageSize: 10
			},
			hasMore: 'more'
		};
	},
	watch: {
		contentIndex(newVal) {
			this.par.currentPage = 1;
			this.hasMore = 'more';
			if (newVal == 0) {
				this.getNewsList('new');
			} else {
				this.getDynamicList('new');
			}
		}
	},
	mounted() {
		getNetwork();
		this.getNewsList('new');
	},
	async onPullDownRefresh() {
		this.getSwiperList();
		this.par.currentPage == 1;
		if (this.contentIndex == 0) {
			await this.getNewsList('new');
		} else {
			await this.getDynamicList('new');
		}
		uni.stopPullDownRefresh();
	},
	onPageScroll(scroll) {
		this.isFixed = scroll.scrollTop > 0 ? true : false;
	},

	methods: {
		$_replaceSrc(data) {
			data.forEach(val => {
				val.url = val.url.replace(/http:\/\/localhost:3000/g, baseUrl);
			});
		},
		async getDynamicList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await dynamicApi.getDynamicList(this.par);
					this.dynamicList.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await dynamicApi.getDynamicList(this.par);
					this.dynamicList = res.data.list;
				}
				this.$_replaceSrc(this.dynamicList);
				if (res.data.list.length == 0) {
					this.hasMore = 'noMore';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		async getNewsList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await newsApi.getNewsList(this.par);
					this.newsList.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await newsApi.getNewsList(this.par);
					this.newsList = res.data.list;
				}
				this.$_replaceSrc(this.newsList);
				if (res.data.list.length == 0) {
					this.hasMore = 'noMore';
				} else {
					this.hasMore = 'more';
				}
			} catch (e) {
				toast(e.message);
			}
		},
		async getSwiperList() {
			try {
				let res = await indexApi.getCarouselList();
				this.newsList = res.data;
			} catch (e) {
				toast(e.message);
			}
		},
		onClickItem(index) {
			this.contentIndex = index;
		},
		toDetail(item) {
			this.$Router.push({ name: 'dynamicDetail', query: { id: item.id, type: item.type } });
		},
		async loadMore() {
			this.hasMore = 'loading';
			let type = null;
			if (this.contentIndex == 0) {
				await this.getNewsList('add');
			} else {
				await this.getDynamicList('add');
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
	.content {
		width: 100%;
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
}
</style>
