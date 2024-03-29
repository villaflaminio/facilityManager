package it.flaminiovilla.facilitymanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "FacilityManagerApplication", version = "2.0", description = "FacilityManager Application"),servers = {
//        @Server(url = "https://facilitymanager.be.flaminiovilla.it/", description = "Default Server URL")
//})
@OpenAPIDefinition(info = @Info(title = "FacilityManagerApplication", version = "2.0", description = "FacilityManager Application")
)
@Log4j2
public class FacilityManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacilityManagerApplication.class, args);
    }
    @Component
    public class Runner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            log.info("Log4j2 logger works!");

        }
    }
}