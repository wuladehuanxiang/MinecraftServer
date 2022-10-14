package moudle.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import moudle.common.base.BasePageInfo;
import moudle.common.exceptin.DefaultException;
import moudle.dao.*;
import moudle.data.StaticData;
import moudle.entity.RequestInfo;
import moudle.entity.SysUser;
import moudle.entity.form.CommonSelectForm;
import moudle.entity.form.Params;
import moudle.service.CommonService;
import moudle.utils.ReflectUtil;
import moudle.utils.StringTools;
import moudle.utils.UUIdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.Ref;
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
    private SysUserMapper SysUserMapper;
    @Resource
    private BmCategoryMapper BmCategoryMapper;
    @Resource
    private BmTypeMapper BmTypeMapper;
    @Resource
    private BmItemMapper BmItemMapper;


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
            //说明拿了一批数据 那么要拿多少 由他自己定义
            BasePageInfo basePageInfo = requestInfo.getBasePageInfo();
            //为分页设置数值
            basePageInfo.setPageNum((basePageInfo.getPageNum() - 1) * basePageInfo.getPageSize());
            //生成物体对象
            Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));
            //生成选取格式
            CommonSelectForm commonSelectForm = new CommonSelectForm();
            List<String> field = new ArrayList<>();
            List<Params> params = new ArrayList<>();
            //设置表名
            commonSelectForm.setTableName(ReflectUtil.getTableName(requestInfo.getClassName()));
            //设置查询参数
            for (Field fieldName : obj.getClass().getDeclaredFields()
            ) {
                if (fieldName.getName().equals("password")) {
                    continue;
                }
                //遍历所有属性判断是否需要进行加入判断条件
                fieldName.setAccessible(true);
                field.add(ReflectUtil.getTableName(fieldName.getName()));
                try {
                    if (fieldName.get(obj) != null && !StringTools.isEmpty(fieldName.get(obj).toString())) {
                        params.add(new Params() {{
                            //若这个地方有值 说明他需要被加入查询条件
                            this.setParam(fieldName.get(obj).toString());
                            this.setProperty(ReflectUtil.getTableName(fieldName.getName()));
                            this.setTableField(ReflectUtil.getTableName(fieldName.getName()));
                        }});
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            commonSelectForm.setParams(params);
            commonSelectForm.setTableFields(field);
            //取一定量的数量 返回
            List<Object> objects = baseMapper.commonSelectList(commonSelectForm, basePageInfo);
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

        if (!StpUtil.hasRole("admin")) {
            return "你没有这个权限";
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
        if (!StpUtil.hasRole("admin")) {
            return "你没有这个权限";
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
        if (!requestInfo.getClassName().equals("SysUser")) {
            if (!StpUtil.hasRole("admin")) {
                return "你没有这个权限";
            }
        }


        //通用逻辑处理
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

        BaseMapper outMapper = this.getMapper(requestInfo);

        //特殊逻辑处理

        if (obj instanceof SysUser) {
            SysUser user = (SysUser) obj;

            BasePageInfo basePageInfo = new BasePageInfo();
            //为分页设置数值
            basePageInfo.setPageNum(0);
            basePageInfo.setPageSize(9);
            CommonSelectForm commonSelectForm = new CommonSelectForm();
            commonSelectForm.setTableName("sys_user");
            List<String> field = new ArrayList<>();
            List<Params> params = new ArrayList<>();
            field.add("account");
            params.add(new Params() {{
                //若这个地方有值 说明他需要被加入查询条件
                this.setParam(user.getAccount());
                this.setProperty("account");
                this.setTableField("account");
            }});
            commonSelectForm.setParams(params);
            commonSelectForm.setTableFields(field);

            List<Object> objects = baseMapper.commonSelectList(commonSelectForm, basePageInfo);

            if (objects.size() >= 1) {

                if (StringUtil.isEmpty(uuid)) {
                    throw new DefaultException("uuid 生成失败");
                }
                return "此用户已经存在";

            } else {
                int out = outMapper.insert(obj);

                StpUtil.login(((SysUser) obj).getUuid());
                StaticData.userRole.put(uuid, "0");
                StpUtil.checkLogin();
                return out;
            }
        } else {
            int out = outMapper.insert(obj);
            if (StringUtil.isEmpty(uuid)) {
                throw new DefaultException("uuid 生成失败");
            }
            return out;
        }


    }

    @Override
    public Object verificationService(RequestInfo requestInfo) throws DefaultException {
        if (!requestInfo.getRequestType().equals(RequestInfo.requestType.verification.toString())) {
            throw new DefaultException("接口请求错误,此接口为验证专用");
        }
        Class mapper = ReflectUtil.hasThisMapper(requestInfo.getClassName() + "Mapper");
        if (mapper == null) {
            throw new DefaultException("反射请求mapper失败，请联系后台管理员");
        }

        if (StpUtil.isLogin()) {
            return true;
        }

        Object obj = ReflectUtil.getObjectFJSON(requestInfo.getJsonString(), ReflectUtil.hasThisClass(requestInfo.getClassName()));

        if (obj instanceof SysUser) {
            CommonSelectForm commonSelectForm = new CommonSelectForm();
            List<String> field = new ArrayList<>();
            List<Params> params = new ArrayList<>();
            //设置表名
            commonSelectForm.setTableName(ReflectUtil.getTableName(requestInfo.getClassName()));
            //设置查询参数
            for (Field fieldName : obj.getClass().getDeclaredFields()
            ) {
                //遍历所有属性判断是否需要进行加入判断条件
                fieldName.setAccessible(true);
                field.add(fieldName.getName());
                params.add(new Params() {{
                    try {
                        //若这个地方有值 说明他需要被加入查询条件
                        if (fieldName.get(obj) != null) {
                            this.setParam(fieldName.get(obj).toString());
                            this.setProperty(fieldName.getName());
                            this.setTableField(ReflectUtil.getTableName(fieldName.getName()));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }});
            }
            commonSelectForm.setParams(params);
            commonSelectForm.setTableFields(field);
            //取一定量的数量 返回
            BasePageInfo basePageInfo = new BasePageInfo() {{
                this.setPageSize(1);
                this.setPageNum(0);
            }};
            List<Object> objects = baseMapper.commonSelectList(commonSelectForm, basePageInfo);
            if (objects.size() == 1) {
                HashMap hashMap = ((HashMap) objects.get(0));
                String uuid = hashMap.get("uuid").toString();
                StpUtil.login(uuid);
                SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
                StaticData.userRole.put(uuid, hashMap.get("isadmin").toString());
                return saTokenInfo;
            } else {
                return "密码错误或此用户不存在";
            }
        } else {
            return false;
        }


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
