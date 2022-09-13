<template>
    <el-card shadow="never" class="aui-card--fill">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()"></el-form>
        <div>
            <el-input v-model="input" placeholder="请输入内容"></el-input>
        </div>

        <el-table v-loading="dataListLoading" :data="dataList" border @selection-change="dataListSelectionChangeHandle"
                  style="width: 100%;">
            <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
            <el-table-column prop="id" :label="$t('user.username')" sortable="custom" header-align="center" align="center"></el-table-column>
            <el-table-column :label="$t('handle')" fixed="right" header-align="center" align="center" width="150">
                <template slot-scope="scope">
                    <el-button v-if="$hasPermission('sys:params:update')" type="text" size="small"
                               @click="addOrUpdateHandle(scope.row.id)">{{ $t('update') }}
                    </el-button>
                    <el-button v-if="$hasPermission('sys:params:delete')" type="text" size="small"
                               @click="deleteHandle(scope.row.id)">{{ $t('delete') }}
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                :current-page="page"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="limit"
                :total="total"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="pageSizeChangeHandle"
                @current-change="pageCurrentChangeHandle">
        </el-pagination>
    </el-card>

</template>

<script>
    import mixinViewModule from '@/mixins/view-module'
    export default {
        mixins: [mixinViewModule],
        data() {
            return {
                input: '',
                mixinViewModuleOptions: {
                    getDataListURL: '/owner/getOwnerList',
                    getDataListIsPage: true,
                    deleteURL: '',
                    deleteIsBatch: true
                },
                dataForm: {
                    id:""
                }
            }
        }
    }
</script>
