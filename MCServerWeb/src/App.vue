<template>
  <div id="app" v-if="isRouterAlive">
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
</style>
