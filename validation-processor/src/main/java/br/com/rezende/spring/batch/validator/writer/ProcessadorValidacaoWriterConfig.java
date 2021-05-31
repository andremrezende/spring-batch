package br.com.rezende.spring.batch.validator.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.validator.model.Client;

@Configuration
public class ProcessadorValidacaoWriterConfig {
	@Bean
	public ItemWriter<Client> processadorValidacaoWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
