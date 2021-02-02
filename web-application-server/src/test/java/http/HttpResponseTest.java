package http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import http.HttpResponse;

public class HttpResponseTest {
	private String testDirecotry = "./src/test/resources/";

	@Test
	public void responseForward() throws Exception {
		//Http_Forward.txt의 응답결과는 body에 index.html이 포함되어있어야 함.
		HttpResponse response = new HttpResponse(createOutputStream("Http_Forward.txt"));
		response.forward("/index.html");
	}

	@Test
	public void responseRedirect() throws Exception {
		//Http_Redirect.txt의 응답결과는 header의 Location정보가 /index.html로 포함되어있어야 함.
		HttpResponse response = new HttpResponse(createOutputStream("Http_Redirect.txt"));
		response.sendRedirect("/index.html");
	}

	@Test
	public void responseCookies() throws Exception {
		//Http_Cookie.txt의 응답결과는 header의 Set-Cookie값으로 logined=true값이 포함되어있어야함.
		HttpResponse response = new HttpResponse(createOutputStream("Http_Cookie.txt"));
		response.addHeader("Set-Cookie", "logined=true");
		response.sendRedirect("/index.html");
	}

	private OutputStream createOutputStream(String filename) throws FileNotFoundException {
		return new FileOutputStream(new File(testDirecotry + filename));
	}
}
