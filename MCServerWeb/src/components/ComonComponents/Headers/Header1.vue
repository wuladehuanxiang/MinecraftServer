<template>
  <div class="head">
    <!-- 顶部选择部分 -->
    <div class="headCard">
      <div
        id="Home"
        class="nva-item"
        style="margin-left: 0%"
        @click="changeViewPage('Home')"
        ref="Home"
      >
        <Icon type="ios-home" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          主页
        </div>
      </div>

      <div class="nva-item" @click="changeViewPage('Forum')" ref="Forum">
        <Icon
          type="ios-chatbubbles"
          size="50"
          style="width: 50%; float: left"
        />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          论坛
        </div>
      </div>
      <div class="nva-item" @click="changeViewPage('Server')" ref="Server">
        <Icon type="ios-list-box" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          服务器
        </div>
      </div>

      <div class="nva-item" @click="changeViewPage('Atlas')" ref="Atlas">
        <Icon type="ios-bookmarks" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          图鉴
        </div>
      </div>
      <div class="nva-item" @click="changeViewPage('Rules')" ref="Rules">
        <Icon type="ios-settings" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          规则
        </div>
      </div>
      <div class="nva-item" @click="changeViewPage('Donate')" ref="Donate">
        <Icon type="ios-cash" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          捐赠
        </div>
      </div>
      <div class="nva-item">
        <Icon type="ios-add" size="50" style="width: 50%; float: left" />
        <div
          style="
            height: 100%;
            width: 50%;
            float: left;
            text-align: center;
            padding-top: 15%;
          "
        >
          更多
        </div>
      </div>
    </div>
    <!-- LOGO部分 -->
    <div class="LogoCard">
      <div
        style="height: 30vh; width: 45vw; float: left; margin-left: 12.5vw"
        class="LogoCardItem"
      >
        <img
          src="../../../lib/images/logo.png"
          alt=""
          style="height: 30vh; width: 45vw"
        />
      </div>
      <div
        class="LogoCardItem"
        style="
          height: 8vh;
          width: 70vw;
          float: left;
          margin-top: -5vh;
          margin-bottom: 1vh;
        "
      >
        <div class="serverMSG">
          <p style="color: #1684a2; font-size: 20px; text-align: right">
            服务器在线人数: {{ onlinePlayers }}
          </p>
          <p style="color: #afa6a6; text-align: right; font-size: 15px">终焉</p>
        </div>
        <div style="float: left; width: 5%; height: 100%; margin-top: 1vh">
          <svg
            style="background: transparent"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4,2H20A2,2 0 0,1 22,4V20A2,2 0 0,1 20,22H4A2,2 0 0,1 2,20V4A2,2 0 0,1 4,2M6,6V10H10V12H8V18H10V16H14V18H16V12H14V10H18V6H14V10H10V6H6Z"
            ></path>
          </svg>
        </div>
      </div>
    </div>
    <!-- 登陆栏部分 -->
    <LoginPage></LoginPage>
  </div>
</template>


<script>
import bus from "../../../js/bus.js";
import interworking from "../../../js/utils/Interworking.js";
import LoginPage from "./Login/LoginPage1.vue";

import { BmCategory } from "../../../js/object";

export default {
  name: "Header1",
  mounted: function () {},
  components: { LoginPage },
  methods: {
    changeViewPage: function (data) {
      //传递信息给主页面 即父组件
      bus.$emit("changeViewPage", data);
      //需要更改样式 即选中的高亮 未选中的不高亮

      //取消原来的高亮
      this.$refs[this.showingContet].style.opacity = 0.5;
      //更改存储的正在看的页面ID
      this.showingContet = data;
      //高亮现在在看的页面
      this.$refs[data].style.opacity = 1;
    },
  },
  data() {
    return {
      onlinePlayers: "0",
      showingContet: "Home",
    };
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
.serverMSG {
  width: 50%;
  height: 100%;
  float: left;
  margin-top: 1%;
  margin-right: 0.5vw;
}

.nva-item {
  margin-left: 5%;
  height: 80%;
  width: 15%;
  font-size: 1vw;
  font-weight: bold;
  float: left;
  font-weight: 600;
  color: rgb(255, 255, 255);
  opacity: 0.5;
}

.nva-item:hover {
  opacity: 1;
}

.headCard {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 15vw;
  width: 70vw;
  height: 6vh;
  margin: auto;
}

.LogoCard {
  height: 25vh;
  width: 70vw;
  margin-left: 15vw;
  margin-top: 1vh;
}

.LogoCardItem {
  opacity: 0.75;
}

.LogoCardItem:hover {
  opacity: 1;
}

.head {
  background: rgb(0, 0, 0, 0.7);
}
</style>