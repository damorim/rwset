package dados.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import dados.entidade.Administrador;
import dados.IteratorClasse;

public interface IRepositorioAdm {
	
	public void adicionar(Administrador administrador);
	public void remover(String cpf);
	public void atualizar(Administrador administrador);
	public Administrador procurar(String cpf) throws FileNotFoundException, ClassNotFoundException, IOException;

	IteratorClasse<Administrador> iterator();
}