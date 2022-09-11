package moudle.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import moudle.common.base.BasePageInfo;
import moudle.common.base.RespResult;
import moudle.dao.CommonMapper;
import moudle.dao.UserMapper;
import moudle.entity.RequestInfo;
import moudle.entity.User;
import moudle.service.CommonService;
import moudle.utils.DataUtil;
import moudle.utils.ReflectUtil;
import moudle.utils.UUIdUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    @Resource
    private CommonMapper CommonMapper;

    @Resource
    private UserMapper UserMapper;


    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object requestService(RequestInfo requestInfo) throws IllegalAccessException, NoSuchFieldException {
        //若这个请求不是内容获取请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.request.toString())) {
            return RespResult.failed("接口请求错误,此接口为请求专用");
        }
        //没有这个实体类
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }

        if (JSON.parseObject(requestInfo.getJsonString()).getJSONObject("BasePageInfo") != null) {
            //说明拿了一批数据 那么要拿多少 由他自己定义
            BasePageInfo basePageInfo = (BasePageInfo) ReflectUtil.getObjectFJSON(JSON.parseObject(requestInfo.getJsonString()).getString("BasePageInfo"), BasePageInfo.class);
            Class theClass = ReflectUtil.hasThisClass(requestInfo.getClassName());
            QueryWrapper<Object> wrapper = new QueryWrapper<>();
            wrapper.isNotNull("uuid");

            Page<Object> page = new Page<>(1, 4);
//            Page<Object> result = CommonMapper.selectPage((Page<theClass>) page, wrapper);

        }

        System.out.println(JSON.toJSON(requestInfo));

        //UUID为空
        Object uuid = getUUID(requestInfo);


        if (uuid == null) {
            return RespResult.failed("uuid不能为空");
        }
        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {


            if (uuid instanceof ArrayList) {
                return baseMapper.selectBatchIds((ArrayList) uuid);
            } else if (uuid instanceof String) {
                Object object = baseMapper.selectById((String) uuid);
                return object;
            } else {
                return RespResult.succeed("类型错误");
            }

        } else {
            //否则 mapper为NULL说明反射失败了
            return RespResult.failed("反射请求mapper失败，请联系后台管理员");
        }
    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object updateService(RequestInfo requestInfo) throws IllegalAccessException, NoSuchFieldException {
        //若这个请求不是更新请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.update.toString())) {
            return RespResult.failed("接口请求错误,此接口为更新专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }


        //UUID为空
        Object uuid = getUUID(requestInfo);

        if (uuid == null) {
            return RespResult.failed("uuid不能为空");
        }


        //查询并回传
        Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));
        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {
            if (uuid instanceof ArrayList) {
                return "暂时不支持批量修改";
            } else if (uuid instanceof String) {
                int index = baseMapper.updateById(obj);
                return ("请求成功 返回码为" + index);
            } else {
                return ("类型错误");
            }

        } else {
            //否则 mapper为NULL说明反射失败了
            return RespResult.failed("反射请求mapper失败，请联系后台管理员");
        }


    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object deleteService(RequestInfo requestInfo) throws IllegalAccessException, NoSuchFieldException {
        //若这个请求不是删除请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.delete.toString())) {
            return RespResult.failed("接口请求错误,此接口为删除专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }


        //UUID为空
        Object uuid = getUUID(requestInfo);
        if (uuid == null) {
            return RespResult.failed("uuid不能为空");
        }

        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {
            if (uuid instanceof ArrayList) {
                int index = baseMapper.deleteBatchIds((ArrayList) uuid);
                return ("请求成功 返回码为" + index);
            } else if (uuid instanceof String) {
                int index = baseMapper.deleteById((String) uuid);
                return ("请求成功 返回码为" + index);
            } else {
                return ("类型错误");
            }

        } else {
            //否则 mapper为NULL说明反射失败了
            return RespResult.failed("反射请求mapper失败，请联系后台管理员");
        }

    }

    /**
     * @param requestInfo
     * @return 返回处理的结果
     */
    @Override
    public Object createService(RequestInfo requestInfo) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //若这个请求不是创建请求
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.create.toString())) {
            return RespResult.failed("接口请求错误,此接口为创建专用");
        }
        if (ReflectUtil.hasThisClass(requestInfo.getClassName()) == null) {
            return RespResult.failed("不存在此类名，请重新请求");
        }
        //UUID为空
        String uuid = (String) getUUID(requestInfo);
        if (StringUtil.isEmpty(uuid)) {
            return RespResult.failed("uuid不能为空");
        }

        Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));
        for (Field f : obj.getClass().getDeclaredFields()
        ) {
            if (f.getName().equals("uuid")) {
                f.setAccessible(true);
                f.set(obj, UUIdUtils.getUUID());
            }
        }


        BaseMapper baseMapper = this.getMapper(requestInfo);
        if (baseMapper != null) {
            baseMapper.insert(obj);
            return baseMapper.selectById(uuid);
        } else {
            return RespResult.failed("反射请求mapper失败，请联系后台管理员");

        }


    }

    public BaseMapper getMapper(RequestInfo requestInfo) throws IllegalAccessException {
        Class mapper = ReflectUtil.hasThisMapper(requestInfo.getClassName() + "Mapper");
        if (mapper == null) {
            return null;
        }
        for (Field f : this.getClass().getDeclaredFields()
        ) {
            f.setAccessible(true);
            //说明是通用接口里面的 实例化的Mapper
            if ((requestInfo.getClassName() + "Mapper").equals(f.getName())) {
                f.setAccessible(true);
                return (BaseMapper) f.get(this);
            }
        }
        return null;

    }

    public static Object getUUID(RequestInfo requestInfo) throws IllegalAccessException, NoSuchFieldException {

        Object objArray = JSON.parse(requestInfo.getJsonString());

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


    }


}
