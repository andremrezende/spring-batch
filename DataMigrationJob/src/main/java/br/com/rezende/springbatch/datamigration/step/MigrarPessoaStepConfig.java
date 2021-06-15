package br.com.rezende.springbatch.datamigration.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.springbatch.datamigration.DataMigrationJobApplication;
import br.com.rezende.springbatch.datamigration.model.Pessoa;

@Configuration
public class MigrarPessoaStepConfig {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step migrarPessoaStep(ItemReader<Pessoa> arquivoPessoaReader, 
			ItemWriter<Pessoa> bancoPessoaWriter,
			ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
			FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter
			) {
		return stepBuilderFactory.get("migrarPessoaStep")
				.<Pessoa, Pessoa>chunk(DataMigrationJobApplication.CHUNK_SIZE)
				.reader(arquivoPessoaReader)
				.writer(pessoaClassifierWriter)
				.stream(arquivoPessoasInvalidasWriter)
				.build();
	}
}
