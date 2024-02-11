package fr.charisma.bvj.rc_bvj.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class JobScheduler {
	private final JobLauncher jobLauncher;
	private Job myJob;
	
	public void perfomJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters params =  new JobParametersBuilder()
				.addString("JobId", 
						String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(myJob, params);
		
	}
	
}
