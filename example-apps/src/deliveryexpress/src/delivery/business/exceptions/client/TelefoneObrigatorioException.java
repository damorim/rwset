package delivery.business.exceptions.client;

public class TelefoneObrigatorioException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4410881523879785870L;

	public TelefoneObrigatorioException (){
		super("O campo Telefone não deve ficar em branco");
	}

}
