package com.fullquackdeveloper.quartzservice.config;

import com.fullquackdeveloper.quartzservice.job.HelloJob;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {
    @Bean
    public JobDetail helloJobDetails() {
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger helloJobTrigger(JobDetail helloJobDetails) {
        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetails)
                .withIdentity("helloJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * * *"))
                .build();
    }
}
