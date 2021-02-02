package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 요청라인을 처리하는 클래스
 * <p>
 * HttpRequest의 processRequsetLine 함수를 class로 분리
 * 
 * @author Suhyeon Kim
 *
 */
public class RequestLine {
	private static final Logger log = LoggerFactory.getLogger(RequestLine.class);

	private HttpMethod method;
	private String path;
	private String queryString;

	public RequestLine(String requestLine) {
		log.debug("request line : {}", requestLine);
		String tokens[] = requestLine.split(" ");
		this.method = HttpMethod.valueOf(tokens[0]);

		String[] url = tokens[1].split("\\?");
		this.path = url[0];

		if (url.length == 2) {
			this.queryString = url[1];
		}
	}

	public HttpMethod getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getQueryString() {
		return queryString;
	}
}
