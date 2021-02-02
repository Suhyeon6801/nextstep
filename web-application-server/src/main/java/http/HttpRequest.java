package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HttpRequestUtils;
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
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, String> params = new HashMap<String, String>();
	private RequestLine requestLine;

	public HttpRequest(InputStream in) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = br.readLine();
			if (line == null) {
				return;
			}

			requestLine = new RequestLine(line);
			while (!line.equals("")) {
				log.debug("header : {}", line);
				String[] tokens = line.split(":");
				headers.put(tokens[0].trim(), tokens[1].trim());
				line = br.readLine();

				if ("POST".equals(getMethod())) {
					String body = IOUtils.readData(br, Integer.parseInt(headers.get("Content-Length")));
					params = HttpRequestUtils.parseQueryString(body);
				} else {
					params = requestLine.getParams();
				}
			}
		} catch (IOException io) {

		}
	}

	public HttpMethod getMethod() {
		return requestLine.getMethod();
	}

	public String getPath() {
		return requestLine.getPath();
	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public String getParameter(String name) {
		return params.get(name);
	}
}
