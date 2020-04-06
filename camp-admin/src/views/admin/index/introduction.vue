<template>
    <div>
        <h3>协会介绍内容</h3>
        <div class="divider"></div>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
        >
            <el-menu-item index="/简介">协会简介</el-menu-item>
            <el-menu-item index="/章程">协会章程</el-menu-item>
            <el-menu-item index="/架构">协会架构</el-menu-item>
        </el-menu>
        <div>
            <editor :content="initContent" :imgFlag="false" @contentChange="saveContent" />
        </div>
        <div class="mt30 tac">
            <el-button type="primary" @click="submit">修改</el-button>
        </div>
    </div>
</template>

<script>
import editor from "../../../components/editor.vue";
import indexApi from "../../../api/admin/index";
export default {
    components: {
        editor
    },
    data() {
        return {
            type: null,
            activeIndex: "/简介",
            content: null,
            initContent: null
        };
    },
    methods: {
        handleSelect(content) {
            if (content != "/" + this.type) {
                if (content.includes("简介")) {
                    this.getBrief();
                    this.type = "简介";
                    this.initContent = "简介";
                } else if (content.includes("章程")) {
                    this.type = "章程";
                    this.initContent = "章程";
                    this.getConsitiution();
                } else {
                    this.type = "架构";
                    this.initContent = "架构";
                    this.getFrameWork();
                }
            }
        },

        async getConsitiution() {
            try {
                let res = await indexApi.getConsitiution();
                this.initContent = res.data;
                this.content = null;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getBrief() {
            try {
                let res = await indexApi.getBrief();
                this.initContent = res.data;
                this.content = null;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getFrameWork() {
            try {
                let res = await indexApi.getFrameWork();
                this.initContent = res.data;
                this.content = null;
            } catch (error) {
                this.$message.error(error.message);
            }
        },

        saveContent(val) {
            if (val == "<p><br></p>") {
                this.content = null;
                this.backupContent = null;
            } else {
                this.content = val;
                this.backupContent = val;
            }
        },

        async submit() {
            if (this.content == null) {
                this.$message.error("内容不能为空");
                return false;
            }
            try {
                switch (this.type) {
                    case "章程":
                        await indexApi.modifyConstitution(this.content);
                        break;
                    case "简介":
                        await indexApi.modifyBrief(this.content);
                        break;
                    case "架构":
                        await indexApi.modifyFramework(this.content);
                        break;
                    default:
                        break;
                }
                this.$message.success("修改成功");
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>

<style lang="scss" scoped>
</style>