package moudle.service;

import moudle.entity.RequestInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * 通用的服务
 */
public interface CommonService {
    /**
     * @param requestInfo
     * @return 请求的服务
     */
    public Object requestService(RequestInfo requestInfo) throws IllegalAccessException;

    /**
     * @param requestInfo
     * @return  更新的服务
     */
    public Object updateService(RequestInfo requestInfo) throws IllegalAccessException;

    /**
     * @param requestInfo
     * @return  移除的服务
     */
    public Object deleteService(RequestInfo requestInfo) throws IllegalAccessException;

    /**
     * @param requestInfo
     * @return  创建的服务
     */
    public Object createService(RequestInfo requestInfo) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;


}
