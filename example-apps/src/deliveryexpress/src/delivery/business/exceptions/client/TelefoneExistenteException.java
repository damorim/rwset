package delivery.business.exceptions.client;

public class TelefoneExistenteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2388587239357433509L;

	public TelefoneExistenteException (){
		super("Já existe um cadastro com esse telefone");
	}
	
}
