package exceptions.cliente;

public class TelefoneJaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166024353844041522L;

	public TelefoneJaExisteException(){
		super("Telefone já cadastrado!");
	}
	
}
