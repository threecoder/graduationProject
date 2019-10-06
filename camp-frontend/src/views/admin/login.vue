<template>
    <div class="container">
        <h3>证书管理平台管理系统</h3>
        <el-form :model="userForm" :rules="rules" ref="loginForm">
            <el-form-item prop="username">
                <el-input
                    suffix="el-icon-user"
                    placeholder="请输入账号"
                    v-model.trim="userForm.username"
                >
                    <template slot="prepend">
                        <i class="userIcon"></i>
                    </template>
                </el-input>
            </el-form-item>

            <el-form-item prop="password">
                <el-input
                    type="password"
                    suffix="el-icon-user"
                    placeholder="请输入密码"
                    v-model.trim="userForm.password"
                >
                    <template slot="prepend">
                        <i class="pasIcon"></i>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item>
                <span class="fr cursor" @click="forgetFlag=true">忘记密码</span>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">{{buttonText}}</el-button>
            </el-form-item>
        </el-form>

        <el-dialog v-if="forgetFlag">
            <el-form></el-form>
        </el-dialog>
    </div>
</template>
<script>
import { Encrypt, request } from "@/api/request.js";
import { setLocalStorage  } from "@/assets/js/util.js";
export default {
    data() {
        let valid = (rule, value, callback) => {
            let reg = /^([a-zA-Z0-9]|\_)*$/g;
            if (!reg.test(value)) {
                callback(new Error("账号只能由数字、字母和下划线组成"));
                return false;
            }
            callback();
        };
        return {
            redirect: this.$route.query.redirect,
            userForm: {
                username: null,
                password: null,
                registerFlag: false
            },
            rules: {
                username: [
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: "change"
                    },
                    { validator: valid, trigger: "change" }
                ],
                password: [
                    {
                        required: true,
                        message: "请输入6-18位密码",
                        trigger: "change"
                    },
                    {
                        min: 6,
                        max: 18,
                        message: "密码需要6-18位",
                        trigger: "change"
                    }
                ]
            },
            forgetFlag: false
        };
    },
    methods: {
        login() {
            if (
                this.userForm.username == null ||
                this.userForm.password == null
            ) {
                this.$message.error("账号或者密码不能为空");
                return false;
            }
            let params = {
                username: this.userForm.username,
                password: Encrypt(this.userForm.password)
            };

            request("/campback/student/login", params, "post").then(res => {
                this.$message.success(res.msg)
                this.$router.push({
                    path: this.redirect ? this.redirect : "/personal"
                });
                // setLocalStorage("user",res.data);
                setLocalStorage("user",{name:"张三",idType:1});
            }).catch(error=>{});
        }
    },
    computed: {
        buttonText() {
            return this.userForm.registerFlag ? "注册" : "登陆";
        }
    }
};
</script>
<style lang="scss" scoped>
.container {
    text-align: center;
    width: 68%;
    margin: 0 auto;
    overflow: hidden;
    h3 {
        margin: 150px 0 100px;
        color: orange;
    }
    .el-form {
        margin: 0 auto;
        width: 375px;
        ::v-deep .el-input-group__prepend {
            padding: 0 10px;
        }
        .userIcon {
            background: url("../login/images/user.png") no-repeat center center;
            display: inline-block;
            width: 40px;
            height: 30px;
        }
        .pasIcon {
            background: url("../login/images/psw.png") no-repeat center center;
            display: inline-block;
            width: 40px;
            height: 30px;
        }
        span {
            color: gray;
        }
        .el-button {
            width: 100%;
            margin-top: 20px;
            background-color: orange;
            border: 0;
        }
    }
}
</style>
