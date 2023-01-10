package sidepj.learningchain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {
	@GetMapping("/test")
	public String test(){
		log.info("로그 테스트");
		return "로그테스트";
	}
}
