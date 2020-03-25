<template>
    <div class="form-container">
        <el-form :model="form" :rules="rules" ref="adminForm">
            <el-form-item label="新的账号" prop="account">
                <el-input v-model="form.account" placeholder="请输入新的管理员账号"></el-input>
            </el-form-item>
            <el-form-item label="管理员名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入管理员名称"></el-input>
            </el-form-item>
        </el-form>
        <div class="tac">
            <el-button size="medium" @click="cancel">取消</el-button>
            <el-button size="medium" @click="add" type="primary">添加</el-button>
        </div>
    </div>
</template>
<script>
import systemApi from "../../../../api/admin/system";
export default {
    data() {
        return {
            form: {
                name: null,
                account: null
            },
            rules: {
                account: [
                    {
                        required: true,
                        trigger: "blur",
                        message: "管理员账号必须填"
                    }
                ],
                name: [
                    {
                        required: true,
                        trigger: "blur",
                        message: "管理员名称必须填"
                    }
                ]
            }
        };
    },
    methods: {
        add() {
            this.$refs.adminForm.validate(async valid => {
                if (valid) {
                    try {
                        let res = await systemApi.addNewAdmin(this.form);
                        this.$message.success("添加成功");
                        this.cancel();
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                }
            });
        },
        cancel() {
            this.$emit("cancel");
        }
    }
};
</script>
<style lang="scss" scoped>
.form-container {
    width: 80%;
    margin: 0 auto;
}
</style>