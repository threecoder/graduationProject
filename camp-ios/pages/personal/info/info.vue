<template>
    <view>
        <view class="evan-form-show">
            <evan-form ref="form" :model="info">
                <evan-form-item label="身份证号：" prop="idNum">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.idNum"
                        disabled
                    />
                </evan-form-item>
                <evan-form-item label="姓名：" prop="name">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.name"
                        placeholder="请输入姓名"
                        @input="inputChange"
                        data-key="name"
                    />
                </evan-form-item>
                <evan-form-item label="性别" prop="sex">
                    <picker @change="sexChange" :value="info.sex" :range="sexes">
                        <view
                            class="uni-input"
                            :class="{grey: sexes[info.sex]=='请选择'}"
                        >{{sexes[info.sex]}}></view>
                    </picker>
                </evan-form-item>

                <evan-form-item label="手机号：" prop="phone">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.phone"
                        placeholder="请输入手机号"
                        @input="inputChange"
                        data-key="phone"
                    />
                </evan-form-item>
                <evan-form-item label="邮箱：" prop="email">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.email"
                        @input="inputChange"
                        data-key="email"
                        placeholder="请输入邮箱"
                    />
                </evan-form-item>
                <evan-form-item label="职务：" prop="position">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.position"
                        placeholder="请输入职务"
                        @input="inputChange"
                        data-key="position"
                    />
                </evan-form-item>
                <evan-form-item label="省份：" prop="province">
                    <picker @change="provinceChange" :value="info.province" :range="provinceList">
                        <view
                            class="uni-input"
                            :class="{grey: provinceList[info.province]=='请选择'}"
                        >{{provinceList[info.province]}}></view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="城市：" prop="city">
                    <picker @change="cityChange" :value="info.city" :range="cityList">
                        <view
                            class="uni-input"
                            :class="{grey: cityList[info.city]=='请选择'}"
                        >{{cityList[info.city]}}></view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="区域：" prop="area">
                    <picker @change="areaChange" :value="info.area" :range="areaList">
                        <view
                            class="uni-input"
                            :class="{grey: areaList[info.area]=='请选择'}"
                        >{{areaList[info.area]}}></view>
                    </picker>
                </evan-form-item>
                <evan-form-item label="详细地址：" prop="zone">
                    <input
                        class="form-input"
                        placeholder-class="form-input-placeholder"
                        :value="info.zone"
                        placeholder="请输入详细地址"
                        @input="inputChange"
                        data-key="zone"
                    />
                </evan-form-item>
            </evan-form>
            <button @click="save" class="evan-form-show__button">保存</button>
            <!-- <button @click="utilsSave" class="evan-form-show__button">直接调用utils验证</button>
            <button @click="validateSingle" class="evan-form-show__button">只验证邮箱</button>
            <button @click="validateMultiple" class="evan-form-show__button">只验证邮箱和手机号</button>-->
        </view>
    </view>
</template>

<script>
import EvanForm from "../../../components/evan-form/evan-form.vue";
import EvanFormItem from "../../../components/evan-form/evan-form-item.vue";
import utils from "../../../components/evan-form/utils.js";
import infoApi from "../../../api/modules/info.js";
import cityApi from "../../../assets/js/city.js";
import { toast } from "../../../assets/js/commom.js";
export default {
    components: {
        EvanForm,
        EvanFormItem
    },
    data() {
        return {
            // 表单的内容必须初始化
            info: {
                idNum: "111111",
                name: "京津冀",
                email: "101@qq.com",
                position: "职务",
                province: 0,
                city: 0,
                area: 0,
                zone: "哈哈哈哈",
                phone: "1571111",
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
            email: "",
            sexes: ["请选择", "男", "女"],
            provinceList: ["请选择"],
            cityList: ["请选择"],
            areaList: ["请选择"]
        };
    },
    beforeMount() {},
    mounted() {
        // 这里必须放在mounted中，不然h5，支付宝小程序等会找不到this.$refs.form
        this.$refs.form.setRules(this.rules);
        this.initList();
        this.getUserInfo();
    },
    methods: {
        initList() {
            let arr = cityApi.getProvinceList();
            this.provinceList.push(...arr);
        },
        inputChange(e) {
            let key = e.target.dataset.key;
            this.info[key] = e.detail.value;
        },
        async getUserInfo() {
            try {
                let res = await infoApi.getStudentInfo();
                let data = res.data;
                this.cityList.push(...cityApi.getCityList(data.province));
                this.areaList.push(
                    ...cityApi.getAreaList(data.province, data.city)
                );
                data.province = this.provinceList.indexOf(data.province);
                data.city = this.cityList.indexOf(data.city);
                data.area = this.areaList.indexOf(data.area);
                data.sex = 1;
                this.info = { ...data };
            } catch (error) {
                console.log(error);
                toast(error.message);
            }
        },
        save() {
            this.$refs.form.validate(async res => {
                if (res) {
                    if (!this.info.sex) {
                        toast("请选择性别");
                        return false;
                    } else if (!this.info.province) {
                        toast("请选择省份");
                        return false;
                    } else if (!this.info.city) {
                        toast("请选择城市");
                        return false;
                    } else if (!this.info.area) {
                        toast("请选择区域");
                        return false;
                    }
                    try {
                        let data = { ...this.info };
                        data.sex = this.sexes[data.sex];
                        data.province = this.provinceList[data.province];
                        data.city = this.cityList[data.city];
                        data.area = this.areaList[data.area];
                        console.log(data);
                        let res = await infoApi.setStudentInfo(data);
                        uni.showToast({
                            title: "保存成功"
                        });
                    } catch (error) {
                        toast(error.message);
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
            let val = e.detail.value;
            this.info.province = val;
            this.info.city = this.info.area = 0;
            if (val > 0) {
                this.cityList = cityApi.getCityList(this.provinceList[val]);
                this.cityList.unshift("请选择");
            } else if (val == 0) {
                this.cityList = ["请选择"];
            }
            this.areaList = ["请选择"];
        },
        cityChange(e) {
            let val = e.detail.value;
            this.info.city = val;
            this.info.area = 0;
            if (val > 0) {
                this.areaList = cityApi.getAreaList(
                    this.provinceList[this.info.province],
                    this.cityList[val]
                );
                this.areaList.unshift("请选择");
            } else if (val == 0) {
                this.areaList = ["请选择"];
            }
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