package br.com.rezende.springbatch.fatura.arquivos.model;

import lombok.Data;

@Data
public class Cliente {
	private int id;
	private String nome;
	private String endereco;
}
