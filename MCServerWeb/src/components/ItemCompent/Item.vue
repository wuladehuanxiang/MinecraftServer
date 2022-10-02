<template>
  <div>
    <Card class="CardStyle">
      <div class="CardHead">{{ itemName }}</div>
      <Divider />
      <div class="CardContent">
        <div
          id="webglDom"
          ref="webglDom"
          style="min-height: 100%; min-width: 100%"
        ></div>
      </div>

      <Divider />
      <div class="CardBottom">This is Bottom</div>
    </Card>
  </div>
</template>



<script>
import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls";
import { OBJLoader } from "three/examples/jsm/loaders/OBJLoader.js";

export default {
  props: ["ItemData"],
  name: "Item",
  mounted: function () {
    this.$nextTick(this.init);
  },
  watch: {
    skyType() {
      this.addSkeyBox();
    },
  },
  components: {},
  methods: {
    init() {
      // 初始化画布宽高
      const container = this.$refs.webglDom;

      this.width = document.documentElement.clientWidth * 0.125;
      this.height = document.documentElement.clientHeight * 0.15;
      // 场景
      this.scene = new THREE.Scene();
      this.scene.fog = new THREE.Fog(0x005577, 1, 2800);
      this.textloader = new THREE.TextureLoader();

      // 相机
      this.camera = new THREE.PerspectiveCamera(
        45,
        this.width / this.height,
        0.01,
        10000
      );
      this.camera.position.set(860, 470, 720);
      this.add(this.camera);
      this.camera.lookAt(this.scene.position);

      // AxesHelper
      let axisHelper = new THREE.AxesHelper(100, 100);
      this.add(axisHelper);
      // 添加灯光
      this.addLight();

      // this.addBox();
      this.initModel();
      // 渲染器
      this.renderer = new THREE.WebGLRenderer({
        antialias: true,
      });
      this.renderer.setClearColor(new THREE.Color("#ffffff"), 1);
      this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
      this.renderer.setSize(this.width, this.height);
      document.getElementById("webglDom").appendChild(this.renderer.domElement);

      // OrbitControls
      new OrbitControls(this.camera, this.renderer.domElement);
      this.render();
    },

    /* *
     * 加载模型
     * */
    initModel() {
      var objLoader = new OBJLoader();
      objLoader.load("/static/webgl/goblin.obj", (geometry) => {
        this.scene.add(geometry);
        console.log(geometry);
      });
    },

    addBox() {
      const geometry = new THREE.BoxBufferGeometry(300, 300, 300);
      const material = new THREE.MeshPhongMaterial({
        color: 0xff0000,
      });
      const mesh = new THREE.Mesh(geometry, material);
      this.add(mesh);
    },

    addLight() {
      // 环境光
      const light = new THREE.AmbientLight(0xffffff, 0.5); // soft white light
      this.add(light);

      // // 平行光源
      const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
      directionalLight.position.set(200, 600, 200);
      this.add(directionalLight);
    },
    add(obj) {
      this.scene.add(obj);
    },
    render() {
      this.renderer.render(this.scene, this.camera);
      requestAnimationFrame(this.render);
    },
  },
  data() {
    return {
      scene: null,
      camera: null,
      renderer: null,
      textloader: null,
      width: 0,
      height: 0,
      //

      itemName: this.ItemData.ItemName,
    };
  },
};
</script>


<style scoped>
.CardStyle {
  width: 17%;
  height: 30%;
  min-width: 200px;
  min-height: 350px;
  float: left;
  margin: 1vw;
}

.CardHead {
  text-align: center;
  height: 20%;
  min-height: 35px;
}

.CardContent {
  text-align: center;
  height: 70%;
  min-height: 150px;
}
.CardBottom {
  text-align: center;
  height: 10%;
  min-height: 20px;
}
</style>