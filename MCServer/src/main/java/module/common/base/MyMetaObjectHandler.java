package module.common.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  自动填充信息
 * @Author: Dragon丿Z
 * @Date: 2022/10/27 22:13
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时的填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //三个参数：字段名，字段值，元对象参数
        this.setFieldValByName("createBy","admin",metaObject);
        this.setFieldValByName("updateBy","admin",metaObject);
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 修改时的填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy","admin",metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}

