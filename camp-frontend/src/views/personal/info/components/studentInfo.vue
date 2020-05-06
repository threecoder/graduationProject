<template>
    <div>
        <h2>个人信息</h2>
        <div>
            <span class="panel-title">基本信息</span>
            <el-form :model="info" :loading="loading">
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="身份证号码">
                            <el-input v-model="info.idNum" disabled></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="名字">
                            <el-input v-model="info.name" disabled></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label-position="top" label="手机号码">
                            <el-input v-model="info.phone" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="邮箱">
                            <el-input v-model="info.email" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="所属公司">
                            <el-input v-model="info.company" :disabled="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="职务">
                            <el-input v-model="info.position" :disabled="readOnly"></el-input>
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
            idType: null,
            info: {
                idNum: null,
                phone: null,
                name: null,
                email: null,
                company: null,
                position: null,
                province: null,
                city: null,
                area: null,
                zone: null
            },
            readOnly: true,
            provinceOptions: [],
            cityOptions: [],
            loading: false,
            confirmLoading: false
        };
    },
    mounted() {
        console.log(11);
        this.getUserInfo();
    },
    methods: {
        async getUserInfo() {
            try {
                this.loading = true;
                let res = await infoApi.getStudentInfo();
                this.info = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        async setUserInfo() {
            try {
                this.confirmLoading = true;
                let res = await infoApi.setStudentInfo(this.info);
                this.$message.success(res.msg);
                this.readOnly = true;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.confirmLoading = false;
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