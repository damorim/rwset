package delivery.data.interfaces;

public interface InterfaceIterator<T> {
	/**
	 * Verifica se há mais algum objeto no Iterator
	 * @return
	 * 		Verdadeiro se houver algum objeto, falso caso contrário
	 */
	boolean hasNext();
	/**
	 * Retorna um objeto do tipo especificado
	 * @return
	 * 		Objeto de um tipo especificado
	 */
	T next();

}
