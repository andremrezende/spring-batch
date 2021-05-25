package br.com.rezende.spring.batch.delimitedfile.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.delimitedfile.dominio.Client;

@Configuration
public class DelimitedFileStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step DelimitedFileStep(ItemReader<Client> DelimitedFileReader, ItemWriter<Client> DelimitedFileWriter) {
		return stepBuilderFactory.get("DelimitedFileStep").<Client, Client>chunk(1).reader(DelimitedFileReader)
				.writer(DelimitedFileWriter).build();
	}
}
