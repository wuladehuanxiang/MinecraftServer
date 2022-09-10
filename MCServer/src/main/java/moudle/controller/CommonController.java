package moudle.controller;

import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.User;
import moudle.service.CommonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: wula
 * @date: 2022/09/10
 **/

@RestController
@RequestMapping("/common")
public class CommonController {


    @Resource
    private CommonService commonService;



}
