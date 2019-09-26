<template>
    <el-table
        size="small"
        :data="data"
        :max-height="data?600:200"
        ref="tableRef"
        v-loading="loading"
        v-bind="tableAttr"
        v-on="tableEvent"
        class="m-table"   
    >
        <template v-for="(item,i) in tableConfig">
            <slot
                v-if="item.slot"
                :name="item.slot"
                :params="item"
                :prop="item.prop">
            </slot>

            <el-table-column v-else align="center" :key="i" v-bind="item"></el-table-column>
        </template>
    </el-table>
</template>
<script>
export default {
    name: 'm-Table',
    props: {
        /**
         * 主要设置列表表头与每列对应的字段，el-table-column的所有属性都适用
         * @type {Array}
         */
        tableConfig: {
            type: Array
        },
        /**
         * 列表数据
         * @type {Array}
         */
        data: {
            type: Array
        },
        /**
         * loading效果
         * @type {Boolean}
         */
        loading: {
            type: Boolean
        },
        /**
         * 可加入所有element-ui表格属性
         * @type {Object}
         */
        tableAttr: {
            type: Object
        },
        /**
         * 可加入所有element-ui表格事件
         * @type {Object}
         */
        tableEvent: {
            type: Object
        }
    }
};
</script>
<style lang="scss" scoped>
.m-table {
    border-top: 1px solid #ebeef5;
    border-left: 1px solid #ebeef5;
    border-right: 1px solid #ebeef5;
    border-radius: 2px;
}
</style>