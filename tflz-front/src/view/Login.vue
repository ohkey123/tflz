<template>
  <div id="login">
    <div class="table">
      <el-card shadow="always">
        <el-form>
          <el-form-item label="电子邮件">
            <el-input v-model="email" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="password" show-password></el-input>
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
            <el-button @click="login" type="primary">登陆</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      email: '',
      password: '',
      checkCode: '',
      img_checkCode_src: '/api/tflz/utils/getCheckCode?' + Math.random()
    }
  },
  methods: {
    getCheckCode() {
      this.img_checkCode_src = "/api/tflz/utils/getCheckCode?" + Math.random();
    },
    login() {
      var loginRequest = {
        email: this.email,
        password: this.password,
        checkCode: this.checkCode
      }

      var _this = this;

      this.axios.post("/api/login", loginRequest)
          .then(function (resp) {
            if (resp.data.isSucceed == true) {
              _this.$store.commit('updateUserInfo', resp.data.userInfo);
              window.location.href = '/';
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
  width: 50%;
  margin-left: 25%;
}
</style>