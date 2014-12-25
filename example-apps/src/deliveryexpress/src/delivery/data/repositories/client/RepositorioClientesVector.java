package delivery.data.repositories.client;

import java.util.Vector;
import delivery.business.Cliente;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.iterator.Iterator;
import java.util.ArrayList;

public class RepositorioClientesVector implements RepositorioObjetos<Cliente> {

	private Vector<Cliente> clientes;
	/**
	 * Instancia um repositório em vector de objetos do tipo Cliente
	 */
	public RepositorioClientesVector (){
		this.clientes = new Vector<Cliente>();

	}

	public void adicionar(Cliente cliente){
		this.clientes.add(cliente);		
	}

	public void remover (Cliente cliente){
		this.clientes.remove(cliente);
	}

	public boolean existe(String telefone){
		boolean achou = false;

		Iterator<Cliente> clientesCadastrados = new Iterator<Cliente>(this.clientes);

		while (clientesCadastrados.hasNext() && achou == false){
			Cliente cliente = clientesCadastrados.next();

			if (cliente.gettelefone().equalsIgnoreCase(telefone)){
				achou = true;
			}

		}

		return achou;
	}

	public ArrayList<Cliente> vizualizar(String parteNome){
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		ArrayList<Cliente> nomesCompatives = new ArrayList<Cliente>();
		Cliente proxCliente;

		while(todosClientes.hasNext()){
			proxCliente = todosClientes.next();

			if(proxCliente.getNome().contains(parteNome)){
				nomesCompatives.add(proxCliente);
			}


		}

		return nomesCompatives;
	}


	public Cliente vizualizarEspecifico(String telefone) {
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		Cliente clienteCompativel =  null;
		Cliente proxCliente;

		while(todosClientes.hasNext()){
			proxCliente = todosClientes.next();

			if(proxCliente.gettelefone().equalsIgnoreCase(telefone)){
				clienteCompativel = proxCliente;
			}

		}

		return clienteCompativel;
	}

}
