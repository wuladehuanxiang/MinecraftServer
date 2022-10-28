package module.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import module.common.utils.SecurityUtils;
import module.common.utils.StringTools;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 * </p>
 *
 * @author : Dragon丿Z
 * @date : 2022/10/28 14:20
 */

@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date nowDate = new Date();
        this.setFieldValByName("createTime", nowDate, metaObject);
        this.setFieldValByName("updateTime", nowDate, metaObject);
        String createBy = String.valueOf(getFieldValByName("createBy",metaObject));
        String updateBy = String.valueOf(getFieldValByName("updateBy",metaObject));
        if(StringTools.isEmpty(createBy)){
            this.setFieldValByName("createBy", getUserCode(), metaObject);
        }
        if(StringTools.isEmpty(updateBy)){
            this.setFieldValByName("updateBy", getUserCode(), metaObject);
        }

    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        String updateBy = String.valueOf(getFieldValByName("updateBy",metaObject));
        if(StringTools.isEmpty(updateBy)){
            this.setFieldValByName("updateBy", getUserCode(), metaObject);
        }
    }

    /**
     * 获取用户账号
     * @return
     */
    private String getUserCode(){
        try {
            return SecurityUtils.getUser().getAccount();
        }catch (Exception e){
            log.warn("getUserCode null");
        }
        return "";
    }
}
