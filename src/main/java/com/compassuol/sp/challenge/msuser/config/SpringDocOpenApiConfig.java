package com.compassuol.sp.challenge.msuser.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {


    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("MS User API")
                                .description("API for user management.")
                                .version("v1.0")
                                .contact(new Contact().name("Winicius Girardi - Linkedin").url("https://www.linkedin.com/in/winicius-girardi"))
                                .contact(new Contact().name("Winicius Girardi - Github").url("https://github.com/winicius-girardi")
                        )
                );
    }

}
