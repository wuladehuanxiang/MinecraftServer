<template>
  <div>
    <div>
      <Table :columns="columns" :data="row"></Table>
      <Row type="flex" justify="end" class="page" style="margin-top: 60vh">
        <Page
          :current="nestedTable.pageNumber"
          :total="total"
          :page-size="nestedTable.pageSize"
          @on-change="changePage"
          size="small"
          show-total
        ></Page>
      </Row>
    </div>
  </div>
</template>
  
  
  
  <script>
import bus from "../../../js/bus.js";
export default {
  name: "Manager",
  mounted: function () {
    bus.$on("changeEdit", (data) => {
      console.log(data);
    });
  },
  watch: {},
  components: {},
  methods: {
    init() {},
    changePage: function (params) {},
  },
  data() {
    return {
      total: 0,
      row: [],
      nestedTable: {
        // 搜索框初始化对象
        peUnitId: "",
        pageNumber: 1, // 当前页数
        pageSize: 10, // 页面大小
        sort: "createTime", // 默认排序字段
        order: "desc", // 默认排序方式
      },
      columns: [],
    };
  },
};
</script>
  
  
  <style scoped>
</style>