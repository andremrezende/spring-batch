package br.com.rezende.spring.batch.delimitedfile.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.delimitedfile.dominio.Client;

@Configuration
public class DelimitedFileWriterConfig {
	@Bean
	public ItemWriter<Client> DelimitedFileWriter() {
		return items -> items.forEach(System.out::println);
	}
}
