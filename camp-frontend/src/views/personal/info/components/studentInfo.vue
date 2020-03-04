<template>
    <div>
        <h2>个人信息</h2>
        <div>
            <span class="panel-title">基本信息</span>
            <el-form :model="info" :loading="loading">
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="身份证号码">
                            <el-input v-model="info.idNum" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="手机号码">
                            <el-input v-model="info.phone" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="名字">
                            <el-input v-model="info.name" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="邮箱">
                            <el-input v-model="info.email" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="所属公司">
                            <el-input v-model="info.company" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label-position="top" label="职务">
                            <el-input v-model="info.position" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label-position="top" label="地址">
                            <el-input
                                id="zone-input"
                                v-model="info.zone"
                                class="input-with-select"
                                placeholder="请输入具体地址"
                                :readonly="readOnly"
                            >
                                <v-distpicker
                                    v-on:province="changeProvince"
                                    v-on:city="changeCity"
                                    v-on:area="changeArea"
                                    :disabled="readOnly"
                                    :province="info.province"
                                    :city="info.city"
                                    :area="info.area"
                                    slot="prepend"
                                ></v-distpicker>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div class="tac mt30">
                <el-button v-if="readOnly" type="primary" round @click="readOnly=false">修改资料</el-button>
                <el-button v-if="!readOnly" type="primary" round @click="setUserInfo">确定</el-button>
                <el-button v-if="!readOnly" type="primary" round @click="readOnly=true">取消</el-button>
            </div>
        </div>
    </div>
</template>
<script>
import infoApi from '../../../../api/modules/info';
export default {
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
            this.info.province = val.value;
        },
        changeCity(val) {
            this.info.city = val.value;
        },
        changeArea(val) {
            this.info.area = val.value;
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