package module.common.base;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dragon丿Z
 * @desc 请求参数头部信息
 * @date : 2022/09/09 14:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Api(value = "请求头")
public class Header {
    /**
     * 请求内容
     */
    //@ApiModelProperty("请求内容")
    private String content;
}
