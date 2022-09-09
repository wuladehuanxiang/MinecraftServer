package moudle.common.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import moudle.utils.StringTools;

import java.util.List;

/**
 * @desc 分页业务处理
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage(RequestPageMessage msg) {
        if(msg != null  && msg.getBasePageInfo() != null){
            BasePageInfo pageInfo = msg.getBasePageInfo();
            Integer pageNum = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            if (StringTools.isNotNull(pageNum) && StringTools.isNotNull(pageSize)) {
                PageHelper.startPage(pageNum, pageSize);
            }
        }
    }

    /**
     * 响应请求分页数据
     */
    protected RespPageInfo getPageData(List<?> list)
    {
        RespPageInfo rspData = new RespPageInfo(list);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}

