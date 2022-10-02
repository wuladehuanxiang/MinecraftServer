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
            {{ i.name }}
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
import interworking from "../../js/utils/Interworking.js";

import { categroyUrl } from "../../js/urls.js";
import { BmCategory } from "../../js/object";

export default {
  name: "Header1",
  mounted: function () {
    this.getDictory();
  },
  components: {},
  methods: {
    //添加目录
    ok() {
      this.addCategory();
    },
    cancel() {},

    addCategory: function () {
      var category = new BmCategory();
      category.name = this.newCategoryName;
      category.image = "";
      category.uuid = "";
      this.Categorys = interworking.create(
        "BmCategory",
        JSON.stringify(category)
      );
    },

    getDictory: function () {
      this.Categorys = interworking.request(
        "BmCategory",
        JSON.stringify(category),
        1,
        10
      );
    },

    deleteCategory: function () {
      var category = new BmCategory();
      category.uuid = "d58bed4b13ef42bfbbb62047b075b742";

      var innermsg = JSON.stringify(category);
      var MSG = {
        header: {
          content: "",
        },
        content: {
          className: "BmCategory",
          requestType: "delete",
          jsonString: innermsg,
        },
      };
      axios
        .post("http://127.0.0.1:8888/common/delete", MSG)
        .then((res) => {
          console.log(res.data);
        })
        .catch(function (err) {
          console.log("err" + err);
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