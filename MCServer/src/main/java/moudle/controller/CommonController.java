package moudle.controller;

import com.alibaba.fastjson.JSONObject;
import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.RequestInfo;
import moudle.entity.User;
import moudle.service.CommonService;
import moudle.utils.StringTools;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: wula
 * @date: 2022/09/10
 **/

@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;

    /**
     * 新增实例
     *
     * @param message {
     *                "header": {
     *                "content": ""
     *                },
     *                "content": {
     *                "className": "User",
     *                "requestType": "request",
     *                "basePageInfo": {
     *                "pageNum": 1,
     *                "pageSize": 10
     *                },
     *                "jsonString": "{\"tableName\":\"sys_user\",\"tableFields\":[\"account\",\"name\"],\"params\":[{\"tableField\":\"name\",\"property\":\"name\",\"param\":\"系统\"},{\"tableField\":\"account\",\"property\":\"account\",\"param\":\"123456\"}]}"
     *                }
     *                }
     * @return 结果
     */
    //@ApiOperation(value = "新增服务")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RespResult add(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.commonService.createService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 删除实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespResult delete(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.commonService.deleteService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 更新实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespResult update(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.commonService.updateService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 请求实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "查询")
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public RespResult RespResult(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.commonService.requestService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }


    public String login(JSONObject jsonObject) {
        String name = (String) jsonObject.get("userName");
        String pass = (String) jsonObject.get("password");
        if (StringTools.isEmpty(name) || StringTools.isEmpty(pass)) {
            return JSONObject.toJSONString("用户密码不能为空");
        }
        try {
//            User user = new User(name, "1", true);
//            // 手动在db里面配置新增用户
//            User userInfo = userMapper.selectBySelectiveFields(user);
//            if (userInfo == null) {
//                return JSONObject.toJSONString(ServiceUtil.returnError("用户名不存在!"));
//            }
//            Boolean status = DomainUtil.checkDomain("CORP\\" + name, pass);
//            if (status) {
//                String jwtToken = JwtUtil.createJWT(userInfo.getUserName(), userInfo.getId());
//                JSONObject data = new JSONObject();
//                data.put("jwtToken", jwtToken);
//                data.put("roleId", userMapper.getUserRole(userInfo.getId()));
//                return JSONObject.toJSONString(ServiceUtil.returnSuccessData(data));
//            } else {
//                return JSONObject.toJSONString(ServiceUtil.returnError("用户名或者密码错误!"));
//            }
        } catch (Exception e) {
            return "Error";
        }
        return "";
    }


}
