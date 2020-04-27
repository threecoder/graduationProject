<template>
    <div class="my-container">
        <div>
            <el-form v-if="idType==0" :model="info" :loading="loading">
                <el-row :gutter="40">
                    <el-col :span="8">
                        <el-form-item label-position="top" label="身份证号码">
                            <el-input v-model="info.idNum" disabled></el-input>
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
                            <el-input v-model="info.company" disabled></el-input>
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
            <el-form v-else :model="info" :loading="loading">
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
                            <el-select v-model="info.vip" :disabled="readOnly">
                                <el-option :value="1" label="是"></el-option>
                                <el-option :value="0" label="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label-position="top" label="会员开始时间">
                            <el-date-picker
                                type="datetime"
                                v-model="info.vipBegin"
                                :disabled="readOnly"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label-position="top" label="会员到期时间">
                            <el-date-picker
                                type="datetime"
                                v-model="info.vipEnd"
                                :disabled="readOnly"
                            ></el-date-picker>
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
                                :disabled="readOnly"
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
                <el-button
                    v-if="!readOnly"
                    type="primary"
                    round
                    @click="modifyInfo"
                    :loading="confirmLoading"
                >确定</el-button>
                <el-button v-if="!readOnly" type="primary" round @click="readOnly=true">取消</el-button>
            </div>
        </div>
        <!-- <div class="modifyButton">
            <el-button type="primary" round @click="dialogFormVisible=true">修改密码</el-button>
            <el-button type="primary" round >续费会员</el-button>
        </div>-->

        <!-- 修改密码弹出框
        <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="password">
                <el-form-item label="原密码" :label-width="formLabelWidth">
                    <el-input v-model="password.oldPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码" :label-width="formLabelWidth">
                    <el-input v-model="password.newPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认新密码" :label-width="formLabelWidth">
                    <el-input
                        v-model="password.newPasswordAgain"
                        autocomplete="off"
                        type="password"
                    ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="setNewPassword">确 定</el-button>
            </div>
        </el-dialog>-->
    </div>
</template>
<script>
import adminStudentApi from "../../../api/admin/student";
import adminMemberApi from "../../../api/admin/member.js";
import { getLocalStorage, formatDateAndTime } from "../../../assets/js/util.js";
export default {
    props: {
        idType: {
            type: Number,
            default: 0
        },
        infor: {
            type: Object,
            default() {
                return {};
            }
        }
    },
    data() {
        return {
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
                zone: null,
                memberFlag: null,
                memberBeginDate: null,
                memberEndDate: null
            },
            readOnly: true,
            dialogFormVisible: false,
            formLabelWidth: "120px",
            loading: false,
            confirmLoading: false
        };
    },
    mounted() {
        this.info = this.infor;
    },
    methods: {
        async modifyInfo() {
            this.confirmLoading = true;
            try {
                this.confirmLoading = true;
                let res = null;
                if (this.idType == 0) {
                    res = await adminStudentApi.modifyInfo(this.info);
                    this.$message.success("修改学员资料成功");
                } else {
                    //处理参数
                    let par = { ...this.info };
                    if (typeof par.vipBegin !== "string") {
                        par.vipBegin = formatDateAndTime(par.vipBegin);
                    }
                    if (typeof par.vipEnd !== "string") {
                        par.vipEnd = formatDateAndTime(par.vipEnd);
                    }
                    res = await adminMemberApi.modifyInfo(par);
                    this.$message.success("修改会员资料成功");
                }
                this.readOnly = true;
                this.confirmLoading = false;
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
.my-container {
    // padding: 20px;
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
        min-width: 300px !important;
    }
}
</style>