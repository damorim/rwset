Readme

Localização dos arquivos:
Seguimos os paths originais, ou seja, 
	os testes estão em src-tests(rwsets.database, rwsets.cqueue, rwsets.forest e rwsets.pgproj), 
	os jars estão em example-apps(database.jar, cqueue.jar, forest.jar e pgproj.jar) e
	as fontes estão em example-apps/src, em pastas com os mesmos nomes dos jars.

----------------------------------------------------
Testes:
	Nossos testes se encontram no AllTestsGrupo4

####   known-bug  #####################################################33
-Ao rodar os testes no pgproj encontramos uma possível falha no rwsets.

No primeiro teste(test0), o JUnit retorna um NullPointerException
	-Para analisar este erro adicionamos um System.out
 	na linha 318 do arquivo src/depend/MethodDependencyAnalysis.java
		->System.out.println(fr.getDeclaringClass());
	-Esse System.out visa retornar o nome da classe que será passado para a chamada onde está ocorrendo a exceção.
	-Com vários testes diferentes percebemos que a execução sempre para quando o rwsets tenta encontrar a classe Ponto dentro de projpg.

Fizemos então um segundo teste (test1) que, ao ser rodado, evidencia o erro com um RuntimeException decorrente de não conseguir encontrar a classe.
	-O erro não deveria ocorrer pois a classe Lprojpg/Ponto existe.
	(apesar de termos usado pgproj no nome das pastas, o nome da package do projeto é projpg)
