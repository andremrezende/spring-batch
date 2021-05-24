package br.com.rezende.spring.batch.flatfile.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class FlatFileJobConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job flatFileJob(Step leituraArquivoLarguraFixaStep) {
		return jobBuilderFactory
				.get("flatFileJob")
				.start(leituraArquivoLarguraFixaStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
