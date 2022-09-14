package moudle.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import moudle.common.exceptin.DefaultException;
import moudle.dao.SysUserMapper;
import moudle.entity.SysUser;
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
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser addUser(SysUser sysUser) {
        Assert.isNull(sysUser.getUuid(),"UUID不为空");
        if(sysUser.getUuid() != null){
            throw new DefaultException("请求失败");
        }
        sysUserMapper.insert(sysUser);
        log.info("新增人员成功；");
        return sysUser;
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
