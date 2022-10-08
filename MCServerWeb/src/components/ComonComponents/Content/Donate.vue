<template>
  <div class="ess">
    <div class="esscontent">
      <div
        class="title"
        style="
          float: left;
          width: 100%;
          text-align: center;
          margin: 0;
          font-size: 3vw;
          color: rgb(255, 255, 255);
        "
      >
        大哥大嫂过年好 微信还是支付宝？
      </div>

      <div class="AtlasDiretory">
        <img
          src="../../../lib/images/微信.png"
          alt=""
          style="height: 100%; width: 100%"
        />
      </div>
      <div class="AtlasDiretory" style="float: right; margin-right: 10vw">
        <img
          src="../../../lib/images/支付宝.jpg"
          style="height: 100%; width: 100%"
          alt=""
        />
      </div>
    </div>
  </div>
</template>
    <script>
export default {
  name: "Donate",
  mounted: function () {},
  components: {},
  methods: {},

  data() {
    return {};
  },
  created() {},
};
</script>
    
    <style scoped>
.contentMSG {
  margin-top: 8vh;
  font-weight: 600;
  color: rgb(255, 255, 255);
  font-weight: bold;
}

.innerimg {
  width: 10vw;
  height: 30vh;
  float: left;
}
.title {
  color: #1684a2;
  font-size: 20px;
  text-align: left;
  float: left;
  margin-left: 5vw;
}
.text {
  margin-top: 5vh;
  width: 15vw;
  height: 20vh;
  float: left;
}
.ess {
  padding-top: 1vh;
  padding-bottom: 1vh;
  width: 100vw;
  background: rgb(0, 0, 0, 0.7);
}
.esscontent {
  background: rgb(255, 255, 255, 0.2);
  width: 70vw;
  padding-top: 5vh;
  padding-bottom: 5vh;
  margin-left: 15vw;
  overflow: hidden;
}

.AtlasDiretory {
  float: left;
  border-radius: 5px 5px 5px 5px;
  width: 40vh;
  height: 40vh;
  background-color: rgb(0, 0, 0, 0.4);
  margin-left: 10vw;
  margin-top: 5vh;
  margin-bottom: 5vh;
}
</style>
     