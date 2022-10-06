<template>
  <div class="ess clearfix">
    <div class="esscontent">
      <!-- 所有的目录 -->
      <div refs="allDiretory" v-show="diretoryVisible">
        <!-- 管理员才能看到的编辑目录部分 -->
        <div class="AtlasDiretory">
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

        <div
          class="AtlasDiretory"
          v-for="(item, index) in allDiretory"
          :key="index"
          @click="clickDiretory(item)"
        >
          <div v-if="allDiretory.length != 0">
            <div class="innerimg">
              <img
                :src="item.image"
                style="height: 90%; width: 90%; float: left"
                alt=""
              />
            </div>
            <div class="text">
              <p class="title">{{ item.name }}</p>

              <p class="contentMSG">
                {{ item.description }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- 选中的展示目录的所有物品与种类 -->
      <div refs="allItems" v-show="!diretoryVisible">
        <Icon
          type="md-return-left"
          size="50"
          style="background: white; margin-left: 3vw"
          @click="diretoryVisible = !diretoryVisible"
        />
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

        <div class="AtlasDiretory">
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
              placeholder="输入新物品名称"
              v-model="newItem.name"
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
              v-model="newItem.description"
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
      </div>
    </div>
  </div>
</template>
  <script>
import Item from "../ItemCompent/Item.vue";
import fileutile from "../../../js/utils/fileutils.js";
import Interworking from "../../../js/utils/Interworking";
import { BmCategory } from "../../../js/object.js";

export default {
  name: "Atlas",
  mounted: function () {
    this.imgTree = fileutile.getAllFile();
    this.initAllDiregoty();
  },
  components: { Item },
  methods: {
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
    createDiretory: function () {
      Interworking.create("BmCategory", JSON.stringify(this.newDiretory))
        .then((value) => {
          console.log(value.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    createItem: function () {
      Interworking.create("BmItem", JSON.stringify(this.newItem))
        .then((value) => {
          console.log(value.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //点击图鉴触发的方法
    clickDiretory: function (data) {
      this.diretoryVisible = false;
      this.watchingDiretory = data;
    },
    getTypes: function () {},
    getItems: function () {},

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
  },

  data() {
    return {
      //是在看图鉴 还是在看里面的内容
      diretoryVisible: true,
      //编辑的图片
      showingImg: "",
      //所有图片的树状数据
      imgTree: [],
      //选择的图片的数据
      imgValue: [],
      //是否在编辑框显示正在编辑的图片
      editImgVisible: false,
      //新目录的容器
      newDiretory: {
        name: "",
        image: "",
        description: "",
      },
      //新物品的容器
      newItem: {
        name: "",
        image: "",
        description: "",
      },
      //缓存所有的目录
      allDiretory: [],
      //正在看的目录
      watchingDiretory: {},
    };
  },
  created() {},
};
</script>
  
  <style scoped>
.editIcon {
  width: calc(90%);
  height: calc(90%);
}

.contentMSG {
  margin-top: 8vh;
  font-weight: 600;
  color: rgb(255, 255, 255);
  font-weight: bold;
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
   