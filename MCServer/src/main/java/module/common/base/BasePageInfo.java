package module.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dragon丿Z
 * @desc 分页信息
 * @date : 2022/09/09 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageInfo {
    /**
     * 当前记录起始索引  1
     */
    private Integer pageNum;

    /**
     * 每页显示记录数  10
     */
    private Integer pageSize;
}
