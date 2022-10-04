<template>
  <div class="layout">
    <Header1> </Header1>

    <Footer></Footer>
  </div>
</template>
  <script>
import Item from "../ComonComponents/ItemCompent/Item.vue";
import Header1 from "../ComonComponents/Headers/Header1.vue";
import Footer from "../ComonComponents/Footers/Footer1.vue";
import bus from "../../js/bus.js";
import WulaMap from "../ComonComponents/Wula/WulaMap.vue";
import Table from "../ComonComponents/Table/Table.vue";
import { BmCategory } from "../../js/object";
import interworking from "../../js/utils/Interworking.js";

export default {
  name: "EditerViewVue",

  mounted: function () {},

  components: { Item, Header1, Footer, WulaMap, Table },
  methods: {
    clickLeftTab: function (menu) {
      console.log(menu);
      if (menu.name == "大类") {
        this.getAllCategorys();
      }
    },
    reachBottom: function () {},
    getAllCategorys: function () {
      var category = new BmCategory();

      interworking
        .request("BmCategory", JSON.stringify(category), 1, 10)
        .then((res) => {
          this.allCategorys = res.data;
          console.log(res.data.data);
        })
        .catch(function (err) {
          console.log("err" + err);
        });
    },
  },

  data() {
    return {
      screenHeight: document.documentElement.clientHeight * 0.8,
      Dirctory: [
        { name: "大类" },
        { name: "小类" },
        {
          name: "展示",
        },
      ],
      allCategorys: [],
      allTypes: [],
      allItems: [],
      ItemMessage: [],
    };
  },
  created() {},
};
</script>
  
  <style scoped>
.layout {
  height: 100vh;
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-logo {
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}
.layout-nav {
  width: 420px;
  margin: 0 auto;
  margin-right: 20px;
  border-radius: 4px;
}

.sider {
  background-color: #d7dde4;
}

.MenuGroup {
  min-height: 8vh;
  background: solid #999;
  border: 1px solid #999;
  border-radius: 3px;
}
</style>
   