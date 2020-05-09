<template>
	<view>
		<evan-form class="form-container" ref="form">
			<evan-form-item label="原密码：" prop="oldPassword">
				<input type="password" v-model="account.oldPassword" placeholder="请输入原密码" class="form-input" />
			</evan-form-item>
			<evan-form-item label="新密码：" prop="newPassword"><input type="password" v-model="account.newPassword" placeholder="请输入密码" /></evan-form-item>
			<evan-form-item label="再次确认密码：" prop="newPassword2"><input type="password" v-model="account.newPassword2" placeholder="请再次确认密码" /></evan-form-item>
			<button class="evan-form-show__button" @click="modify">修改密码</button>
		</evan-form>
	</view>
</template>

<script>
import EvanForm from '../../../components/evan-form/evan-form.vue';
import EvanFormItem from '../../../components/evan-form/evan-form-item.vue';
import utils from '../../../components/evan-form/utils.js';
import { Encrypt } from '../../../api/request.js';
import { toast } from '../../../assets/js/commom.js';
import accountApi from '../../../api/modules/account.js';
export default {
	components: {
		EvanForm,
		EvanFormItem
	},
	data() {
		return {
			account: {
				oldPassword: '',
				newPassword: '',
				newPassword2: ''
			},
			rules: {
				oldPassword: {
					required: true,
					message: '请输入原密码'
				},
				newPassword: {
					required: true,
					message: '请输入新密码'
				},
				newPassword2: [
					{
						required: true,
						message: '请再次确认密码'
					},
					{
						validator: (rule, value, callback) => {
							if (value === this.account.newPassword) {
								callback();
							} else {
								callback(new Error('两次输入新密码不一致'));
							}
						}
					}
				]
			}
		};
	},
	methods: {
		modify() {
			this.$refs.form.validate(async res => {
				try {
					let data = { ...this.account };
					let res = await accountApi.setPassword(data);
					toast('修改密码成功，请重新登录账号');
					this.$store.commit('removeUser');
					uni.switchTab({ url: '/pages/personal/personal' });
				} catch (e) {
					toast(e.message);
					//TODO handle the exception
				}
			});
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
		text-align: right;
	}
}
</style>
