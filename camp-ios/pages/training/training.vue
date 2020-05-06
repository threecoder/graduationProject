<template>
	<view class="container">
		<view>
			<seg :isFixed="isFixed" :current="contentIndex" :segItem="segItem" @clickItem="onClickItem"></seg>
			<view class="content">
				<view><single v-for="(item, i) in list" :key="i" :item="item" /></view>
				<load-more :status="hasMore" @clickLoadMore="loadMore" />
			</view>
		</view>
	</view>
</template>

<script>
import seg from '../../components/seg/seg.vue';
import single from './components/singleTraining.vue';
import loadMore from '../../components/load-more/load-more.vue';
import courseApi from '../../api/index/courses.js';
import { toast } from '../../assets/js/commom.js';
export default {
	components: {
		seg,
		single,
		loadMore
	},
	data() {
		return {
			res: null,
			contentIndex: 1,
			segItem: ['往期', '当前', '即将'],
			isFixed: false,
			list: [],
			par: {
				type: 'now',
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
				this.par.type = 'previous';
			} else if (newVal == 1) {
				this.par.type = 'now';
			} else {
				this.par.type = 'future';
			}
			this.getList('new');
		}
	},
	mounted() {
		this.getList('new');
	},
	async onPullDownRefresh() {
		this.par.currentPage == 1;
		await this.getList("new");
		uni.stopPullDownRefresh();
	},
	onPageScroll(scroll) {
		this.isFixed = scroll.scrollTop > 0 ? true : false;
	},

	methods: {
		async getList(type = 'add') {
			try {
				let res = null;
				if (type == 'add') {
					this.par.currentPage++;
					res = await courseApi.getCourses(this.par);
					this.list.push(...res.data.list);
				} else {
					this.par.currentPage = 1;
					res = await courseApi.getCourses(this.par);
					this.list = res.data.list;
				}
				console.log(res);
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
		toDetail(item) {
			this.$Router.push({ name: 'dynamicDetail', query: { id: item.id, type: item.type } });
		},
		async loadMore() {
			this.hasMore = 'loading';
			this.getList();
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
