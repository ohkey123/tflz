<template>
  <div id="home">

    <el-row>
      <div class="block">
        <el-carousel height="600px" align="center">
          <el-carousel-item>
            <el-image :src="img_home_1" :fit="fit_team" style="width: 100%; height: 100%"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="img_home_2" :fit="fit_team" style="width: 100%; height: 100%"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="img_home_3" :fit="fit_team" style="width: 100%; height: 100%"></el-image>
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-row>
    <br><br>
    <el-row>
      <el-col :xs="24" :sm="12" :md="16">
        <div class="team" align="center">
          <br>
          <el-avatar :size="200" :src="img_team_url" :fit="fit_team"></el-avatar>
          <h1>我们的团队</h1>
          <hr style="width: 100px">
          <p>指导老师：赵淑贤</p>
          <p>来自：四川大学锦江学院计算机学院</p>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="8">
        <div class="team">
          <el-card class="card" shadow="hover">
            朱瀚锐
          </el-card>
          <el-card class="card" shadow="hover">
            文 &nbsp; &nbsp; 娇
          </el-card>
          <el-card class="card" shadow="hover">
            王一行
          </el-card>
          <el-card class="card" shadow="hover">
            张华俊
          </el-card>
          <el-card class="card" shadow="hover">
            喻鼎明
          </el-card>
          <el-card class="card" shadow="hover">
            史 &nbsp; &nbsp; 洋
          </el-card>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      img_team_url: require('../assets/img/home/team.png'),
      img_home_1: require('../assets/img/home/home1.jpg'),
      img_home_2: require('../assets/img/home/home2.jpg'),
      img_home_3: require('../assets/img/home/home3.jpg'),
      fit_team: 'fill'
    }
  },
  created() {
    this.$alert('内测阶段，仅限TFLZ开发团队测试使用（资质已绑定，请使用您的QQ邮箱注册会员）\n 由于服务器带宽和优化问题，访问部分页面时可能会出现卡顿，目前正解决中... —— NL.Yang', '天府绿洲 TFLZ Alpha 1.0', {
      confirmButtonText: '确定'
    });
  }
}
</script>

<style scoped>
.team {
  height: 400px;
}

.card {
  border-radius: 30px;
  border: 1px solid #dcdfe6;
  margin-top: 5px;
}
</style>