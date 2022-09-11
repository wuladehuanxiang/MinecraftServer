package moudle.entity.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 通用查询条件
 * @Author: Dragon丿Z
 * @Date: 2022/9/11 17:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonSelectForm implements Serializable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 查询字段集合
     */
    private List<String> tableFields;

    /**
     * 查询参数
     */
    private List<Params> params;
}
