package moudle.service.impl;

import org.springframework.stereotype.Service;

import moudle.service.UserService;
/**
 * 用户管理实现层
 */
@Service
public class UserServiceImpl implements UserService {

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
