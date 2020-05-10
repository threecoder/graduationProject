<template>
	<view>
		<view class="my-top"></view>
		<view class="person-info" hover-class="person-info--hover" @click="settingOrLogin">
			<view class="item">
				<text v-if="name">
					<text class="name">{{ name }}</text>
					<text class="type">{{ type }}</text>
				</text>
				<text v-else class="name">未登录</text>
			</view>
			<view class="item item-arrow"><uni-icons :size="20" class="my-arrow" color="#bbb" type="arrowright" /></view>
		</view>
		<uni-list>
			<template v-for="(item, i) in list">
				<uni-list-item v-if="item.title != '我的消息'" :key="i" :title="item.title" @click="to(item)"></uni-list-item>
				<uni-list-item v-else :key="list.length" :badgeText="numString" title="我的消息" :showBadge="true" badgeType="warning" @click="to(item)"></uni-list-item>
			</template>
		</uni-list>
	</view>
</template>

<script>
import uniIcons from '../../components/uni-icons/uni-icons.vue';
import uniList from '../../components/uni-list/uni-list.vue';
import uniListItem from '../../components/uni-list-item/uni-list-item.vue';
import msgApi from '../../api/modules/message.js';
import accountApi from '../../api/modules/account.js';
import { toast } from '../../assets/js/commom.js';
export default {
	components: {
		uniIcons,
		uniList,
		uniListItem
	},
	data() {
		return {
			list: [
				{
					title: '我的信息',
					name: 'info'
				},
				{
					title: '我的证书',
					name: 'certificate'
				},
				{
					title: '我的培训',
					name: 'training'
				},
				{
					title: '我的考试',
					name: 'exam'
				},
				{
					title: '我的活动',
					name: 'activity'
				},
				{
					title: '我的投票',
					name: 'vote'
				},
				{
					title: '我的订单',
					name: 'order'
				},
				{
					title: '我的消息',
					name: 'message'
				}
			],
			type: '学员'
		};
	},
	watch: {
		'$Router.path'(val) {
			console.log('personal', val);
			this.getUnreadNum();
		}
	},
	onShow() {
		console.log('展示');
		if (this.$store.getters.isLogin) {
			this.getUnreadNum();
		}
	},
	computed: {
		name: function() {
			console.log('baneeeee', this.$store.state);
			return this.$store.getters.userName;
		},
		numString: function() {
			return this.$store.getters.msgNum.toString();
		}
	},

	methods: {
		to(item) {
			this.$Router.push({ name: item.name });
		},
		settingOrLogin() {
			if (!this.name) {
				this.$Router.push({ name: 'login' });
			} else {
				// this.$Router.push({ name: 'setting' });
				uni.showActionSheet({
					itemList: ['修改密码', '退出登录'],
					itemColor: '#409EFF',
					success: res => {
						if (res.tapIndex == 0) {
							this.$Router.push({ name: 'password' });
						} else {
							this.logout();
						}
					},
					fail: function(res) {
						console.log(res.errMsg);
					}
				});
			}
		},
		async logout() {
			try {
				let res = await accountApi.logout();
			} catch (e) {
				//TODO handle the exception
			}
			toast('退出账号成功');
			this.$store.commit('removeUser');
			this.$Router.push({ name: 'login' });
		},
		async getUnreadNum() {
			try {
				let time = this.$store.getters.msgTime;
				let newTime = new Date().getTime();
				if (newTime - time > 20000) {
					this.$store.commit('setMsgTime', { time: newTime });
					let res = await msgApi.getUnReadMsgNum();
					this.$store.commit('setMsgNum', { msgNum: res.data });
				}
			} catch (e) {
				toast(e.message);
				//TODO handle the exception
			}
		}
	}
};
</script>

<style lang="scss" scoped>
.person-info {
	height: 0.8rem;
	width: 100%;
	background-color: rgb(64, 158, 255);
	display: flex;
	justify-content: flex-end;
	align-items: center;
	.item {
		width: 50%;
		height: 0.8rem;
		line-height: 0.8rem;
		margin-top: 0.2rem;
	}
	.name {
		display: block;
		font-size: 0.2rem;
		color: #fff;
		margin-left: 0.3rem;
		float: left;
	}
	.type {
		display: block;
		font-size: 0.14rem;
		color: #fff;
		margin-left: 0.3rem;
		float: left;
	}
	.item-arrow {
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		padding-right: 0.3rem;
	}
	.my-arror {
		margin-right: 0.3rems;
	}
}
.person-info--hover {
	background-color: rgb(84, 178, 255);
}
</style>
