<template>
    <div>
        <div class="form-container">
            <el-form :model="form"></el-form>
        </div>
        <div class="tac">
            <el-button size="medium" @click="cancel">取消</el-button>
            <el-button size="medium" type="primary" @click="modify">修改</el-button>
        </div>
    </div>
</template>
<script>
import cerApi from "../../../../api/modules/certificate";
export default {
    props: ["idNum", "cerId"],
    data() {
        return {
            form: {}
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.getCerInfo();
    },
    methods: {
        async getCerInfo() {
            try {
                let par = {
                    cerId: this.cerId
                };
                if (this.idNum) {
                    par.idNum = this.idNum;
                }
                let res = await cerApi.getCerInfo(this.idType, par);
                this.form = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        cancel() {
            this.$emit("cacel");
        },
        async modify() {
            try {
                let res = await cerApi.modifyCer(this.idType, this.form);
                this.$alert(
                    "提交证书修改成功，请等待管理员审核后缴费",
                    "提示",
                    {
                        cancelButtonText: "取消",
                        confirmButtonText: "确定"
                    }
                ).then(() => {
                    this.cancel();
                });
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
</style>