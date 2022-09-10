package moudle.controller;

//import io.swagger.annotations.ApiOperation;
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
     *
     * @param message
     * {
     * 	"header": {
     * 		"content": ""
     *        },
     * 	"content": {
     * 		"uuid": null,
     * 		"account": "test",
     * 		"name": "测试",
     * 		"password": "123456"
     *    }
     * }
     *
     * @return
     * {
     *     "data": {
     *         "uuid": "f369cb79c27cfd2e83d97aa5347dc1ff",
     *         "account": "test",
     *         "name": "测试",
     *         "password": "123456"
     *     },
     *     "respCode": 200,
     *     "respMsg": "处理成功"
     * }
     */
    //@ApiOperation(value = "新增用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespResult<User> add(@Valid @RequestBody RequestMessage<User> message) {
        User user = this.userService.addUser(message.getContent());
        return RespResult.succeed(user,RespCodeEnum.SUCCESS.getMessage());
    }
}
