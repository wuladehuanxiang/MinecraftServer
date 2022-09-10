package moudle.common.base;


//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 请求参数
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Api(value = "请求分页信息")
public class RequestPageMessage<T> extends RequestMessage<T>{
    /**
     * 分页信息
     */
    //@ApiModelProperty(value = "分页信息")
    private BasePageInfo basePageInfo ;
}
