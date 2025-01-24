<template>
  <div>
    <div style="box-shadow: 0 0 10px rgba(0,0,0,.1);padding: 10px 20px;border-radius: 5px; margin-bottom: 10px">
      早安，{{ user.name }}，祝你开心每一天
    </div>
    <div style="display: flex;">
      <el-card style="width: 50%;margin-right: 10px">
        <div slot="header" class="clearfix">
          <span>朱哥哥带你做毕设2024</span>
        </div>
        <div>
          2024毕业社开始了！朱哥哥带你手把手敲出来
          <div style="margin-top: 20px">
            <div style="margin: 20px 0"><strong>主题色</strong></div>
            <el-button disabled>按钮</el-button>
            <el-button type="primary" disabled>按钮</el-button>
            <el-button type="success" disabled>按钮</el-button>
            <el-button type="info" disabled>按钮</el-button>
            <el-button type="warning" disabled>按钮</el-button>
            <el-button type="danger" disabled>按钮</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>

</template>


<script>
export default {
  name: "Home",
  data() {
    return{
      user:JSON.parse(localStorage.getItem("honey-user") || "{}")
    }
  }
}
</script>
<style scoped>

</style>