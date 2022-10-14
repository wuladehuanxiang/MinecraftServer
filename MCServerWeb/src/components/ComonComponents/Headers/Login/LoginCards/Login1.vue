<template>
  <div>
    <div class="card">
      <div class="cardLeft"></div>
      <div class="cardRight">
        <Icon
          type="ios-close-circle-outline"
          style="float: right"
          size="30"
          @click="closeLoginPage()"
        />
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
import bus from "../../../../../js/bus.js";

import Interworking from "../../../../../js/utils/Interworking.js";
import { SysUser } from "../../../../../js/object.js";
export default {
  name: "LoginAndRegist1",
  mounted: function () {
    //判断是否登陆
  },

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
    closeLoginPage: function () {
      bus.$emit("closeLoginPage");
    },

    login: function () {
      var user = {
        account: this.User.account,
        password: this.User.password,
      };
      console.log(JSON.stringify(user));
      Interworking.verification("SysUser", JSON.stringify(user))
        .then((value) => {
          if (value.data.data == "密码错误或此用户不存在") {
            this.$Notice.info({
              title: "登陆失败",
              desc: "密码错误或此用户不存在",
            });
          } else {
            if (value.data.data.tokenValue) {
              localStorage.setItem("satoken", value.data.data.tokenValue);
              bus.$emit("closeLoginPage");
              bus.$emit("login");
            }
            //若是管理员则需要让图鉴部分编辑部分全部显示
            Interworking.request("SysUser", JSON.stringify(user), 1, 1)
              .then((value) => {
                if (value.data.data) {
                  if (
                    value.data.data[0].isadmin &&
                    value.data.data[0].isadmin == "1"
                  ) {
                    bus.$emit("isadmin");
                  }
                }
              })
              .catch(function (error) {
                console.log(error);
              });
          }
        })
        .catch(function (error) {});
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
  position: fixed;
  background: white;
  min-width: 500px;
  min-height: 400px;
  margin-left: 25vw;
  margin-top: -30vh;
  width: 50vw;
  height: 50vh;
  z-index: 4;
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