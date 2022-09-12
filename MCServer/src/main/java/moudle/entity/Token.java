package moudle.entity;

/**
 * @author: wula
 * @date: 2022/09/12
 * 用户登陆用的token
 **/
public class Token {
    /**
     * 用户的uuid
     */
    public String uuid;
    /**
     * 用户的账户
     */
    public String account;
    /**
     * 用户的token值
     */
    public String token;
    /**
     * 到期的时间
     */
    public Long expiration;
}
