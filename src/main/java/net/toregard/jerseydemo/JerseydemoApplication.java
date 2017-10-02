package net.toregard.jerseydemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@SpringBootApplication
public class JerseydemoApplication extends RepositoryRestConfigurerAdapter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JerseydemoApplication.class).
				initializers(new SpringApplicationContextInitializer())
				.application()
				.run(args);
	}
}
