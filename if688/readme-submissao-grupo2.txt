Encontramos dificuldades no entendimento geral do c�digo.
Os par�metros dos m�todos realizados no teste de exemplo e nomes das vari�veis n�o eram intuitivos e levamos bastante tempo para compreender o que significavam, visto que n�o �ramos familiarizados com ele. Contudo, conseguimos progredir com o apoio do professor e dos colegas de turma.
Percebemos que o programa n�o responde bem aos seguintes casos:
 - A classe de teste extende de outra classe
 - A classe de teste implementa outra classe
 - O arquivo .java que contem a classe de teste tamb�m contem outra classe. Esse erro � resolvido movendo a segunda classe para um arquivo .java separado.
 - Projetos que continham pacotes com subpacotes 


 Em rela��o aos grafos resultantes verificamos certas anomalias no pdf mostrando as depend�ncias dos seguintes arquivos:

 - Hardware.testArquivoPrintln (Grafo desconexo)
 - Testes no projeto de L�gica que possuem linhas de an�lise dentro do m�todo main n�o tem seu grafo expresso corretamente. O m�todo acessado n�o � ligado a nenhum outro e o restante do grafo est� desconectado.
 Exemplos de testes com esse problema:
 LogicaTest.testClearClausulas
 LogicaTest.testFunctionsHorn
 
 No pdf gerado com o teste testResolucaoFNC2 podemos ver que a linha "return Functions.verifyFNC(exp);" n�o tem nenhuma depend�ncia visto que o m�todo verifyFNC s� trabalha com String. Diferentemente do testResolucaoFNC que possui a linha "if (Functions.verifyFNC(exp)){" dentro do main e gera um pdf com um grafo desconectado do m�todo alvo.  (print em anexo no projeto com o nome fnc1e2)
 O que n�s fizemos foi, retirar o camando que estava dentro do main e criamos uma funcao que continha apenas o nosso metodo testado. � a �nica diferen�a entre os arquivos Resolucao.java e Resolucao2.java



--Gabriela Mota, Geovane Pereira e Manuela Barbosa
