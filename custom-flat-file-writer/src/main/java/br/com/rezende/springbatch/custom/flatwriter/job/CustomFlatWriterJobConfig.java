package br.com.rezende.springbatch.custom.flatwriter.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CustomFlatWriterJobConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job demonstrativoOrcamentarioJob(Step customFlatWriterStep) {
		return jobBuilderFactory
				.get("customFlatWriterJob")
				.start(customFlatWriterStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
