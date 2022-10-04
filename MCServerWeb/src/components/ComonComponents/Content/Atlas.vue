<template>
  <div class="ess clearfix">
    <div class="esscontent">
      <div class="AtlasDiretory">
        <div class="innerimg">
          <img
            src="../../../lib/images/blockbench/怪物/哥布林系列/哥布林怪物/goblin.png"
            style="height: 100%; width: 100%; float: left"
            alt=""
          />
        </div>
        <div class="text">
          <p class="title">怪物图鉴</p>

          <p class="contentMSG">
            你可以在这里找到所有的怪物，并且可以知道他们的属性以及分布区域
          </p>
        </div>
      </div>

      <div class="AtlasDiretory">
        <div class="innerimg">
          <img
            src="../../../lib/images/blockbench/功能性与装饰品/有趣的小玩意/alarm_clock.png"
            style="height: 90%; width: 100%; float: left"
            alt=""
          />
        </div>
        <div class="text">
          <p class="title">物品图鉴</p>

          <p class="contentMSG">菲尼斯大路上奇奇怪怪的物品</p>
        </div>
      </div>

      <div class="AtlasDiretory">
        <div class="innerimg">
          <img
            src="../../../lib/images/blockbench/Boss/曼谷斯/magnus_sword.png"
            style="height: 50%; width: 100%; float: left;margin-top:10vh"
            alt=""
          />
        </div>
        <div class="text">
          <p class="title">装备图鉴</p>
          <p class="contentMSG">由弱到强，不断的变强吧！</p>
        </div>
      </div>

      <div class="AtlasDiretory">
        <div class="innerimg">
          <img
            src="../../../lib/images/blockbench/功能性与装饰品/战斗特效/health_pack.png"
            style="height: 90%; width: 90%; float: left"
            alt=""
          />
        </div>
        <div class="text">
          <p class="title">消耗品图鉴</p>

          <p class="contentMSG">想要在菲尼斯活下去，你可得消耗不少东西呢</p>
        </div>
      </div>
    </div>
  </div>
</template>
  <script>
export default {
  name: "Atlas",
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
  margin-left: 1vw;
  margin-top: 1vh;
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
  width: 30vw;
  height: 30vh;
  background-color: rgb(0, 0, 0, 0.4);
  margin-left: 4vw;
  margin-top: 5vh;
  margin-bottom: 5vh;
}
</style>
   