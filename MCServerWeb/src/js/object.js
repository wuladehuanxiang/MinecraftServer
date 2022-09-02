class msgObject {
    constructor() {
        this.code = 0;
        this.requestInfo = {
            className: "", //类名
            type: "", //操作方式 insert update delete select
            propertyInfo: [] //里面为propertyInfoObject
        }
    }


}

class propertyInfoObject {
    constructor() {
        this.name = "";
        this.searchtype = ""; //STRING 
        this.operator = ""; //= like > <
        this.value = {}; //abc 可能是 字符串 或者是 propertyInfoObject->即这个类 里面还有类
        this.target = {}
    }

}


exports.msgObject = msgObject;
exports.propertyInfoObject = propertyInfoObject;