<template>
    <div class="my-container">
        <el-row>
            <el-col :span="20">
                <h3>公司信息</h3>
            </el-col>
            <el-col :span="4">
                <el-button class="fr" type="primary" @click="recharge" v-if="info.memberFlag">续费会员</el-button>
            </el-col>
        </el-row>
        <div>
            <span class="panel-title">基本信息</span>
            <el-form :model="info" :loading="loading" label-position="top">
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="公司名字">
                            <el-input v-model="info.name" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="号码">
                            <el-input v-model="info.phone" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="邮箱">
                            <el-input v-model="info.email" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="4">
                        <el-form-item label-position="top" label="是否是会员">
                            <el-input v-model="info.memberFlag" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label-position="top" label="会员开始时间">
                            <el-input v-model="info.memberBeginDate" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label-position="top" label="会员到期时间">
                            <el-input v-model="info.memberEndDate" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label-position="top" label="地址">
                            <my-address
                                :province="info.province"
                                :area="info.area"
                                :city="info.city"
                                :zone="info.zone"
                                :disabled="readOnly"
                                @provinceChange="changeProvince"
                                @cityChange="changeCity"
                                @areaChange="changeArea"
                                @zoneChange="changeZone"
                            />
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div class="tac mt30">
                <el-button v-if="readOnly" type="primary" @click="readOnly=false">修改资料</el-button>
                <el-button v-if="!readOnly" type="primary" @click="setUserInfo">确定</el-button>
                <el-button v-if="!readOnly" type="primary" @click="readOnly=true">取消</el-button>
            </div>
        </div>
    </div>
</template>
<script>
import myAddress from "../../../../components/address.vue";
import infoApi from "../../../../api/modules/info";
export default {
    components: {
        myAddress
    },
    data() {
        return {
            info: {
                phone: null,
                name: null,
                email: null,
                company: null,
                province: null,
                city: null,
                area: null,
                zone: null,
                memberFlag: null,
                memberBeginDate: null,
                memberEndDate: null
            },
            readOnly: true,
            provinceOptions: [],
            cityOptions: [],
            dialogFormVisible: false,
            formLabelWidth: "120px",
            loading: false,
            confirmLoading: false
        };
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        async getUserInfo() {
            try {
                this.loading = true;
                let res = await infoApi.getMemberInfo();
                this.info = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        async setUserInfo() {
            try {
                this.confirmLoading = true;
                let res = await infoApi.setMemberInfo(this.info);
                this.$message.success(res.msg);
                this.readOnly = true;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.confirmLoading = false;
        },
        recharge() {
            this.$confirm("确定要续费吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await infoApi.rechargeVIP();
                    this.$alert(
                        `你的续费订单号是：${res.data.orderNum}，请及时付款完成续费`,
                        "提示",
                        {
                            cancelButtonText: "取消",
                            confirmButtonText: "确定",
                            type: "warning"
                        }
                    ).then(() => {
                        this.$router.push("/order");
                    });
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        changeProvince(val) {
            this.info.province = val;
        },
        changeCity(val) {
            this.info.city = val;
        },
        changeArea(val) {
            this.info.area = val;
        },
        changeZone(val) {
            this.info.zone = val;
        }
    }
};
</script>
<style lang="scss" scoped>
::v-deep .el-input-group__prepend {
    width: 250px;
    margin: 0 !important;
}
::v-deep .distpicker-address-wrapper {
    select {
        border: 0;
        background-color: #f5f7fa;
    }
}
.modifyButton {
    position: absolute;
    top: 35px;
    right: 5%;
}
::v-deep #zone-input {
    // min-width: 300px !important;
    width: 100%;
}
</style>