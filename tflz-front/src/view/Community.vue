<template>
  <div id="community">
    <el-row>
      <h1 style="background-color: #C8EAD1; color: #397D54">@绿洲社区</h1>
    </el-row>

    <el-row>
      <el-col :xs="24" :sm="18">
        <div>
          <p style="color: #397D54">最新</p>
        </div>
        <br>

        <div>
          <div v-for="(propose, index) in propose_list" :key="index+'propose'">
            <el-card class="propose_card" shadow="hover">
              <h2>
                {{ propose.name }}
                <el-link type="primary" @click="getProposeDetails(propose.id)">点击阅读</el-link>
              </h2>
              <p style="color: grey">{{ propose.description }}</p>
              <small>创建者：{{ propose.builder.name }}</small>

              <br>
              <div v-for="(tag, index) in propose.tags" :key="index+'tag'">
                <el-tag style="float: left; margin-left: 1px;">{{ tag.name }}</el-tag>
              </div>
              <br>

              <br>
              <el-divider content-position="left">
                创建时间：{{ propose.timeCreate }}
                <el-divider direction="vertical"></el-divider>
                最后回复时间：{{ propose.timeReply }}
              </el-divider>
            </el-card>
            <br>
          </div>
        </div>

        <div align="center">
          <el-pagination
              @current-change="handleCurrentChange"
              @prev-click="handlePrevClick"
              @next-click="handleNextClick"
              :page-size="page_size"
              :total="total_num"
              :current-page="page_cur"
              layout="total, prev, pager, next, jumper"
              prev-text="上一页"
              next-text="下一页"
              background
              hide-on-single-page>
          </el-pagination>
        </div>
      </el-col>
      <el-col :xs="24" :sm="6">
        <br>
        <el-row>
          <div align="center">
            <router-link to="/createPropose">
              <el-button type="primary" plain>发布倡议</el-button>
            </router-link>
          </div>
          <div align="center">
            <h2>热度排行榜</h2>
            <p style="color: grey">敬请期待...</p>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Community",
  data() {
    return {
      propose_list: [],
      page_cur: 1,
      page_size: 0,
      total_num: 0
    }
  },
  created() {
    this.getProposeList(1);
  },
  methods: {
    handleCurrentChange(cur) {
      this.getProposeList(cur);
    },
    handlePrevClick() {
      if (this.page_cur > 1) {
        this.getProposeList(this.page_cur - 1);
      }
    },
    handleNextClick() {
      if (this.page_cur < this.total_pages) {
        this.getProposeList(this.page_cur + 1);
      }
    },
    getProposeList(reqNum) {
      var _this = this;

      _this.axios.get('/api/tflz/propose/getList/' + reqNum)
          .then(function (resp) {
            _this.propose_list = resp.data.records;
            _this.page_cur = resp.data.pageInfo.current;
            _this.page_size = resp.data.pageInfo.size;
            _this.total_num = resp.data.pageInfo.total;
            _this.total_pages = resp.data.pageInfo.pages;
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
    },
    getProposeDetails(reqNum) {
      this.$router.push({
        path: '/propose',
        query: {
          id: reqNum
        }
      });
    }
  }
}
</script>

<style scoped>

</style>