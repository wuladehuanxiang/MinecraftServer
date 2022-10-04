<template>
  <div class="foot">
    <div class="footer">
      <div class="contentcard" style="margin-left: 15vw">
        <!-- 标题 -->
        <h3 class="title">下载客户端！</h3>
        <p>现在开始你的旅途！加入服务器成为其中重要的一员吧！</p>
      </div>

      <div class="contentcard" style="">
        <!-- 标题 -->
        <h3 class="title">其他链接</h3>

        <p>这里是一些其他的链接</p>
      </div>

      <div class="contentcard" style="">
        <!-- 标题 -->
        <h3 class="title">图鉴</h3>
        <p>你想知道的一切，都可以从这里找到答案</p>
      </div>
    </div>
    <!-- <div class="Other"></div> -->
  </div>
</template>


<script>
export default {
  name: "Footer1",
  mounted: function () {},
  components: {},
  methods: {},
  data() {
    return {};
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
.foot {
  width: 100vw;
  height: 30vh;
  background: rgb(0, 0, 0, 0.9);
}

.footer {
  height: 20vh;
  width: 100%;
  text-align: center;
  border-bottom: 3px solid rgba(139, 138, 138, 0.2);
}

.contentcard {
  text-align: left;
  padding-top: 5vh;
  width: 23vw;
  height: 20vh;
  float: left;
}
p {
  font-size: 15px;
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.title {
  font-weight: 600;
  color: #fff;
  font-size: 1.6rem;
}

.Other {
  height: 10vh;
  width: 100%;
}
</style>