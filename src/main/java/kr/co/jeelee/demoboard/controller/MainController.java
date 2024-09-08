package kr.co.jeelee.demoboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jeelee.demoboard.vo.KeyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {

	@GetMapping("/test")
	public KeyVO test() {
		log.info("test");
		KeyVO keyVO = new KeyVO();

		keyVO.setKey("hello, world!");
		return keyVO;
	}
}
