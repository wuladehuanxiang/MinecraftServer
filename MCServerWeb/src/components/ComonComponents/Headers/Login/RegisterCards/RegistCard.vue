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
        <Input placeholder="账户名" class="input" v-model="User.account" />
        <Input
          type="password"
          class="input"
          placeholder="至少6位密码，区分大小写"
          @on-change="onPasswordChange(User.password)"
          v-model="User.password"
        />
        <Input class="input" type="password" placeholder="确认密码" />

        <Poptip trigger="focus" placement="right" width="240" class="input">
          <div class="demo-register-tip">
            <div class="demo-register-tip-title" :class="passwordTip.class">
              强度：{{ passwordTip.strong }}
            </div>
            <Progress
              :percent="passwordTip.percent"
              hide-info
              :stroke-width="6"
              :stroke-color="passwordTip.color"
            />
            <div class="demo-register-tip-desc">
              请至少输入 6 个字符。请不要使用容易被猜到的密码。
            </div>
          </div>
        </Poptip>

        <Button type="primary" @click="register()" class="input">注册</Button>
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
    closeLoginPage: function () {
      bus.$emit("closeLoginPage");
    },

    register: function () {
      var user = new SysUser();
      user.account = this.User.account;
      user.password = this.User.password;

      console.log("is registing");
      Interworking.create("SysUser", JSON.stringify(user))
        .then((value) => {
          console.log(value);
          if (value.data.data == "1") {
            this.$Notice.info({
              title: "创建成功",
              desc: "欢迎加入终焉",
            });
            //注册成功了
            bus.$emit("closeLoginPage");
            bus.$emit("login");

          } else if (value.data.data == "此用户已经存在") {
            this.$Notice.info({
              title: "创建失败",
              desc: "这个用户已经存在",
            });
          }else
          {
            this.$Notice.info({
              title: "error",
              desc: value.data.data,
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
ivu-modal-mask {
  background: rgba(0, 0, 0, 0.5);
}
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
  background: rgb(255, 255, 255, 0.8);
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