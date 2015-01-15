package exceptions;


public class CodigoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3687586306981069060L;

	public CodigoInvalidoException(){
		super("Código inválido!");
	}
	
}
