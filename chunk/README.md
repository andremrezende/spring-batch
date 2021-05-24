# Porque utilizar Chunk?

O job precisa ser capaz de processar um grande arquivo em apenas um dia. Se fosse utilizada uma tasklet para esse trabalho, 
caso ocorresse alguma instabilidade no envio dos lançamentos, seria necessário realizar novamente todo o trabalho do job. 
Utilizando um chunk, é possível particionar esse envio em pedaços garantindo que se houver uma falha não é necessário recomeçar do zero, os chunks já processados 
estarão salvos e ao reiniciar o job o trabalho seria retomado de onde ele parou. Dessa forma, utilizar um chunk é a melhor solução para esse problema.

