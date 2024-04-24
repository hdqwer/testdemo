<template>
	<!-- 对话框 -->
	<el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
		<el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
			label-width="80px">
			<!-- 表单项 -->
			<el-form-item label="商品名称" prop="name">
				<el-input v-model="dataForm.name" placeholder="请输入商品名称" type="text"></el-input>
			</el-form-item>
			<el-form-item label="商品原价" prop="originalPrice">
				<el-input v-model="dataForm.originalPrice" placeholder="请输入商品价格" type="number"></el-input>
			</el-form-item>
			<el-form-item label="商品现价" prop="currentPrice">
				<el-input v-model="dataForm.currentPrice" placeholder="请输入商品价格" type="number"></el-input>
			</el-form-item>
			<el-form-item label="商品描述" prop="introduction">
				<el-input v-model="dataForm.introduction" placeholder="请输入商品描述" type="text"></el-input>
			</el-form-item>
			<el-form-item label="所属类别" prop="subcatId">
					  <!-- 这是一个树型选择组件，:multiple="false"：控制用户是否可以选择多个选项，false说明只能选择一个 -->
					  <!-- options:为树型选择组件提供选择数据源，绑定到deptList属性 -->
			  <TreeSelect v-model="dataForm.subcatId" :multiple="false" :options="deptList" placeholder="请选择类别"/>
			</el-form-item>

			<!-- 上传图片的表单项 -->
			<el-form-item label="商品图片">
				<el-upload class="avatar-uploader" :action="uploadUrl" :headers="tokenInfo" :show-file-list="false"
					:on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
					<img v-if="imageUrl" :src="imageUrl" class="avatar">
					<i v-else class="el-icon-plus avatar-uploader-icon"></i>
				</el-upload>
			</el-form-item>

		</el-form>
		<!-- 底部按钮 -->
		<span slot="footer" class="dialog-footer">
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="dataFormSubmit()">确定</el-button>
		</span>
	</el-dialog>
</template>

<script>
	// 导入需要的模块和组件
	
	import {treeDataTranslate} from '@/utils'
	//从@riophae/vue-treeselect模块导入组件TreeSelect
	import TreeSelect from '@riophae/vue-treeselect'
	//导入TreeSelect的样式
	import '@riophae/vue-treeselect/dist/vue-treeselect.css'

	export default {
		components: {TreeSelect},
		//定义组件的初始数据
		data() {
			return {
				visible: false,
				imageUrl: '', // 商品图片URL
				// 表单数据模型
				dataForm: {
					id: 0,
					name: '',
					currentPrice: '',
					originalPrice: '',
					introduction: '',
					picture: ''
				},
				deptList: [],
				// 表单验证规则
				dataRule: {
					name: [{
						required: true,
						message: '商品名称不能为空',
						trigger: 'blur'
					}],
					currentPrice: [{
						required: true,
						message: '商品价格不能为空',
						trigger: 'blur'
					}],
					originalPrice: [{
						required: true,
						message: '商品价格不能为空',
						trigger: 'blur'
					}],
					introduction: [{
						required: true,
						message: '商品描述不能为空',
						trigger: 'blur'
					}]
				},
				// 上传图片的URL和请求头信息
				uploadUrl: this.$http.adornUrl('/sys/file/upload'),
				tokenInfo: {
					'token': this.$cookie.get('token')
				}
			}
		},
		methods: {
			// 初始化方法，用于设置组件状态和数据
			init(id) {
				this.dataForm.id = id || 0; // 设置ID，如果未传入，则为0
				//获取商品类别数据
				this.$http({
						url: this.$http.adornUrl('/sys/goods/tree'),
						method: 'get',
						params: this.$http.adornParams()
					}).then(({
						data
					}) => {
						// 返回的数据通过 treeDataTranslate 函数转换后赋值给 deptList
						this.deptList = treeDataTranslate(data)
						console.log(this.deptList+"shujv")
					}).then(() => {
							// 如果存在ID，则说明是修改商品信息，此时会根据id发送请求获取对应的商品信息并填充到表单中
							if (this.dataForm.id) {
								// 发起获取商品信息的请求
								this.$http({
									url: this.$http.adornUrl(`/sys/goods/info/${this.dataForm.id}`),
									method: 'get',
									params: this.$http.adornParams()
								}).then(({
									data
								}) => {
									if (data && data.code === 0) {
										// 更新表单数据
										const goodsData = data.goods;
										this.dataForm.name = goodsData.name;
										this.dataForm.currentPrice = goodsData.currentPrice;
										this.dataForm.originalPrice = goodsData.originalPrice;
										this.dataForm.introduction = goodsData.introduction;
										this.dataForm.picture = goodsData.picture;
										this.dataForm.subcatId = goodsData.subcatId;
										// console.log(this.dataForm + "shujv")
										// 更新商品图片URL
										if (goodsData.picture) {
											this.imageUrl = this.$http.adornUrl(goodsData.picture);
										}
									}
								});
							}

							// 设置visible为true，显示对话框
							this.visible = true;
							// 使用$nextTick确保DOM已经更新完成
							this.$nextTick(() => {
								this.$refs['dataForm'].resetFields(); // 重置表单状态

							});

						});



					},







					// 表单提交方法
					dataFormSubmit() {
						// 调用表单验证方法
						this.$refs['dataForm'].validate((valid) => {
							// 如果验证通过则执行表单提交逻辑
							if (valid) {
								// 提交post请求，请求的URL根据ID决定是新增还是修改
								this.$http({
									url: this.$http.adornUrl(
										`/sys/goods/${!this.dataForm.id ? 'save' : 'update'}`),
									method: 'post',
									data: this.$http.adornData(this.dataForm)
									// console.log(this.dataForm)
								}).then(({
									data
								}) => {
									if (data && data.code === 0) {
										this.$message({
											message: '操作成功',
											type: 'success',
											duration: 1500,
											onClose: () => {
												this.visible = false;
												this.$emit(
												'refreshDataList'); // 发出事件通知父组件刷新数据列表
											}
										});
									} else {
										this.$message.error(data.msg);
										this.visible = false;
										this.$emit('refreshDataList');
									}
								});
							}
						});
					},


					// 处理上传图片成功的方法
					handleAvatarSuccess(res, file) {
						this.imageUrl = URL.createObjectURL(file.raw); // 显示图片预览
						this.dataForm.picture = res.data.url; // 设置图片URL
					},

					// 上传图片之前的校验方法
					beforeAvatarUpload(file) {
						const isJPG = file.type === 'image/jpeg';
						const isPNG = file.type === 'image/png';
						const isLt2M = file.size / 1024 / 1024 < 2;
						if (!(isJPG || isPNG)) {
							this.$message.error('上传头像图片只能是 JPG 或者 png 格式!');
						}
						if (!isLt2M) {
							this.$message.error('上传头像图片大小不能超过 2MB!');
						}
						return (isJPG || isPNG) && isLt2M;
					}
			}
		}
</script>




<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}
</style>