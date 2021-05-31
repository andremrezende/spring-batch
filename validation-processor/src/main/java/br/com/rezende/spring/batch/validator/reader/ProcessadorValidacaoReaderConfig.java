package br.com.rezende.spring.batch.validator.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.rezende.spring.batch.validator.model.Client;

@Configuration
public class ProcessadorValidacaoReaderConfig {
	@StepScope
	@Bean
	public FlatFileItemReader<Client> processadorValidacaoReader(
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		return new FlatFileItemReaderBuilder<Client>()
				.name("processadorValidacaoReader")
				.resource(arquivoClientes)
				.delimited()
				.names("nome", "idade", "email")
				.targetType(Client.class)
				.build();
	}
}
