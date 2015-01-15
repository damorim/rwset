package exceptions.cliente;

public class EstadoInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2501972420789733441L;

	public EstadoInvalidoException(){
        super ("Estado inválido");
    }
}
