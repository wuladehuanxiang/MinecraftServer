package moudle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import moudle.entity.User;

/**
 * 用户管理服务层
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户
     * @param user
     * @return
     */
    public User addUser(User user);

    public boolean login();

    public boolean register();

    public boolean getMessage(String token);
}
