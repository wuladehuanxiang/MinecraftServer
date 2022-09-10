package moudle.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import moudle.common.base.RespResult;
import moudle.dao.CommonMapper;
import moudle.dao.UserMapper;
import moudle.entity.RequestInfo;
import moudle.entity.User;
import moudle.service.CommonService;
import moudle.utils.DataUtil;
import moudle.utils.ReflectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: wula
 * @date: 2022/09/10
 * 通用服务接口
 **/
@Slf4j
@DS("core")
@Service
public class CommonServiceImpl extends ServiceImpl<CommonMapper, Object> implements CommonService {

    @Resource
    private CommonMapper commonMapper;


    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object requestService(RequestInfo requestInfo) {
        //若这个请求不是内容获取请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.request)) {
            return RespResult.failed("接口请求错误,此接口为请求专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) != null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }


        return null;
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object updateService(RequestInfo requestInfo) {
        //若这个请求不是更新请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.update)) {
            return RespResult.failed("接口请求错误,此接口为更新专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) != null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }
        return null;
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object deleteService(RequestInfo requestInfo) {
        //若这个请求不是删除请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.delete)) {
            return RespResult.failed("接口请求错误,此接口为删除专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) != null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }


        return null;
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object createService(RequestInfo requestInfo) {
        //若这个请求不是创建请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.create)) {
            return RespResult.failed("接口请求错误,此接口为创建专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) != null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }


        return null;
    }

}
