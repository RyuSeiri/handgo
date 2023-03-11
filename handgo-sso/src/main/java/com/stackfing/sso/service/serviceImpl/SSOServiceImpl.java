package com.stackfing.sso.service.serviceImpl;

import com.stackfing.common.utils.CookieUtil;
import com.stackfing.common.utils.HandgoResult;
import com.stackfing.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fing
 * @Description: 单点登录
 * @Date: 下午2:04 18-1-3
 */
@Service
public class SSOServiceImpl implements SSOService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 验证用户信息
	 *
	 * @param type
	 * @param content
	 * @return
	 */
	@Override
	public HandgoResult checkData(Integer type, String content) {
		HandgoResult handgoResult = null;
		if (type == 1) {
			//用户名
			handgoResult = new HandgoResult().Build(200).Build("ok");
		}

		if (type == 2) {
			//密码
		}

		if (type == 3) {
			//邮箱
		}

		return handgoResult;
	}

	/**
	 * 验证用户登录
	 *
	 * @param account
	 * @param password
	 * @param response
	 * @return
	 */
	@Override
	public HandgoResult login(String account, String password, HttpServletResponse response) {
		HandgoResult handgoResult = null;
		if (account.equals("a") && password.equals("a")) {
			handgoResult = new HandgoResult();
			handgoResult.Build(200).Build("ok");
			ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
			String uuid = UUID.randomUUID().toString();
			Cookie cookie = new Cookie("token", uuid);
			CookieUtil.addCookie(new Cookie[]{cookie}, response);
			stringStringValueOperations.set(uuid, "account", 1L, TimeUnit.DAYS);
			handgoResult.setData(null);
		}
		if (handgoResult == null) {
			handgoResult = new HandgoResult().Build("登录失败").Build(400);
		}
		System.out.println(handgoResult);
		return handgoResult;
	}


	@Override
	public HandgoResult logout(String token) {
		redisTemplate.delete(token);
		return HandgoResult.success("ok");
	}

	/**
	 * 用户是否在线
	 *
	 * @param token
	 * @return
	 */
	@Override
	public boolean isAlive(String token) {
		ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
		if (null == stringStringValueOperations.get(token)) {
			return false;
		} else {
			return true;
		}
	}
}
