package dados.repositorio;

import java.util.Vector;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioCliente;
import dados.entidade.Cliente;

@SuppressWarnings("rawtypes")
public class RepClienteArray implements IRepositorioCliente, Iterable {

	private Cliente[] clientes;
	private int indice;

	public RepClienteArray() {
		this.clientes = new Cliente[1000];
		this.indice = 0;
	}
	
	public boolean isVazia() {
		if (indice == 0 && clientes[0].equals(null)) 
			return true;
		return false;
	}
	
	public void imprimir() {
		for(int i = 0; i < indice; i++) {
			System.out.println(clientes[i].getCpf() + " - " + clientes[i].getNome());
		}

	}

	public void adicionar(Cliente cliente) {
		if (this.indice < this.clientes.length) {
			clientes[this.indice] = cliente;
			this.indice++;
		} else {
			Cliente[] auxiliar = new Cliente[this.clientes.length * 2];
			for (int i = 0; i < this.clientes.length; i++) {
				auxiliar[i] = this.clientes[i];
			}
			this.clientes = auxiliar;
			this.adicionar(cliente);
		}
	}

	public void remover(String cpf) {
		for(int i = 0; i < indice; i++) {
			if(clientes[i].getCpf().equals(cpf)) {
				clientes[i] = clientes[indice - 1];
				indice--;
			}
		}	
	}

	public Cliente procurar(String cpf) {
		for(int i = 0; i < indice; i++) {
			if(clientes[i].getCpf().equals(cpf))
				return clientes[i];
		}

		return null;
	}

	public void sort() {

		for (int i = 0; i < indice - 1; ++i)  {
			for (int j = i + 1; j < indice; ++j)  {
				if (clientes[i].getCpf().compareTo(clientes[j].getCpf()) > 0) {  
					Cliente aux = clientes[i];  
					clientes[i] = clientes[j];  
					clientes[j] = aux;  
				}  
			}
		}

	}

	public void atualizar(Cliente cliente) {
		
	}

	public IteratorClasse<Cliente> iterator() {
		Vector<Cliente> listCli = new Vector<Cliente>();
		for(int i = 0; i < indice; i++) {
			listCli.add(this.clientes[i]);
		}

		IteratorClasse<Cliente> iterator = new IteratorClasse<Cliente>(listCli);
		return iterator;
	}
}