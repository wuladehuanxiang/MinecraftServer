package moudle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import moudle.common.base.BasePageInfo;
import moudle.entity.form.CommonSelectForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 */
@Repository
public interface CommonMapper extends BaseMapper<Object> {

    List<Object> commonSelectList(@Param("form") CommonSelectForm commonSelectForm,
                                  @Param("page") BasePageInfo basePageInfo);

    int replace(@Param("form") CommonSelectForm commonSelectForm);

}
