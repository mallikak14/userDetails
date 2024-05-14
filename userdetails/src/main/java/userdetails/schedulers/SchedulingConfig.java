package userdetails.schedulers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulingConfig {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;

    @Scheduled(cron="0 * * * * *")
    public void jobScheduling() throws Exception{
        JobParameters params=new JobParametersBuilder()
        .addString("JobID", String.valueOf(System.currentTimeMillis()))
        .toJobParameters();
        System.out.println("starting job...");
        jobLauncher.run(job, params);
    }
   

    
}
