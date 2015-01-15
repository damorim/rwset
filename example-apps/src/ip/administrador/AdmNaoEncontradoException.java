package exceptions.administrador;

public class AdmNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 518772763727978768L;

	public AdmNaoEncontradoException() {
		super("Administrador não encontrado!");
	}

}
