package exceptions;


public class TipoRepositorioInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8387339307091616306L;

	public TipoRepositorioInvalidoException(){
		super("Tipo de repositório inválido!");
	}
	
}
