package kr.co.jeelee.demoboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBoardApplication.class, args);
	}

}
