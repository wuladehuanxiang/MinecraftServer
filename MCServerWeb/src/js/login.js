import axios from "axios";
import router from '../router.js'
var playerData = {
    qqNumber: "",
    token: "",
    headImg: "",
    gameData: {},
    name: ""
}
var gamePages = {}

var background = "";

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
var essurl = "http://175.24.228.204:14045";

export default {
    getWord: function () {
        var message = {
            word: "",
            from: ""
        }
        axios
            .get("https://v1.hitokoto.cn?c=k&c=i&c=h&c=d")
            .then((res) => {
                message.word = res.data.hitokoto;
                message.from = res.data.from;
            })
            .catch(function (err) {
                console.log(err);
            });
        return message;
    },
    getRandomVerBackImg: function () {
        var picId = Math.floor((Math.random() * 27) + 1);
        var picSrc = require("../lib/images/piexl/Hor/" + picId + ".gif")
        return picSrc;

    },
    getRandomHorBackImg: function () {
        var picId = Math.floor((Math.random() * 7) + 1);
        var picSrc = require("../lib/images/piexl/Ver/" + picId + ".gif")
        return picSrc;
    },
    login: function (token, dom) {
        var params = JSON.stringify({ "token": token })
        axios
            .post(essurl + "/login", params)
            .then((res) => {
                if (res.data.login == "true") {
                    playerData = res.data.playerData;
                    gamePages = res.data.gamePages;
                    router.push("/MainPage");
                } else {
                    dom.token = "";
                    dom.$refs.token.placeholder = "此Token无效";
                    dom.$refs.token.style.background = "red";
                }

            })
            .catch(function (err) {
                console.log("err" + err);
            })
    },
    getPlayerData: function () {
        return playerData;
    },
    getGamePage: function () {
        return gamePages;
    },
    setBackground: function (params) {
        this.background = params;
    },
    getBackgrund: function () {
        return this.background;
    }
    ,
    getEssURL:function()
    {
        return this.essurl;
    }
};