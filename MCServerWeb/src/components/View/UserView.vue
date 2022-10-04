<template>
  <div style="height:100%;  overflow: hidden;">
    <Header1> </Header1>
    <Home v-show="showingContet == 'Home'"></Home>
    <Atlas v-show="showingContet == 'Atlas'"></Atlas>
    <Donate v-show="showingContet == 'Donate'"></Donate>
    <Forum v-show="showingContet == 'Forum'"></Forum>
    <Rules v-show="showingContet == 'Rules'"></Rules>
    <Server v-show="showingContet == 'Server'"></Server>
    <Footer></Footer>
  </div>
</template>
<script>
import bus from "../../js/bus.js";
import Header1 from "../ComonComponents/Headers/Header1.vue";
import Footer from "../ComonComponents/Footers/Footer1.vue";
import Home from "../ComonComponents/Content/Home.vue";
import Atlas from "../ComonComponents/Content/Atlas.vue";
import Donate from "../ComonComponents/Content/Donate.vue";
import Forum from "../ComonComponents/Content/Forum.vue";
import Rules from "../ComonComponents/Content/Rules.vue";
import Server from "../ComonComponents/Content/Server.vue";

export default {
  name: "UserViewVue",

  mounted: function () {
    bus.$on("changeViewPage", (res) => {
      //显示应该显示的部分
      this.showingContet = res;
    });
  },

  components: { Header1, Footer, Home, Atlas, Donate, Forum, Rules, Server },
  methods: {},

  data() {
    return {
      showingContet: "Home",
    };
  },
  created() {},
};
</script>
   
    <style scoped>
</style>
     