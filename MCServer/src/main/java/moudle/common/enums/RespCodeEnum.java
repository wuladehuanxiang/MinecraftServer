package moudle.common.enums;

/**
 * @desc 普通常量
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public enum RespCodeEnum {
	/**处理成功**/
	SUCCESS(200, "处理成功"),
	/**处理失败**/
	ERROR(ExceptionEnum.DATA_HANDLE_ERROR.getCode(), ExceptionEnum.DATA_HANDLE_ERROR.getMsg());

	private Integer code;
	private String message;
	RespCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static RespCodeEnum get(String code) {
		for (RespCodeEnum c : values()) {
			if (c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}
}
