<template>
    <div class="my-container">
        <member-info v-if="idType==1" />
        <student-info v-else />

        <!-- 修改密码弹出框 -->
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
        </el-dialog>
    </div>
</template>
<script>
import memberInfo from "./components/memberInfo.vue";
import studentInfo from "./components/studentInfo.vue";
import { getLocalStorage } from "@/assets/js/util";
export default {
    components: {
        studentInfo,
        memberInfo
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
            password: {
                oldPassword: null,
                newPassword: null,
                newPasswordAgain: null
            },
            loading: false,
            confirmLoading: false
        };
    },
    watch: {
        dialogFormVisible() {
            if (this.dialogFormVisible == false) {
                this.password = {
                    oldPassword: null,
                    newPassword: null,
                    newPasswordAgain: null
                };
            }
        }
    },
    beforeMount() {
        this.initType();
    },
    methods: {
        initType() {
            this.idType = getLocalStorage("user").type;
        },
        async setNewPassword() {
            try {
                this.confirmLoading = true;
                let setPassword = infoApi.setStudentPassword;
                if (this.idType == 1) {
                    setPassword = infoApi.setMemberPassword;
                }
                let res = await setPassword(this.password);
                this.$message.success(res.msg);
                this.dialogFormVisible = false;
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
    padding: 20px;
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
}
</style>