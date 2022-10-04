<template>
  <div>
    <div class="card">
      <div class="cardLeft"></div>
      <div class="cardRight">
        <!-- 账户名 -->
        <Input
          prefix="ios-contact"
          placeholder="账户名"
          class="input"
          v-model="User.account"
        />
        <!-- 密码 -->
        <Input
          prefix="ios-search"
          placeholder="密码"
          type="password"
          class="input"
          v-model="User.password"
        />
        <!-- 是否自动登陆 -->
        <div class="demo-auto-login">
          <Checkbox v-model="autoLogin" size="large">自动登录</Checkbox>
          <a>忘记密码</a>
        </div>

        <Button type="primary" @click="login" class="input">登陆</Button>
      </div>
    </div>
  </div>
</template>


<script>
import router from "../../../../../router.js";

export default {
  name: "LoginAndRegist1",
  mounted: function () {},

  computed: {
    // 密码强度提示文案等
    passwordTip() {
      let strong = "强";
      let className = "strong";
      let percent = this.passwordLen > 10 ? 10 : this.passwordLen;
      let color = "#19be6b";

      if (this.passwordLen < 6) {
        strong = "太短";
        className = "low";
        color = "#ed4014";
      } else if (this.passwordLen < 10) {
        strong = "中";
        className = "medium";
        color = "#ff9900";
      }

      return {
        strong,
        class:
          "demo-register-tip-" + this.passwordLen < 6
            ? "low"
            : this.passwordLen < 10
            ? "medium"
            : "strong",
        percent: percent * 10,
        color,
      };
    },
  },
  components: {},
  methods: {
    login: function () {
      router.push("/MainPage");
    },
    onPasswordChange: function (val) {
      this.passwordLen = val.length;
    },
  },
  data() {
    return {
      passwordLen: 0,
      autoLogin: true,
      User: {
        account: "",
        password: "",
      },
      // 密码长度，在密码强度提示时作为判断依据
    };
  },
};
</script>

<!-- transform: rotate(40deg); -->

<style scoped>

.card {
  min-width: 500px;
  min-height: 400px;
  width: 50vw;
  height: 50vh;
}
.cardLeft {
  width: 25vw;
  height: 50vh;
  min-width: 250px;
  min-height: 400px;
  float: left;
  border-radius: 10px 0 0 10px;
}
.cardRight {
  width: 25vw;
  height: 50vh;
  min-width: 250px;
  min-height: 400px;
  float: left;
}
.demo-register {
  width: 400px;
  margin: 0 auto !important;
}
.demo-register .ivu-poptip,
.demo-register .ivu-poptip-rel {
  display: block;
}
.demo-register-tip {
  text-align: left;
}
.demo-register-tip-low {
  color: #ed4014;
}
.demo-register-tip-medium {
  color: #ff9900;
}
.demo-register-tip-strong {
  color: #19be6b;
}
.demo-register-tip-title {
  font-size: 14px;
}
.demo-register-tip-desc {
  white-space: initial;
  font-size: 14px;
  margin-top: 6px;
}

.demo-auto-login {
  margin-left: 7.5%;
  margin-right: 7.5%;
  margin-top: 5%;
  text-align: left;
}
.demo-auto-login a {
  float: right;
}
.innercardRegister {
  min-height: 100%;
  margin-bottom: 15%;
  padding: 1vh;
}
.innercardLogin {
  margin-top: 15%;
  min-height: 100%;
  margin-bottom: 15%;
  padding: 1vh;
}

.input {
  width: 85%;
  margin-top: 5%;
  margin-left: 7.5%;
}
</style>