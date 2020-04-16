<template>
    <div>
        <h3>学员信息及挂靠管理</h3>
        <div class="divider"></div>
        <div class="new-container">
            <el-button @click="newFlag=true" type="primary">添加学员</el-button>
        </div>
        <div>
            <stu-list
                :type="0"
                @modifyInfo="modifyInfo"
                @delete="beforeMoRely"
                @resetPass="resetPass"
            ></stu-list>
        </div>

        <!-- 添加学员弹窗 -->
        <el-dialog title="添加学员" v-if="newFlag" :visible.sync="newFlag">
            <new-student
                :type="0"
                :uploadUrl="`/campback/admin/importStudentByFile`"
                @close="closeNew"
            />
        </el-dialog>

        <!-- 修改学员信息 -->
        <el-dialog title="修改信息" v-if="infoFlag" :visible.sync="infoFlag">
            <info :idType="0" :infor="temRow" />
        </el-dialog>

        <el-dialog title="修改学员挂靠公司" v-if="comFlag" :visible.sync="comFlag">
            <div class="tac">
                <el-select v-model="comPar.memberId">
                    <el-option :value="0" label="无挂靠"></el-option>
                    <el-option
                        v-for="(item,i) in companyList"
                        :value="item.id"
                        :label="item.name"
                        :key="i"
                    ></el-option>
                </el-select>
                <el-button
                    style="margin-left:10px;"
                    type="primary"
                    @click="modifyStuRely"
                    :loading="relyLoading"
                >确 定</el-button>
                <el-button type="primary" @click="comFlag=false">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import stuList from "./components/studentList.vue";
import newStudent from "./components/newStudent.vue";
import info from "../components/info.vue";
import adminStudentApi from "../../../api/admin/student";
import event from "../.././../assets/js/eventBus";
export default {
    components: {
        stuList,
        newStudent,
        info
    },
    data() {
        return {
            newFlag: false,
            infoFlag: false,
            comFlag: false,
            temRow: null,
            companyList: [],
            comPar: {
                memberId: null,
                idNum: null
            },
            relyLoading: false
        };
    },
    mounted() {
        this.getCompanyList();
    },
    methods: {
        //学员弹窗相关
        closeNew() {
            this.newFlag = false;
            event.$emit("refreshStudent");
        },
        async getCompanyList() {
            try {
                let res = await adminStudentApi.getMemSelectList();
                this.companyList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        beforeMoRely(row) {
            this.comPar.idNum = row.idNum;
            this.comFlag = true;
        },
        async modifyStuRely() {
            if (this.comPar.memberId === null) {
                this.$message.error("请选择要挂靠的公司");
                return false;
            }
            this.relyLoading = true;
            try {
                let res = await adminStudentApi.modifyRely(this.comPar);
                this.$message.success("修改挂靠成功");
                // this.getCompanyList
                event.$emit("refreshStudent");
            } catch (error) {
                this.$message.error(error.message);
            }
            this.relyLoading = false;
        },
        modifyInfo(row) {
            this.temRow = row;
            this.infoFlag = true;
        },
        resetPass(row) {
            this.$confirm("此操作将重置密码为123456，确定重置吗？", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(async () => {
                    try {
                        let res = await adminStudentApi.resetPassword(
                            row.idNum
                        );
                        this.$message.success("重置密码成功");
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }
};
</script>
<style lang="scss" scoped>
.new-container {
    position: absolute;
    right: 0;
    top: -10px;
}
</style>