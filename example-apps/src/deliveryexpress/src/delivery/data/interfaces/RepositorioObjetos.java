package delivery.data.interfaces;

import java.util.ArrayList;

public interface RepositorioObjetos<T> {
	/**
	 * Insere em um repositorio específico objetos de um tipo específico
	 * @param objeto
	 * 			Objeto que será inserido
	 */
	void adicionar(T objeto);
	/**
	 * Verifica se existe um determinado objeto no repositório
	 * @param identificador
	 * 			Servirá de filtro para verificar se existe esse objeto
	 * @return
	 * 		Verdadeiro se existir, falso caso contrário
	 */
	boolean existe (String identificador);
	/**
	 * Remove um objeto do repositório
	 * @param objeto
	 * 			Objeto que será removido
	 */
	void remover (T objeto);
	/**
	 * Retorna uma lista específica do repositório
	 * @param identificador
	 * 			Será usado para filtrar os objetos desejados
	 * @return
	 * 			Lista de objetos
	 */
	ArrayList<T> vizualizar(String identificador);
	/**
	 * Retorna apenas um objeto
	 * @param identificador
	 * 			String única para cada objeto, será usada para buscar o objeto desejado
	 * @return
	 * 			Objeto
	 */
	T vizualizarEspecifico (String identificador);
}
