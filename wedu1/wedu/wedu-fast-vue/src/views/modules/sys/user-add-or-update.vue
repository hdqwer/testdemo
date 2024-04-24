<template>
	<!-- 对话框 -->
	<!-- title：设置对话框的标题，如果dataForm.id不存在，标题变为“新增”，存在变为“修改” -->
	<!-- close-on-click-modal：当用户点击对话框时是否关闭对话框，false：不关闭 -->
	<!-- visible.sync="visible"：visible是一个布尔值，false：关闭对话框。sync是一个语法，用于简化组件之间的双向数据绑定 -->
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
	<!-- el-form：它是一个表单 -->
	<!-- model="dataForm"：绑定表单数据模型到dataForm，rules：绑定表单验证规则到dataRule，ref：使js部分能够通过this.$refs.dataForm方式访问到dataForm -->
	<!-- @keyup.enter.native：监听表单内部的 keyup 事件，特别是当按键为 enter（回车键）时。.native 修饰符用于监听组件根元素上的原生事件 -->
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
     <!-- el-form-item：用于包裹一个表单控件，如输入框 -->
	 <!-- label：设置了表单的标签为“用户名”，prop指定的值与dataForm的属性相对应的 -->
	  <el-form-item label="用户名" prop="userName">
		  <!-- v-model="dataForm.userName：用于在输入框和属性之间做双向数据绑定。这意味着当用户在输入框中输入内容时，dataForm.userName 的值会实时更新 -->
        <el-input v-model="dataForm.userName" placeholder="登录帐号"></el-input>
      </el-form-item>
	  <!-- 如果dataForm.id不存在，则添加is-required类 -->
      <el-form-item label="密码" prop="password" :class="{ 'is-required': !dataForm.id }">
		  <!-- v-model：双向数据绑定，用户输入的值会保存在dataForm当中 -->
        <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="comfirmPassword" :class="{ 'is-required': !dataForm.id }">
        <el-input v-model="dataForm.comfirmPassword" type="password" placeholder="确认密码"></el-input>
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
		  <!-- 这是一个树型选择组件，:multiple="false"：控制用户是否可以选择多个选项，false说明只能选择一个 -->
		  <!-- options:为树型选择组件提供选择数据源，绑定到deptList属性 -->
        <TreeSelect v-model="dataForm.deptId" :multiple="false" :options="deptList" placeholder="请选择部门"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="角色" size="mini" prop="roleIdList">
		  <!-- el-checkbox-group：复选框 -->
        <el-checkbox-group v-model="dataForm.roleIdList">
			<!-- 遍历roleList数组并为每个role创建一个复选框，为每一个提供一个唯一的key，并设置复选框的label属性。role.roleName：以插表的方式给定复选框的值 -->
          <el-checkbox v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{ role.roleName }}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="状态" size="mini" prop="status">
		  <!-- el-radio-group：单选按钮组件，用来包裹el-radio标签 -->
        <el-radio-group v-model="dataForm.status">
			<!-- 设置该按钮的label组件为0 -->
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
      </el-form-item>
	  <!-- 上传图片的组件 -->
      <el-form-item label="头像">
		  <!-- el-upload是上传组件 -->
		  <!-- action：设置了上传文件的url，headers：设置了上传文件使的http头部信息，show-file-list：隐藏了已上传文件的列表显示 -->
		  <!-- on-success：文件上传成功，调用handleAvatarSuccess方法。before-upload：在文件上传之前调用beforeAvatarUpload，通常进行大小校验 -->
        <el-upload
          class="avatar-uploader"
          :action= "uploadUrl"
          :headers= "tokenInfo"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
		  <!-- 如果imageUrl存在即已经上传了头像，就显示这个img标签，将它的src属性绑定到imageUrl，设置样式为avatar -->
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
		  <!-- 如果没有上传头像。显示这个i标签 -->
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
		<!-- 点击取消，将visible设置为false从而关闭对话框 -->
      <el-button @click="visible = false">取消</el-button>
	  <!-- type="primary"：更加明显。点击 确定，触发一个事件 -->
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
	//从@/utils/validate模块导入两个函数
  import { isEmail, isMobile } from '@/utils/validate'
  //从@/utils导入函数
  import {treeDataTranslate} from '@/utils'
  //从@riophae/vue-treeselect模块导入组件TreeSelect
  import TreeSelect from '@riophae/vue-treeselect'
  //导入TreeSelect的样式
  import '@riophae/vue-treeselect/dist/vue-treeselect.css'
  export default {
	  //注册之前导入的TreeSelect组件
    components: {TreeSelect},
    data () {
		//这个函数使用了箭头函数的语法，接受了三个参数
		//rule：通常是与当前验证字段相关的规则对象。在这个函数中，它似乎没有被直接使用。
		//value：是要验证的密码字段的值。
		//callback：是一个回调函数，用于通知验证是否成功。通常，当验证失败时，你会传递一个错误对象给callback；当验证成功时，你可以简单地调用callback()（没有参数）。
      var validatePassword = (rule, value, callback) => {
        if (!this.dataForm.id && !/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }
	  // 检查this.dataForm.id是否存在，并且value（即密码）是否包含非空白字符（通过正则表达式/\S/实现）。
	  // 如果this.dataForm.id不存在且密码为空（即只包含空白字符），则调用callback并传递一个错误对象，表示“密码不能为空”。
	  // 如果上述条件不满足（即this.dataForm.id存在或密码不为空），则简单地调用callback()，表示验证成功
      var validateComfirmPassword = (rule, value, callback) => {
        if (!this.dataForm.id && !/\S/.test(value)) {
          callback(new Error('确认密码不能为空'))
        } else if (this.dataForm.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        } else {
          callback()
        }
      }
      var validateEmail = (rule, value, callback) => {
		  //使用isEmail判断格式问题，isEmail是导入的函数
        if (!isEmail(value)) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }
      var validateMobile = (rule, value, callback) => {
        if (!isMobile(value)) {
          callback(new Error('手机号格式错误'))
        } else {
          callback()
        }
      }
      return {
		  //返回一些对象属性
        visible: false,
        roleList: [],
        deptList: [],
        imageUrl: '',
        uploadUrl: this.$http.adornUrl('/sys/file/upload'),
        tokenInfo: {
			//从cookie中获取值
          'token': this.$cookie.get('token')
        },
		//表单信息
        dataForm: {
          id: 0,
          userName: '',
          password: '',
          comfirmPassword: '',
          salt: '',
          email: '',
          mobile: '',
          roleIdList: [],
          status: 1,
          deptId: null,
          avatar: null
        },
		//表单验证规则
        dataRule: {
          userName: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
		  //password验证规则是一个数组，其中包含了一个对象
		  //当触发blur事件，会调用validatePassword方法
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ],
          comfirmPassword: [
            { validator: validateComfirmPassword, trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' },
            { validator: validateEmail, trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号不能为空', trigger: 'blur' },
            { validator: validateMobile, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
		//这个方法的目的是初始化组件状态
      init (id) {
		  //将传入的 id 赋值给 dataForm 的 id 属性，如果 id 为假值，则赋值为0。
        this.dataForm.id = id || 0
		//发起一个GET请求到 /sys/dept/tree URL，获取部门树形数据，并将返回的数据通过 treeDataTranslate 函数转换后赋值给 deptList
        this.$http({
          url: this.$http.adornUrl('/sys/dept/tree'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.deptList = treeDataTranslate(data)
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/role/select'),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
			  //这个代码是一个三元运算符，如果data存在并且data.code为0，那么 this.roleList =data.list，如果没有满足条件，则为空
            this.roleList = data && data.code === 0 ? data.list : []
          })
		  //获取完角色列表之后
        }).then(() => {
			//将visible设置为true，控制某个元素为显示状态
          this.visible = true
		  //$nextTick方法允许你在DOM更新完成后执行回调函数，确保此时DOM已经反映了最新的数据状态。
          this.$nextTick(() => {
			  //这行代码调用了dataForm引用上的resetFields方法，这个方法用于重置表单状态到它们初始的阶段。在Vue中，$refs是一个对象，持有已注册过ref特性的所有DOM元素和组件实例
            this.$refs['dataForm'].resetFields()
          })
		  //根据用户id获取用户信息
        }).then(() => {
			//如果id存在
          if (this.dataForm.id) {
			  //发送get请求，通过id获取用户信息
            this.$http({
              url: this.$http.adornUrl(`/sys/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
				//如果请求成功，更新dataForm的各个字段
              if (data && data.code === 0) {
                this.dataForm.userName = data.user.username
                this.dataForm.salt = data.user.salt
                this.dataForm.email = data.user.email
                this.dataForm.mobile = data.user.mobile
                this.dataForm.roleIdList = data.user.roleIdList
                this.dataForm.status = data.user.status
                this.dataForm.deptId = data.user.deptId
                this.dataForm.avatar = data.user.avatar
                if(this.dataForm.avatar){
					//调用this.$http对象的adornUrl方法将dataForm.avatar参数传进去并赋值给imageUrl
                  this.imageUrl = this.$http.adornUrl(this.dataForm.avatar)
                }
              }
            })
          }
        })
      },
      // 表单提交
	  //定义了一个 dataFormSubmit方法处理表单提交
      dataFormSubmit () {
		  //调用 this.$refs对象访问dataForm组件，并调用validate方法来进行验证（检查表单字段是否满足一些条件），验证的结果valid是一个布尔值
        this.$refs['dataForm'].validate((valid) => {
			//如果验证通过则执行表单提交逻辑
          if (valid) {
			  //提交post请求，请求的url通过this.$http.adornUrl方法构建
            this.$http({
              url: this.$http.adornUrl(`/sys/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
			  //请求体数据通过this.$http.adornData处理，将表单数据转换为适合发送的格式
              data: this.$http.adornData({
                'userId': this.dataForm.id || undefined,
                'username': this.dataForm.userName,
                'password': this.dataForm.password,
                'salt': this.dataForm.salt,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'status': this.dataForm.status,
                'deptId': this.dataForm.deptId,
                'roleIdList': this.dataForm.roleIdList,
                'avatar': this.dataForm.avatar
              })
			  //当http请求成功返回后，通过then方法处理响应数据
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
					//通过$emit方法发出一个名为refreshDataList的事件，让父组件知道需要刷新数据列表。
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
	  //处理头像上传成功的函数，res：服务器返回的响应，file：用户选择的文件对象
      handleAvatarSuccess(res, file) {
        console.log(res)
		//创建一个临时的对象url，代表用户选择的文件内容。file.raw：代表的是文件的原始数据
		// 用于在前端显示用户选择的图片（通常是在上传之前）
        this.imageUrl = URL.createObjectURL(file.raw);
		//将服务器返回的头像url赋值给dataForm.avatar
        this.dataForm.avatar = res.data.url
      },
	  //在头像上传之前检查
      beforeAvatarUpload(file) {
		  //检查是否为jpeg，png
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
		//大小是否小于2M
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!(isJPG || isPNG)) {
          this.$message.error('上传头像图片只能是 JPG 或者 png 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
		//满足条件返回true
        return (isJPG || isPNG) && isLt2M;
      }
    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
	  /* 虚线边框 */
    border: 1px dashed #d9d9d9;
	/* 边框圆角 */
    border-radius: 6px;
	/* 鼠标悬浮到上面变为手指 */
    cursor: pointer;
	/* 定位:相对 */
    position: relative;
	/* 如果超出指定宽度高度,超出部分隐藏 */
    overflow: hidden;
  }
  /* 鼠标悬停到el-upload,边框变色 */
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
	/* 行高 */
    line-height: 178px;
	/* 内容居中 */
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

