<template>
  <div id="register">
    <div class="table">
      <el-card shadow="always">
        <el-form>
          <el-form-item label="电子邮件">
            <el-input v-model="email" maxlength="320" clearable></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="username" minlength="1" maxlength="12" show-word-limit></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input-number size="small" v-model="age" :min="10" :max="99" :step="1" step-strictly></el-input-number>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="sex" label="0">男</el-radio>
            <el-radio v-model="sex" label="1">女</el-radio>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="password" minlength="8" maxlength="15" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="rpassword" minlength="8" maxlength="15" show-password></el-input>
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
            <el-button @click="register" type="primary">注册</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      username: '',
      email: '',
      age: '21',
      sex: '0',
      password: '',
      rpassword: '',
      checkCode: '',
      img_checkCode_src: '/api/tflz/utils/getCheckCode?' + Math.random()
    }
  },
  methods: {
    getCheckCode() {
      this.img_checkCode_src = '/api/tflz/utils/getCheckCode?' + Math.random();
    },
    register() {
      if (this.rpassword != this.password) {
        alert("两次密码输入不一致！");
        return;
      }

      var registerInfo = {
        name: this.username,
        email: this.email,
        age: this.age,
        sex: this.sex == '0' ? false : true,
        password: this.password,
        checkCode: this.checkCode
      }

      var _this = this;

      _this.axios.post('/api/tflz/user/register', registerInfo)
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