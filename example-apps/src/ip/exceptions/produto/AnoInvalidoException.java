package exceptions.produto;

public class AnoInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6899488026114280437L;

	public AnoInvalidoException() {
		super("Ano inválido");
	}
}