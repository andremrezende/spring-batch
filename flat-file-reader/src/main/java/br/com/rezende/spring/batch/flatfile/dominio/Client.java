package br.com.rezende.spring.batch.flatfile.dominio;

import lombok.Data;

@Data
public class Client {
	private String name;
	private String lastname;
	private String age;
	private String email;	
}