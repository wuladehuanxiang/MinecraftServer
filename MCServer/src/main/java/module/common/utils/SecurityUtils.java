package module.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import module.entity.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 * @author : Dragon丿Z
 * @date : 2022/10/28 14:20
 */
@UtilityClass
@Slf4j
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public Token getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof Token) {
			return (Token) principal;
		}
		log.error("认证失败");
		return null;
	}

	/**
	 * 获取用户
	 */
	public Token getUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return getUser(authentication);
	}

	/**
	 * 获取用户账号
	 * @return
	 */
	public String getUserAccount(){
		Token token = getUser();
		return token != null ? token.getAccount() : "";
	}

	/**
	 * 获取用户uuid
	 * @return
	 */
	public String getUserUuId(){
		Token token = getUser();
		return token != null ? token.getUuid() : null;
	}
}
