package module.utils;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public class UUIdUtils {

    /**
     * 返回uuid 去掉"-"符号
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
