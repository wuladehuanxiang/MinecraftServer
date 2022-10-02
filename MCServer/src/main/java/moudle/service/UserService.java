package moudle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import moudle.common.exceptin.DefaultException;
import moudle.entity.SysUser;

/**
 * 用户管理服务层
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public interface UserService extends IService<SysUser> {

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    public SysUser addUser(SysUser sysUser) throws DefaultException;

    public boolean login();

    public boolean register();

    public boolean getMessage(String token);
}
