<template>
  <div id="header">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" router>
      <el-menu-item index="/">天府绿洲·首页</el-menu-item>
      <el-menu-item index="/register" v-if="!isLogin" style="float: right">注册</el-menu-item>
      <el-menu-item index="/login" v-if="!isLogin" style="float: right">登陆</el-menu-item>
      <el-menu-item index="/personal" v-if="isLogin" style="float: right">
        <el-badge value="new" :hidden="isHidden">欢迎您：{{ this.$store.getters.getUserInfo.name }}</el-badge>
      </el-menu-item>
      <el-menu-item v-if="isLogin" @click="logout" style="float: right">
        退出
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      activeIndex: '/',
      isLogin: this.$store.getters.getUserInfo.id != -1 ? true : false,
      isHidden: this.$store.getters.getUserInfo.notice < 1
    }
  },
  created() {
    this.updateUserInfoFromRemote();
  },
  methods: {
    logout() {
      this.$store.commit("logout");
      location.reload();
    },
    updateUserInfoFromRemote() {
      var _this = this;
      window.setInterval(function () {
        if (_this.$store.getters.getUserInfo.id < 1) {
          return;
        }
        _this.axios.post('/api/tflz/user/getUserInfo/' + _this.$store.getters.getUserInfo.id)
            .then(function (resp) {
              _this.$store.commit('updateUserInfo', resp.data);
              _this.isHidden = resp.data.notice < 1;
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
      }, 3000);
    }
  }
}
</script>

<style scoped>
</style>