package moudle.service;

import moudle.entity.RequestInfo;

/**
 * 通用的服务
 */
public interface CommonService {
    /**
     * @param requestInfo
     * @return 请求的服务
     */
    public Object requestService(RequestInfo requestInfo);

    /**
     * @param requestInfo
     * @return  更新的服务
     */
    public Object updateService(RequestInfo requestInfo);

    /**
     * @param requestInfo
     * @return  移除的服务
     */
    public Object deleteService(RequestInfo requestInfo);

    /**
     * @param requestInfo
     * @return  创建的服务
     */
    public Object createService(RequestInfo requestInfo);


}
