package moudle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 账号(唯一)
     */
    private String account;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
