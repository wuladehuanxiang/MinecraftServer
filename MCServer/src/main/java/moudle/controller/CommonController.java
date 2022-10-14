package moudle.controller;

import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.RequestInfo;
import moudle.service.CommonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: wula
 * @date: 2022/09/10
 **/

@RestController
@CrossOrigin()
@RequestMapping(value = "/common")
public class CommonController {

    @Resource
    private CommonService CommonService;

    /**
     * 新增实例
     *
     * @param message {
     *                "header": {
     *                "content": ""
     *                },
     *                "content": {
     *                "className": "SysUser",
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
    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RespResult add(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.createService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 删除实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "删除")
    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespResult delete(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.deleteService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 更新实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "更新")
    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespResult update(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.updateService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 请求实例
     *
     * @param message
     * @return
     */
    //@ApiOperation(value = "查询")
    @CrossOrigin
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public RespResult request(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.requestService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }

    @CrossOrigin
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public RespResult verification(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.verificationService(message.getContent()), RespCodeEnum.SUCCESS.getMessage());
    }



}
