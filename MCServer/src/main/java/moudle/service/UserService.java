package moudle.service;

/**
 * 用户管理服务层
 */
public interface UserService {

    public boolean login();

    public boolean register();

    public boolean getMessage(String token);



}
