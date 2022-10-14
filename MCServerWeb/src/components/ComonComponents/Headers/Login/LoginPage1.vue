<template>
  <div>
    <div class="loginLink">
      <i style="font-size: large; font-style: normal; float: left"
        >欢迎来到终焉</i
      >
      <i v-show="!hasLogin" style="font-size: large; font-style: normal; float: left"
        >  ，登陆以加入聊天！</i
      >
    
      <Button
        type="primary"
        size="large"
        style="float: left; margin-left: 20%"
        @click="
          showLoginCard = true;
          showing = 'login';
        "
        v-show="!hasLogin"
        >登录</Button
      >

      <Button
        type="warning"
        size="large"
        style="float: left; margin-left: 1%"
        @click="
          showLoginCard = true;
          showing = 'register';
        "
        v-show="!hasLogin"
        >注册</Button
      >
      <!-- <div class="otherMSG">OtherMSG</div> -->
    </div>
    <div v-show="showLoginCard" width="55vw" class="LoginCard" id="LoginCard">
      <Login v-show="showing == 'login'"></Login>
      <Regist v-show="showing == 'register'"></Regist>
    </div>
  </div>
</template>


<script>
import Login from "./LoginCards/Login1.vue";
import Regist from "./RegisterCards/RegistCard.vue";
import Interworking from "../../../../js/utils/Interworking.js";
import bus from "../../../../js/bus.js";
import { copyFileSync } from "fs";
export default {
  name: "LoginPage1",
  mounted: function () {
    bus.$on("closeLoginPage", () => {
      //显示应该显示的部分
      this.showLoginCard = false;
    });
    bus.$on("login", () => {
      //显示应该显示的部分
      this.hasLogin = true;
    });
    
    Interworking.verification("BmCategory", JSON.stringify({}))
      .then((value) => {
        if (value.data.data == true) {
          this.hasLogin = true;
        } else {
          this.hasLogin = false;
          this.$Notice.info({
            title: "您的登陆已过期",
            desc: "请重新登陆",
          });
        }
      })
      .catch(function (error) {});
  },
  components: { Login, Regist },
  methods: {},
  data() {
    return {
      hasLogin: false,
      showLoginCard: false,
      showing: "login",
    };
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
.loginLink {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 10vh;
  width: 70vw;
  margin-left: 15vw;
  margin-top: 1vh;
  background: rgb(255, 255, 255, 0.2);
  font-weight: 600;
  color: rgb(255, 255, 255);
}
.otherMSG {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: aquamarine;
  opacity: 0.5;
}
</style>