<template>
    <div>
        <div class="all-container" v-loading="loading">
            <el-row :gutter="40">
                <el-col :span="12">
                    <div class="title-container">
                        <label>标题</label>
                        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
                    </div>
                </el-col>
            </el-row>

            <div class="desc-container">
                <label>简要内容</label>
                <el-input
                    type="textarea"
                    v-model="form.desc"
                    :maxlength="50"
                    show-word-limit
                    placeholder="请输入简要内容"
                ></el-input>
            </div>
            <div class="edit-container">
                <label>内容</label>
                <editor :imgFlag="false" :content="initContent" @contentChange="saveContent" />
            </div>
            <div class="tac">
                <el-button @click="cancel">取消</el-button>
                <el-button type="primary" @click="submit">提交</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import editor from "../../../../components/editor.vue";
import indexApi from "../../../../api/admin/index";
export default {
    props: {
        noticeId: String
    },
    components: {
        editor
    },
    data() {
        return {
            form: {
                title: null,
                desc: null,
                content: null
            },
            loading: false,
            initContent: null
        };
    },
    mounted() {
        if (this.noticeId) {
            this.getNoticeDetail();
        }
    },
    methods: {
        //新增和编辑功能通用方法
        saveContent(val) {
            if (val == "<p><br></p>") {
                this.form.content = null;
            } else {
                this.form.content = val;
            }
        },
        cancel() {
            this.$emit("cancel");
        },
        async submit() {
            let form = this.form;
            console.log(this.form);
            if (!form.title || !form.desc || !form.content) {
                this.$message.error("必须填写所有内容");
                return false;
            }
            this.loading = true;
            try {
                if (this.noticeId) {
                    form.noticeId = this.noticeId;
                    await indexApi.modifyNotice(form);
                } else {
                    await indexApi.addNotice(form);
                }
                this.$message.success("编辑公告成功");
                this.$emit("refresh");
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        async getNoticeDetail() {
            this.loading = true;
            try {
                let res = await indexApi.getNoticeDetail(this.noticeId);
                this.form = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>

<style lang="scss" scoped>
.title-container {
    label {
        display: block;
        margin-left: 20px;
        margin-bottom: 10px;
    }
}
.desc-container,
.edit-container {
    margin-top: 30px;
    label {
        display: block;
        margin-left: 20px;
        margin-bottom: 10px;
    }
}
.tac {
    margin: 30px auto;
}
</style>