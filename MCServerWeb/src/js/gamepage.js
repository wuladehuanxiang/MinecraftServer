import axios from "axios";

export default {
    getSideData: function() {
        var gameList = {};
        axios
            .get("https://v1.hitokoto.cn?c=k&c=i&c=h&c=d")
            .then((res) => {
                message.word = res.data.hitokoto;
                message.from = res.data.from;
            })
            .catch(function(err) {
                console.log(err);
            });
    }
}