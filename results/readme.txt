This file is needed so that the results folder is included in the repository.
This folder may contain some application outputs.


NullPointerException, quando rodei o debug na linha 444 do MethodDependencyAnalisys.java  ( Set<AccessInfo> reads = rwSets.get(method).readSet;) está dando ai, ele está pegando um rwSet que fica vazio sempre então dá nullpointer mas não sabemos como resolver ainda
O ultimo erro foi resolvido trocando o prefixo do "app" pra o package que esta a classe, e em vez de pontos transformar em barras

ex: test.unit.Test.java -> test/unit/ seria o package