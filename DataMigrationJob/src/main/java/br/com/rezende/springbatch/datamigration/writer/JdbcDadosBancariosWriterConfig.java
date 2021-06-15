package br.com.rezende.springbatch.datamigration.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.springbatch.datamigration.model.DadosBancarios;

@Configuration
public class JdbcDadosBancariosWriterConfig {
	
	@Bean
	public JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter(@Qualifier("appDataSource") DataSource datasource) {
		return new JdbcBatchItemWriterBuilder<DadosBancarios>()
				.dataSource(datasource)
				.sql("INSERT INTO dados_bancarios(id, pessoa_id, agencia, conta, banco) VALUES (:id, :pessoaId, :agencia, :conta, :banco)")
				.beanMapped()
				.build();
	}

}
