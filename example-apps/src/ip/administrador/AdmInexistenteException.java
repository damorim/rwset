package exceptions.administrador;

public class AdmInexistenteException extends Exception {

	private static final long serialVersionUID = -7061377520446245525L;

	public AdmInexistenteException() {
		super("Administrador não existe.");
	}
}
