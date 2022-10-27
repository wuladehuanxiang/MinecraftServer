package module.common.enums;

/**
 * @author : Dragon丿Z
 * @desc 普通常量
 * @date : 2022/09/09 14:20
 */
public enum ExceptionEnum {
    /**
     * 服务成功
     **/
    SERVER_SUCCEED(200, "服务成功"),
    /**
     * 服务异常
     **/
    SERVER_ERROR(201, "服务异常"),
    /**
     * 处理失败
     **/
    DATA_HANDLE_ERROR(202, "处理失败"),
    /**
     * 非法参数
     **/
    VALID_ERROR(203, "非法参数"),
    /**
     * 请求异常
     **/
    WEB_REQ_ERROR(204, "请求异常"),
    /**
     * 信息不存在
     **/
    CODE_NOT_EXIST(205, "信息不存在"),
    /**
     * 信息已存在
     **/
    CODE_EXIST(206, "信息已存在"),
    /**
     * 设备数量已超出证书许可最大限制数，请联系系统管理员更新证书
     **/
    DEVICE_EXCESS(207, "设备数量已超出证书许可最大限制数，请联系系统管理员更新证书"),
    /**
     * 数据不规范
     **/
    DATA_ERROR(208, "数据不规范"),
    /**
     * 业务异常
     **/
    BUSINESS_ERROR(301, "业务异常"),
    /**
     * 认证失败
     **/
    AUTH_FAIL(401, "认证失败"),
    /**
     * 解析excel失败
     **/
    ANALYSIS_FAIL(601, "解析excel失败,请检查excel格式");

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
