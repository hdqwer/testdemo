<template>
  <div class="mod-user">
	  <!-- inline="true"表示表单项是内联的，model="dataForm表示将表单绑定到dataForm对象 -->
	  <!-- @keyup.enter.native="search()"表示在表单的任何部分按下enter键都会触发search方法 -->
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="search()">
      <el-form-item>
		  <!-- 将输入框的值绑定到dataForm的userName属性，如果输入框为空，显示"用户名"，clearable：显示一个小的清除图标 -->
        <el-input v-model="dataForm.userName" placeholder="用户名" clearable></el-input>
      </el-form-item>
	  <!-- <el-form-item>是一个表单项，其中包含了三个按钮，每个按钮都绑定了点击事件处理函数，其中两个按钮是根据用户的权限进行渲染的 -->
      <el-form-item>
        <el-button @click="search()">查询</el-button>
		<!-- v-if决定是否显示这个按钮，只有当isAuth('sys:user:save')返回true才会显示。 -->
		<!-- type="primary"：设置按钮为主要类型 ，type="danger"：设置按钮为危险类型-->
		<!-- ：disabled设置按钮的禁用状态，dataListSelections.length小于等于0会被禁用 -->
        <el-button v-if="isAuth('sys:user:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('sys:user:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
	<!-- <el-table表示表格 -->
	<!-- :data="dataList"将表格的数据绑定到datalist -->
	<!-- border：表格 带有边框 -->
	<!-- dataListLoading为true时，表格会显示加载状态 -->
	<!-- selection-change：当表格中的行被选中或者取消时，会触发selectionChangeHandle方法 -->
	<!-- style="width: 100%;"，设置表格的宽度为100%，占满父元素 -->
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
	  <!-- 这个标签用来定义表格的列 -->
	  <!-- type="selection"设置这一列为可以选择的列，header-align：表头内容的位置，align：单元格内容，width：该列的宽度 -->
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
	  <!-- prop：对应dataList中的一个字段 -->
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        width="80"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱">
      </el-table-column>
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="手机号">
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="状态">
		<!-- 这是一个作用域插槽，用于自定义列的内容。slot-scope="scope"：允许访问当前行的数据对象以及其他信息 -->
        <template slot-scope="scope">
			<!-- el-tag：显示一个带有颜色的标签 -->
			<!-- 当当前行的status值为0，显示一个红色的小标签，标签内容为禁用 -->
          <el-tag v-if="scope.row.status === 0" size="small" type="danger">禁用</el-tag>
		  <!-- 当前行的status不为0，显示一个默认颜色的小标签，标签内容为正常 -->
          <el-tag v-else size="small">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        width="180"
        label="创建时间">
      </el-table-column>
	  <!-- 固定在右侧 -->
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
			<!-- 当前用户拥有相应的权限才会渲染 -->
          <el-button v-if="isAuth('sys:user:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.userId)">修改</el-button>
          <el-button v-if="isAuth('sys:user:delete')" type="text" size="small" @click="deleteHandle(scope.row.userId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
	<!-- el-pagination是一个分页组件
	size-change：每页个数发生变化会触发这个事件，并把当前每页条数作为参数传递到sizeChangeHandle方法
	current-change：当前页码发生变化会触发这个事件，并将当前页码作为参数传递给currentChangeHandle
	current-page="pageIndex"：绑定当前页码到pageIndex变量
	page-sizes="[10, 20, 50, 100]"：设置每页个数选择器、
	:total="totalPage"：设置总条目数
	layout="total, sizes, prev, pager, next, jumper"：组件布局
	total: 总条目数
	sizes: 每页显示个数选择器
	prev: 上一页按钮
	pager: 页码按钮
	next: 下一页按钮
	jumper: 跳转至指定页输入框 -->
	
	
	
	
	<!-- vue什么时候加this -->
	<!-- this表示当前类，只在return里定义的变量才能用this.变量
	this.方法
	插槽，在el--标签里再加一个标签template，把它变为全局变量（作为形参传出去） -->
	
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
	<!-- ref="addOrUpdate"：为组件设置引用信息，这样可以在vue实例的方法中通过this.$refs.addOrUpdate来访问组件实例 -->
	<!-- @refreshDataList="getDataList"：监听add-or-update组件触发的refreshDataList事件，并在触发时调用getDataList方法，通常用于在操作后更新列表 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './user-add-or-update'
  export default {
    data () {
      return {
		  //dataform存储表单输入的用户名
        dataForm: {
          userName: ''
        },
		//dataList是表格数据
        dataList: [],
		//当前页码
        pageIndex: 1,
		//每页显示的行数
        pageSize: 10,
		//总行数
        totalPage: 0,
		//控制数据加载的显示状态，默认值为false
        dataListLoading: false,
		//存储dataList中的选择项
        dataListSelections: [],
		//控制AddOrUpdate组件的显示与隐藏
        addOrUpdateVisible: false
      }
    },
	//components：在当前vue组件中注册其他组件。这里就可以之间使用add-or-update标签渲染
    components: {
      AddOrUpdate
    },
	//这是一个生命周期钩子，当组件被<keep-alive>包裹并从缓存中重新激活时，钩子方法会触发。<keep-alive>可以缓存不活动组件的实例
	//触发这个方法之后，它调用了组件的getDataList方法
    activated () {
      this.getDataList()
    },
    methods: {
      // 查询
	  //重置当前页码为第一页，并获取数据列表
      search() {
        this.pageIndex = 1
        this.getDataList()
      },
      // 获取数据列表
	  // 设置 dataListLoading 为 true 以显示加载状态。
	  // 发送请求到服务器获取用户数据列表。
	  // 根据返回的数据更新 dataList 和 totalPage。
	  // 设置 dataListLoading 为 false 以隐藏加载状态。
      getDataList () {
        this.dataListLoading = true
		//使用this.$http发送请求
        this.$http({
			// url是通过this.$http.adornUrl方法处理过的
          url: this.$http.adornUrl('/sys/user/list'),
          method: 'get',
		  //这个请求包含三个参数，当前页码，每页个数，用户名
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'username': this.dataForm.userName
          })
		  //请求成功之后，检查返回的参数data和其他code属性
        }).then(({data}) => {
			//如果code=0表示请求成功，更新dataList和totalPage的值
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
			  //如果请求不成功，清空这些数据
            this.dataList = []
            this.totalPage = 0
          }
		  //隐藏状态
          this.dataListLoading = false
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
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
		//this.$nextTick()是Vue的一个实例方法，它用于在DOM更新循环结束之后执行延迟回调。
		//在修改数据之后立即使用它，然后等待DOM更新。这对于需要在DOM实际更新之后执行的操作非常有用。
        this.$nextTick(() => {
			//在DOM更新完之后，初始化表单数据
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
		  //根据传入的id生成一个用户ID数组。如果id存在，则数组只有一个元素即这个 id
		  //如果id不存在，则从this.dataListSelections中映射出所有用户组成数组
        var userIds = id ? [id] : this.dataListSelections.map(item => {
          return item.userId
        })
		//使用vue的confirm方法弹出一个确认框，询问是否继续
        this.$confirm(`确定对[id=${userIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
			//如果用户点击了确定，则发送删除请求
          this.$http({
            url: this.$http.adornUrl('/sys/user/delete'),
            method: 'post',
			//请求体是经过this.$http.adornData方法处理过的userIds数组
            data: this.$http.adornData(userIds, false)
			
			//这里和post方法与上面的get有什么区别
          }).then(({data}) => {
			  //处理后端返回的数据data
            if (data) {
				//如果操作成功，显示提示信息并重新获取数据列表
              this.$message({
                message: '操作成功',
                type: 'success',
				//定义了这条消息显示的时间
                duration: 1500,
				//这是一个回调函数，在 this.$message方法关闭时触发
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
				//如果操作失败，显示错误信息
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
