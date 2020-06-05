<template>
	<view class="dynamic-container">
		<view class="header">
			<p class="title">{{ detail.title }}</p>
			<p class="date">{{ detail.date }}</p>
		</view>
		<view v-html="detail.content" class="content"></view>
	</view>
</template>

<script>
import dynamicApi from '../../api/index/dynamic';
import newsApi from '../../api/index/news';
import noticeApi from '../../api/index/notice';
import { toast } from '../../assets/js/commom';
import { baseUrl } from '../../const';
export default {
	data() {
		return {
			info: {
				type: 'news',
				id: '11'
			},
			detail: {
				title: '',
				date: '',
				content: ''
			}
		};
	},
	beforeMount() {
		this.info = this.$Route.query;
		this.getDetail();
		console.log(this.info);
	},
	methods: {
		async getDetail() {
			try {
				let res = null,
					type = this.info.type;
				if (type == 'news') {
					res = await newsApi.getNewsDetail(this.info.id);
				} else if (type == 'notice') {
					res = await noticeApi.getNoticeDetail(this.info.id);
				} else {
					res = await dynamicApi.getDynamicDetail(this.info.id);
				}
				console.log('详情', res);
				this.detail = res.data;
				this.$_handleImg();
				this.detail.content = this.detail.content.replace(/http:\/\/localhost:3000/g, baseUrl);
				console.log(this.detail.content);
			} catch (error) {
				toast(error.message);
			}
		},
		$_handleImg() {
			let placeholder = this.detail.placeholder;
			let content = this.detail.content;
			let index = 0;
			while (index < this.detail.urlList.length) {
				let img = '<img src="' + this.detail.urlList[index++] + '" />';
				content = content.replace(placeholder, img);
			}
			this.detail.content = content;
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
