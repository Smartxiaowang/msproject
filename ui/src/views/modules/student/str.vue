<template>
    <el-card shadow="never" class="aui-card--fill">
        <div class="mod-stu__srr">
            <el-form :inline="true" :model="dataForm" @keyup.enter.native="">
                <el-date-picker
                        v-model="dataForm.dateTime"
                        type="date"
                        placeholder="请选择登记时间">
                </el-date-picker>

                <el-form-item>
                    <el-button @click="">{{ $t('query') }}</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button v-if="$hasPermission('sys:params:save')" type="primary" @click="">{{ $t('add') }}</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button v-if="$hasPermission('sys:params:delete')" type="danger" @click="">{{ $t('deleteBatch') }}</el-button>
                </el-form-item>
            </el-form>
            <!--  <el-table v-loading="dataListLoading" :data="dataList" border @selection-change="dataListSelectionChangeHandle" style="width: 100%;">
                 <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
                 <el-table-column prop="paramCode" :label="$t('params.paramCode')" header-align="center" align="center"></el-table-column>
                 <el-table-column prop="paramValue" :label="$t('params.paramValue')" header-align="center" align="center"></el-table-column>
                 <el-table-column prop="remark" :label="$t('params.remark')" header-align="center" align="center"></el-table-column>
                 <el-table-column :label="$t('handle')" fixed="right" header-align="center" align="center" width="150">
                     <template slot-scope="scope">
                         <el-button v-if="$hasPermission('sys:params:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">{{ $t('update') }}</el-button>
                         <el-button v-if="$hasPermission('sys:params:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">{{ $t('delete') }}</el-button>
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
             &lt;!&ndash; 弹窗, 新增 / 修改 &ndash;&gt;
             <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
         </div>-->
        </div>
    </el-card>
</template>

<script>
    import mixinViewModule from '@/mixins/view-module'
    //  import AddOrUpdate from './params-add-or-update'
    export default {
        mixins: [mixinViewModule],
        data () {
            return {
                mixinViewModuleOptions: {
                    getDataListURL: '/stu/getBodyTemperatureList',
                    getDataListIsPage: true,
                    deleteURL: '/sys/params',
                    deleteIsBatch: true
                },
                dataForm: {
                    'dateTime': ''
                }
            }
        },
        components: {
            // AddOrUpdate
        }
    }
</script>
