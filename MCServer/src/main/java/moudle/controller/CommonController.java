package moudle.controller;

import com.alibaba.fastjson.JSONObject;
import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.RequestInfo;
import moudle.entity.SysUser;
import moudle.service.CommonService;
import moudle.utils.JwtUtil;
import moudle.utils.StringTools;
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


    public String login(RequestInfo requestInfo) {
        JSONObject jsonObject = JSONObject.parseObject(requestInfo.getJsonString());
        String userName = jsonObject.getString("account");
        String passWord = jsonObject.getString("password");

        if (StringTools.isEmpty(userName) || StringTools.isEmpty(passWord)) {
            return JSONObject.toJSONString("用户密码不能为空");
        }
        try {
            SysUser sysUser = (SysUser) commonService.requestService(requestInfo);
            if (sysUser == null) {
                return JSONObject.toJSONString(JSONObject.toJSONString("用户不存在"));
            }
            if (sysUser.getAccount().equals(userName) && sysUser.getPassword().equals(passWord)) {
                String jwtToken = JwtUtil.createJWT(sysUser.getAccount(), sysUser.getUuid());
                JSONObject data = new JSONObject();
                data.put("jwtToken", jwtToken);
                data.put("roleId", sysUser.getUuid());
                return JSONObject.toJSONString(data);
            }
        } catch (Exception e) {
            return "Error";
        }
        return "Error";
    }


}
