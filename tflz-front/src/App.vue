<template>
  <div id="app">

    <el-row>
      <Header></Header>
    </el-row>

    <el-row>
      <!-- 侧边栏 -->
      <el-col :xs="24" :sm="4">
        <Navigation></Navigation>
      </el-col>
      <!-- 主页 -->
      <el-col :xs="24" :sm="20">
        <el-row>
          <router-view></router-view>
        </el-row>

        <!-- 页尾 -->
        <el-row>
          <p align="center">Copyright © 2020 天府绿洲</p>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Header from "@/components/Header";
import Navigation from "@/components/Navigation";

export default {
  name: 'App',
  components: {
    Header,
    Navigation
  }
}
</script>

<style>

</style>
