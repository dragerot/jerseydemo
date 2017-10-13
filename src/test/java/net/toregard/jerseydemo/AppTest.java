package net.toregard.jerseydemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppTest {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppTest.class)
                .application()
                .run(args);
    }
}
