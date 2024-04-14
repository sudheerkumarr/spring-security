package com.example.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	// JobLauncher is responsible for launching Job
	// Job is configured in CsvBatchConfig class
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;

	// Job Runner
	
	@GetMapping("/employees")
	public void loadCscToDb() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParams = new JobParametersBuilder()
				.addLong("Started-At", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(job, jobParams);
	}
}
