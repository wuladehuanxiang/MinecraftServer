<template>
  <div>
    <div class="headCard">
      <Menu
        mode="horizontal"
        theme="dark"
        active-name="1"
        @on-select="changeTabList"
      >
        <div class="layout-logo"></div>
        <div class="layout-nav">
          <Modal
            v-model="modal"
            title="新增一个目录"
            @on-ok="ok"
            @on-cancel="cancel"
          >
            <Input
              prefix="ios-contact"
              placeholder="目录名称"
              v-model="newCategoryName"
            />
          </Modal>
          <MenuItem v-for="i in Categorys" :key="i.name" :name="i.name">
            <Icon :type="i.icon"></Icon>
            {{ i.name }}
          </MenuItem>
          <!-- 地图 -->
          <MenuItem name="map">
            <Icon type="md-map" />
            地图
          </MenuItem>
          <MenuItem name="add">
            <Icon type="md-add-circle" />
            新增
          </MenuItem>

          <!-- 搜索 -->
          <MenuItem name="5">
            <div style="widht: 25vw">
              <Input
                placeholder="搜索"
                style="width: 15vw; margin-left: 25vw"
              />
            </div>
          </MenuItem>

          <!-- 右侧 -->
          <MenuItem
            name="6"
            style="width: 20vw; float: right"
            @on-select="changeTabList"
          >
            <Dropdown trigger="custom">
              <Avatar
                src="https://i.loli.net/2017/08/21/599a521472424.jpg"
                size="large"
                style="height: 6vh; width: 6vh"
              />
              <template #list>
                <DropdownMenu style="width: 15vw; height: 25vh"> </DropdownMenu>
              </template>
            </Dropdown>
          </MenuItem>
        </div>
      </Menu>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import bus from "../../js/bus.js";
import { categroyUrl } from "../../js/urls.js";

export default {
  name: "Header1",
  mounted: function () {
    //获取所有的目录
    // var params = JSON.stringify({ "token": token })
    axios
      .post(categroyUrl, {})
      .then((res) => {
        this.Categorys = res.data;
        if (this.Categorys[0] != null) {
          bus.$emit("HeadToMainTab", this.Categorys[0].types);
        }
      })
      .catch(function (err) {
        console.log(err);
      });
  },
  components: {},
  methods: {
    //添加目录
    ok() {
      this.addCategory();
    },
    cancel() {},
    addCategory: function () {
      if (this.newCategoryName == "") {
        this.$Message.error("目录名不能为空");
        return;
      }
      //添加目录

      axios
        .post(categroyUrl, { name: this.newCategoryName })
        .then((res) => {
          this.$Message.info(res.data);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    changeTabList: function (menu) {
      this.Categorys.forEach((element) => {
        if (element.name == menu) {
          bus.$emit("HeadToMainTab", element.types);
        }
      });
      if (menu == "map") {
        bus.$emit("changeMainPageToMap", menu);
      }
      if (menu == "add") {
        this.modal = true;
      }
    },
  },
  data() {
    return {
      newCategoryName: "",
      Categorys: [],
      haslogin: false,
      User: {},
      // 显示添加目录
      modal: false,
    };
  },
};
</script>
<!-- transform: rotate(40deg); -->

<style scoped>
.ivu-dropdown-item {
  height: 100%;
}
.headCard {
  width: 100vw;
}
</style>