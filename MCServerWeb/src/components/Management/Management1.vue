<template>
  <div class="layout">
    <Header1> </Header1>
    <Layout>
      <Layout>
        <!-- 左侧栏位 -->
        <Sider hide-trigger>
          <Menu active-name="1" theme="dark" width="auto" style="height: 85vh">
            <Submenu
              v-for="(item, i) in Dirctory"
              :key="i"
              class="MenuGroup"
              :name="item.name"
            >
              <template #title>
                {{ item.name }}
              </template>
              <MenuItem v-for="(group, j) in item.value" :key="j" :name="group">
                {{ group }}
              </MenuItem>
            </Submenu>
          </Menu>
        </Sider>

        <Layout :style="{ padding: '0 24px 24px' }">
          <Breadcrumb :style="{ margin: '12px 0' }"> </Breadcrumb>

          <Content :style="{ padding: '24px', background: '#fff' }">
            <!-- <Item
              v-for="(item, i) in ItemMessage"
              :key="i"
              :ItemData="item"
            ></Item> -->
           
          </Content>
        </Layout>
      </Layout>
    </Layout>
    <Footer></Footer>
  </div>
</template>
<script>
import Item from "../ItemCompent/Item.vue";
import Header1 from "../Headers/Header1.vue";
import Footer from "../Footers/Footer1.vue";
import Manager from "../Manager/Manager.vue";


export default {
  name: "Management1",
  mounted: function () {},
  components: { Item, Header1, Footer, Manager },
  methods: {
    reachBottom: function () {
      alert("bottom");
    },
  },

  data() {
    return {
      screenHeight: document.documentElement.clientHeight * 0.8,
      Dirctory: [],
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