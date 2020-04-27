<template>
    <div>
        <el-form :model="form"></el-form>
        <div class="tac">
            <el-button type="primary" size="medium" @click="modifyCer">设置</el-button>
        </div>
    </div>
</template>
<script>
import cerApi from "../../../../api/admin/certificate";
export default {
    props: ["trainingId"],
    data() {
        return {
            form: {}
        };
    },
    mounted() {
        this.getModuleInfo();
    },
    methods: {
        async getModuleInfo() {
            try {
                let res = await cerApi.getCerInfo(this.trainingId);
                this.form = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async modifyCer() {
            try {
                let data = {};
                let res = await cerApi.modifyCerInfo(data);
                this.$message.success("修改证书信息成功");
                this.cancel();
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        cancel() {
            this.$emit("cancel");
        }
    }
};
</script>