<template>
  <div class="ess">
    <div class="esscontent">
      <div class="AtlasDiretory">
        <p class="title">怪物图鉴</p>
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
.ess {
  padding-top: 1vh;
  padding-bottom: 1vh;
  width: 100%;
  background: rgb(0, 0, 0, 0.7);
}
.esscontent {
  min-height: 60vh;
  margin-left: 15vw;
  background: rgb(255, 255, 255, 0.2);
  width: 70vw;
  padding-top: 5vh;

}

.AtlasDiretory {
  width: 40%;
  height: 30vh;
  background-color: blue ;
  margin-left: 2vw;
}

.title {
  color: #1684a2;
  font-size: 20px;
  text-align: left;
}
</style>
   