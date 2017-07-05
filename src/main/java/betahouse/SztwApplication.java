package betahouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@ServletComponentScan
@MapperScan("betahouse.mapper")
public class SztwApplication {
	public static void main(String[] args) {
		SpringApplication.run(SztwApplication.class, args);
	}
}
