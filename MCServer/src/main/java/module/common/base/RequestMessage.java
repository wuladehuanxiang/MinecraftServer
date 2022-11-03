package module.common.base;

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
public class RequestMessage<T> {
    /**
     * 请求头部信息
     */
    private Header header;
    /**
     * 请求内容
     */
    private T content;
}
