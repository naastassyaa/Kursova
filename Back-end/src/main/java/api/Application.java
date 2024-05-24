package api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@ComponentScan({"api.controller", "api.service", "api.exceptions", "api.config", "api.repository"})
@EntityScan({"api.models"})
@EnableJpaRepositories({"api.repository"})
public class Application {
      public static void main(String[] args) {
//        try {
//            Runtime.getRuntime().exec("cmd /c start mysqld --console");
//        }catch (IOException e){
//            System.out.println("No mysql in path(environment variables)");
//        }
        SpringApplication.run(Application.class, args);
    }
}


