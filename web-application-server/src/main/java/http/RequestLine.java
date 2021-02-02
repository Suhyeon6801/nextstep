package http;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HttpRequestUtils;

/**
 * 요청라인을 처리하는 클래스
 * <p>
 * HttpRequest의 processRequsetLine 함수를 class로 분리
 * 
 * @author Suhyeon Kim
 *
 */
public class RequestLine {
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

	private HttpMethod method;
	private String path;
	private Map<String, String> params = new HashMap<String, String>();

	public RequestLine(String requestLine) {
		log.debug("request line : {}", requestLine);
		String tokens[] = requestLine.split(" ");

		if (tokens.length != 3) {
			throw new IllegalArgumentException(requestLine + "이 형식에 맞지 않습니다.");
		}

		method = HttpMethod.valueOf(tokens[0]);
		if (method.isPost()) {
			path = tokens[1];
			return;
		}

		int index = tokens[1].indexOf("?");
		if (index == -1) {
			path = tokens[1];
		} else {
			path = tokens[1].substring(0, index);
			params = HttpRequestUtils.parseQueryString(tokens[1].substring(index + 1));
		}
	}

	public HttpMethod getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getParams() {
		return params;
	}
}
