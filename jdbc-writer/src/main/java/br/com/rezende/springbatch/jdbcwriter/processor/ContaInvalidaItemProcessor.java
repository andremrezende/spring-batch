package br.com.rezende.springbatch.jdbcwriter.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.rezende.springbatch.jdbcwriter.dominio.Cliente;
import br.com.rezende.springbatch.jdbcwriter.dominio.Conta;
import br.com.rezende.springbatch.jdbcwriter.dominio.TipoConta;

public class ContaInvalidaItemProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente cliente) throws Exception {
		Conta conta = new Conta();
		conta.setClienteId(cliente.getEmail());
		conta.setTipo(TipoConta.INVALIDA);
		return conta;
	}
	
}