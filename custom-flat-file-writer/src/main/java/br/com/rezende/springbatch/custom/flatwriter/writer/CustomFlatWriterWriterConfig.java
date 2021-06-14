package br.com.rezende.springbatch.custom.flatwriter.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.rezende.springbatch.custom.flatwriter.model.GrupoLancamento;
import br.com.rezende.springbatch.custom.flatwriter.model.Lancamento;
import br.com.rezende.springbatch.custom.flatwriter.step.CustomFlatWriterStepConfig;


@Configuration
public class CustomFlatWriterWriterConfig {
	 
	
	@StepScope
	@Bean
	public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioWriter(
			@Value ("#{jobParameters['demonstrativosOrcamentarios']}") Resource demonstrativosOrcamentarios,
			FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
		return new MultiResourceItemWriterBuilder<GrupoLancamento>()
				.name("multiDemonstrativoOrcamentarioWriter")
				.resource(demonstrativosOrcamentarios)
				.delegate(demonstrativoOrcamentarioWriter)
				.resourceSuffixCreator(suffixCreator())
				.itemCountLimitPerResource(CustomFlatWriterStepConfig.COUNT_LIMIT)
				.build();
	}


	@StepScope
	@Bean
	public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
		@Value("#{jobParameters['demonstrativoOrcamentario']}") Resource demonstrativoOrcamentario,
		CustomFlatWriterRodape rodapeCallback) {
		return new FlatFileItemWriterBuilder<GrupoLancamento>()
			.name("customFlatWriterWriterWriter")
			.resource(demonstrativoOrcamentario)
			.lineAggregator(lineAggregator())
			.headerCallback(cabecalhoCallback())
			.footerCallback(rodapeCallback)
			.build();
	}


	private ResourceSuffixCreator suffixCreator() {
		return new ResourceSuffixCreator() {
			
			@Override
			public String getSuffix(int index) {
				return index + ".txt";
			}
		};
	}
	
	private FlatFileHeaderCallback cabecalhoCallback() {
		return new FlatFileHeaderCallback() {

			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.append(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s\n", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
				writer.append(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t\t HORA: %s\n", new SimpleDateFormat("HH:MM").format(new Date())));
				writer.append(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n"));
				writer.append(String.format("----------------------------------------------------------------------------\n"));
				writer.append(String.format("CODIGO NOME VALOR\n"));
				writer.append(String.format("\t Data Descricao Valor\n"));
				writer.append(String.format("----------------------------------------------------------------------------\n"));				
			}
		};
	}

	private LineAggregator<GrupoLancamento> lineAggregator() {
		return new LineAggregator<GrupoLancamento>() {

			@Override
			public String aggregate(GrupoLancamento grupoLancamento) {
				StringBuilder lancametos = new StringBuilder(String.format("[%d] %s - %s\n",
						grupoLancamento.getCodigoNaturezaDespesa(), grupoLancamento.getDescricaoNaturezaDespesa(),
						NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal())));
				for (Lancamento lancamento : grupoLancamento.getLancamentos()) {
					lancametos.append(String.format("\t [%s] %s - %s\n",
							new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
							NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
				}
				return lancametos.toString();
			}
		};
	}
}
