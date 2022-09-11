package moudle.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import moudle.common.exceptin.DefaultException;
import moudle.dao.UserMapper;
import moudle.entity.User;
import org.springframework.stereotype.Service;

import moudle.service.UserService;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 用户管理实现层
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
@Slf4j
@DS("core")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User addUser(User user) {
        Assert.isNull(user.getUuid(),"UUID不为空");
        if(user.getUuid() != null){
            throw new DefaultException("请求失败");
        }
        userMapper.insert(user);
        log.info("新增人员成功；");
        return user;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public boolean getMessage(String token) {
        return false;
    }
}
