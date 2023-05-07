package it.bruffa.facilitymanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "FacilityManagerApplication", version = "2.0", description = "FacilityManager Application"))

public class FacilityManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacilityManagerApplication.class, args);
    }

}