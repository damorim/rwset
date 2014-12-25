package delivery.data.interfaces;

import java.util.ArrayList;

public interface RepositorioObjetos<T> {
	/**
	 * Insere em um repositorio espec�fico objetos de um tipo espec�fico
	 * @param objeto
	 * 			Objeto que ser� inserido
	 */
	void adicionar(T objeto);
	/**
	 * Verifica se existe um determinado objeto no reposit�rio
	 * @param identificador
	 * 			Servir� de filtro para verificar se existe esse objeto
	 * @return
	 * 		Verdadeiro se existir, falso caso contr�rio
	 */
	boolean existe (String identificador);
	/**
	 * Remove um objeto do reposit�rio
	 * @param objeto
	 * 			Objeto que ser� removido
	 */
	void remover (T objeto);
	/**
	 * Retorna uma lista espec�fica do reposit�rio
	 * @param identificador
	 * 			Ser� usado para filtrar os objetos desejados
	 * @return
	 * 			Lista de objetos
	 */
	ArrayList<T> vizualizar(String identificador);
	/**
	 * Retorna apenas um objeto
	 * @param identificador
	 * 			String �nica para cada objeto, ser� usada para buscar o objeto desejado
	 * @return
	 * 			Objeto
	 */
	T vizualizarEspecifico (String identificador);
}
