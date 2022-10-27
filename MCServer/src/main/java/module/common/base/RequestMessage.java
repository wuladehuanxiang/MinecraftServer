package module.common.base;


//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dragon丿Z
 * @desc 请求参数
 * @date : 2022/09/09 14:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Api(value = "请求参数")
public class RequestMessage<T> {
    /**
     * 请求头部信息
     */
    //@ApiModelProperty(value = "请求头部信息")
    private Header header;
    /**
     * 请求内容
     */
    //@ApiModelProperty(value = "请求内容")
    private T content;
}
