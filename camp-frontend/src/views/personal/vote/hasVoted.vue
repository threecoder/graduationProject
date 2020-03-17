<template>
    <div>
        <div class="form-item">
            <el-form :model="form" inline>
                <el-form-item label="名称">
                    <el-input v-model="form.name" placeholder="投票名字"></el-input>
                </el-form-item>
                <el-form-item label="投票结束">
                    <el-select v-model="form.isFinish" placeholder="是否结束">
                        <el-option label="是" :value="true"></el-option>
                        <el-option label="否" :value="false"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getVoteList">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div class="table-container">
            <m-table :data="table.data" :tableConfig="table.config">
                <el-table-column slot-scope="{params}" v-bind="params" align="center">
                    <div slot-scope="{row}">
                        <el-button type="primary"></el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import mTable from "../../../components/mTable.vue";
export default {
    components: {
        page,
        mTable
    },
    data() {
        return {
            form: {
                name: null,
                isFinish: null,
                total: 100,
                currentPage: 1,
                pageSize: 10
            }
        };
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>