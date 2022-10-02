package moudle.common.exceptin;


import moudle.common.enums.ExceptionEnum;

/**
 * 默认异常
 * @author : Dragon丿Z
 * @date : 2022/09/11 14:20
 */
public class DefaultException extends RuntimeException {

    private static final long serialVersionUID = 4206789752267888707L;
    /**
     * 返回码
     */
    protected ExceptionEnum exceptionEnum;

    public DefaultException() {
        super();
    }


    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(String message) {
        super(message);
        this.exceptionEnum = ExceptionEnum.DATA_HANDLE_ERROR;
    }

    public DefaultException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.exceptionEnum = exceptionEnum;
    }

    public DefaultException(ExceptionEnum exceptionEnum, String message) {
        super(message);
        this.exceptionEnum = exceptionEnum;
    }

    public DefaultException(ExceptionEnum exceptionEnum, String message, Throwable cause) {
        super(message, cause);
        this.exceptionEnum = exceptionEnum;
    }

    public DefaultException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMsg(), cause);
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
