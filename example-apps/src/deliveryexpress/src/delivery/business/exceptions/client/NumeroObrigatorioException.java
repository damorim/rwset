package delivery.business.exceptions.client;

public class NumeroObrigatorioException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8302872455310479512L;

	public NumeroObrigatorioException(){
		super ("O campo N�mero n�o foi preenchido");
	}

}
