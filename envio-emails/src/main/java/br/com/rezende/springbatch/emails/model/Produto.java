package br.com.rezende.springbatch.emails.model;

import lombok.Data;

@Data
public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private Double preco;
}
