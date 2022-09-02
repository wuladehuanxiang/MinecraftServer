<template>
  <div>
    <div class="card">
      <Tabs style="height: 85%" type="line">
        <!-- 登陆板块 -->
        <TabPane label="登陆" name="登陆" class="innercardLogin">
          <Input
            prefix="ios-contact"
            placeholder="账户名"
            class="input"
            v-model="User.account"
          />

          <Input
            prefix="ios-search"
            placeholder="密码"
            type="password"
            class="input"
            v-model="User.password"
          />

          <div class="demo-auto-login">
            <Checkbox v-model="autoLogin" size="large">自动登录</Checkbox>
            <a>忘记密码</a>
          </div>
          <Button type="primary" @click="login" class="input">登陆</Button>
        </TabPane>
        <!-- 注册板块 -->
        <TabPane label="注册" name="注册" class="innercardRegister">
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

          <Button type="primary" @click="login" class="input">注册</Button>
        </TabPane>
      </Tabs>
      <Divider />
    </div>
  </div>
</template>


<script>
import { propertyInfoObject, msgObject } from "../../../js/object.js";

export default {
  name: "LoginAndRegist2",
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
      var object = new msgObject();
      object.code = 200;
      object.requestInfo.className = "User";
      object.requestInfo.type = "select";

      var account = new propertyInfoObject();
      account.name = "account";
      account.operator = "=";
      account.searchtype = "STRING";
      account.target = "NULL";
      account.value = this.User.account;

      var password = new propertyInfoObject();
      password.name = "password";
      password.operator = "=";
      password.searchtype = "STRING";
      password.target = "NULL";
      password.value = this.User.password;

      var lib = new propertyInfoObject();
      lib.name = "Lib";
      lib.operator = "=";
      lib.searchtype = "OBJECT";
      lib.value = new propertyInfoObject();
      let innervalue = lib.value;

      innervalue.name = "库";
      innervalue.operator = "=";
      innervalue.searchtype = "STRING";
      innervalue.value = [
        {
          name: "libname",
          seartype: "STRING",
          value: "测试库",
        },
        {
          name: "libcontent",
          seartype: "STRING",
          value: "测试库的内容",
        },
      ];

      object.requestInfo.propertyInfo = [account, password, lib];
      console.log(object);
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
.card {
  float: right;
  background: rgb(0, 0, 0, 0.7);
  width: 20vw;
  height: 50vh;
  border-radius: 10px;
  padding: 1vh;
  margin-right: 10vw;
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