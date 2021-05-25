package br.com.rezende.spring.batch.delimitedfile.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import br.com.rezende.spring.batch.delimitedfile.dominio.Client;


@Configuration
public class DelimitedFileReaderConfig {
	
	@StepScope
	@Bean
	public FlatFileItemReader<Client> delimitedFileReader(@Value("#{jobParameters['clientFile']}") String clientFile) {
		return new FlatFileItemReaderBuilder<Client>()
				.name("delimitedFileReader")
				.resource(new PathResource(clientFile))
				.delimited()
				.names(new String[] {"name", "lastname", "age", "email"})
				.targetType(Client.class)
				.build();
	}
}
