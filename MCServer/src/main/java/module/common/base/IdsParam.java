package module.common.base;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * ids数组
 * </p>
 *
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@Data
public class IdsParam {
    /**
     * ids数组
     */
    @NotEmpty(message = "ids数组不能为空")
    private List<Integer> ids;
}
