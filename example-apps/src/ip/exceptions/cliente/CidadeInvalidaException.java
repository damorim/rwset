package exceptions.cliente;

public class CidadeInvalidaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2501972420789733441L;

	public CidadeInvalidaException(){
        super ("Cidade inválida");
    }
}
