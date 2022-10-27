package module.service;

import module.common.exceptin.DefaultException;
import module.entity.RequestInfo;

/**
 * 通用的服务
 */
public interface CommonService {

    /**
     * 创建的服务
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object createService(RequestInfo requestInfo) throws DefaultException;

    /**
     * 移除的服务
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object deleteService(RequestInfo requestInfo) throws DefaultException;

    /**
     * 更新的服务
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object updateService(RequestInfo requestInfo) throws DefaultException;

    /**
     * 请求的服务
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object requestService(RequestInfo requestInfo) throws DefaultException;

    /**
     * 校驗
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object verificationService(RequestInfo requestInfo) throws DefaultException;

    /**
     * 替換
     *
     * @param requestInfo
     * @return
     * @throws DefaultException
     */
    public Object replaceService(RequestInfo requestInfo) throws DefaultException;
}
