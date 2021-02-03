package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import util.IOUtils;

/**
 * 클라이언트 요청 데이터에서 요청라인을 읽고 헤더를 읽는 로직을 수행하는 class
 * <p>
 * 요청데이터를 읽고 각 데이터를 사용하기 좋은 형태로 분리
 * 
 * @author user
 *
 */
public class HttpRequest {
	private RequestLine requestLine;
	private HttpHeaders headers;
	private RequestParams requestParams = new RequestParams();

	public HttpRequest(InputStream in) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			requestLine = new RequestLine(createRequestLine(br));
			requestParams.addQueryString(requestLine.getQueryString());
			headers = processHeaders(br);
			requestParams.addBody(IOUtils.readData(br, headers.getContentLength()));
		} catch (IOException io) {

		}
	}

	private String createRequestLine(BufferedReader br) throws IOException {
		String line = br.readLine();
		if (line == null) {
			throw new IllegalStateException();
		}
		return line;
	}

	private HttpHeaders processHeaders(BufferedReader br) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String line;
		while (!(line = br.readLine()).equals("")) {
			headers.add(line);
		}
		return headers;
	}

	public HttpMethod getMethod() {
		return requestLine.getMethod();
	}
	
	public HttpCookie getCookie() {
		return new HttpCookie(getHeader("Cookie"));
	}

	public String getPath() {
		return requestLine.getPath();
	}

	public String getHeader(String name) {
		return headers.getHeader(name);
	}

	public String getParameter(String name) {
		return requestParams.getParameter(name);
	}
}
