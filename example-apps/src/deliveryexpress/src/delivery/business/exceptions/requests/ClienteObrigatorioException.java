package delivery.business.exceptions.requests;

public class ClienteObrigatorioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 547105366958085505L;

	public ClienteObrigatorioException(){
		super("O campo Cliente deve ser preenchido");
	}
	
}
