package bg.fibank.spring_ucp_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
		scanBasePackages = {"bg.fibank.spring_ucp_demo", "bg.fibank.dbcon"}
)
public class SpringUcpDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringUcpDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringUcpDemoApplication.class, args);
	}
}
