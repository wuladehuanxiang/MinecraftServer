package moudle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {

    /**
     * 请求类型
     */
    private requestType requestType;

    /**
     * 数据格式
     */
    private String jsonString;

    /**
     * 类名
     */
    private String className;

    enum requestType {
        request_info, update, delete, create
    }
}
