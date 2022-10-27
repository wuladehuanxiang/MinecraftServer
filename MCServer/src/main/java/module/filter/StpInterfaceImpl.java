package module.filter;

/**
 * @author: wula
 * @date: 2022/10/13
 **/

import cn.dev33.satoken.stp.StpInterface;
import module.data.StaticData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();

        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        //从静态缓存取user
        if ("1".equals(StaticData.userRole.get(loginId))) {
            list.add("admin");
            list.add("super-admin");
        }
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        return list;
    }

}
