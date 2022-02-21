package com.quick.batch.schedulers;

	import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
	
	@Component
	@EnableScheduling
	public class MyScheduler {
		long counter =0;
	
		@Autowired
		private JobLauncher jobLauncher;
		
		@Autowired
		@Qualifier("importUserJob1")
		private Job job;
		
		
		@Autowired
		JobExplorer jobExplorer;
		
		@Scheduled(cron="*/10 * * * * *")
		public void myScheduler(){
			JobParameters jobParameters = new JobParametersBuilder().addLong("number", counter++).toJobParameters();
			System.err.println("NAme:"+jobExplorer.getJobNames());
			try {
				JobExecution jobExecution = jobLauncher.run(job, jobParameters);
				System.err.println("Job's Status:::"+jobExecution.getStatus());
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
			}
		}
	}