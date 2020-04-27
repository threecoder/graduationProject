<template>
    <div>
        <el-form :model="modifyPass" :rules="rules" ref="modifyPassForm">
            <el-form-item label="旧密码" prop="oldPassword">
                <el-input type="password" v-model="modifyPass.oldPassword"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
                <el-input type="password" v-model="modifyPass.newPassword"></el-input>
            </el-form-item>
            <el-form-item label="再次确认" prop="newPassword2">
                <el-input type="password" v-model="modifyPass.newPassword2"></el-input>
            </el-form-item>
            <el-form-item>
                <div class="tac">
                    <el-button @click="hideDiaglog">取消</el-button>
                    <el-button type="primary" @click="modifyPassword">确定</el-button>
                </div>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import accountApi from "../../../api/admin/account";
export default {
    data() {
        let valid = (rule, value, callback) => {
            if (value != this.modifyPass.newPassword) {
                callback(new Error("两次输入的新密码必须一致"));
            }
            callback();
        };
        let arr = [
            {
                required: true,
                message: "请输入6-18位密码",
                trigger: "blur"
            },
            {
                min: 6,
                max: 18,
                message: "密码需要6-18位",
                trigger: "change"
            }
        ];
        return {
            modifyPass: {
                oldPassword: null,
                newPassword: null,
                newPassword2: null
            },
            rules: {
                oldPassword: arr,
                newPassword: arr,
                newPassword2: [...arr, { validator: valid, trigger: "blur" }]
            }
        };
    },
    methods: {
        modifyPassword() {
            this.$refs.modifyPassForm.validate(async valid => {
                if (valid) {
                    try {
                        let res = await accountApi.resetPassword(
                            this.modifyPass
                        );
                        this.$message.success("修改密码成功");
                        this.hideDiaglog();
                    } catch (e) {
                        this.$message.error(e.message);
                    }
                }
            });
        },
        hideDiaglog() {
            this.$emit("hide");
        }
    }
};
</script>
<style lang="scss" scoped>
</style>