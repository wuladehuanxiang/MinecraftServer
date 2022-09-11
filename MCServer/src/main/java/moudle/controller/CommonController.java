package moudle.controller;

import moudle.common.base.RequestMessage;
import moudle.common.base.RespResult;
import moudle.common.enums.RespCodeEnum;
import moudle.entity.RequestInfo;
import moudle.entity.User;
import moudle.service.CommonService;
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
     * @param message
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


}
