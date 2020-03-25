<template>
    <div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="名字">
                    <el-input v-model="form.name" placeholder clearable></el-input>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading"></m-table>
        </div>
        <div class="page-container">
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPae: 1,
                total: 100,
                name: null
            },
            table: {
                config: [],
                data: [],
                loading: false
            }
        };
    },
    methods: {
        curChange(newVal) {
            this.form.currentPae = newVal;
            this.getList();
        },
        async getList() {
            try {
                let res = await api.getList(par);
                this.table.data = res.data.data;
                this.form.total = res.data.tota;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
</style>