package moudle.common.base;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import moudle.common.enums.ExceptionEnum;
import moudle.common.enums.RespCodeEnum;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @desc 响应结果
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "响应接口")
public class RespResult<T> implements Serializable {
	public static final long serialVersionUID = 42L;
	/**
	 * 响应数据
 	 */
	@ApiModelProperty(value = "响应数据")
	private T data;
	/**
	 * 响应编码(200:正常，其他:异常)
 	 */
	@ApiModelProperty(value = "响应编码(200:正常，其他:异常)")
	private Integer respCode;
	/**
	 * 响应消息
 	 */
	@ApiModelProperty(value = "响应消息")
	private String respMsg;

	public static <T> RespResult<T> of(T data, Integer code, String msg) {
		return new RespResult<>(data, code, msg);
	}

	public static <T> RespResult<T> succeed(String msg) {
		return of(null, RespCodeEnum.SUCCESS.getCode(), msg);
	}

	public static <T> RespResult<T> succeed() {
		return of(null, RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage());
	}

	public static <T> RespResult<T> succeed(T model) {
		return of(model, RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage());
	}

	public static <T> RespResult<T> succeed(T model, String msg) {
		return of(model, RespCodeEnum.SUCCESS.getCode(), msg);
	}

	public static <T> RespResult<T> failed(ExceptionEnum exceptionEnum) {
		return failed(exceptionEnum.getCode(), exceptionEnum.getMsg());
	}

	public static <T> RespResult<T> failed(String msg) {
		return failed(ExceptionEnum.DATA_HANDLE_ERROR.getCode(),msg);
	}

	public static <T> RespResult<T> failed(Integer code, String msg) {
		return of(null, code, msg);
	}

	/**
	 * 调用结果是否成功
	 *
	 * @param result result
	 * @return the boolean
	 */
	public static boolean isSuccess(RespResult result) {
		return Objects.nonNull(result) && ObjectUtils.nullSafeEquals(RespCodeEnum.SUCCESS.getCode(), result.getRespCode());
	}
}
