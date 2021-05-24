package br.com.rezende.spring.batch.flatfile.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.rezende.spring.batch.flatfile.dominio.Client;


@Configuration
public class FlatFileReaderConfig {
	@Bean
	public FlatFileItemReader<Client> flatFileReader(@Value("${clientFile}") 
	Resource clientFile) {
		return new FlatFileItemReaderBuilder<Client>()
				.name("flatFileReader")
				.resource(clientFile)
				.fixedLength()
				.columns(new Range[] {new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
				.names(new String[] {"name", "lastname", "age", "email"})
				.targetType(Client.class)
				.build();
	}
}
