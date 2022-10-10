<template>
  <div id="app" v-if="isRouterAlive" class="main">
    <keep-alive>
      <router-view> </router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "App",
  provide() {
    return { reload: this.reload };
  },
  data() {
    return { isRouterAlive: true };
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
  },
  components: {},
};
</script>
<style scoped>
.main {
  height: 100%;
  background-size: 100% 100%;
  background-attachment: fixed;
  background-image: url("./lib/images/other/ElvenCastle.png");
  background-repeat: no-repeat;
}
</style>
