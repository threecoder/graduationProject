<template>
    <div>
        <el-form :model="form" inline>
            <el-form-item label="省份">
                <el-select v-model="form.province" clearable>
                    <el-option
                        v-for="(item,i) in provinceList"
                        :key="i"
                        :label="item"
                        :value="item"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="城市">
                <el-select v-model="form.city" clearable>
                    <el-option v-for="(item,i) in cityList" :key="i" :label="item" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="区域">
                <el-select v-model="form.area" clearable>
                    <el-option v-for="(item,i) in areaList" :key="i" :label="item" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="medium" @click="getMemberList">查询</el-button>
            </el-form-item>
        </el-form>
        <div v-if="tip" class="tip-container">
            <p>
                地址在
                <span>{{form.province}}{{form.city}}{{form.area}}</span>的会员共有：
                <span>{{form.total}}</span>个
            </p>
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
import cityApi from "../../../../assets/js/city";
import memberApi from "../../../../api/admin/member";
import event from "../../../../assets/js/eventBus";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                province: null,
                city: null,
                area: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "id", label: "会员ID", width: "150px" },
                    { prop: "name", label: "公司名称", width: "150px" },
                    { prop: "phone", label: "联系电话", width: "150px" },
                    { prop: "email", label: "邮箱", width: "150px" },
                    { prop: "enterData", label: "加入时间" },
                    { prop: "vip", label: "是否会员" },
                    { prop: "vipBegin", label: "会员开始时间", width: "150px" },
                    { prop: "vipEnd", label: "会员结束时间", width: "150px" },
                    { prop: "deadline", label: "会员是否快到期" },
                    { prop: "province", label: "省份" },
                    { prop: "city", label: "城市" },
                    { prop: "area", label: "区/县" },
                    { prop: "zone", label: "详细地址" }
                ],
                data: [],
                loading: false
            },
            provinceList: [],
            cityList: [],
            areaList: [],
            tip: true
        };
    },
    watch: {
        "form.province"(newVal) {
            this.tip = false;
            this.form.city = null;
            this.form.area = null;
            this.areaList = [];
            this.cityList = cityApi.getCityList(newVal);
        },
        "form.city"(newVal) {
            this.tip = false;
            this.form.area = null;
            this.areaList = cityApi.getAreaList(this.form.province, newVal);
        }
    },
    mounted() {
        this.provinceList = cityApi.getProvinceList();
        this.getMemberList();
        event.$on("diqu", this.getMemberList);
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getMemberList();
        },
        changeProvince(val) {
            this.form.province = val.value;
        },
        changeCity(val) {
            this.form.city = val.value;
        },
        changeArea(val) {
            this.form.area = val.value;
        },
        async getMemberList() {
            this.table.loading = true;
            try {
                let res = await memberApi.getMemberList(this.form);
                this.table.data = res.data.data;
                this.form.total = res.data.total;
                this.tip = true;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.tip-container {
    color: rgb(96, 98, 102);
    margin: 10px auto 20px;
    span {
        color: rgb(64, 158, 255);
    }
}
</style>