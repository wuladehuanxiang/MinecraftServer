import axios from "axios";


const $axios = axios.create({
    timeout: 3000
});
$axios.defaults.withCredentials = true

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
        return $axios
            .post("http://127.0.0.1:8888/common/request", MSG, {
                headers: { 'satoken': localStorage.getItem("satoken"), }
            });

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

        return $axios
            .post("http://127.0.0.1:8888/common/delete", MSG, {
                headers: {
                    'satoken': localStorage.getItem("satoken"),
                }
            })

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

        return $axios
            .post("http://127.0.0.1:8888/common/update", MSG, {
                headers: { 'satoken': localStorage.getItem("satoken"), }
            })

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
            .post("http://127.0.0.1:8888/common/create", MSG, {
                headers: {
                    'satoken': localStorage.getItem("satoken"),
                }
            })

    },
    verification: function(className, data) {
        var MSG = {
            header: {
                content: "",

            },
            content: {
                className: className,
                requestType: "verification",
                jsonString: data,
            },
        };
        return $axios
            .post("http://127.0.0.1:8888/common/verification", MSG, {
                headers: {
                    'Access-Control-Allow-Credentials': 'true', //解决session问题
                    'satoken': localStorage.getItem("satoken"),
                },
                withCredentials: true
            })

    },

}