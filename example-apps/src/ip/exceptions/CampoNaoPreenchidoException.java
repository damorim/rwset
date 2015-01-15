package exceptions;

public class CampoNaoPreenchidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8545544619586522920L;

	public CampoNaoPreenchidoException() {
		super("Algum campo obrigatório não foi preenchido!");
	}

}
