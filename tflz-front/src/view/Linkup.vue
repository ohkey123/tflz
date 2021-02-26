<template>
  <div id="linkup">
    <el-row>
      <div align="center">
        <h1>排行榜</h1>
        <el-table
            :data="players"
            stripe
            style="width: 100%">
          <el-table-column
              type="index"
              style="width: 25%">
          </el-table-column>
          <el-table-column
              prop="player.name"
              label="用户"
              style="width: 25%">
          </el-table-column>
          <el-table-column
              prop="score"
              label="分数"
              style="width: 25%">
          </el-table-column>
          <el-table-column
              prop="timeCreate"
              label="时刻"
              style="width: 25%">
          </el-table-column>
        </el-table>
      </div>
    </el-row>

    <el-row>
      <br>
      <div align="center">
        <el-button @click="toGame">进入游戏</el-button>
      </div>
      <br>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Linkup",
  data() {
    return {
      players: []
    }
  },
  created() {
    this.getList();
  },
  methods: {
    toGame() {
      if (this.$store.getters.getUserInfo.id < 1) {
        this.$router.push('/login');
        return;
      }
      this.$router.push('/game');
    },
    getList() {
      var _this = this;
      _this.axios.post('/api/tflz/game/listPlayer')
          .then(function (resp) {
            _this.players = resp.data;
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