DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa(id INT, nome VARCHAR(500),email VARCHAR(500),data_nascimento timestamp,idade INT, PRIMARY KEY(id));
DROP TABLE IF EXISTS dados_bancarios;
CREATE TABLE dados_bancarios(id INT primary key, pessoa_id INT,agencia INT, conta INT, banco INT);
