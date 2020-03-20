<template>
    <div>
        <h3>学员培训记录以及证书</h3>
        <div class="divider"></div>
        <div class="student-list">
            <stu-list :type="1" @trainingHistory="trainingHistory" @certificate="certificate"></stu-list>
        </div>

        
        <!-- 学员持有证书弹窗 -->
        <el-dialog :title="dialog.title" :visible.sync="dialog.flag" v-if="dialog.flag">
            <hist :idNum="dialog.idNum" :type="dialog.type" />
        </el-dialog>
    </div>
</template>
<script>
import stuList from "./components/studentList.vue";
import hist from "./components/recordDialog.vue";
export default {
    components: {
        stuList,
        hist
    },
    data() {
        return {
            dialog: {
                flag: false,
                idNum: null,
                title: null,
                type: null
            }
        };
    },
    methods: {
        trainingHistory(row) {
            this.dialog.idNum = row.idNum;
            this.dialog.type = "training";
            this.dialog.flag = true;
            this.dialog.title = row.name + "的培训记录";
        },
        certificate(row) {
            this.dialog.idNum = row.idNum;
            this.dialog.type = "certificate";
            this.dialog.flag = true;
            this.dialog.title = row.name + "的证书";
        }
    }
};
</script>
<style lang="scss" scoped>
</style>