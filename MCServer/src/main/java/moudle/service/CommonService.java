package moudle.service;

import moudle.common.exceptin.DefaultException;
import moudle.entity.RequestInfo;

/**
 * 通用的服务
 */
public interface CommonService {
    /**
     * @param requestInfo
     * @return 请求的服务
     */
    public Object requestService(RequestInfo requestInfo) throws DefaultException;

    /**
     * @param requestInfo
     * @return 更新的服务
     */
    public Object updateService(RequestInfo requestInfo) throws DefaultException;

    /**
     * @param requestInfo
     * @return 移除的服务
     */
    public Object deleteService(RequestInfo requestInfo) throws DefaultException;

    /**
     * @param requestInfo
     * @return 创建的服务
     */
    public Object createService(RequestInfo requestInfo) throws DefaultException;

    public Object verificationService(RequestInfo requestInfo) throws DefaultException;

}
