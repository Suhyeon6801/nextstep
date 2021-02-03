package http;

import java.util.HashMap;
import java.util.Map;

import util.HttpRequestUtils;

/**
 * 쿠키 헤더 값에 대한 처리를 담당하는 클래스
 * 
 * @author user
 *
 */
public class HttpCookie {
	private Map<String, String> cookies;
	
	public HttpCookie(String cookieValues) {
		if(cookieValues==null) {
			cookies = new HashMap<String,String>();
			return;
		}
		
		cookies = HttpRequestUtils.parseCookies(cookieValues);
	}
	
	public String getCookie(String name) {
		return cookies.get(name);
	}
}
