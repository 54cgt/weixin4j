package com.foxinmy.weixin4j.qy.suite;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.token.TokenStorager;

/**
 * 应用套件ticket的存取
 * 
 * @className SuiteTicketHolder
 * @author jy
 * @date 2015年6月22日
 * @since JDK 1.7
 * @see
 */
public class SuiteTicketHolder {

	private final String suiteId;
	private final String suiteSecret;
	private final TokenStorager tokenStorager;

	public SuiteTicketHolder(String suiteId, String suiteSecret,
			TokenStorager tokenStorager) {
		this.suiteId = suiteId;
		this.suiteSecret = suiteSecret;
		this.tokenStorager = tokenStorager;
	}

	/**
	 * 查找ticket
	 * 
	 * @param suiteId
	 * @return
	 * @throws WeixinException
	 */
	public String getTicket() throws WeixinException {
		return tokenStorager.lookup(getCacheKey()).getAccessToken();
	}

	/**
	 * 获取ticket的key
	 * 
	 * @param suiteId
	 * @return
	 */
	private String getCacheKey() {
		return String.format("qy_suite_ticket_%s", suiteId);
	}

	/**
	 * 缓存ticket
	 * 
	 * @param suiteTicket
	 * @throws WeixinException
	 */
	public void cachingTicket(WeixinSuiteMessage suiteTicket)
			throws WeixinException {
		Token token = new Token(suiteTicket.getSuiteTicket());
		token.setExpiresIn(-1);
		tokenStorager.caching(getCacheKey(), token);
	}

	public String getSuiteId() {
		return this.suiteId;
	}

	public String getSuiteSecret() {
		return this.suiteSecret;
	}

	public TokenStorager getTokenStorager() {
		return this.tokenStorager;
	}
}