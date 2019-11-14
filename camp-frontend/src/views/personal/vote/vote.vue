<template>
    <div>
        <h2 v-if="type==0">全部投票</h2>
        <h2 v-else>我参与的投票</h2>
        <div class="table-container">
            <m-table
                :data="voteTable.tableData"
                :tableConfig="voteTable.tableConfig"
                :loading="voteTable.loading"
                :tableAttr="voteTable.tableAttr"    
            >
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button type="primary" v-if="row.result==0" @click="voteInfo(row)">参与投票</el-button>
                        <el-button type="primary" v-else @click="dialogResult=true">查看结果</el-button>
                        <el-dialog
                            title="投票详情"
                            :visible.sync="dialogVisible"
                            :modal-append-to-body="false">
                            <div class="vote-title">
                                <h2>{{dialogInfo.name}}</h2>
                            </div>
                            <div>
                                <div class="vote-choose">
                                    <el-radio-group
                                        v-if="dialogInfo.vtype==0"
                                        v-model="radio" 
                                        class="radio-group">
                                        <el-radio 
                                            v-for="(item,index) in dialogInfo.votelist" :key="index" 
                                            :label="item.id" 
                                            border
                                            class="radio">
                                            <div class="radio-text">{{item.id}}号   {{item.name}}   {{item.brief}}</div>
                                                <!-- <div v-if="item.url" class="">
                                                    <img :src="item.url" alt />
                                                </div>
                                                <div>
                                                    <div class="">
                                                        <span class="">{{item.id}}号</span>
                                                        <span class="">{{item.name}}</span>
                                                    </div>
                                                    <div>
                                                        <p>{{item.brief}}</p>
                                                    </div>
                                                </div>--> 
                                        </el-radio>
                                    </el-radio-group>
                                    <el-checkbox-group 
                                        v-if="dialogInfo.vtype==1"
                                        v-model="voteArray">
                                        <el-checkbox 
                                        v-for="(item,index) in dialogInfo.votelist" :key="index" 
                                        :label="item.id"
                                        border
                                        class="checkbox">
                                            <!-- <div v-if="item.url" class="">
                                                <img :src="item.url" alt />
                                            </div>
                                            <div>
                                                <div class="">
                                                    <span class="">{{item.id}}号</span>
                                                    <span class="">{{item.name}}</span>
                                                </div>
                                                <div>
                                                    <p>{{item.brief}}</p>
                                                </div>
                                            </div> -->
                                            {{item.id}}号   {{item.name}}   {{item.brief}}
                                        </el-checkbox>
                                    </el-checkbox-group>
                                </div>
                            </div>
                            <span slot="footer" class="dialog-footer">
                                <el-button type="primary" @click="vote(vtype==0 ? radio : voteArry) , dialogVisible = false">提交</el-button>
                                <el-button @click="dialogVisible = false">取消</el-button>
                            </span>
                        </el-dialog>
                        <el-dialog
                            title="投票详情"
                            :visible.sync="dialogResult"
                            :modal-append-to-body="false">
                                <div v-for="(item,index) in row.votelist" :key="index">
                                    <span>{{item.id}}号</span>
                                    &nbsp;&nbsp;&nbsp;
                                    <span>{{item.vote}}票</span>
                                    <el-progress 
                                        :text-inside="true" 
                                        :stroke-width="20" 
                                        :percentage="item.progress">
                                    </el-progress>
                                </div>
                            <span slot="footer" class="dialog-footer">
                                <el-button type="primary" @click="dialogResult = false">确定</el-button>
                                <el-button @click="dialogResult = false">取消</el-button>
                            </span>
                        </el-dialog>
                    </div>
                </el-table-column>
            </m-table>
        </div>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import { isArray } from 'util';
export default {
    components:{
        mTable
    },
    data(){
        return{
            type: this.$route.params.id,
            voteTable:{
                tableConfig:[
                    {prop:"id", label:"投票序号", whdth: 100 },
                    {prop:"name", label:"投票名称" },
                    {prop:"date", label:"截止时间" },
                    {prop:"vtype",label:"投票类型" },
                    {
                        prop:"opera",
                        label:"操作",
                        fixed:"right",
                        whdth: 150,
                        slot:"oper"
                    }
                ],
                tableData:[
                    {
                        id:1,
                        name:"投票测试1",
                        date:"2016-10-10 16:00:00",
                        vtype:0,
                        result:0,
                        votelist:[
                            {
                                id:"1",
                                name:"选项一",
                                brief:"我是选项一，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"2",
                                name:"选项二",
                                brief:"我是选项二，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"3",
                                name:"选项三",
                                brief:"我是选项三，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"4",
                                name:"选项四",
                                brief:"我是选项四，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                        ]
                    },
                    {
                        id:2,
                        name:"投票测试2",
                        date:"2016-10-10 16:00:00",
                        vtype:1,
                        result:0,
                        votelist:[
                            {
                                id:"1",
                                name:"选项一",
                                brief:"我是选项一，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"2",
                                name:"选项二",
                                brief:"我是选项二，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"3",
                                name:"选项三",
                                brief:"我是选项三，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"4",
                                name:"选项四",
                                brief:"我是选项四，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                        ]
                    },
                    {
                        id:3,
                        name:"投票测试3",
                        date:"2016-10-10 16:00:00",
                        vtype:0,
                        result:1,
                        votelist:[
                            {
                                id:"1",
                                name:"选项一",
                                brief:"我是选项一，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"2",
                                name:"选项二",
                                brief:"我是选项二，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"3",
                                name:"选项三",
                                brief:"我是选项三，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                            {
                                id:"4",
                                name:"选项四",
                                brief:"我是选项四，请大家选我吧",
                                url:null,
                                vote:15,
                                progress:25
                            },
                        ]
                    }
                ],
                tableAttr: {
                    stripe: true
                },
                loading: false
            },
            dialogInfo:{
                id:null,
                name:null,
                date:null,
                vtype:null,
                result:null,
                votelist:[]
            },
            dialogVisible:false,
            dialogResult:false,
            radio:-1,
            voteArray:[],
            votelist:[]
        }
    },
    methods:{
        voteInfo(row){
            this.dialogVisible=true;
            this.dialogInfo=row;
        },
        async vote(res){
            try{
                if(Array.isArray(res)){
                    for(let i=0;i<res.length;i++){
                        this.row.votelist[res[i-1]].vote++;
                    }
                    this.row.result=1;
                }else{
                    this.row.votelist[res-1].vote++;
                    this.row.result=1;
                }
                this.$message.success("投票成功");
            }catch(error){}
        }
    }
    
}
</script>
<style lang="scss">
.table-container {
    margin-top: 50px;
    ::v-deep .el-table__header-wrapper {
        thead {
            .cell {
                font-size: 16px;
                font-weight: 100;
                color: black;
            }
        }
    }
}
.vote-title{
    width: auto;
    text-align: center;
    font-size: 18px;
    color: black;
}
.el-radio{
    width: 720px;
    margin-top: 20px;        
    margin-left: auto;
    margin-right: auto;
    .el-radio__label{
        display: inline-block;
        width: 700px;
    }
}
.checkbox{
    width: 720px;
    margin-top: 20px;        
    margin-left: 0;
    margin-right: 0;
    .el-checkbox__label{
        display: inline-block;
        width: 700px;
    }
}


</style>