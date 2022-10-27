package module.controller;

import module.common.base.RequestMessage;
import module.common.base.RespResult;
import module.entity.RequestInfo;
import module.service.CommonService;
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
    public RespResult create(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.createService(message.getContent()));
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
        return RespResult.succeed(this.CommonService.deleteService(message.getContent()));
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
        return RespResult.succeed(this.CommonService.updateService(message.getContent()));
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
        return RespResult.succeed(this.CommonService.requestService(message.getContent()));
    }

    @CrossOrigin
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public RespResult verification(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.verificationService(message.getContent()));
    }

    @CrossOrigin
    @RequestMapping(value = "/replace", method = RequestMethod.POST)
    public RespResult replace(@Valid @RequestBody RequestMessage<RequestInfo> message) {
        return RespResult.succeed(this.CommonService.replaceService(message.getContent()));
    }
}
