(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2b37470c"],{"0e11":function(e,t,n){},"14eb":function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-col",{staticClass:"mt30",attrs:{span:24}},[n("el-pagination",{attrs:{background:"",layout:"total, prev, pager, next","current-page":e.currentPage,"page-size":e.pageSize,total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)},r=[],i={data:function(){return{}},props:["total","pageSize","currentPage"],methods:{handleSizeChange:function(e){},handleCurrentChange:function(e){this.$emit("curChange",e,this.pageSize)}}},o=i,l=(n("c8f4"),n("2877")),s=Object(l["a"])(o,a,r,!1,null,"8890b7c4",null);t["a"]=s.exports},"386d":function(e,t,n){"use strict";var a=n("cb7c"),r=n("83a1"),i=n("5f1b");n("214f")("search",1,(function(e,t,n,o){return[function(n){var a=e(this),r=void 0==n?void 0:n[t];return void 0!==r?r.call(n,a):new RegExp(n)[t](String(a))},function(e){var t=o(n,e,this);if(t.done)return t.value;var l=a(e),s=String(this),c=l.lastIndex;r(c,0)||(l.lastIndex=0);var u=i(l,s);return r(l.lastIndex,c)||(l.lastIndex=c),null===u?-1:u.index}]}))},"3b30":function(e,t,n){"use strict";var a=n("0e11"),r=n.n(a);r.a},"4b60":function(e,t,n){},"5b99":function(e,t,n){"use strict";var a=n("ba1b"),r=n.n(a);r.a},"7e91":function(e,t,n){"use strict";var a=n("0c6d"),r=n("b441"),i=function(e){return Object(a["c"])(r["d"]+"/getStudentListByCondition","get",e)},o=function(e){return Object(a["c"])(r["d"]+"/deleteOneStudent","post",e)},l=function(e){return Object(a["c"])(r["d"]+"/importSingleStudent","post",e)},s=function(){return Object(a["c"])(r["d"]+"/getTemplate","get",{},"blob")},c=function(e){return Object(a["c"])(r["d"]+"/getStudentTrainingHistory","get",e)};t["a"]={getStudentList:i,deleteOneStudent:o,addOneStudent:l,getTemplate:s,getStudentTrainingHistory:c}},"83a1":function(e,t){e.exports=Object.is||function(e,t){return e===t?0!==e||1/e===1/t:e!=e&&t!=t}},a585:function(e,t,n){"use strict";var a=n("4b60"),r=n.n(a);r.a},add5:function(e,t,n){"use strict";var a=n("e01e"),r=n.n(a);r.a},b6b0:function(e,t,n){},ba1b:function(e,t,n){},c8f4:function(e,t,n){"use strict";var a=n("b6b0"),r=n.n(a);r.a},d2a2:function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-table",e._g(e._b({directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"tableRef",staticClass:"m-table",attrs:{size:"small",data:e.data,"max-height":e.data?600:200}},"el-table",e.tableAttr,!1),e.tableEvent),[e._l(e.tableConfig,(function(t,a){return[t.slot?e._t(t.slot,null,{params:t,prop:t.prop}):n("el-table-column",e._b({key:a,attrs:{align:"center"}},"el-table-column",t,!1))]}))],2)},r=[],i={name:"m-Table",props:{tableConfig:{type:Array},data:{type:Array},loading:{type:Boolean},tableAttr:{type:Object},tableEvent:{type:Object}}},o=i,l=(n("add5"),n("2877")),s=Object(l["a"])(o,a,r,!1,null,"bcfa35a4",null);t["a"]=s.exports},e01e:function(e,t,n){},eaf2:function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("el-row",[n("el-col",{attrs:{span:22}},[n("h3",[e._v("管理挂靠关系")])]),n("el-col",{attrs:{span:2}},[n("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogFlag=!0}}},[e._v("添加学员")])],1)],1),n("div",{staticClass:"divider"}),n("div",{staticClass:"form-container"},[n("el-form",{attrs:{model:e.form,inline:""}},[n("el-form-item",{attrs:{label:"手机号码","label-position":"left"}},[n("el-input",{attrs:{placeholder:"输入手机号码"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),n("el-form-item",{attrs:{label:"身份证号码","label-position":"top"}},[n("el-input",{attrs:{placeholder:"输入身份证号码"},model:{value:e.form.idNum,callback:function(t){e.$set(e.form,"idNum",t)},expression:"form.idNum"}})],1),n("el-form-item",{attrs:{label:"姓名","label-position":"top"}},[n("el-input",{attrs:{placeholder:"输入姓名"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),n("el-button",{attrs:{type:"primary"},on:{click:e.search}},[e._v("搜索")])],1)],1),n("div",{staticClass:"table-container"},[n("m-table",{attrs:{data:e.tableData,tableConfig:e.tableConfig,loading:e.loading},scopedSlots:e._u([{key:"oper",fn:function(t){var a=t.params;return n("el-table-column",e._b({staticClass:"myoper",attrs:{align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.row;return n("div",{},[n("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){return e.deleteRelation(a)}}},[e._v("解除挂靠")]),n("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){return e.trainingHistory(a)}}},[e._v("培训记录")])],1)}}])},"el-table-column",a,!1))}}])})],1),n("div",[n("page",{attrs:{currentPage:e.form.currentPage,total:e.form.total,pageSize:e.form.pageSize},on:{curChange:e.curChange}})],1),n("div",{staticClass:"modifyButton"}),e.dialogFlag?n("el-dialog",{attrs:{title:"添加学员",visible:e.dialogFlag,width:"40%"},on:{"update:visible":function(t){e.dialogFlag=t}}},[n("new-student",{on:{hideDialog:function(t){e.dialogFlag=!1}}})],1):e._e(),e.history.flag?n("el-dialog",{attrs:{title:"添加学员",visible:e.history.flag},on:{"update:visible":function(t){return e.$set(e.history,"flag",t)}}},[n("training-history",{attrs:{idNum:e.history.idNum}})],1):e._e()],1)},r=[],i=(n("96cf"),n("3b8d")),o=(n("386d"),n("d2a2")),l=n("14eb"),s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("h6",{staticStyle:{"font-size":"16px"}},[e._v("方式一：输入信息添加一个学员")]),n("el-form",{attrs:{model:e.newStudent}},[n("el-form-item",{attrs:{label:"身份证号码","label-width":"50"}},[n("el-input",{attrs:{autocomplete:"off",type:"text"},model:{value:e.newStudent.idNum,callback:function(t){e.$set(e.newStudent,"idNum",t)},expression:"newStudent.idNum"}})],1),n("el-form-item",{attrs:{label:"手机号码","label-width":"50"}},[n("el-input",{attrs:{autocomplete:"off",type:"text"},model:{value:e.newStudent.phone,callback:function(t){e.$set(e.newStudent,"phone",t)},expression:"newStudent.phone"}})],1),n("el-form-item",{attrs:{label:"姓名","label-width":"50"}},[n("el-input",{attrs:{autocomplete:"off",type:"text"},model:{value:e.newStudent.name,callback:function(t){e.$set(e.newStudent,"name",t)},expression:"newStudent.name"}})],1)],1),n("div",{staticClass:"tac"},[n("el-button",{on:{click:e.hideDialog}},[e._v("取 消")]),n("el-button",{attrs:{type:"primary"},on:{click:e.addSingleStudent}},[e._v("确 定")])],1),n("div",{staticClass:"divider"}),n("h6",{staticStyle:{"font-size":"16px"}},[e._v("\n        方式二：\n        "),n("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.getListTemplate}},[e._v("导出模板")]),e._v("填写完信息后上传文件批量添加\n    ")],1),n("div",{staticClass:"upload-container tac"},[n("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{action:"/campback/member/importStudentByFile","on-remove":e.handleRemove,"on-exceed":e.handleExceed,"on-success":e.handleSuccess,"on-error":e.handleError,"file-list":e.fileList,"auto-upload":!1,limit:1}},[n("el-button",{attrs:{slot:"trigger",size:"small",type:"primary"},slot:"trigger"},[e._v("选取文件")]),n("el-button",{staticStyle:{"margin-left":"10px"},attrs:{size:"small",type:"success"},on:{click:e.submitUpload}},[e._v("上传名单")]),n("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传Excel文件，且不超过500kb")])],1)],1)],1)},c=[],u=(n("7f7f"),n("7e91")),p={data:function(){return{newStudent:{idNum:null,phone:null,name:null},fileList:[]}},methods:{hideDialog:function(){this.$emit("hideDialog")},addSingleStudent:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(){var t,n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:if(t=this.newStudent,null!=t.idNum&&null!=t.name&&null!=t.phone){e.next=4;break}return this.$message.error("身份证、手机号码及姓名必填"),e.abrupt("return",!1);case 4:return e.prev=4,e.next=7,u["a"].addOneStudent(this.newStudent);case 7:n=e.sent,this.$message.success(n.msg),this.dialogFormVisible=!1,this.search(),e.next=16;break;case 13:e.prev=13,e.t0=e["catch"](4),this.$message.error(e.t0.message);case 16:case"end":return e.stop()}}),e,this,[[4,13]])})));function t(){return e.apply(this,arguments)}return t}(),getListTemplate:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,u["a"].getTemplate();case 3:t=e.sent,download(t),e.next=10;break;case 7:e.prev=7,e.t0=e["catch"](0),this.$message.error(e.t0.message);case 10:case"end":return e.stop()}}),e,this,[[0,7]])})));function t(){return e.apply(this,arguments)}return t}(),handleRemove:function(e){this.$message.warning("移除文件：".concat(e.name))},handleSuccess:function(e){this.$alert(e.msg,"上传文件成功",{confirmButtonText:"确定"}),this.hideDialog()},handleExceed:function(){this.$message.error("一次只能添加一个文件")},handleError:function(){this.$message.error("上传文件失败，请重试")},submitUpload:function(){this.$refs.upload.submit()}}},d=p,m=(n("5b99"),n("2877")),f=Object(m["a"])(d,s,c,!1,null,"d27e7074",null),g=f.exports,b=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"dialog-container"},[n("div",{staticClass:"table-container"},[n("m-table",{attrs:{tableConfig:e.table.config,data:e.table.data}})],1),n("div",{staticClass:"page-container"},[n("page",{attrs:{total:e.form.total,currentPage:e.form.currentPage,pageSize:e.form.pageSioze},on:{curChange:e.curChange}})],1)])},h=[],v=(n("8e6e"),n("ac6a"),n("456d"),n("bd86"));function y(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function S(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?y(Object(n),!0).forEach((function(t){Object(v["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):y(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var w={props:{idNum:{type:String,required:!0}},components:{page:l["a"],mTable:o["a"]},data:function(){return{form:{total:10,pageSize:10,currentPage:1},table:{config:[{prop:"id",label:"培训序号"},{prop:"name",label:"培训名称"},{prop:"startTime",label:"开始时间"},{prop:"endTime",label:"结束时间"},{prop:"address",label:"培训地点"},{prop:"status",label:"状态"}],data:[]}}},mounted:function(){this.getTrainingHistory()},methods:{curChange:function(e){this.form.currentPage=e,this.getTrainingHistory()},getTrainingHistory:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(){var t,n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,t=S({},this.form),t.idNum=this.idNum,e.next=5,u["a"].getStudentTrainingHistory(t);case 5:n=e.sent,this.table.data=n.data.list,this.form.total=n.data.total,e.next=13;break;case 10:e.prev=10,e.t0=e["catch"](0),this.$message.error(e.t0.message);case 13:case"end":return e.stop()}}),e,this,[[0,10]])})));function t(){return e.apply(this,arguments)}return t}()}},x=w,O=(n("a585"),Object(m["a"])(x,b,h,!1,null,"5af2be60",null)),C=O.exports,_=(n("0c6d"),{components:{mTable:o["a"],page:l["a"],newStudent:g,trainingHistory:C},data:function(){return{form:{phone:null,idNum:null,name:null,currentPage:1,total:100,pageSize:1},tableData:[{idNum:22222222222,phone:13534625262,name:"张三",email:"101@qq.com",position:"经理",province:"广东省",city:"广州市",area:"番禺区",zone:"小谷围街道华南理工大学"}],tableConfig:[{prop:"idNum",label:"身份证号码"},{prop:"phone",label:"手机号码"},{prop:"name",label:"姓名"},{prop:"email",label:"邮箱"},{prop:"position",label:"职位"},{prop:"province",label:"省份"},{prop:"city",label:"城市"},{prop:"area",label:"区/县"},{prop:"zone",label:"详细地址"},{prop:"oper",label:"操作",slot:"oper",fixed:"right",width:"220px"}],loading:!1,dialogFlag:!1,history:{idNum:null,flag:!1}}},mounted:function(){this.search()},methods:{curChange:function(e){this.form.curChange=e,this.search()},search:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,this.loading=!0,e.next=4,u["a"].getStudentList(this.form);case 4:t=e.sent,this.tableData=t.data.list,this.form.total=t.total,e.next=12;break;case 9:e.prev=9,e.t0=e["catch"](0),this.$message.error(e.t0.message);case 12:this.loading=!1;case 13:case"end":return e.stop()}}),e,this,[[0,9]])})));function t(){return e.apply(this,arguments)}return t}(),deleteRelation:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(t){var n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,this.loading=!0,n={idNum:t.idNum,phone:t.phone},e.next=5,u["a"].deleteOneStudent(n);case 5:e.sent,this.$message.success("解除挂靠关系成功"),this.search(),e.next=13;break;case 10:e.prev=10,e.t0=e["catch"](0),this.$message.error(e.t0.message);case 13:this.loading=!1;case 14:case"end":return e.stop()}}),e,this,[[0,10]])})));function t(t){return e.apply(this,arguments)}return t}(),trainingHistory:function(e){this.history.idNum=e.idNum,this.history.flag=!0}}}),k=_,j=(n("3b30"),Object(m["a"])(k,a,r,!1,null,"269289ae",null));t["default"]=j.exports}}]);