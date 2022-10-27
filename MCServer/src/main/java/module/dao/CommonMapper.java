package module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import module.common.base.BasePageInfo;
import module.entity.form.CommonSelectForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 */
@Repository
public interface CommonMapper extends BaseMapper<Object> {

    /**
     * 通用查询列表
     * @param commonSelectForm
     * @param basePageInfo
     * @return
     */
    List<Object> commonSelectList(@Param("form") CommonSelectForm commonSelectForm,
                                  @Param("page") BasePageInfo basePageInfo);

    /**
     * 替换
     * @param commonSelectForm
     * @return
     */
    int replace(@Param("form") CommonSelectForm commonSelectForm);

}
