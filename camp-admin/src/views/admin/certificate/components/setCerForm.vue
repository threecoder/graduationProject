<template>
    <div>
        <!-- <el-form :model="form"></el-form> -->
        <div class="tac">
            <el-select placeholder="培训证书模板背景图" v-model="imgId">
                <el-option
                    v-for="(item,i) in cerList"
                    :key="i"
                    :label="item.name"
                    :value="item.imgId"
                ></el-option>
            </el-select>
            <el-button style="margin-left: 20px" type="primary" size="medium" @click="modifyCer">设置</el-button>
        </div>
    </div>
</template>
<script>
import cerApi from "../../../../api/admin/certificate";
export default {
    props: ["trainingId"],
    data() {
        return {
            form: {},
            imgId: null,
            cerList: [{ name: "111", imgId: 111 }, { name: "222", imgId: 222 }]
        };
    },
    mounted() {
        this.getTemplateImg();
        this.getModuleInfo();
    },
    methods: {
        async getModuleInfo() {
            try {
                let res = await cerApi.getCerInfo(this.trainingId);
                this.imgId = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getTemplateImg() {
            try {
                let res = await cerApi.getCerBackgroundImg();
                this.cerList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async modifyCer() {
            try {
                let data = {
                    trainingId: this.trainingId,
                    imgId: this.imgId
                };
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