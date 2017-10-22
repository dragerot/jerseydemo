package net.toregard.jerseydemo.config;

import net.toregard.jerseydemo.services.UserService;
import net.toregard.jerseydemo.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UserService createUserService(){
        return new UserServiceImpl();
    }
}
