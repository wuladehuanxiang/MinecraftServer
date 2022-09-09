package moudle.controller;

import io.swagger.annotations.ApiOperation;
import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.User;
import moudle.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户管理控制层
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增用户
     * @param message
     * @return
     */
    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RespResult add(@Valid @RequestBody RequestMessage<User> message) {
        this.userService.addUser(message.getContent());
        return RespResult.succeed(RespCodeEnum.SUCCESS.getMessage());
    }
}
