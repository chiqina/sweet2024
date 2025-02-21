<template>
  <div>
    <el-card style="margin: 10px;width: 50%">
      <el-form :model="user" label-width="80px">
        <el-form-item label="原始密码" prop="password">
          <el-input type="password" v-model="info.password"/>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="info.newPassword"/>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="info.confirmPassword"/>
        </el-form-item>
        <div style="text-align: center;margin-bottom: 20px">
          <el-button type="primary" @click="save">确认更新</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>


<script>
export default {
  name: "password",
  data(){
    return{
      user:JSON.parse(localStorage.getItem('honey-user') || '{}'),
      info:{password:'',newPassword:'',confirmPassword:''},
    }
  },
  methods:{
    save(){
      if(this.info.password == ''){
        return this.$message.error("请输入密码")
      }
      if(this.info.newPassword == ''){
        return this.$message.error("请输入新密码")
      }
      if(this.info.confirmPassword == ''){
        return this.$message.error("请输入确认密码")
      }
      if(this.info.password !== this.user.password){
        return this.$message.error("原始密码不正确")
      }
      if(this.info.newPassword !== this.info.confirmPassword){
        return this.$message.error("两次输入的密码不一致")
      }
      this.user.password = this.info.newPassword;
      this.$request.put('/user/update',this.user).then(res=>{
          if(res.code == '200' ){
            this.$router.push('/login');
            this.$message.success("修改成功")
          }else{
            this.$message.error(res.data.msg)
          }
      })

    }
  }
}
</script>

<style scoped>

</style>