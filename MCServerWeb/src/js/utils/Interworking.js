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

        return axios
            .post("http://127.0.0.1:8888/common/delete", MSG)

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

        return axios
            .post("http://127.0.0.1:8888/common/update", MSG)

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


        return axios
            .post("http://127.0.0.1:8888/common/create", MSG)

    },

}