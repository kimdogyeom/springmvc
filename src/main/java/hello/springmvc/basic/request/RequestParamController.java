package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		log.info("username={}, age={}", username, age);

		response.getWriter().write("ok");
	}

	@ResponseBody	// View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
		@RequestParam("username") String memberName,
		@RequestParam("age")int memberAge) {
		log.info("username={}, age={}", memberName, memberAge);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(
		@RequestParam String username,
		@RequestParam int age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}		// 파라미터 이름과 변수명이 같으면 생략이 가능하다.

	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}		// 파라미터 이름과 변수명이 같으면 @RequestParam 애노테이션 마저도 생략이 가능하다.
			// 추가로 String, int, Integer 같은 단순타입이어야 한다.

	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
				@RequestParam(required = true) String username,
				@RequestParam(required = false) Integer age) {		// int타입은 기본형 타입이기 때문에 null이 들어갈 수 없다.
																	// Integer로 바꾸어서 null을 넣음
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
		@RequestParam(required = true, defaultValue = "guest") String username,
		@RequestParam(required = false, defaultValue = "-1") Integer age) {
				// defaultvalue는 빈 문자의 경우에도 설정한 기본 값이 적용된다.
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam MultiValueMap<String, Object> paramMap) {
		log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute HelloData helloData){
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		log.info("helloData={}", helloData);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData){
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		log.info("helloData={}", helloData);

		return "ok";
	}


}
