package moudle.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import moudle.common.base.BasePageInfo;
import moudle.common.exceptin.DefaultException;
import moudle.dao.CommonMapper;
import moudle.dao.UserMapper;
import moudle.entity.RequestInfo;
import moudle.entity.form.CommonSelectForm;
import moudle.service.CommonService;
import moudle.utils.ReflectUtil;
import moudle.utils.UUIdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: wula
 * @date: 2022/09/10
 * 通用服务接口
 **/
@Slf4j
@DS("core")
@Service
public class CommonServiceImpl extends ServiceImpl<CommonMapper, Object> implements CommonService {



    //这个地方 请一定记得添加 你需要使用到的MAPPER 否则会获取不到mapper
    @Resource
    private CommonMapper CommonMapper;
    @Resource
    private UserMapper UserMapper;


    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object requestService(RequestInfo requestInfo) {
        //若这个请求不是内容获取请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.request.toString())) {
            throw new DefaultException("接口请求错误,此接口为请求专用");
        }
        //没有这个实体类
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            throw new DefaultException("不存在此类名，请重新请求");
        }

        if (requestInfo.getBasePageInfo() != null && requestInfo.getJsonString() != null) {
//            BaseMapper baseMapper = this.getMapper(requestInfo);
            //说明拿了一批数据 那么要拿多少 由他自己定义
            BasePageInfo basePageInfo = requestInfo.getBasePageInfo();
            basePageInfo.setPageNum((basePageInfo.getPageNum() - 1) * basePageInfo.getPageSize());


            String replace = requestInfo.getJsonString().replace("\\", "");


            CommonSelectForm form = JSONObject.parseObject(replace, CommonSelectForm.class);
            //取一定量的数量 返回
            List<Object> objects = baseMapper.commonSelectList(form, basePageInfo);

            for (Object o:objects
                 ) {
                HashMap hashMap=(HashMap)o;
            }


            return objects;
        }

        //UUID为空
        Object uuid = getUUID(requestInfo);
        if (uuid == null) {
            throw new DefaultException("uuid不能为空");
        }
        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {
            if (uuid instanceof ArrayList) {
                return baseMapper.selectBatchIds((ArrayList) uuid);
            } else if (uuid instanceof String) {
                Object object = baseMapper.selectById((String) uuid);
                return object;
            } else {
                throw new DefaultException("类型错误");
            }

        } else {
            //否则 mapper为NULL说明反射失败了
            throw new DefaultException("反射请求mapper失败，请联系后台管理员");
        }
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object updateService(RequestInfo requestInfo) {
        //若这个请求不是更新请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.update.toString())) {
            throw new DefaultException("接口请求错误,此接口为更新专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            throw new DefaultException("不存在此类名，请重新请求");
        }

        //UUID为空
        Object uuid = getUUID(requestInfo);

        if (uuid == null) {
            throw new DefaultException("uuid不能为空");
        }

        //查询并回传
        Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));
        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {
            if (uuid instanceof ArrayList) {
                throw new DefaultException("暂时不支持批量修改");
            } else if (uuid instanceof String) {
                int index = baseMapper.updateById(obj);
                return index;
            } else {
                throw new DefaultException("类型错误");
            }
        } else {
            //否则 mapper为NULL说明反射失败了
            throw new DefaultException("反射请求mapper失败，请联系后台管理员");
        }
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object deleteService(RequestInfo requestInfo) {
        //若这个请求不是删除请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.delete.toString())) {
            throw new DefaultException("接口请求错误,此接口为删除专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            throw new DefaultException("不存在此类名，请重新请求");
        }

        //UUID为空
        Object uuid = getUUID(requestInfo);
        if (uuid == null) {
            throw new DefaultException("uuid不能为空");
        }

        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (uuid instanceof ArrayList) {
            int index = baseMapper.deleteBatchIds((ArrayList) uuid);
            return index;
        } else if (uuid instanceof String) {
            int index = baseMapper.deleteById((String) uuid);
            return index;
        } else {
            throw new DefaultException("类型错误");
        }
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object createService(RequestInfo requestInfo) {
        //若这个请求不是创建请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.create.toString())) {
            throw new DefaultException("接口请求错误,此接口为创建专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            throw new DefaultException("不存在此类名，请重新请求");
        }

        Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));

        String uuid = "";
        for (Field f : obj.getClass().getDeclaredFields()
        ) {
            if (f.getName().equals("uuid")) {
                f.setAccessible(true);
                try {
                    uuid = UUIdUtils.getUUID();
                    f.set(obj, uuid);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        BaseMapper baseMapper = this.getMapper(requestInfo);

        baseMapper.insert(obj);
        if (StringUtil.isEmpty(uuid)) {
            throw new DefaultException("uuid 生成失败");
        }
        return baseMapper.selectById(uuid);
    }

    public BaseMapper getMapper(RequestInfo requestInfo) {
        Class mapper = ReflectUtil.hasThisMapper(requestInfo.getClassName() + "Mapper");
        if (mapper == null) {
            throw new DefaultException("反射请求mapper失败，请联系后台管理员");
        }
        for (Field f : this.getClass().getDeclaredFields()
        ) {
            f.setAccessible(true);
            //说明是通用接口里面的 实例化的Mapper
            if ((requestInfo.getClassName() + "Mapper").equals(f.getName())) {
                f.setAccessible(true);
                try {
                    return (BaseMapper) f.get(this);
                } catch (IllegalAccessException e) {
                    throw new DefaultException("反射请求mapper失败，请联系后台管理员", e);
                }
            }
        }
        throw new DefaultException("反射请求mapper失败，请联系后台管理员");
    }

    public static Object getUUID(RequestInfo requestInfo) {
        Object objArray = JSON.parse(requestInfo.getJsonString());

        try {
            if (objArray instanceof JSONArray) {
                Class c = ReflectUtil.hasThisClass(requestInfo.getClassName());
                JSONArray jsonArray = JSON.parseArray(requestInfo.getJsonString());
                ArrayList objectList = (ArrayList) jsonArray.toJavaList(c);
                ArrayList<String> uuids = new ArrayList<>();

                for (Object o : objectList
                ) {
                    for (Field f : o.getClass().getDeclaredFields()
                    ) {
                        if (f.getName().equals("uuid")) {
                            f.setAccessible(true);
                            uuids.add(f.get(o).toString());
                        }
                    }
                }
                return uuids;
            } else {
                Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));
                String uuid = "";

                for (Field f : obj.getClass().getDeclaredFields()
                ) {
                    f.setAccessible(true);
                    if (f.getName().equals("uuid")) {
                        uuid = f.get(obj).toString();
                    }
                }
                return uuid;
            }
        } catch (IllegalAccessException e) {
            throw new DefaultException("uuid获取异常", e);
        }
    }
}
