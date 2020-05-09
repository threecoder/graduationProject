<template>
	<view class="all-container"><router-view></router-view></view>
</template>

<script>
import { request, Encrypt } from './api/request.js';
export default {
	data() {
		return {
		};
	},
	globalData: {
		network: null
	},
	onLaunch: function() {
		console.log('App Launch');
		uni.getNetworkType({
			success(res) {
				getApp().globalData.network = res.networkType;
				uni.showToast({
					title: '当前网络' + res.networkType,
					position: 'center'
				});
			}
		});
		uni.onNetworkStatusChange(function(res) {
			getApp().globalData.network = res.networkType;
			uni.showToast({
				title: '网络状态改变为' + res.networkType,
				position: 'center'
			});
		});
	},
	onShow: function() {
		console.log('App Show');
		this.$store.commit('init');
	},
	onHide: function() {
		console.log('App Hide');
	}
};
</script>

<style lang="scss">
/*每个页面公共css */
* {
	padding: 0;
	margin: 0;
}
html {
	font-size: 100px !important;
}
.my-top {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 0.2rem;
	z-index: 998;
	background-color: rgb(64, 158, 255);
}
.all-container {
	background-color: #eee;
	height: 100vh;
}
.evan-form-show {
	padding: 0 0.3rem;
	background-color: #fff;
	.form-input {
		font-size: 0.14rem;
		color: #333;
		text-align: right;
		width: 100%;
		box-sizing: border-box;
		height: 0.5rem;
		&.textarea {
			line-height: 0.5rem;
			height: 2.4rem;
		}
		::v-deep .uni-input-input:disabled {
			color: rgb(153, 153, 153);
		}
	}

	picker {
		width: 100%;
		height: 0.5rem;
		text-align: right;
		box-sizing: border-box;
		& > :last-child {
			width: 100%;
			height: 100%;
			font-size: 0.14rem;
			line-height: 0.5rem;
		}
	}
	.form-input-placeholder {
		font-size: 0.14rem;
		color: #999;
		line-height: 0.5rem;
	}
	&__button {
		width: 100% !important;
		height: 0.4rem;
		border-radius: 0.08rem;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 0;
		font-size: 0.16rem;
		color: #fff;
		margin-top: 0.2rem;
		background-color: rgb(64, 158, 255);
		&::before,
		&::after {
			border: none;
		}
	}
	.customize-form-item {
		&__label {
			font-size: 0.14rem;
			color: #333;
		}
		&__radio {
			display: flex;
			align-items: center;
			height: 0.5rem;
			line-height: 0.5rem;
			&__text {
				font-size: 0.14rem;
				color: #333;
				line-height: 0.5rem;
			}
		}
	}
}
.button-big {
	width: 80%;
	height: 0.4rem;
	border-radius: 0.08rem;
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 0;
	font-size: 0.16rem;
	color: #fff;
	margin: 0.2rem auto;
	background-color: rgb(64, 158, 255);
	&::before,
	&::after {
		border: none;
	}
}
.button-small {
	margin: 0;
	font-size: 0.14rem;
	background-color: rgb(64, 158, 255);
	color: #fff;
	padding: 0rem 0.14rem;
	margin-left: 0.1rem;
}
</style>
