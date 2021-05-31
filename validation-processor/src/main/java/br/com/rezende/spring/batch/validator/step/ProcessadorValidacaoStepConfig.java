package br.com.rezende.spring.batch.validator.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.validator.model.Client;

@Configuration
public class ProcessadorValidacaoStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step processadorValidacaoStep(
			ItemReader<Client> processadorValidacaoReader,
			ItemProcessor<Client, Client> processadorValidacaoProcessor,
			ItemWriter<Client> processadorValidacaoWriter) {
		return stepBuilderFactory
				.get("processadorValidacaoStep")
				.<Client, Client>chunk(1)
				.reader(processadorValidacaoReader)
				.processor(processadorValidacaoProcessor)
				.writer(processadorValidacaoWriter)
				.build();
	}
}
