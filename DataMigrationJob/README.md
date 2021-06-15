# Projeto completo de Migração de Dados
O objetivo desse projeto foi mostrar a realização de todos passos necessários para migrar dados de uma entrada (arquivo) para um banco de dados Postgres.
Processos pelos quais o Spring Batch utiliza como Reader, Processor e Writer.
Também adicionei um classificador, que identifica dados de entrada inválido, não insere no banco de dados Postgres, e criar um arquivo do qual sinalizada o ID mais o nome da pessoa inválido sobre
uma regra simples de email em branco.
Como iniciei o projeto de forma simples, identifiquei o delay alto de tempo e passei a ajusta-lo para realizar tunning e otimizar a migração de 20k registros em dividos igualmente entre duas tabelas e mais um arquivo de pessoas inválidas.
Abaixo os tempos:
Projeto inicial: *2m51s554ms*
Alterando o CHUNK SIZE de 1 para 10k: *1s579ms*
Adicionando processamento paralelo: **1s34ms**

Vale ressaltar que o último passo vai depender muito das características de cada hardware, principalmente processadores físicos.