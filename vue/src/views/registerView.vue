<template>
  <div class="login">
    <div class="login-box">
      <div class="login-left">
        <h4 class="title">客户管理系统</h4>
        <div class="log-img">
          <img src="@/assets/img/logo1.png" alt="logo" />
        </div>
      </div>
      <div class="login-right">
        <p class="register"><span>已经有账号？去</span><a href="#" @click="$router.push('/login')" style="font-size: 14px; color: #027bf1;">登录</a></p>
        <a href=""></a>
        <h4 class="title name">CRM 注册：岁满168</h4>
        <div class="form-data">
          <el-input v-model="username"  class="input-info" placeholder="请输入用户名" />
          <el-input v-model="password"  type="password" class="input-info" placeholder="请输入密码" />
          <el-input v-model="newpassword"  type="password" class="input-info" placeholder="请输入确认密码" />
          <el-button class="logins" @click="register">注 册</el-button>
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      users:[],
      username: "",
      password: "",
      newpassword:""
    };
  },
  methods: {
    register() {
      if(this.username == "" || this.password == ""){
        this.$message.error("用户名或密码不能为空")
        return
      }
      if(this.newpassword == ""){
        this.$message.error("确认密码不能为空")
        return
      }
      if(this.password != this.newpassword){
        this.$message.error("两次输入密码不一致")
        return
      }
      this.users = {
        username:this.username,
        password:this.password
      }
      // 后台验证
      this.$request.post("/register",this.users).then(res=>{
        if(res.code == 200){
          this.$message.success("注册成功")
          this.$router.push("/login")
        }else{
          this.$message.error(res.msg)
        }
      })
    }
  }
}

</script>


<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image:url("@/assets/img/bg_pk133.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.login-box{
  display: flex;
  width: 700px;
  min-height: 500px;
}

.login-left{
  flex: 1.285;
  padding: 15px;
  background: #4f84f5;
}

.title{
  color: #fff;
  line-height:1.8;
  text-align: center;
  margin-bottom:40px;
  font-size: 28px;
  font-weight: 400;
}

.name{
  color: #000;
}


.log-img{
  display: flex;
  height: 300px;
  align-items: center;
  justify-content: center;
}

.log-img>img{
  width: 90%;
}

.form-data{
  margin: 0 10px;
  overflow: hidden;
  padding: 20px 20px 15px;
  margin-top: -30px;
}

.login-right{
  position: relative;
  top:0;
  left: 0;
  flex: 2;
  padding: 15px;
  text-align: center;
  background: #fff;
  border-top-right-radius: 3px;
  border-bottom-right-radius:3px
}
.input-info{
  margin-bottom:20px;
  height:40px !important;
}
.options{
  display: flex;
  justify-content:flex-start;
}

.el-checkbox{
  margin-right:10px !important;
}


::v-deep(.el-input--small .el-input__inner) {
  height: 40px !important;
  line-height: 40px !important;
}


.logins:hover{
  background-color: #027bf1 !important; /* 设置背景颜色为透明 */
  border-color: inherit; /* 如果需要保持边框颜色一致 */
  color: #fff; /* 如果需要保持文本颜色一致 */
}

.wx-logo:hover{
  background-color: #b5dd9b !important; /* 设置背景颜色为透明 */
  border-color: inherit; /* 如果需要保持边框颜色一致 */
  color: #fff; /* 如果需要保持文本颜色一致 */
}

.logins{
  width: 100%;
  background: #027bf1;
  color: #FFFFFF;
  padding: 12px;
  margin-top: 25px;
  font-size: 16px;
  word-spacing: 1em;
}
.other{
  color: #9da9bb;
  font-size: 12px;
  position: relative;
  top: -10px;
  margin:20px 0;
}
.other::before {
  content: '';
  width: calc(50% - 70px);
  height: 1px;
  background-color: #d8e2ef;
  position: absolute;
  right: 30px;
  top: 11px;
}
.other::after {
  content: '';
  width: calc(50% - 70px);
  height: 1px;
  background-color: #d8e2ef;
  position: absolute;
  left: 30px;
  top: 11px;
}
.wx-logo{
  width:100%;
  background: #ffffff;
  color: #b5dd9b;
  padding: 12px;
  border: 1px solid #b5dd9b;
  margin-top: -10px;
  margin-bottom: 10px;
  font-size: 16px;
  word-spacing: 1em;
}
.register{
  position: absolute;
  top: 0px;
  right: 10px;
}
.register>a{
  color: #9da9bb;
  font-size: 12px;
}
</style>