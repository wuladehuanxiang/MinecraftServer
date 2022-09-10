package moudle.common.base;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 *  ids数组
 * </p>
 *
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@Data
//@ApiModel(value = "ids数组")
public class IdsParam {
    /**
     * ids数组
     */
    @NotEmpty(message = "ids数组不能为空")
    //@ApiModelProperty(value = "ids数组",required = true)
    private List<Integer> ids;
}
