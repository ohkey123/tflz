<template>
  <div id="personal">
    <el-card shadow="never">
      <el-tabs v-model="activeName">
        <el-tab-pane label="通知" name="notice" @tab-click="handleClick">

          <br>
          <el-row>
            <div v-if="noticeNum > 0">
              <h2>未读消息数：{{ noticeNum }}</h2>

              <div v-for="(n,index) in unread" :key="index+'notice'">
                <el-card shadow="never">
                  <div>
                    <p>用户：{{ n.sname }}</p>
                    <p>回复了您名为：《{{ n.pname }}》 的倡议</p>
                    <el-button plain @click="readNotice(n.id,n.pid)">点击阅读</el-button>
                  </div>
                </el-card>
              </div>
            </div>
            <div v-else>
              <h2>您暂时没有未读的消息！</h2>
            </div>
          </el-row>
          <br>

          <el-row>
            <div v-for="(n,index) in read" :key="index+'notice'">
              <el-card shadow="never">
                <div style="color: grey">
                  (已读)
                </div>
                <div style="color: grey">
                  <p>用户：{{ n.sname }}</p>
                  <p>回复了您名为：《{{ n.pname }} 》 的倡议</p>
                  <el-button plain @click="readNotice(-2,n.pid)">点击阅读</el-button>
                </div>
              </el-card>
            </div>
          </el-row>
        </el-tab-pane>

        <el-tab-pane label="个人信息" name="info">
          <el-row>
            <div align="center">
              <el-avatar icon="el-icon-user-solid" :size="100"></el-avatar>
              <h1>我的信息</h1>

              <div>
                <el-table
                    :data="tableData"
                    stripe
                    style="width: 100%">
                  <el-table-column
                      prop="fir"
                      width="180"
                      align="center">
                  </el-table-column>
                  <el-table-column
                      prop="sec"
                      width="180"
                      align="center">
                  </el-table-column>
                </el-table>
              </div>
            </div>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Personal",
  data() {
    return {
      activeName: 'notice',
      noticeNum: 0,
      read: [],
      unread: [],
      curUserInfo: {},
      tableData: []
    }
  },
  created() {
    if (this.$store.getters.getUserInfo.id < 1) {
      this.$router.push('/login');
    }
    this.getUnread();
    this.getRead();
    this.synchroUserInfo();
  },
  methods: {
    handleClick() {
      this.getUnread();
      this.getRead();
      this.synchroUserInfo();
    },
    synchroUserInfo() {
      this.curUserInfo = this.$store.getters.getUserInfo;
      this.tableData = [{
        fir: '用户id',
        sec: this.curUserInfo.id,
      }, {
        fir: '电子邮箱',
        sec: this.curUserInfo.email,
      }, {
        fir: '用户名',
        sec: this.curUserInfo.name,
      }, {
        fir: '年龄',
        sec: this.curUserInfo.age,
      }, {
        fir: '性别',
        sec: this.curUserInfo.sex == 0 ? '男' : '女',
      }, {
        fir: '账户创建日期',
        sec: this.curUserInfo.timeCreate,
      }, {
        fir: '最近活跃日期',
        sec: this.curUserInfo.timeModify,
      }]
    },
    getRead() {
      var _this = this;
      _this.axios.post('/api/tflz/notice/getRead/' + this.$store.getters.getUserInfo.id)
          .then(function (resp) {
            _this.read = resp.data;
          })
          .catch(function (error) {
            if (error.response) {
              // 请求已发出，但服务器响应的状态码不在 2xx 范围内
              for (var i = 0; i < error.response.data.errors.length; i++) {
                _this.$notify({
                  title: '错误',
                  message: error.response.data.errors[i].defaultMessage,
                  type: 'error'
                });
              }
            } else {
              // Something happened in setting up the request that triggered an Error
              alert(error.message);
            }
          })
    },
    getUnread() {
      var _this = this;
      _this.axios.post('/api/tflz/notice/getUnread/' + this.$store.getters.getUserInfo.id)
          .then(function (resp) {
            _this.unread = resp.data;
            _this.noticeNum = resp.data.length;
          })
          .catch(function (error) {
            if (error.response) {
              // 请求已发出，但服务器响应的状态码不在 2xx 范围内
              for (var i = 0; i < error.response.data.errors.length; i++) {
                _this.$notify({
                  title: '错误',
                  message: error.response.data.errors[i].defaultMessage,
                  type: 'error'
                });
              }
            } else {
              // Something happened in setting up the request that triggered an Error
              alert(error.message);
            }
          })
    },
    readNotice(nid, pid) {
      if (nid == -2) {
        this.$router.push({
          path: '/propose',
          query: {
            id: pid
          }
        });
        return;
      }
      var _this = this;
      _this.axios.post('/api/tflz/notice/read/' + nid)
          .then(function () {
            _this.$router.push({
              path: '/propose',
              query: {
                id: pid
              }
            });
          })
          .catch(function (error) {
            if (error.response) {
              // 请求已发出，但服务器响应的状态码不在 2xx 范围内
              for (var i = 0; i < error.response.data.errors.length; i++) {
                _this.$notify({
                  title: '错误',
                  message: error.response.data.errors[i].defaultMessage,
                  type: 'error'
                });
              }
            } else {
              // Something happened in setting up the request that triggered an Error
              alert(error.message);
            }
          })
    }
  }
}
</script>

<style scoped>

</style>