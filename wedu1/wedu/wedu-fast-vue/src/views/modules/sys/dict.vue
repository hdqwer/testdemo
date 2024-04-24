<template>
  <div class="mod-config">
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card>
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="search()">
            <el-form-item>
              <el-input v-model="dataForm.name" placeholder="字典名称" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="search()">查询</el-button>
              <el-button v-if="isAuth('sys:dict:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="dataList"
            border
            highlight-current-row
            v-loading="dataListLoading"
            @current-change="handleCurrentChange"
            style="width: 100%;">
            <el-table-column
              prop="name"
              header-align="center"
              align="center"
              label="字典名称">
            </el-table-column>
            <el-table-column
              prop="code"
              header-align="center"
              align="center"
              label="字典编码">
            </el-table-column>
            <el-table-column
              prop="createTime"
              header-align="center"
              align="center"
              label="创建时间">
            </el-table-column>
            <el-table-column
              fixed="right"
              header-align="center"
              align="center"
              width="150"
              label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
                <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper">
          </el-pagination>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-button v-if="isAuth('sys:dict:save')" type="primary" @click="childAddOrUpdateHandle()">新增</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="childDataList"
            border
            v-loading="dataListLoading"
            style="width: 100%;">
            <el-table-column
              prop="name"
              header-align="center"
              align="center"
              label="字典项">
            </el-table-column>
            <el-table-column
              prop="value"
              header-align="center"
              align="center"
              label="字典值">
            </el-table-column>
            <el-table-column
              prop="sort"
              header-align="center"
              align="center"
              label="排序">
            </el-table-column>
            <el-table-column
              fixed="right"
              header-align="center"
              align="center"
              width="150"
              label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="childAddOrUpdateHandle(scope.row.id)">修改</el-button>
                <el-button type="text" size="small" @click="deleteChildHandle(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="childSizeChangeHandle"
            @current-change="childCurrentChangeHandle"
            :current-page="childPageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="childPageSize"
            :total="childTotalPage"
            layout="total, sizes, prev, pager, next, jumper">
          </el-pagination>
        </el-card>
      </el-col>
    </el-row>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <child-add-or-update v-if="childAddOrUpdateVisible" ref="childAddOrUpdate"
                         @refreshChildDataList="getChildDataList($event)"></child-add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './dict-add-or-update'
  import ChildAddOrUpdate from './dict-child-add-or-update'

  export default {
    data () {
      return {
        dataForm: {
          name: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        childDataList: [],
        childPageIndex: 1,
        childPageSize: 10,
        childTotalPage: 0,
        childDataListLoading: false,
        childAddOrUpdateVisible: false,
        currentCode: null
      }
    },
    components: {
      AddOrUpdate,
      ChildAddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 查询
      search() {
        this.pageIndex = 1
        this.getDataList()
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/sys/dict/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'name': this.dataForm.name
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      getChildDataList (code) {
        this.childDataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/sys/dict/childList'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.childPageIndex,
            'limit': this.childPageSize,
            'code': code
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.childDataList = data.page.list
            this.childTotalPage = data.page.totalCount
          } else {
            this.childDataList = []
            this.childTotalPage = 0
          }
          this.childDataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 每页数
      childSizeChangeHandle (val) {
        this.childPageSize = val
        this.childPageIndex = 1
        this.getChildDataList(this.currentCode)
      },
      // 当前页
      childCurrentChangeHandle (val) {
        this.childPageIndex = val
        this.getChildDataList(this.currentCode)
      },
      handleCurrentChange (val) {
        if (val) {
          this.getChildDataList(val.code)
          this.currentCode = val.code
        }
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 新增 / 修改
      childAddOrUpdateHandle (id) {
        if (typeof id === 'undefined' && this.currentCode == null) {
          this.$message({
            message: '请选择字典',
            type: 'warning',
            duration: 1500
          })
          return
        }
        this.childAddOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.childAddOrUpdate.init(id, this.currentCode)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`会同时删除字典项，确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/dict/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                  this.childDataList = []
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {
        })
      },
      // 删除
      deleteChildHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/dict/deleteChild'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getChildDataList(this.currentCode)
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {
        })
      }
    }
  }
</script>
