<template>
  <div>
    <Header1 style="float: left"></Header1>
    <LoginCard style="float: left; margin-left: 25vw; margin-top: 15vh">
    </LoginCard>
    <Footer style="float: left; margin-top: 20vh"></Footer>
  </div>
</template>


<script>
import login from "../../js/login.js";
import background from "../../js/background.js";
import LoginCard from "./LoginCards/LoginAndRegist1.vue";
import LoginCard1 from "./LoginCards/LoginAndRegist2.vue";
import Footer from "../Footers/Footer1.vue";
import Header1 from "../Headers/Header1.vue";

export default {
  name: "LoginPage1",
  mounted: function () {
    //获取随机背景图片
    var img = login.getRandomVerBackImg();
    login.setBackground(img);
    background.InitBackGround(img);
  },
  components: { LoginCard, LoginCard1, Footer, Header1 },
  methods: {},
  data() {
    return {};
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
</style>