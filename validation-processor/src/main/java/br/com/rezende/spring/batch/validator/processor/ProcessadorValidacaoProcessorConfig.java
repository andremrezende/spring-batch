package br.com.rezende.spring.batch.validator.processor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rezende.spring.batch.validator.model.Client;

@Configuration
public class ProcessadorValidacaoProcessorConfig {
	private Set<String> emails = new HashSet<>();

	@Bean
	public ItemProcessor<Client, Client> procesadorValidacaoProcessor() throws Exception {
		return processadorValidacaoProcessor();
	}

	@Bean
	public ItemProcessor<Client, Client> processadorValidacaoProcessor() throws Exception {
		return new CompositeItemProcessorBuilder<Client, Client>()
				.delegates(Arrays.asList(beanValidatingProcessor(), duplicateValidatingProcessor())).build();
	}

	private BeanValidatingItemProcessor<Client> beanValidatingProcessor() throws Exception {
		BeanValidatingItemProcessor<Client> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true);
		processor.afterPropertiesSet();
		return processor;
	}

	private ValidatingItemProcessor<Client> duplicateValidatingProcessor() {
		ValidatingItemProcessor<Client> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true);
		return processor;
	}

	private Validator<Client> validator() {
		return new Validator<Client>() {

			@Override
			public void validate(Client client) throws ValidationException {
				if (emails.contains(client.getEmail())) {
					throw new ValidationException(String.format("O Client %s já foi processado.", client.getEmail()));
				}
				emails.add(client.getEmail());
			}

		};
	}
}
