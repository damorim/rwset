package dados.interfaces;

import dados.entidade.Cliente;
import dados.IteratorClasse;

public interface IRepositorioCliente {

	public void adicionar(Cliente cliente);
	public void remover(String cpf);
	public void atualizar(Cliente cliente);
	public Cliente procurar(String cpf);

	IteratorClasse<Cliente> iterator();
}