package com.demneru.batch.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step-demner-1")
										 .tasklet(new Tasklet() {
											@Override
											public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
												System.out.println("Demo spring batch ");
												return RepeatStatus.FINISHED;
											}
										}).build();
	}
	
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("job-demner-1")
				.start(step1())
				.build();
	}
}
