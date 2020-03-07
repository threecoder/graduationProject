<template>
    <div>
        <h3>培训名称</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="form">
                <el-form-item label="学员ID">
                    <el-input v-model="form.id" clearable></el-input>
                </el-form-item>
                <el-form-item label="学员名字">
                    <el-input v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属公司">
                    <el-select v-model="form.isEnrolled" clearable multiple>
                        <el-option
                            v-for="(item,i) in memberList"
                            :key="i"
                            :label="item.name"
                            :value="item.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="getSituation">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="getSituation">提醒学员报名</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="getSituation">提醒会员报名</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data">
                <el-table-column slot="oper" align="center" slot-scope="{params}" v-bind="params">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="prompt(row)">提示支付</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :pageSize="form.pageSize"
                :currentPage="form.currentPage"
                :total="form.total"
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
            trainingId: this.$route.query.trainingId,
            loading: true,
            form: {
                id: null,
                name: null,
                isEnrolled: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "idNum", label: "证件号码" },
                    { prop: "name", label: "名字" },
                    { prop: "phone", label: "联系电话" },
                    { prop: "company", label: "所属公司" },
                    { prop: "status", label: "是否支付" },
                    // { slot: "oper", label: "操作" }
                ],
                data: [
                    {
                        idNum: 1,
                        name: "模拟名字",
                        phone: "151551515",
                        status: "未支付"
                    }
                ]
            },
            memberList: [
                {
                    name: "bb公司",
                    id: 1,
                    phone: "2121"
                }
            ]
        };
    },
    watch: {
        "$route.query.trainingId"(newVal) {
            this.trainingId = newVal;
            this.getSituation();
        }
    },
    methods: {
        async getSituation() {
            console.log(this.trainingId);
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getSituation();
        },
        prompt(row) {}
    }
};
</script>
<style lang="scss" scoped>
</style>