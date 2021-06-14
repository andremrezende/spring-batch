package br.com.rezende.springbatch.custom.flatwriter.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.springbatch.custom.flatwriter.model.GrupoLancamento;
import br.com.rezende.springbatch.custom.flatwriter.reader.GrupoLancamentoReader;
import br.com.rezende.springbatch.custom.flatwriter.writer.CustomFlatWriterRodape;

@Configuration
public class CustomFlatWriterStepConfig {
	//Tamanho do Chunk e limite de arquivos para MultiResourceItemWriter
	public final static byte COUNT_LIMIT = 2;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step demonstrativoOrcamentarioStep(
			GrupoLancamentoReader customFlatWriterStepReader, 
			//ItemWriter<GrupoLancamento> customFlatWriterStepWriter, /* Para um único arquivo */
			MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioWriter, /* Para quebra de multiplos arquivos */
			CustomFlatWriterRodape rodapeCallback) {
		return stepBuilderFactory.get("customFlatWriterStep").<GrupoLancamento, GrupoLancamento>chunk(COUNT_LIMIT)
				.reader(customFlatWriterStepReader)
				//.writer(customFlatWriterStepWriter)
				.writer(multiDemonstrativoOrcamentarioWriter)
				.listener(rodapeCallback)
				.build();
	}
}
