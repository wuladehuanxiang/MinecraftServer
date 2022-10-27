package module.common.enums;

/**
 * @author : Dragon丿Z
 * @desc 操作类型常量
 * @date : 2022/10/27 20:43
 */
public enum RequestTypeEnum {
    REQUEST("request", "请求"),
    UPDATE("update", "更新"),
    DELETE("delete", "删除"),
    CREATE("create", "创建"),
    VERIFICATION("verification", "验证"),
    REPLACE("replace", "replace");
    private String type;
    private String message;

    RequestTypeEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RequestTypeEnum get(String type) {
        for (RequestTypeEnum c : values()) {
            if (c.getType().equals(type)) {
                return c;
            }
        }
        return null;
    }
}
