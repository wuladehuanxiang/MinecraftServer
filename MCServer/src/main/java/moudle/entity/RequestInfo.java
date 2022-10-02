package moudle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import moudle.common.base.BasePageInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class RequestInfo {

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 数据
     */
    private String jsonString;
    /**
     * 类名
     */
    private String className;

    /**
     * 请求页面信息
     */
    private BasePageInfo basePageInfo;

    public enum requestType {

        /**
         * request 请求信息
         * update 更新信息
         * delete 删除信息
         * create 创建信息
         * verification 验证信息
         */
        request, update, delete, create, verification
    }
}
