package br.com.rezende.springbatch.fatura.arquivos.writer;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import br.com.rezende.springbatch.fatura.arquivos.model.FaturaCartaoCredito;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {
	private BigDecimal total = new BigDecimal(0.0);

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write(
				String.format("\n#121s", "Total" + NumberFormat.getCurrencyInstance().format(total.doubleValue())));
	}
	
	@BeforeWrite
	public void beforeWrite(List<FaturaCartaoCredito> faturas) {
		for(FaturaCartaoCredito faturaCartaoCredito: faturas) {
			total = total.add(new BigDecimal(faturaCartaoCredito.getTotal()));
		}
	}
	
	@AfterChunk
	public void afterChunk(ChunkContext chunkContext) {
		total = new BigDecimal(0.0);
	}
	
	
}
