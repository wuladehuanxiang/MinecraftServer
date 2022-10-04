<template>
  <div>
    <div class="loginLink">
      <i style="font-size: large; font-style: normal; float: left"
        >欢迎来到终焉，登陆以加入聊天！</i
      >
      <Button
        type="primary"
        size="large"
        style="float: left; margin-left: 20%"
        @click="showLoginCard = true"
        >登录</Button
      >

      <Button type="warning" size="large" style="float: left; margin-left: 1%"
        >注册</Button
      >
      <!-- <div class="otherMSG">OtherMSG</div> -->
    </div>
    <Modal
      v-model="showLoginCard"
      footer-hide
      :transfer="false"
      width="55vw"
      id="LoginCard"
    >
      <LoginAndRegist1Vue></LoginAndRegist1Vue>
    </Modal>
  </div>
</template>


<script>
import LoginAndRegist1Vue from "./LoginCards/Login1.vue";

export default {
  name: "LoginPage1",
  mounted: function () {},
  components: { LoginAndRegist1Vue },
  methods: {},
  data() {
    return {
      showLoginCard: false,
    };
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
.ivu-modal-content {
  background: black;
}
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