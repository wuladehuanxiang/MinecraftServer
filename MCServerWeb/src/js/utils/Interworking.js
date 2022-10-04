import axios from "axios";

export default {
    request: function(className, data, pageNum, pageSize) {
        var MSG = {
            header: {
                content: "",
            },

            content: {
                className: className,
                requestType: "request",
                basePageInfo: {
                    pageNum: pageNum,
                    pageSize: pageSize,
                },
                jsonString: data,
            },
        };

        return axios
            .post("http://127.0.0.1:8888/common/request", MSG);

    },
    delete: function(className, data) {
        var MSG = {
            header: {
                content: "",
            },
            content: {
                className: className,
                requestType: "delete",
                jsonString: data,
            },
        };

        axios
            .post("http://127.0.0.1:8888/common/delete", MSG)
            .then((res) => {
                return res.data;
            })
            .catch(function(err) {
                console.log("err" + err);
            });
    },
    update: function(className, data) {
        var MSG = {
            header: {
                content: "",
            },
            content: {
                className: className,
                requestType: "update",
                jsonString: data,
            },
        };

        axios
            .post("http://127.0.0.1:8888/common/update", MSG)
            .then((res) => {
                return res.data;
            })
            .catch(function(err) {
                console.log("err" + err);
            });
    },
    create: function(className, data) {
        var MSG = {
            header: {
                content: "",
            },
            content: {
                className: className,
                requestType: "create",

                jsonString: data,
            },
        };

        axios
            .post("http://127.0.0.1:8888/common/create", MSG)
            .then((res) => {
                return res.data;
            })
            .catch(function(err) {
                console.log("err" + err);
            });
    },

}