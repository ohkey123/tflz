<template>
  <div id="propose">
    <el-row>
      <el-card shadow="never">
        <el-page-header @back="goBack" content="倡议详情">
        </el-page-header>
      </el-card>
    </el-row>

    <el-row>
      <el-col>
        <div>
          <el-card shadow="never">
            <small style="color: grey">编号：{{ proposeDetails.id }}</small>
            <h1>{{ proposeDetails.name }}</h1>
            <p style="color: grey">{{ proposeDetails.description }}</p>
            <small v-if="proposeDetails.builder">创建者：{{ proposeDetails.builder.name }}</small>

            <br>
            <div v-if="proposeDetails.tags">
              <div v-for="(tag, index) in proposeDetails.tags" :key="index+'tag'">
                <el-tag style="float: left; margin-left: 1px;">{{ tag.name }}</el-tag>
              </div>
            </div>
            <br>

            <br>
            <el-divider content-position="left">
              创建时间：{{ proposeDetails.timeCreate }}
              <el-divider direction="vertical"></el-divider>
              最后回复时间：{{ proposeDetails.timeReply }}
            </el-divider>
          </el-card>
        </div>
      </el-col>
    </el-row>

    <br>
    <el-row>
      <div align="center">
        <el-button type="primary" round @click="dialogFormVisible = true">添加回复</el-button>
      </div>
    </el-row>
    <br>

    <el-dialog title="添加回复" :visible="dialogFormVisible" :show-close="false">
      <el-form>
        <el-form-item label="详情">
          <el-input
              type="textarea"
              :autosize="{ minRows: 4, maxRows: 7}"
              placeholder="请输入内容"
              v-model="description"
              show-word-limit>
          </el-input>
        </el-form-item>
        <el-form-item label="验证码">
          <el-input v-model="checkCode"></el-input>
        </el-form-item>
        <el-form-item align="center">
          <el-image :src="img_checkCode_src"></el-image>
        </el-form-item>
        <el-form-item align="center">
          <el-link type="info" @click="getCheckCode">看不清？换一张</el-link>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="createReply">发 布</el-button>
      </div>
    </el-dialog>

    <div v-if="proposeDetails.replies">
      <div v-for="(reply, index) in proposeDetails.replies" :key="index+'reply'">
        <el-row>
          <el-card shadow="never">

            <el-col :xs="12" :sm="6">
              <h3>{{ reply.user.name }}</h3>
              <small>{{ reply.user.declaration }}</small>
            </el-col>

            <el-col :xs="12" :sm="18">
              <p>{{ reply.detail }}</p>
              <br>

              <el-divider content-position="left">
                回复时间：{{ reply.timeCreate }}
              </el-divider>
            </el-col>

          </el-card>
          <br>
        </el-row>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: "Propose",
  data() {
    return {
      proposeDetails: {},
      description: '',
      checkCode: '',
      img_checkCode_src: '/api/tflz/utils/getCheckCode?' + Math.random(),
      dialogFormVisible: false
    }
  },
  created() {
    var id = this.$route.query.id;
    if (id < 1) {
      alert("请求错误！");
    } else {
      this.getProposeDetails(id);
    }
  },
  methods: {
    createReply() {
      var _this = this;

      if (_this.$store.getters.getUserInfo.id < 1) {
        _this.$router.push('/login');
        return;
      }

      var reqInfo = {
        sid: this.$route.query.id,
        uid: this.$store.getters.getUserInfo.id,
        detail: this.description,
        checkCode: this.checkCode
      }

      _this.axios.post('/api/tflz/reply/createReply', reqInfo)
          .then(function (resp) {
            if (resp.data.isSucceed) {
              _this.dialogFormVisible = false;
            } else {
              alert(resp.data.msg);
              _this.getCheckCode();
            }
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
            _this.getCheckCode();
          })
    },
    getCheckCode() {
      this.img_checkCode_src = '/api/tflz/utils/getCheckCode?' + Math.random();
    },
    goBack() {
      this.$router.back();
    },
    getProposeDetails(id) {
      var _this = this;

      _this.axios.post('/api/tflz/propose/getProposeDetails/' + id)
          .then(function (resp) {
            _this.proposeDetails = resp.data;
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