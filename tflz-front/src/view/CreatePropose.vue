<template>
  <div id="createPropose">
    <el-row>
      <el-card shadow="never">
        <el-page-header @back="goBack" content="发布倡议">
        </el-page-header>
      </el-card>
    </el-row>

    <el-row>
      <el-col :xs="24" :sm="18">
        <br>
        <div class="table">
          <el-card shadow="always">
            <el-form>
              <el-form-item label="主题">
                <el-input v-model="name" maxlength="30" show-word-limit></el-input>
              </el-form-item>
              <el-form-item label="描述">
                <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    placeholder="请输入内容"
                    v-model="description"
                    maxlength="200" show-word-limit>
                </el-input>
              </el-form-item>
              <el-form-item label="标签">
                <el-input v-model="tags" clearable></el-input>
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
              <el-form-item align="center">
                <el-button @click="createPropose" type="primary">发布</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>
        <br>
      </el-col>
      <el-col :xs="24" :sm="6">
        <br>
        <h2>发布指南</h2>
        <el-collapse accordion>
          <el-collapse-item title="倡议-主题 Topic">
            请用精简的语言描述您发布的议题，不超过
            <mark>30字</mark>
          </el-collapse-item>
          <el-collapse-item title="倡议-描述 Description">
            补充您的议题内容，并确保问题描述清晰直观，不超过
            <mark>200字</mark>
          </el-collapse-item>
          <el-collapse-item title="倡议-标签 Tags">
            填写一个或者多个合适的标签，用
            <mark>"空格"</mark>
            隔开，每个标签不超过
            <mark>20个字</mark>
          </el-collapse-item>
        </el-collapse>
      </el-col>
      <br>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "CreatePropose",
  data() {
    return {
      name: '',
      description: '',
      tags: '',
      checkCode: '',
      img_checkCode_src: '/api/tflz/utils/getCheckCode?' + Math.random()
    }
  },
  created() {
    if (this.$store.getters.getUserInfo.id < 1) {
      this.$router.push('/login');
    }
  },
  methods: {
    goBack() {
      this.$router.back();
    },
    getCheckCode() {
      this.img_checkCode_src = "/api/tflz/utils/getCheckCode?" + Math.random();
    },
    createPropose() {
      var proposeInfo = {
        uid: this.$store.getters.getUserInfo.id,
        name: this.name,
        description: this.description,
        tags: this.tags,
        checkCode: this.checkCode
      };
      var _this = this;
      _this.axios.post('/api/tflz/propose/createPropose', proposeInfo)
          .then(function (resp) {
            if (resp.data.isSucceed) {
              _this.$router.push("/community");
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
    }
  }
}
</script>

<style scoped>
.table {
  height: 50%;
  width: 80%;
  margin-left: 10%;
}
</style>