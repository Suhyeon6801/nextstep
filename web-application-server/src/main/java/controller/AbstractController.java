package controller;

import http.HttpMethod;
import http.HttpRequest;
import http.HttpResponse;

/**
 * HTTP 메소드(GET, POST)에 따라 다른 처리를 할 수 있는 추상클래스
 * 
 * @author Suhyeon Kim
 *
 */
public abstract class AbstractController implements Controller {

	@Override
	public void service(HttpRequest request, HttpResponse response) {
		HttpMethod method = request.getMethod();

		if (method.isPost()) {
			doPost(request, response);
		} else {
			doGet(request, response);
		}
	}

	protected void doPost(HttpRequest request, HttpResponse response) {

	}

	protected void doGet(HttpRequest request, HttpResponse response) {

	}

}
