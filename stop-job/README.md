# Stop-job

Esse projeto tem como objetivo iniciar um Job com um Step em Java com Spring Batch e por parâmetrização no banco de dados interromper o processo de Job.

## Requisitos para o projeto:

* Postgres
* JDK 11
* Maven
* IDE de sua preferência

## Configurações

### Aplicação:

 	1. Alterar o arquivo application.yml para as configurações do Banco de Dados Postgres de sua máquina.
 	2. Após configurado, criar um schema interop no seu banco de dados Postgres
 	3. Executar o script localizado no <projeto>/src/main/resources/db.postgres/0001-create.sql (Irá criar as tabelas e carregar dados nas respectivas tabelas)

## Execução

Ao executar o StopJobApplication, uma lista de usuários "fakes", inserido no script acima, irá listar na tela. Para interromper o job, basta alterar o valor da propriedade *enabled* na tabela batch_parameters.

```sql
update interop.batch_parameters set property_value  = 'true' where name = 'enabled';
```

## Conclusão

Ao interromper um processo, diferente de um CTRL + C, aguarda um step ser concluído para então parar o mesmo. Isso evita que processo iniciados sejam interruptos abruptamente, podendo corromper algo que aguarda deliberação ou mesmo.