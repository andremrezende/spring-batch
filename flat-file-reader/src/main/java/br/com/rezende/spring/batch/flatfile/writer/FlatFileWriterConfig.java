package br.com.rezende.spring.batch.flatfile.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.flatfile.dominio.Client;

@Configuration
public class FlatFileWriterConfig {
	@Bean
	public ItemWriter<Client> flatFileWriter() {
		return items -> items.forEach(System.out::println);
	}
}
