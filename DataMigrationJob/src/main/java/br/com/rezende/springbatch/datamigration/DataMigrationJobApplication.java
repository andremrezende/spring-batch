package br.com.rezende.springbatch.datamigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataMigrationJobApplication {
	
	public static final int CHUNK_SIZE = 10000;

	public static void main(String[] args) {
		SpringApplication.run(DataMigrationJobApplication.class, args);
	}

}
