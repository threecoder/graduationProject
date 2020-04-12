<template>
    <view>
        <view class="evan-form-show">
            <evan-form :hide-required-asterisk="hideRequiredAsterisk" ref="form" :model="info">
                <evan-form-item label="身份证号：" prop="idNum">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.idNum"
                        disabled
                    />
                </evan-form-item>
                <evan-form-item label="姓名：" prop="name">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.name"
                        placeholder="请输入姓名"
                    />
                </evan-form-item>
                <evan-form-item label="性别" prop="sex">
                    <picker @change="sexChange" :value="info.sex" :range="array">
                        <view
                            class="uni-input"
                            :class="{grey: array[info.sex]=='请选择'}"
                        >{{array[info.sex]}}</view>
                    </picker>
                </evan-form-item>

                <evan-form-item label="手机号：" prop="phone">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.phone"
                        placeholder="请输入手机号"
                    />
                </evan-form-item>
                <evan-form-item label="邮箱：" prop="email">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.email"
                        placeholder="请输入邮箱"
                    />
                </evan-form-item>
                <evan-form-item label="职务：" prop="position">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.position"
                        placeholder="请输入职务"
                    />
                </evan-form-item>
                <evan-form-item label="省份：" prop="province">
                    <!-- <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.province"
                        placeholder="请输入省份"
                    />-->
                    <picker @change="provinceChange" :value="info.province" :range="array">
                        <view
                            class="uni-input"
                            :class="{grey: array[info.province]=='请选择'}"
                        >{{array[info.province]}}</view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="城市：" prop="city">
                    <!-- <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.city"
                        placeholder="请输入职务"
                    />-->
                    <picker @change="cityChange" :value="info.city" :range="array">
                        <view
                            class="uni-input"
                            :class="{grey: array[info.city]=='请选择'}"
                        >{{array[info.city]}}</view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="区域：" prop="area">
                    <picker @change="areaChange" :value="info.area" :range="array">
                        <view
                            class="uni-input"
                            :class="{grey: array[info.area]=='请选择'}"
                        >{{array[info.area]}}</view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="详细地址：" prop="zone">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        v-model="info.zone"
                        placeholder="请输入详细地址"
                    />
                </evan-form-item>
            </evan-form>
            <button @click="save" class="evan-form-show__button">保存</button>
            <button @click="utilsSave" class="evan-form-show__button">直接调用utils验证</button>
            <button @click="validateSingle" class="evan-form-show__button">只验证邮箱</button>
            <button @click="validateMultiple" class="evan-form-show__button">只验证邮箱和手机号</button>
        </view>
    </view>
</template>

<script>
import EvanForm from "../../components/evan-form/evan-form.vue";
import EvanFormItem from "../../components/evan-form/evan-form-item.vue";
import utils from "../../components/evan-form/utils.js";
import infoApi from "../../api/modules/info.js";
export default {
    components: {
        EvanForm,
        EvanFormItem
    },
    data() {
        return {
            hideRequiredAsterisk: false,
            sexes: [
                {
                    name: "男",
                    value: "man"
                },
                {
                    name: "女",
                    value: "woman"
                }
            ],
            // 表单的内容必须初始化
            info: {
                idNum: "111111",
                name: "",
                email: "",
                position: "",
                province: 0,
                city: 0,
                area: 0,
                zone: "",
                phone: "",
                sex: 0
            },
            rules: {
                idNum: {
                    required: true,
                    message: "请输入身份证号"
                },
                name: {
                    required: true,
                    message: "请输入姓名"
                },
                email: [
                    {
                        required: true,
                        message: "请输入邮箱"
                    },
                    {
                        type: "email",
                        message: "邮箱格式不正确"
                    }
                ],
                phone: [
                    {
                        required: true,
                        message: "请输入手机号"
                    },
                    {
                        validator: (rule, value, callback) => {
                            // 注意这里如果用的是methods里的isMobilePhone将不生效
                            if (this.$utils.isMobilePhone(value)) {
                                callback();
                            } else {
                                callback(new Error("手机号格式不正确"));
                            }
                        }
                    }
                    // 或者这样也是可以的
                    // {
                    // 	validator: this.isMobile
                    // }
                ],
                position: {
                    required: true,
                    message: "请输入职位"
                },
                province: {
                    required: true,
                    message: "请选择省份"
                },
                city: {
                    required: true,
                    message: "请选择城市"
                },
                area: {
                    required: true,
                    message: "请选择区域"
                },
                zone: [
                    {
                        required: true,
                        message: "请输入详细地址"
                    }
                ],
                sex: {
                    required: true,
                    message: "请选择性别"
                }
            },
            array: ["请选择", "中国", "美国", "巴西", "日本"]
        };
    },
    mounted() {
        // 这里必须放在mounted中，不然h5，支付宝小程序等会找不到this.$refs.form
        this.$refs.form.setRules(this.rules);
        this.getUserInfo();
    },
    methods: {
        async getUserInfo() {
            console.log("获取接口");
            try {
                let res = await infoApi.getStudentInfo();
                this.info = res.data;
                console.log(res);
            } catch (error) {
                uni.showToast({
                    title: error.message,
                    position: "center"
                });
            }
        },
        save() {
            this.$refs.form.validate(async res => {
                if (res) {
                    try {
                        let data = { ...this.form };
                        let res = await infoApi.setStudentInfo(data);
                        uni.showToast({
                            title: "保存成功"
                        });
                    } catch (error) {
                        uni.showToast({
                            title: error.message
                        });
                    }
                }
            });
        },
        utilsSave() {
            utils.validate(this.info, this.rules, (res, errors) => {
                if (res) {
                    uni.showToast({
                        title: "验证通过"
                    });
                }
            });
        },
        validateSingle() {
            this.$refs.form.validateField("email", res => {
                if (res) {
                    uni.showToast({
                        title: "验证通过"
                    });
                }
            });
        },
        validateMultiple() {
            this.$refs.form.validateField(["email", "phone"], res => {
                if (res) {
                    uni.showToast({
                        title: "验证通过"
                    });
                }
            });
        },

        isMobilePhone() {
            const reg = /^1\d{10}$/;
            if (reg.test(value)) {
                return true;
            }
            return false;
        },
        isMobile(rule, value, callback) {
            if (this.$utils.isMobilePhone(value)) {
                callback();
            } else {
                callback(new Error("手机号格式不正确"));
            }
        },
        sexChange(e) {
            this.info.sex = e.detail.value;
        },
        provinceChange(e) {
            console.log(e.detail.value);
            this.info.province = e.detail.value;
        },
        cityChange(e) {
            this.info.city = e.detail.value;
        },
        areaChange(e) {
            this.info.area = e.detail.value;
        }
    }
};
</script>

<style lang="scss">
.grey {
    color: rgb(153, 153, 153);
}
</style>