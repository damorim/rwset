package exceptions.cliente;

public class ClienteNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 518772763727978768L;

	public ClienteNaoEncontradoException() {
		super("Cliente não encontrado!");
	}

}
