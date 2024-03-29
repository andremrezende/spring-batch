package br.com.rezende.springbatch.fatura.arquivos.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import br.com.rezende.springbatch.fatura.arquivos.model.CartaoCredito;
import br.com.rezende.springbatch.fatura.arquivos.model.Cliente;
import br.com.rezende.springbatch.fatura.arquivos.model.Transacao;

@Configuration
public class LerTransacaoReaderConfig {
	@Bean
	public JdbcCursorItemReader<Transacao> lerTransacoesReader(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Transacao>()
				.name("lerTransacoesReader")
				.dataSource(dataSource)
				.sql("SELECT * FROM transacao join cartao_credito USING (numero_cartao_credito) ORDER BY numero_cartao_credito")
				.rowMapper(rowMapperTransacao())
				.build();
	}

	private RowMapper<Transacao> rowMapperTransacao() {
		return new RowMapper<Transacao>() {

			@Override
			public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartaoCredito cartaoCredito = new CartaoCredito();
				cartaoCredito.setNumeroCartaoCredito(rs.getInt("numero_cartao_credito"));
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente"));
				cartaoCredito.setCliente(cliente);

				Transacao transacao = new Transacao();
				transacao.setCartaoCredito(cartaoCredito);
				transacao.setId(rs.getInt("id"));
				transacao.setData(rs.getDate("data"));
				transacao.setValor(rs.getDouble("valor"));
				transacao.setDescricao(rs.getString("descricao"));

				return transacao;
			}			
		};
	}

}
