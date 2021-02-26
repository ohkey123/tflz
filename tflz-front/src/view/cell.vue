<template>
  <td :class="classNames">
    <div v-if="isLine" :class="lineClass"></div>
    <el-image v-if="!isBlank" :src="this.className"></el-image>
    <br>
    点我
  </td>
</template>
<script>
export default {
  name: 'cell',
  props: ['isSelected', 'isBlank', 'className', 'lineClass', 'isLine'],
  computed: {
    classNames() {
      return {
        'selected': this.isSelected, // 选中
        'blank': this.isBlank // 空白
      }
    }
  }
}
</script>
