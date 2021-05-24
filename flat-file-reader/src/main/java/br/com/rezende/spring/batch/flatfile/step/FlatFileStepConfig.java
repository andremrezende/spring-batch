package br.com.rezende.spring.batch.flatfile.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.flatfile.dominio.Client;

@Configuration
public class FlatFileStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step flatFileStep(ItemReader<Client> flatFileReader, ItemWriter<Client> flatFileWriter) {
		return stepBuilderFactory.get("flatFileStep").<Client, Client>chunk(1).reader(flatFileReader)
				.writer(flatFileWriter).build();
	}
}
