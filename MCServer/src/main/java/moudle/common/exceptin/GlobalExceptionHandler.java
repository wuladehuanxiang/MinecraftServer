package moudle.common.exceptin;

import lombok.extern.slf4j.Slf4j;
import moudle.common.base.RespResult;
import moudle.common.enums.ExceptionEnum;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.sql.DataTruncation;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常
 * @author : Dragon丿Z
 * @date : 2022/09/11 14:20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final static String ACCESS_DENIED_EXCEPTION_CLASS_NAME = "org.springframework.security.access.AccessDeniedException";

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public RespResult handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(e.getExceptionEnum().getCode(),e.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = DefaultException.class)
    @ResponseBody
    public RespResult handleBaseException(DefaultException e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(e.getExceptionEnum().getCode(),e.getMessage());
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public RespResult handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(ExceptionEnum.WEB_REQ_ERROR);
    }

    /**
     * 参数绑定异常（@Valid）
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public RespResult handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    @ResponseBody
    public RespResult handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private RespResult wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        String msgContent = "";
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msgContent = error.getDefaultMessage() == null ? "" : error.getDefaultMessage();
            msg.append(msgContent);
        }
        String resultMsg = msg.toString().replaceFirst(", ","");
        log.error("参数绑定校验异常 {}",resultMsg);
        return RespResult.failed(ExceptionEnum.VALID_ERROR.getCode(),resultMsg);
    }

    /**
     * 非法参数异常(Assert断言)
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({IllegalArgumentException.class,
            IllegalStateException.class})
    @ResponseBody
    public RespResult handleIllegalArgumentException(Exception e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(ExceptionEnum.VALID_ERROR.getCode(),e.getMessage());
    }



    /**
     * 唯一键重复
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public RespResult handleIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(ExceptionEnum.CODE_EXIST);
    }


    /**
     * 数据不规范
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = DataTruncation.class)
    @ResponseBody
    public RespResult handleDataTruncation(DataTruncation e) {
        log.error(e.getMessage(), e);
        return RespResult.failed(ExceptionEnum.DEVICE_EXCESS);
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        if(isExistExceptionByClassName(ACCESS_DENIED_EXCEPTION_CLASS_NAME,e)){
            return RespResult.failed(ExceptionEnum.AUTH_FAIL.getCode(), e.getMessage());
        }
        return RespResult.failed(ExceptionEnum.DATA_HANDLE_ERROR);
    }

    /**
     * 根据异常类型判断是否存在该异常
     * @param exceptionClassName
     * @param e
     * @return
     */
    private boolean isExistExceptionByClassName(String exceptionClassName,Exception e){
        String exceptionMsg = e.toString();
        if(exceptionMsg.startsWith(exceptionClassName)){
            return true;
        }
       return false;
    }
}
