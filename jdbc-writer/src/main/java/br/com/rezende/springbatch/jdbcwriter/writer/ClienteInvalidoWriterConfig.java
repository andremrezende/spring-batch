package br.com.rezende.springbatch.jdbcwriter.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.rezende.springbatch.jdbcwriter.dominio.Conta;

@Configuration
public class ClienteInvalidoWriterConfig {
	
	@Bean
	public FlatFileItemWriter<Conta> clienteInvalidoWriter() {
		return new FlatFileItemWriterBuilder<Conta>()
				.name("clienteInvalidoWriter")
				.resource(new FileSystemResource("./files/clientInvalidos.txt"))
				.delimited()
				.names("clienteId")
				.build();
	}
}
