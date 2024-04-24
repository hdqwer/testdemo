<template>
  <div class="content">
    <!-- 上半部分区域 -->
    <div class="content-top">
      <!-- 用户信息区域 -->
      <div style="width:40%;">
        <h2>用户信息</h2>
        <div class="content-userinfo">
          <div style="width:30%;display:flex;">
            <img :src="imageUrl" class="avatar"/>
          </div>
          <div class="content-userinfo-right">
            <div class="content-userinfo-right-text">
              <span>用户名称：{{userInfo.username}}</span>
              <span>邮箱地址：{{userInfo.email?userInfo.email:'未知'}}</span>
              <span>电话号码：{{userInfo.phone?userInfo.phone:'未知'}}</span>
              <span>部门名称：{{userInfo.deptName?userInfo.deptName:'未知'}}</span>
              <span>角色列表：{{userInfo.roleNames.length > 0?userInfo.roleNames.join(","):'未知'}}</span>
            </div>
          </div>
        </div>
        
      </div>
      <!-- 登录日志区域 -->
      <div style="width:60%;">
        <h2>登录日志</h2>
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
            prop="username"
            header-align="center"
            align="center"
            label="用户名">
          </el-table-column>
          <el-table-column
            prop="loginTime"
            header-align="center"
            align="center"
            label="登录时间">
          </el-table-column>
          <el-table-column
            prop="ip"
            header-align="center"
            align="center"
            label="IP地址">
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[5]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </div>
    <!-- 下半部分区域：系统监测 -->
    <div style="widyh:100%;">
      <h2>系统运行状态</h2>
      <div class="content-systemInfo">
        <div class="content-systemInfo-data">
          <h3>CPU使用比率</h3>
          <div id="cpuRate" class="chart-box"></div>
        </div>
        <div class="content-systemInfo-data">
          <h3>内存使用比率</h3>
          <div id="memoryRate" class="chart-box" style="height:400px;"></div>
        </div>
        <div class="content-systemInfo-data">
          <h3>JVM使用比率</h3>
          <div id="jvmRate" class="chart-box" style="height:400px;"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as echarts from 'echarts'


  export default {
    data () {
      return {
        disks: [],
        memoryRateChart: null,
        jvmRateChart: null,
        cpuRateChart: null,
        imageUrl: null,
        userInfo: {
          roleNames: []
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 5,
        totalPage: 0,
        dataListLoading: false,
      }
    },
    created() {
      this.getUserInfo()
      this.getLoginLogList()
    },
    mounted () {
      let that = this
      setTimeout(function(){
        that.init()
      },1000)
      
    },
    activated() {
      // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
      if (this.memoryRateChart) {
        this.memoryRateChart.resize()
      }
    },
    methods: {
      init() {
        let that = this
        this.$http({
          url: this.$http.adornUrl('/sys/info/getSysInfo'),
          method: 'get'
        }).then(({data}) => {
          let result = data.data
          if(result){
            // 初始化内存使用比率
            if(result.memoryRate || result.memoryRate == 0){
              that.initMemoryRate(result.memoryRate)
            }
            if(result.jvmRate || result.jvmRate == 0){
              // 初始化JVM使用比率
              that.initJvmRate(result.jvmRate)
            }
            // 初始化CPU使用比率
            if(result.cpuRate || result.cpuRate == 0){
              that.initCpuRate(result.cpuRate)
            }
            // 初始化磁盘使用率
            if(result.diskDtoList) {
              that.disks = result.diskDtoList[0]
            }
          }
        })

        
      },
      // 获取内存使用比率
      initMemoryRate(data) {
        var option = {
          series: [
            {
              type: 'gauge',
              axisLine: {
                lineStyle: {
                  width: 30,
                  color: [
                    [0.3, '#67e0e3'],
                    [0.7, '#37a2da'],
                    [1, '#fd666d']
                  ]
                }
              },
              pointer: {
                itemStyle: {
                  color: 'auto'
                }
              },
              axisTick: {
                distance: -30,
                length: 8,
                lineStyle: {
                  color: '#fff',
                  width: 2
                }
              },
              splitLine: {
                distance: -30,
                length: 30,
                lineStyle: {
                  color: '#fff',
                  width: 4
                }
              },
              axisLabel: {
                color: 'auto',
                distance: 40,
                fontSize: 20
              },
              detail: {
                valueAnimation: true,
                formatter: '{value}%',
                color: 'auto'
              },
              data: [
                {
                  value: data
                }
              ]
            }
          ]
        }
        this.memoryRateChart = echarts.init(document.getElementById('memoryRate'))
        this.memoryRateChart.setOption(option)
        window.addEventListener('resize', () => {
          this.memoryRateChart.resize()
        })
      },
      // 获取JVM内存使用比率
      initJvmRate(data) {
        var option = {
          series: [
            {
              type: 'gauge',
              axisLine: {
                lineStyle: {
                  width: 30,
                  color: [
                    [0.3, '#67e0e3'],
                    [0.7, '#37a2da'],
                    [1, '#fd666d']
                  ]
                }
              },
              pointer: {
                itemStyle: {
                  color: 'auto'
                }
              },
              axisTick: {
                distance: -30,
                length: 8,
                lineStyle: {
                  color: '#fff',
                  width: 2
                }
              },
              splitLine: {
                distance: -30,
                length: 30,
                lineStyle: {
                  color: '#fff',
                  width: 4
                }
              },
              axisLabel: {
                color: 'auto',
                distance: 40,
                fontSize: 20
              },
              detail: {
                valueAnimation: true,
                formatter: '{value}%',
                color: 'auto'
              },
              data: [
                {
                  value: data
                }
              ]
            }
          ]
        }
        this.jvmRateChart = echarts.init(document.getElementById('jvmRate'))
        this.jvmRateChart.setOption(option)
        window.addEventListener('resize', () => {
          this.jvmRateChart.resize()
        })
      },
      // 获取CPU使用比率
      initCpuRate(data) {
        var option = {
          series: [
            {
              type: 'gauge',
              axisLine: {
                lineStyle: {
                  width: 30,
                  color: [
                    [0.3, '#67e0e3'],
                    [0.7, '#37a2da'],
                    [1, '#fd666d']
                  ]
                }
              },
              pointer: {
                itemStyle: {
                  color: 'auto'
                }
              },
              axisTick: {
                distance: -30,
                length: 8,
                lineStyle: {
                  color: '#fff',
                  width: 2
                }
              },
              splitLine: {
                distance: -30,
                length: 30,
                lineStyle: {
                  color: '#fff',
                  width: 4
                }
              },
              axisLabel: {
                color: 'auto',
                distance: 40,
                fontSize: 20
              },
              detail: {
                valueAnimation: true,
                formatter: '{value}%',
                color: 'auto'
              },
              data: [
                {
                  value: data
                }
              ]
            }
          ]
        }
        this.cpuRateChart = echarts.init(document.getElementById('cpuRate'))
        this.cpuRateChart.setOption(option)
        window.addEventListener('resize', () => {
          this.cpuRateChart.resize()
        })
      },
      // 获取用户登录信息
      getUserInfo() {
        this.$http({
          url: this.$http.adornUrl('/sys/user/getLoginUserInfo'),
          method: 'get'
        }).then(({data}) => {
          this.userInfo = data.data
          this.imageUrl = this.userInfo.avatar ? this.$http.adornUrl(this.userInfo.avatar) : '/static/img/avatar.c58e465.png'
        })
      },
      // 获取用户登录日志
      getLoginLogList () {
        console.log(this.$store.state)
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/sys/loginLog/myLoginList'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize
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


    }
  }
</script>

<style>
  .chart-box {
    min-width:400px;
    min-height: 400px;
  }
  .avatar {
    width: 180px;
    height: 180px;
  }
  .content {
    display:flex;
    flex-direction:column;
    padding:20px 20px;
  }
  .content-top {
    display:flex;
    flex-direction:row;
    width:100%;
  }
  .content-userinfo {
    display:flex;
    flex-direction:row;
    justify-content:center;
    align-items:center;
    padding-top:20px;
  }
  .content-userinfo-right {
    width:70%;
    display:flex;
    flex-direction:row;
    padding-left:20px;
  }
  .content-userinfo-right-text {
    width:100%;
    display:flex;
    flex-direction:column;
    justify-content:flex-start;
    align-items:flex-start;
  }
  .content-userinfo-right-text span {
    padding-bottom:20px;
  }
  .content-systemInfo {
    display:flex;
    flex-direction:row;
  }
  .content-systemInfo-data {
    width:33.33%;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;
  }
</style>

