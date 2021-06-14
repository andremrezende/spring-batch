# Escrita de vários arquivos customizados
Esse aplicação auxilia na escrita de um único ou vários arquivos com layout customizados com adição de totalizador, cabeçalho e rodapé.

Caso deseje um único arquivo, comentar CustomFlatWriterRodape.afterChunk, descomentar CustomFlatWriterStepConfig.demonstrativoOrcamentarioStep 

```java
//ItemWriter<GrupoLancamento> customFlatWriterStepWriter, /* Para um único arquivo */
...

//.writer(customFlatWriterStepWriter)
```

e aumentar a constante CustomFlatWriterStepConfig.COUNT_LIMIT para o número de chunk necessário.