<template>
    <div>
        <h3>发布考试</h3>
        <div class="divider"></div>
        <div class="switcher">
            <el-button type="primary" @click="newFlag=true">新建考试</el-button>
        </div>
        <div>
            <span class="panel-title">已有考试</span>
            <div class="all-exam-container" :loading="loading">
                <div class="single-exam-container" v-for="(item,i) in examList" :key="i">
                    <single-exam
                        :examName="item.examName"
                        :date="item.date"
                        :startTime="item.startTime"
                        :endTime="item.endTime"
                        :belong="item.belong"
                        :min="item.min"
                        :grade="item.grade"
                        :examId="item.examId"
                        :idtype="1"
                        @pick="pickFlag=true"
                    />
                </div>
            </div>
        </div>

        <el-dialog :visible.sync="newFlag">
            <exam-publish  @close="newFlag=false"/>
        </el-dialog>
        <el-dialog :visible.sync="pickFlag">
            <pick-question  @close="pickFlag=false"/>
        </el-dialog>
    </div>
</template>
<script>
import singleExam from "@/components/singleExam.vue";
import page from "@/components/page.vue";
import examPublish from './components/examPublish';
import pickQuestion from './components/pickQuestion';
export default {
    components: {
        singleExam,
        page,
        examPublish,
        pickQuestion
    },
    data() {
        return {
            examList: [
                {
                    examId:"1",
                    examName:"考试名称",
                    date:"2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    grade:null
                }
            ],
            newFlag: false,
            pickFlag: false,
            loading: false
        };
    },
    
    mounted() {
        
    },
    methods: {
        
    }
};
</script>
<style lang="scss" scoped>
.all-exam-container {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: flex-start;
    flex-wrap: wrap;
    .single-exam-container {
        margin: 10px;
        width: 30%;
    }
}
.switcher {
    overflow: hidden;
    float: right;
    .active {
        background-color: rgb(58, 180, 255);
        color: #fff;
    }
}
</style>