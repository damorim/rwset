package delivery.business.exceptions.client;

public class LogradouroObrigatorioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -551481590018445222L;
	
	public LogradouroObrigatorioException(){
		super ("O campo Logradouro n�o deve ficar em branco");
	}

}
