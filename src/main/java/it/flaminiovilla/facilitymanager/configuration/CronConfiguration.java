package it.flaminiovilla.facilitymanager.configuration;

import it.flaminiovilla.facilitymanager.service.impl.CleaningActionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

/**
 * Configuration class for scheduled tasks.
 */
@Configuration
@EnableScheduling
@AllArgsConstructor
public class CronConfiguration {
    @Autowired
    private CleaningActionServiceImpl cleaningActionService;

    // ogni giorno alle 6 di mattina
    @Scheduled(cron = "0 0 6 * * ?")
    public void deleteOldPasswordResetToken() {
        cleaningActionService.assignCleaningAction(LocalDate.now());
    }

}
