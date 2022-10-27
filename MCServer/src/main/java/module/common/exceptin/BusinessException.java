package module.common.exceptin;

import module.common.enums.ExceptionEnum;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author : Dragon丿Z
 * @date : 2022/09/11 14:20
 */
public class BusinessException extends DefaultException {
    private static final long serialVersionUID = 1L;

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public BusinessException(ExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum, message);
    }

    public BusinessException(ExceptionEnum exceptionEnum, String message, Throwable cause) {
        super(exceptionEnum, message, cause);
    }
}
