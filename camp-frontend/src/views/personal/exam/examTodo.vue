<template>
    <div>
        <h2>需完成的考试</h2>
        <div>
            <span v-if="flag" class="panel-title">还未作答的试卷</span>
            <span v-else class="panel-title">已经作答过的试卷</span>
            <div class="all-exam-container">
                <div class="single-exam-container" v-for="(item,i) in examList" :key="i">
                    <single-exam :examName="item.examName" :date="item.date" :startTime="item.startTime" :endTime="item.endTime" :belong="item.belong" :min="item.min" :grade="item.grade"/>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { getTodoExam,getHalfExam } from '@/api/modules/exam.js'
import singleExam from './components/singleExam.vue';
import page from '@/components/page.vue'
export default {
    components: {
        singleExam,page
    },
    data(){
        return {
            examList: [
                {
                    examName:"考试名称",
                    date:"2016-10-10",
                    startTime: "14:00:00",
                    endTime: "16:00:00",
                    min: "120分钟",
                    belong: "HTML入门",
                    grade:null
                }
            ],
            flag: true,
        }
    },
    methods: {
        async getExamList(){
            let res = null;
            if(this.flag){
                res = await getTodoExam();
            }else{
                res = await getHalfExam();
            }
            if(res){
                this.examList = res.data;
            }
        }
    }
}
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
</style>