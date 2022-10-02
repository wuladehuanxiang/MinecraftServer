package moudle.common.base;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 分页信息
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Api(value = "分页信息")
public class BasePageInfo
{
    /** 当前记录起始索引  1*/
    //@ApiModelProperty(value = "当前记录起始索引",required = true, example = "1")
    private Integer pageNum;

    /** 每页显示记录数  10*/
    //@ApiModelProperty(value = "每页显示记录数",required = true, example = "10")
    private Integer pageSize;
}
