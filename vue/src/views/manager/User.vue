<template>
  <div>
    <template>
      <el-table :data="userInfo" style="width: 100%">
        <el-table-column prop="id" label="序号" width="180"/>
        <el-table-column prop="username" label="用户名" width="180"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="phone" label="手机号"/>
        <el-table-column prop="email" label="邮箱"/>
        <el-table-column prop="address" label="地址"/>
        <el-table-column prop="avatar" label="头像"/>
      </el-table>
    </template>
  </div>
</template>

<script>
export default {
  name: "",
  data: {
    return: {
      user: {}
    }
  },
  methods: {
    userInfo() {
      this.$request.get("/selectAll").then(res => {
        this.user = res.data;
      })
    }
  }
}
</script>

<style scoped>

</style>