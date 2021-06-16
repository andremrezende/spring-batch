package br.com.rezende.springbatch.emails.model;

import lombok.Data;

@Data
public class InteresseProdutoCliente {
	private Cliente cliente;
	private Produto produto;
}
