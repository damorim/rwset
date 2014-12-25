package delivery.business.exceptions.client;

public class ClienteNaoCadastradoException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6739541137049352955L;

	public ClienteNaoCadastradoException(){
		super("Esse cliente não está cadastrado");
	}
	
}
