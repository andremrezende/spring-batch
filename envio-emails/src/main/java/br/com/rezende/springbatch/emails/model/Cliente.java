package br.com.rezende.springbatch.emails.model;

import lombok.Data;

@Data
public class Cliente {
	private int id;
	private String nome;
	private String email;
}
