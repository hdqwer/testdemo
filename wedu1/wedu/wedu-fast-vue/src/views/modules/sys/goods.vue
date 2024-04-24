<template>
  <div class="mod-goods">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="search()">
      <el-form-item>
        <el-input v-model="dataForm.goodsName" placeholder="商品名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="search()">查询</el-button>
        <el-button v-if="isAuth('sys:goods:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('sys:goods:delete')"type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="80"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="商品名称">
      </el-table-column>
      <el-table-column
        prop="originalPrice"
        header-align="center"
        align="center"
        label="原价">
      </el-table-column>
      <el-table-column
        prop="currentPrice"
        header-align="center"
        align="center"
        label="现价">
      </el-table-column>
      <el-table-column
        prop="introduction"
        header-align="center"
        align="center"
        label="介绍">
      </el-table-column>
      <el-table-column
        prop="addTime"
        header-align="center"
        align="center"
        width="180"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('sys:goods:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('sys:goods:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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

    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './goods-add-or-update'

export default {
  data() {
    return {
      dataForm: {
        goodsName: ''
      },
      dataList: [],
	  pageIndex: 1,
	  //每页显示的行数
	  pageSize: 10,
	  //总行数
	  totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  activated() {
	  
    this.getDataList()
  },
  methods: {
    search() {
	  this.pageIndex = 1
      this.getDataList();
    },
	
	
	
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/sys/goods/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'goodsname': this.dataForm.goodsName
        })
      }).then(({ data }) => {
    // 直接假设请求成功
    if (data && data.code === 0) {
      this.dataList = data.page.list
	  this.totalPage = data.page.totalCount
      console.log("成功获取到的商品数据:", this.dataList);
    } else {
      this.dataList = []
	  this.totalPage = 0
    }
    this.dataListLoading = false;
  }).catch(error => {
    console.error("获取商品数据时发生错误:", error);
    this.dataList = [];
    this.dataListLoading = false;
  });
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
      // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
	
	
	
    deleteHandle(id) {
      var goodsIds = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${goodsIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/sys/goods/delete'),
          method: 'post',
          data: this.$http.adornData(goodsIds, false)
        }).then(({ data }) => {
          if (data) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {})
    }
  }
}
</script>

