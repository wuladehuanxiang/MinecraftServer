<template>
  <div class="home">
    <div class="homecontent">
      <div class="fullitem">
        <div style="width: 60%; height: 100%; float: left">
          <img
            src="../../../lib/images/VolcanoTown.jpg"
            style="height: 100%; width: 100%"
            alt=""
          />
        </div>
        <div style="width: 40%; height: 100%; float: left">
          <p
            style="
              color: #1684a2;
              font-size: 20px;
              text-align: left;
              margin-left: 4vw;
              margin-top: 10vh;
            "
          >
            沃奇诺小镇
          </p>
          

        </div>
      </div>
    </div>
  </div>
</template>
  
  
  <script>
export default {
  name: "Home",
  mounted: function () {},
  components: {},
  methods: {},
  data() {
    return {};
  },
};
</script>

  
<style scoped>
.fullitem {
  width: 100%;
  height: 45vh;
  background: rgb(255, 255, 255, 0.2);
}

.home {
  padding-top: 2vh;
  padding-bottom: 2vh;
  width: 100%;
  background: rgb(0, 0, 0, 0.7);
}
.homecontent {
  margin-left: 15vw;
  background: rgb(0, 0, 0, 0.7);
  width: 70vw;
}
.introduce {
  width: 70vw;
  height: 30vh;
}
</style>