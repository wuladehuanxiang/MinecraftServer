<template>
  <div class="ess clearfix">
    <!-- 选择与增加类别的部分 -->
    <div style="width: 5vw; float: left; position: fixed; margin-left: 1vw">
      <!-- 以触发选择type -->

      <div style="width: 13vw" v-show="typeDiretoryVis">
        <div
          class="typeStyle"
          v-for="i in allType"
          :key="i.uuid"
          :ref="i.name"
          @click="changeType(i)"
        >
          {{ i.name }}
        </div>

        <!-- 只有管理员能看到的增添类别部分 -->
        <Tooltip
          content="点击以添加类别"
          style="float: left; width: 100%"
          placement="top"
          v-show="isadmin"
        >
          <Button
            type="info"
            style="
              width: 100%;
              height: 100%;
              background: rgb(255, 255, 255, 0.2);
            "
            @click="editTypeVisible = !editTypeVisible"
          >
            <Icon type="ios-add-circle-outline" size="40"
          /></Button>
        </Tooltip>
      </div>

      <Modal
        v-show="isadmin"
        v-model="editTypeVisible"
        :mask-closable="false"
        :footer-hide="true"
        width="50"
      >
        <div style="height: 50vh; background: blanchedalmond">
          <!-- 名称 name -->
          <!-- 内容 content -->
          <!-- 图标 icon 暂时不写 可以选用图片？将会考虑-->
          <Input
            class="title"
            placeholder="输入类别名称"
            v-model="newType.name"
            style="
              margin-left: 10%;
              background: rgb(255, 255, 255, 0.2);
              width: 80%;
              margin-top: 5vh;
            "
          >
          </Input>
          <Input
            class="contentMSG"
            maxlength="100"
            show-word-limit
            type="textarea"
            :rows="4"
            v-model="newType.description"
            placeholder="描述性文字"
            style="width: 80%; margin-top: 1vh; margin-left: 10%"
          ></Input>

          <Button
            type="success"
            style="width: 80%; margin-top: 5%; margin-left: 10%"
            @click="createType()"
            >提交</Button
          >
        </div>
      </Modal>
    </div>

    <div class="esscontent">
      <div refs="allDiretory" v-show="diretoryVisible">
        <!-- 管理员才能看到的编辑图鉴部分 -->
        <div class="AtlasDiretory" v-show="isadmin">
          <Tooltip content="点击以添加图片" style="float: left" placement="top">
            <div class="innerimg" @click="editImgVisible = !editImgVisible">
              <Icon
                v-show="showingImg == ''"
                class="editIcon"
                type="ios-add-circle-outline"
                style="float: left; margin-top: 50%"
                size="50"
              />
              <img
                :src="showingImg"
                v-show="!showingImg == ''"
                alt=""
                style="height: 26vh; width: 20vh; margin-top: 1vh"
              /></div
          ></Tooltip>

          <Modal
            v-model="editImgVisible"
            :mask-closable="false"
            :footer-hide="true"
            width="50"
          >
            <div style="width: 25vw; float: left">
              <Cascader
                :data="imgTree"
                v-model="imgValue"
                @on-change="chooseEditImg"
                style="width: 10vw"
              />
            </div>

            <div style="">
              <img :src="showingImg" alt="" style="height: 40vh; width: 45vh" />
            </div>
          </Modal>
          <div class="text" style="margin-left: 1vw">
            <Input
              class="title"
              placeholder="输入新图鉴标题"
              v-model="newDiretory.name"
              style="
                margin-left: 0;
                margin-bottom: -10%;
                background: rgb(255, 255, 255, 0.2);
                width: 100%;
              "
            >
            </Input>
            <Input
              class="contentMSG"
              maxlength="100"
              show-word-limit
              type="textarea"
              :rows="4"
              v-model="newDiretory.description"
              placeholder="描述性文字"
              style="width: 100%"
            ></Input>

            <Button
              type="primary"
              @click="createDiretory()"
              style="margin-top: 1vh; margin-left: 40%"
              >提交</Button
            >
          </div>
        </div>

        <!-- 所有的图鉴 -->
        <div
          class="AtlasDiretory"
          v-for="(item, index) in allDiretory"
          :key="item.uuid"
        >
          <div v-if="allDiretory.length != 0">
            <div
              class="innerimg"
              @click="
                clickDiretory(item);
                typeDiretoryVis = !typeDiretoryVis;
              "
            >
              <img
                :src="item.image"
                style="height: 90%; width: 90%; float: left"
                alt=""
              />
            </div>
            <div
              class="text"
              @click="
                clickDiretory(item);
                typeDiretoryVis = !typeDiretoryVis;
              "
            >
              <p class="title">{{ item.name }}</p>

              <p class="contentMSG">
                {{ item.description }}
              </p>
            </div>
            <Tooltip
              v-show="isadmin"
              content="点击删除这个图鉴"
              style="float: right; width: 10%"
              placement="top"
            >
              <Icon
                type="ios-close-circle-outline"
                size="40"
                @click="deleteDiretory(item)"
              />
            </Tooltip>
          </div>
        </div>
      </div>

      <!-- 选中的展示目录的所有物品与种类 -->
      <div refs="allItems" v-show="!diretoryVisible">
        <!-- 返回按钮 用以返回图鉴列表 -->
        <Icon
          type="md-return-left"
          size="50"
          style="background: white; margin-left: 3vw"
          @click="
            diretoryVisible = !diretoryVisible;
            typeDiretoryVis = !typeDiretoryVis;
          "
        />

        <!-- 这个图鉴的描述 -->
        <div style="width: 70vw">
          <div
            style="
              width: 70vw;
              color: #1684a2;
              font-size: 30px;
              text-align: center;
              font-weight: bold;
            "
          >
            {{ watchingDiretory.name }}
          </div>
          <div
            style="
              width: 70vw;
              text-align: center;
              color: rgb(255, 255, 255);
              font-weight: bold;
              font-size: 25px;
            "
          >
            {{ watchingDiretory.description }}
          </div>
        </div>

        <!-- 所选类别的物品添加 -->
        <div class="AtlasDiretory" style="height: 40vh" v-show="isadmin">
          <div>
            <Tooltip
              content="点击以添加图片"
              style="float: left"
              placement="top"
            >
              <div class="innerimg" @click="editImgVisible = !editImgVisible">
                <Icon
                  v-show="showingImg == ''"
                  class="editIcon"
                  type="ios-add-circle-outline"
                  style="float: left; margin-top: 50%"
                  size="50"
                />
                <img
                  :src="showingImg"
                  v-show="!showingImg == ''"
                  alt=""
                  style="height: 26vh; width: 20vh; margin-top: 1vh"
                /></div
            ></Tooltip>
            <Modal
              v-model="editImgVisible"
              :mask-closable="false"
              :footer-hide="true"
              width="50"
            >
              <div style="width: 25vw; float: left">
                <Cascader
                  :data="imgTree"
                  v-model="imgValue"
                  @on-change="chooseEditImg"
                  style="width: 10vw"
                />
              </div>

              <div style="">
                <img
                  :src="showingImg"
                  alt=""
                  style="height: 40vh; width: 45vh"
                />
              </div>
            </Modal>
            <div class="text" style="margin-left: 1vw">
              <Input
                class="title"
                placeholder="输入新物品名称"
                v-model="newItem.name"
                style="
                  margin-left: 0;
                  background: rgb(255, 255, 255, 0.2);
                  width: 100%;
                  margin-bottom: 1vh;
                "
              >
              </Input>
              <Input
                maxlength="100"
                show-word-limit
                type="textarea"
                :rows="2"
                v-model="newItem.description"
                placeholder="描述性文字"
                style="width: 100%; margin-bottom: 1vh"
              ></Input>
              <Input
                class="title"
                placeholder="属性(如果是装备或者消耗品)"
                v-model="newItem.attributes"
                style="
                  margin-left: 0;
                  background: rgb(255, 255, 255, 0.2);
                  width: 100%;
                  margin-bottom: 1vh;
                "
              >
              </Input>
              <Input
                class="title"
                placeholder="组成(如果是合成的物品)"
                v-model="newItem.consist"
                style="
                  margin-left: 0;
                  background: rgb(255, 255, 255, 0.2);
                  width: 100%;
                  margin-bottom: 1vh;
                "
              >
              </Input>
              <Input
                class="title"
                placeholder="产出区域(所在区域)"
                v-model="newItem.region"
                style="
                  margin-left: 0;
                  background: rgb(255, 255, 255, 0.2);
                  width: 100%;
                  margin-bottom: 1vh;
                "
              >
              </Input>
              <Input
                class="title"
                placeholder="产出战利品(如果是怪物的话)"
                v-model="newItem.spoils"
                style="
                  margin-left: 0;
                  background: rgb(255, 255, 255, 0.2);
                  width: 100%;
                  margin-bottom: 1vh;
                "
              >
              </Input>

              <Button
                type="primary"
                @click="createItem()"
                style="margin-top: 1vh; margin-left: 40%"
                >提交</Button
              >
            </div>
          </div>
        </div>

        <!-- 展示所有物品 -->
        <div
          v-for="item in allItem"
          :key="item.uuid"
          class="AtlasDiretory"
          style="float: left"
        >
          <div>
            <div class="innerimg">
              <img
                :src="item.image"
                style="height: 90%; width: 90%; float: left"
                alt=""
              />
            </div>
            <div class="text">
              <p class="title">{{ item.name }}</p>

              <p class="contentMSG" style="margin-top: 8vh">
                {{ item.description }}
              </p>
              <p class="contentMSG" style="margin-top: 0">
                {{ item.attributes }}
              </p>
              <p class="contentMSG" style="margin-top: 0">
                {{ item.consist }}
              </p>
              <div v-show="!(item.region == '')">地区:</div>
              <p class="contentMSG" style="margin-top: 0">
                {{ item.region }}
              </p>
              <p class="contentMSG" style="margin-top: 0">
                {{ item.spoils }}
              </p>
            </div>
            <Tooltip
              v-show="isadmin"
              content="点击删除这个物品"
              style="float: right; width: 10%"
              placement="top"
            >
              <Icon
                type="ios-close-circle-outline"
                @click="deleteItem(item)"
                size="40"
              />
            </Tooltip>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  <script>
import Item from "../ItemCompent/Item.vue";
import fileutile from "../../../js/utils/fileutils.js";
import Interworking from "../../../js/utils/Interworking";
import { BmCategory, BmType, BmItem } from "../../../js/object.js";
import bus from "../../../js/bus.js";
import Donate from "./Donate.vue";

export default {
  name: "Atlas",
  mounted: function () {
    var papi = {
      player: "tank_ice",
    };
    Interworking.request("BmPapi", JSON.stringify(papi),1,10)
      .then((value) => {
        console.log(value.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    this.imgTree = fileutile.getAllFile();
    this.initAllDiregoty();

    bus.$on("isadmin", () => {
      //显示应该显示的部分
      this.isadmin = true;
    });
  },
  components: { Item, Donate },
  methods: {
    //弹出提示信息
    info: function (data) {
      this.$Notice.info({
        title: data.title,
        desc: data.nodesc ? "提交成功" : data.content,
      });
    },
    //初始化所有的图鉴
    initAllDiregoty: function () {
      var bm = new BmCategory();
      Interworking.request("BmCategory", JSON.stringify(bm), 1, 10)
        .then((value) => {
          this.allDiretory = value.data.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //初始化此图鉴的所有类别
    initAllType: function () {},
    clickDiretory: function (data) {
      this.diretoryVisible = false;
      this.watchingDiretory = data;
      this.getTypes();
    },
    createDiretory: function () {
      if (this.newDiretory.name == "" || this.newDiretory.description == "") {
        this.info({
          nodesc: false,
          title: "新图鉴不能为空",
          content: "请确保所有信息都填写正确",
        });
        return;
      }
      Interworking.create("BmCategory", JSON.stringify(this.newDiretory))
        .then((value) => {
          initAllDiregoty();
          this.info({
            nodesc: false,
            title: "成功",
            content: "成功添加" + this.newDiretory.name,
          });
          this.newDiretory = {};
          this.showingImg = {};
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: "错误",
          });
        });
    },
    //点击图鉴触发的方法

    createType: function () {
      //msg 为category
      var bt = new BmType();
      bt.categoryUuid = this.watchingDiretory.uuid;
      bt.name = this.newType.name;
      bt.content = this.newType.content;
      if (bt.categoryUuid == "") {
        this.info({
          nodesc: false,
          title: "新类别不能为空",
          content: "请确保所有信息都填写正确",
        });
        return;
      }
      Interworking.create("BmType", JSON.stringify(bt))
        .then((value) => {
          this.getTypes(this.watchingDiretory);
          this.info({
            nodesc: false,
            title: "成功",
            content: "成功添加" + this.newType.name,
          });
          this.newType = {};
          this.showingImg = {};
          this.editTypeVisible = false;
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: "错误",
          });
        });
    },
    createItem: function () {
      this.newItem.categoryUuid = this.watchingDiretory.uuid;
      this.newItem.typeUuid = this.watchingType.uuid;

      console.log(this.newItem);
      if (this.newItem.categoryUuid == "" || this.newItem.typeUuid == "") {
        this.info({
          nodesc: false,
          title: "类别ID 和图鉴ID均不能为空",
          content: "请确保所有信息都填写正确",
        });
        return;
      }

      Interworking.create("BmItem", JSON.stringify(this.newItem))
        .then((value) => {
          this.allItem = value.data.data;
          this.info({
            nodesc: false,
            title: "成功",
            content: "添加成功",
          });
          this.newItem = {};
          this.showingImg = {};
          this.getItems();
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: "错误",
          });
        });
    },

    deleteDiretory: function (msg) {
      var bm = new BmCategory();
      bm.uuid = msg.uuid;
      Interworking.delete("BmCategory", JSON.stringify(bm))
        .then((value) => {
          this.initAllDiregoty();
          if (value.data.data == "1") {
            this.info({
              nodesc: false,
              title: "成功",
              content: "已删除" + msg.title,
            });
          } else {
            this.info({
              nodesc: false,
              title: "失败",
              content: value.data.data,
            });
          }
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: "发生了未知错误请联系管理员",
          });
        });
    },
    deleteItem: function (msg) {
      var bm = new BmItem();
      bm.uuid = msg.uuid;
      Interworking.delete("BmItem", JSON.stringify(bm))
        .then((value) => {
          this.initAllDiregoty();
          if (value.data.data == "1") {
            this.info({
              nodesc: false,
              title: "成功",
              content: "已删除" + msg.title,
            });
          } else {
            this.info({
              nodesc: false,
              title: "失败",
              content: value.data.data,
            });
          }
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: error,
          });
        });
      this.getItems();
    },
    deleteType: function (msg) {
      var bm = new BmType();
      bm.uuid = msg;
      Interworking.delete("BmType", JSON.stringify(bm))
        .then((value) => {
          this.initAllDiregoty();
          if (value.data.data == "1") {
            this.info({
              nodesc: false,
              title: "成功",
              content: "已删除" + msg.title,
            });
          } else {
            this.info({
              nodesc: false,
              title: "失败",
              content: value.data.data,
            });
          }
        })
        .catch(function (error) {
          this.info({
            nodesc: false,
            title: "错误",
            content: "错误",
          });
        });
    },

    getTypes: function () {
      var bt = new BmType();
      bt.categoryUuid = this.watchingDiretory.uuid;
      Interworking.request("BmType", JSON.stringify(bt), 1, 20)
        .then((value) => {
          this.allType = value.data.data;
          if (this.allType.length != 0) {
            this.watchingType = this.allType[0];
            this.allItem = this.getItems();
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getItems: function () {
      if (
        this.watchingType.categoryUuid == "" ||
        this.watchingType.uuid == ""
      ) {
        this.info({
          nodesc: false,
          title: "类别ID 和图鉴ID均不能为空",
          content: "请确保所有信息都填写正确",
        });
      }

      var bm = new BmItem();

      bm.categoryUuid = this.watchingType.category_uuid;
      bm.typeUuid = this.watchingType.uuid;

      Interworking.request("BmItem", JSON.stringify(bm), 1, 20)
        .then((value) => {
          this.allItem = value.data.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    chooseEditImg: function (value, selectedData) {
      if (selectedData[selectedData.length - 1].type) {
        //展示选择的图片
        this.showingImg = require("../../../lib/images/" +
          selectedData[selectedData.length - 1].__label.replace(/\s*/g, "") +
          "." +
          selectedData[selectedData.length - 1].type);

        //为图片赋值
        this.newDiretory.image = this.showingImg;
        this.newItem.image = this.showingImg;
      }
    },

    changeType: function (type) {
      // this.$refs[type].style.background
      this.watchingType = type;
      this.allItem = this.getItems();
      // this.$refs[this.watchingType.name].style =
      //   "  height: 5vh; font-size: 30px;  text-align: center; background:black; opacity: 0.5; ";
    },
    convertImgToBase64: function (url, callback, outputFormat) {
      var canvas = document.createElement("CANVAS"),
        ctx = canvas.getContext("2d"),
        img = new Image();
      img.crossOrigin = "Anonymous";
      img.onload = function () {
        canvas.height = img.height;
        canvas.width = img.width;
        ctx.drawImage(img, 0, 0);
        var dataURL = canvas.toDataURL(outputFormat || "image/png");
        callback.call(this, dataURL);
        canvas = null;
      };
      img.src = url;
    },
  },

  data() {
    return {
      isadmin: false,
      editImgVisible: false,
      editTypeVisible: false,
      diretoryVisible: true,
      //左侧类别可视栏
      typeDiretoryVis: false,
      //编辑的图片
      showingImg: "",
      //所有图片的树状数据
      imgTree: [],
      //选择的图片的数据
      imgValue: [],
      //是否在编辑框显示正在编辑的图片

      //新目录的容器
      newDiretory: {
        name: "",
        image: "",
        description: "",
      },
      newType: {
        name: "",
        categoryUuid: "",
        content: "",
        icon: "",
      },
      //新物品的容器
      newItem: {
        uuid: "",
        name: "",
        categoryUuid: "",
        typeUuid: "",
        image: "",
        description: "",
        attributes: "",
        consist: "",
        region: "",
        spoils: "",
      },
      //缓存所有的目录
      allDiretory: [],
      //缓存当前看的目录的所有类别
      allType: [],
      allItem: [],
      //正在看的目录
      watchingDiretory: {},
      //正在看的种类
      watchingType: {},
    };
  },
  created() {},
};
</script>
  
  <style scoped>
.typeStyle {
  height: 5vh;
  font-size: 30px;
  text-align: center;
  color: black;
  background: white;
}

.editIcon {
  width: calc(90%);
  height: calc(90%);
}

.contentMSG {
  margin-top: 10vh;
  font-weight: 600;
  color: rgb(255, 255, 255);
  font-weight: bold;
  text-align: center;
}

.innerimg {
  margin-left: 1vw;
  margin-top: 1vh;
  width: 10vw;
  height: 30vh;
  float: left;
}
.title {
  color: #1684a2;
  font-size: 20px;
  text-align: left;
  float: left;
  margin-left: 5vw;
}
.text {
  margin-top: 5vh;
  width: 15vw;
  height: 20vh;
  float: left;
}
.ess {
  padding-top: 1vh;
  padding-bottom: 1vh;
  width: 100vw;
  background: rgb(0, 0, 0, 0.7);
}
.esscontent {
  background: rgb(255, 255, 255, 0.2);
  width: 70vw;
  padding-top: 5vh;
  padding-bottom: 5vh;
  margin-left: 15vw;
  overflow: hidden;
}

.AtlasDiretory {
  float: left;
  border-radius: 5px 5px 5px 5px;
  width: 30vw;
  height: 30vh;
  background-color: rgb(0, 0, 0, 0.4);
  margin-left: 4vw;
  margin-top: 5vh;
  margin-bottom: 5vh;
}
</style>
   