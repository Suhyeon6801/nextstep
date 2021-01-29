package webserver;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

public class HttpRequestTest {

	private String testDirecotry = "./src/test/resources";

	@Test
	public void request_GET() throws Exception {
		InputStream in = new FileInputStream(new File(testDirecotry + "Http_GET.txt"));
		HttpRequest request = new HttpRequest(in);

		assertEquals("GET", request.getMethod());
		assertEquals("/user/create", request.getPath());
		assertEquals("keep-alive", request.getHeader("Connection"));
		assertEquals("javajigi", request.getParameter("userId"));
	}
	
	@Test
	public void request_POST() throws Exception {
		InputStream in = new FileInputStream(new File(testDirecotry + "Http_POST.txt"));
		HttpRequest request = new HttpRequest(in);

		assertEquals("POST", request.getMethod());
		assertEquals("/user/create", request.getPath());
		assertEquals("keep-alive", request.getHeader("Connection"));
		assertEquals("javajigi", request.getParameter("userId"));
	}


}
