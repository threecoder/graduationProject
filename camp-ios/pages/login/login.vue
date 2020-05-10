<template>
	<view>
		<evan-form class="form-container">
			<text class="title">协会考试与证书管理平台</text>
			<evan-form-item label="账号：">
				<input type="text" v-model="account.username" placeholder="请输入账号" class="form-input" placeholder-class="form-input-placeholder" />
			</evan-form-item>
			<evan-form-item label="密码："><input type="password" v-model="account.password" placeholder="请输入密码" /></evan-form-item>
			<button class="evan-form-show__button" @click="login">登录</button>
		</evan-form>
	</view>
</template>

<script>
import EvanForm from '../../components/evan-form/evan-form.vue';
import EvanFormItem from '../../components/evan-form/evan-form-item.vue';
import utils from '../../components/evan-form/utils.js';
import { Encrypt } from '../../api/request.js';
import { toast } from '../../assets/js/commom.js';
import accountApi from '../../api/modules/account.js';
export default {
	components: {
		EvanForm,
		EvanFormItem
	},
	data() {
		return {
			account: {
				username: '',
				password: ''
			},
			name: null
		};
	},
	mounted() {
		this.name = this.$Route.query.name;
	},
	methods: {
		async login() {
			try {
				let data = { ...this.account };
				data.password = Encrypt(data.password);
				let res = await accountApi.login(data);
				let user = {
					userName: res.data.name,
					expire: res.data.expire,
					userAccount: data
				};
				this.$store.commit('setUser', user);
				if (this.name) {
					this.$Router.push({ name: this.name });
				} else {
					uni.switchTab({ url: '/pages/personal/personal' });
				}
			} catch (e) {
				toast(e.message);
				//TODO handle the exception
			}
		}
	}
};
</script>

<style lang="scss">
.form-container {
	width: 3.5rem;
	height: 3rem;
	text-align: center;
	position: fixed;
	top: calc(50% - 1.5rem);
	left: calc(50% - 1.75rem);
	.title {
		font-size: 0.2rem;
		margin-bottom: 0.5rem;
		display: block;
	}
	input {
		width: 100%;
	}
}
</style>
