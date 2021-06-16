package br.com.rezende.springbatch.emails.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.rezende.springbatch.emails.model.InteresseProdutoCliente;
import br.com.rezende.springbatch.emails.model.Produto;

@Component
public class ProcessarEmailProdutoClienteProcessor
		implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> {

	@Override
	public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("xpto@no-reply.com");
		email.setTo(interesseProdutoCliente.getCliente().getEmail());
		email.setSubject("Promoção Imperdível!!!");
		email.setText(gerarTextoPromocao(interesseProdutoCliente));
		Thread.sleep(900);
		return email;
	}

	private String gerarTextoPromocao(InteresseProdutoCliente interesseProdutoCliente) {
		StringBuilder writer = new StringBuilder(String.format("Olá, %s!\n\n", interesseProdutoCliente.getCliente().getNome()));
		writer.append("Promoção de seu interesse:\n\n");
		Produto produto = interesseProdutoCliente.getProduto();
		writer.append(String.format("%s - %s\n\n", produto.getNome(), produto.getDescricao()));
		writer.append(String.format("por apenas %s!", NumberFormat.getCurrencyInstance().format(produto.getPreco())));
		return writer.toString();
	}

}
