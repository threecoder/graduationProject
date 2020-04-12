<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content">
				<view class="caroulse">
					<swiper class="swiper" indicator-dots autoplay circular :interval="2000" :duration="500">
						<swiper-item><image src="../../assets/images/index1.jpg" mode="aspectFill"></image></swiper-item>
						<swiper-item><image src="../../assets/images/index2.jpg" mode="aspectFill"></image></swiper-item>
						<swiper-item><image src="../../assets/images/index3.jpg" mode="aspectFill"></image></swiper-item>
					</swiper>
				</view>
				<view v-show="contentIndex === 0"><single v-for="(item, i) in newsList" :key="i" :item="item" /></view>
				<view v-show="contentIndex === 1"><single v-for="(item, i) in dynamicList" :key="i" :item="item" /></view>
			</view>
		</view>
	</view>
</template>

<script>
import uniNavBar from '../../components/uni-nav-bar/uni-nav-bar.vue';
import seg from '../../components/seg/seg.vue';
import single from './components/singleDynamic.vue';
import indexApi from '../../api/index/dynamic.js';
import { getNetwork } from '../../network.js';
export default {
	components: {
		uniNavBar,
		seg,
		single
	},
	data() {
		return {
			res: null,
			contentIndex: 0,
			segItem: ['新闻', '动态'],
			isFixed: false,
			newsList: [
				{
					id: '111',
					title: '新闻：去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
					date: '2020-02-02 14:00',
					url: '11',
					type: "news"
				}
			],
			dynamicList: [
				{
					title: '动态：去年今日韦德生涯最后一舞，香蕉船兄弟场边庆祝韦德三双',
					date: '2020-02-02 14:00',
					url: '11',
					id: '222',
					type: "dynamic"
				}
			]
		};
	},
	mounted() {
		getNetwork();
		this.getDynamicList();
	},
	onPullDownRefresh() {
		console.log('下拉刷新');
		uni.stopPullDownRefresh();
	},
	onPageScroll(scroll) {
		console.log(scroll.scrollTop);
		this.isFixed = scroll.scrollTop > 0 ? true : false;
	},

	methods: {
		async getDynamicList() {
			try {
				//加上类型
			} catch (e) {
				uni.showToast({
					title: e.message,
					position: 'top'
				});
				//TODO handle the exception
			}
		},
		onClickItem(index) {
			this.contentIndex = index;
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
		background-color: #fff;
		.caroulse {
			height: 300rpx;
			.swiper {
				height: 300rpx;
			}
			image {
				width: 100%;
				height: 100%;
			}
		}
	}
}
</style>
