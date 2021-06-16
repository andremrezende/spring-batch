package br.com.rezende.springbatch.fatura.arquivos.model;

import java.util.Date;

import lombok.Data;

@Data
public class Transacao {
	private int id;
	private CartaoCredito cartaoCredito;
	private String descricao;
	private Double valor;
	private Date data;

}
