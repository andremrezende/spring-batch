package br.com.rezende.springbatch.datamigration.model;

import lombok.Data;

@Data
public class DadosBancarios {
	private int id;
	private int pessoaId;
	private int agencia;
	private int conta;
	private int banco;
}
