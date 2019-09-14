<template>
    <div class="my-container">
        <h2>个人信息</h2>
        <div>
            <span class="panel-title">基本信息</span>
            <el-form :model="info">
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
                        <el-form-item label-position="top" label="职务">
                            <el-input v-model="info.position" :readonly="readOnly"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label-position="top" label="地址">
                            <el-input
                                v-model="info.zone"
                                class="input-with-select"
                                placeholder="请输入具体地址"
                                :readonly="readOnly"
                            >
                                <el-select
                                    slot="prepend"
                                    v-model="info.province"
                                    placeholder="请选择省份"
                                    :disabled="readOnly"
                                >
                                    <el-option
                                        v-for="item in provinceOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                    ></el-option>
                                </el-select>
                                <el-select
                                    slot="prepend"
                                    v-model="info.city"
                                    placeholder="请选择城市"
                                    :disabled="readOnly"
                                >
                                    <el-option
                                        v-for="item in cityOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                    ></el-option>
                                </el-select>
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
        <div class="modifyButton">
            <el-button type="primary" round @click="dialogFormVisible=true">修改密码</el-button>
        </div>
        <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="password">
                <el-form-item label="原密码" :label-width="formLabelWidth">
                    <el-input v-model="password.oldPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码" :label-width="formLabelWidth">
                    <el-input v-model="password.newPassword" autocomplete="off" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认新密码" :label-width="formLabelWidth">
                    <el-input v-model="password.newPasswordAgain" autocomplete="off" type="password"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="setNewPassword">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { request } from "@/api/request.js";
import { getInfo, setInfo, setPassword } from "@/api/modules/info.js";
export default {
    data() {
        return {
            info: {
                idNum: null,
                phone: null,
                name: null,
                email: null,
                position: null,
                country: null,
                province: null,
                city: null,
                zone: null
            },
            readOnly: true,
            provinceOptions: [],
            cityOptions: [],
            dialogFormVisible: false,
            formLabelWidth: '120px',
            password: {
                oldPassword: null,
                newPassword: null,
                newPasswordAgain: null
            }
        };
    },
    watch:{
        dialogFormVisible(){
            if(this.dialogFormVisible == false){
                this.password = {
                    oldPassword: null,
                    newPassword: null,
                    newPasswordAgain: null
                }
            }
        }
    },
    methods: {
        async getUserInfo() {
            let res = await getInfo();
            this.info = res.data;
        },
        async setUserInfo() {
            let res = await setInfo(this.info);
            this.$message.success(res.msg);
        },
        async setNewPassword(){
            let res = await setPassword(this.password);
            this.$message.success(res.msg);
        }
    }
};
</script>
<style lang="scss" scoped>
.my-container {
    padding: 20px;
    ::v-deep .el-input-group__prepend {
        width: 250px;
        margin: 0 !important;
        .el-select {
            width: 50%;
            margin: 0 !important;
        }
    }
    .modifyButton {
        position: absolute;
        top: 35px;
        right: 5%;
    }
}
</style>