package com.fragile.reactive_programing.config;

import com.fragile.reactive_programing.repository.TaskLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TaskScheduler {

    private final TaskLockRepository taskLockRepository;

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void scrabSourcesWebsite() {
        long currentTime = System.currentTimeMillis();
        long scheduleRated = Duration.of(1, ChronoUnit.HOURS).toMillis(); //set a limit on how frequently this task should run
        taskLockRepository.findByTaskIdAndLastExecutionLessThanAndDeactivatedUntilLessThan("scrap_website", currentTime - scheduleRated, currentTime)
                .ifPresent(scrapingTask -> {
                    try {
                        scrapingTask.setLastExecution(System.currentTimeMillis());
                    } catch (Exception e) {
                        // Deactivating for 3 hours
                        scrapingTask.setDeactivatedUntil(System.currentTimeMillis() + Duration.of(3, ChronoUnit.HOURS).toMillis()); // 3h
                    }
                });
    }

    @Transactional
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    public void moveDeletedToColdStorage() {
        long currentTime = System.currentTimeMillis();
        long scheduledRate = Duration.of(6, ChronoUnit.HOURS).toMillis(); // 6h
        taskLockRepository.findByTaskIdAndLastExecutionLessThanAndDeactivatedUntilLessThan("move_to_cold", currentTime - scheduledRate, currentTime)
                .ifPresent(moveToCold -> {
                    // Execute migration of deleted to cold storage...
                    moveToCold.setLastExecution(System.currentTimeMillis());
                });
    }
}
