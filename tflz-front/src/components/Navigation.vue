<template>
  <div id="navigation">
    <el-menu @open="handleOpen" @close="handleClose" align="center" router>
      <el-submenu index="">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span slot="title">数据展示</span>
        </template>

        <el-menu-item-group>
          <span slot="title">分组1</span>
          <el-menu-item index="/visionOne">展示项1</el-menu-item>
          <el-menu-item index="/visionTwo" disabled>展示项2</el-menu-item>
        </el-menu-item-group>

        <el-menu-item-group>
          <span slot="title">分组2</span>
          <el-menu-item index="1-3" disabled>展示项3</el-menu-item>
        </el-menu-item-group>

        <el-submenu index="1-4" disabled>
          <span slot="title">展示项4</span>
          <el-menu-item index="1-4-1">条件1</el-menu-item>
          <el-menu-item index="1-4-2">条件2</el-menu-item>
        </el-submenu>
      </el-submenu>

      <el-menu-item index="/Linkup">
        <i class="el-icon-menu"></i>
        <span slot="title">小游戏</span>
      </el-menu-item>

      <el-menu-item index="/community">
        <i class="el-icon-document"></i>
        <span slot="title">绿洲社区</span>
      </el-menu-item>

      <el-menu-item>
        <span slot="title">
          <i class="el-icon-setting el-icon--left"></i>
          <el-button type="text" @click="contactUs">联系我们</el-button></span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  data() {
    return {};
  },
  methods: {
    changeState() {
      this.isCollapse = !this.isCollapse;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    contactUs() {
      this.$alert('内测阶段，仅限TFLZ开发团队测试使用（资质已绑定，请使用您的QQ邮箱注册会员） —— NL.Yang', '天府绿洲 TFLZ Alpha 1.0', {
        confirmButtonText: '确定'
      });
    }
  }
}
</script>

<style scoped>

</style>