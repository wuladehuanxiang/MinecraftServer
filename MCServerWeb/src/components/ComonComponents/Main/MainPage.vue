<template>
  <div>
    <!-- <EditerView></EditerView> -->
    <UserView></UserView>
  </div>
</template>
<script>
import EditerView from "../../View/EditerView.vue";
import UserView from "../../View/UserView.vue";

export default {
  name: "MainPage",
  mounted: function () {},
  components: { EditerView,UserView },
  methods: {},

  data() {
    return {};
  },
  created() {},
};
</script>

<style scoped>
</style>
 