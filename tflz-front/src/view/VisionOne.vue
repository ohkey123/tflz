<template>
  <div id="visionOne">
    <el-row>
      <el-col :md="12" :xl="6">
        <div ref="map" style="height: 500px"></div>
      </el-col>
      <el-col :md="12" :xl="6">
        <div ref="bar" style="height: 500px"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import 'echarts/map/js/china';
import 'echarts/lib/chart/bar';

let echarts = require('echarts/lib/echarts');

export default {
  name: "VisionOne",
  data() {
    return {};
  },
  methods: {
    drawChart_map() {

      function randomData() {
        return Math.round(Math.random() * 500);
      }

      let mapData = [
        {name: '北京', value: '100'}, {name: '天津', value: randomData()},
        {name: '上海', value: randomData()}, {name: '重庆', value: randomData()},
        {name: '河北', value: randomData()}, {name: '河南', value: randomData()},
        {name: '云南', value: randomData()}, {name: '辽宁', value: randomData()},
        {name: '黑龙江', value: randomData()}, {name: '湖南', value: randomData()},
        {name: '安徽', value: randomData()}, {name: '山东', value: randomData()},
        {name: '新疆', value: randomData()}, {name: '江苏', value: randomData()},
        {name: '浙江', value: randomData()}, {name: '江西', value: randomData()},
        {name: '湖北', value: randomData()}, {name: '广西', value: randomData()},
        {name: '甘肃', value: randomData()}, {name: '山西', value: randomData()},
        {name: '内蒙古', value: randomData()}, {name: '陕西', value: randomData()},
        {name: '吉林', value: randomData()}, {name: '福建', value: randomData()},
        {name: '贵州', value: randomData()}, {name: '广东', value: randomData()},
        {name: '青海', value: randomData()}, {name: '西藏', value: randomData()},
        {name: '四川', value: randomData()}, {name: '宁夏', value: randomData()},
        {name: '海南', value: randomData()}, {name: '台湾', value: randomData()},
        {name: '香港', value: randomData()}, {name: '澳门', value: randomData()}
      ];

      const option = {
        backgroundColor: '#FFFFFF',
        title: {
          text: '中国垃圾分类数据',
          subtext: 'power by 天府绿洲',
          x: 'center'
        },
        tooltip: {
          trigger: 'item'
        },

        //左侧小导航图标
        visualMap: {
          show: true,
          x: 'left',
          y: 'center',
          splitList: [
            {start: 500, end: 600}, {start: 400, end: 500},
            {start: 300, end: 400}, {start: 200, end: 300},
            {start: 100, end: 200}, {start: 0, end: 100},
          ],
          color: ['#5475f5', '#9feaa5', '#85daef', '#74e2ca', '#e6ac53', '#9fb5ea']
        },

        //配置属性
        series: [{
          name: '数据',
          type: 'map',
          mapType: 'china',
          roam: false,
          label: {
            normal: {
              show: true  //省份名称
            },
            emphasis: {
              show: false
            }
          },
          data: mapData  //数据
        }]
      };

      echarts.init(this.$refs.map).setOption(option);
    },
    drawChart_bar() {
      const option = {
        title: {
          text: ' ',
          subtext: '公民个人丢弃垃圾分类平均数据',
          x: 'center'
        },
        angleAxis: {},
        radiusAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四'],
          z: 10
        },
        polar: {},
        series: [{
          type: 'bar',
          data: [1, 2, 3, 4, 5],
          coordinateSystem: 'polar',
          name: '厨余垃圾',
          stack: 'a'
        }, {
          type: 'bar',
          data: [2, 4, 6, 8],
          coordinateSystem: 'polar',
          name: '可回收物',
          stack: 'a'
        }, {
          type: 'bar',
          data: [1, 2, 3, 4, 5],
          coordinateSystem: 'polar',
          name: '有害垃圾',
          stack: 'a'
        }, {
          type: 'bar',
          data: [1, 2, 3, 4],
          coordinateSystem: 'polar',
          name: '其他垃圾',
          stack: 'a'
        }],
        legend: {
          show: true,
          data: ['厨余垃圾', '可回收物', '有害垃圾', '其他垃圾']
        }
      };

      echarts.init(this.$refs.bar).setOption(option);
    },
  },
  mounted() {
    this.drawChart_map()
    this.drawChart_bar();
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #67C23A;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: rgb(225, 243, 216);
}

.el-carousel__item:nth-child(2n+1) {
  background-color: rgb(240, 249, 235);
}
</style>