package http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 응답 데이터를 처리하는 클래스
 * <p>
 * 응답 데이터의 상태에 따라 적절한 HTTP 헤더를 처리하는 부분
 * 특히, HTML, CSS, JS파일을 읽어 반환하는 부분과 302상태 코드를 처리하는 부분
 * 쿠키 추가와 같이 HTTP 헤더에 임의의 값을 추가
 * 
 * @author user
 *
 */
public class HttpResponse {
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
	private DataOutputStream dos = null;
	private Map<String, String> headers = new HashMap<String, String>();

	public HttpResponse(OutputStream out) {
		dos = new DataOutputStream(out);
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	public void forward(String url) {
		try {
			byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
			if (url.endsWith(".css")) {
				headers.put("Content-Type", "text/css");
			} else if (url.endsWith(".js")) {
				headers.put("Content-Type", "application/javascript");
			} else {
				headers.put("Content-Type", "text/html;charset=utf-8");
			}
			headers.put("Content-Type", body.length + "");
			response200Header(body.length);
			responseBody(body);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void forwardBody(String body) {
		byte[] contents = body.getBytes();
		headers.put("Content-Type", "text/html;charset=utf-8");
		headers.put("Content-Type", contents.length + "");
		response200Header(contents.length);
		responseBody(contents);
	}

	public void sendRedirect(String redirectUrl) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			processHeaders();
			dos.writeBytes("Location: " + redirectUrl + " \r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void response200Header(int length) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			processHeaders();
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void responseBody(byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void processHeaders() {
		try {
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				dos.writeBytes(key + " : " + headers.get(key) + " \r\n");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}
