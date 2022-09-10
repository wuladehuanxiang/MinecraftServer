package moudle.service.impl;

import moudle.dao.CommonMapper;
import moudle.service.CommonService;

import javax.annotation.Resource;

/**
 * @author: wula
 * @date: 2022/09/10
 **/
public class CommonServiceImpl implements CommonService {

    @Resource
    private CommonMapper commonMapper;


    @Override
    public Object requestService() {
        return null;
    }

    @Override
    public void updateService() {

    }
}
