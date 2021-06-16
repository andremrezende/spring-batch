package br.com.rezende.springbatch.fatura.arquivos.model;

import lombok.Data;

@Data
public class CartaoCredito {
	private int numeroCartaoCredito;
	private Cliente cliente;
}
