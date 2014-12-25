package delivery.data.repositories.client;

import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.iterator.*;
import delivery.business.Cliente;

import java.util.ArrayList;

public class RepositorioClientesArray implements RepositorioObjetos<Cliente> {
	
	private Cliente[] clientes;
	private int indice;
	private int capacidade;
	/**
	 * Instancia um repositório em array de objetos do tipo Cliente
	 */
	public RepositorioClientesArray (){
		
		this.capacidade = 50;
		this.clientes = new Cliente [capacidade];
		this.indice = 0;
	
	}
	
	private int getIndice(String telefone){
		int posicao = -1;
		
		for (int i = 0; i < this.indice; i++) {
			
			if (telefone.equalsIgnoreCase(clientes[i].gettelefone())){
				posicao = i;
			}
		}
		return posicao;
		
	}
	
	
	private void expandirArray(){
		this.capacidade += 10;
		
		Cliente[] novoArray = new Cliente[capacidade];
		
		for (int i = 0; i < clientes.length; i++) {
			novoArray[i] = clientes[i];
		}
		this.clientes = novoArray;
	}
	
	
	public void adicionar(Cliente cliente){
		
		if(this.indice == this.capacidade){
			this.expandirArray();
		}
		
		this.clientes[this.indice] = cliente;
		this.indice ++;
	}
	
	public void remover (Cliente cliente){
		
		int posicao = this.getIndice(cliente.gettelefone());
		
		this.clientes[posicao] = this.clientes[indice - 1];
		indice --;
	}
	
	public boolean existe(String telefone){
		
		int posicao = this.getIndice(telefone);
		
		if (posicao >= 0){
			return true;
		}else {
			return false;
		}
		
	}
	
	public ArrayList<Cliente> vizualizar(String parteNome){
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		ArrayList<Cliente> nomesCompativeis = new ArrayList<Cliente>();
		Cliente proxCliente;
		while(todosClientes.hasNext()) {
			proxCliente = todosClientes.next();
			
			if(proxCliente.getNome().contains(parteNome)){
				nomesCompativeis.add(proxCliente);
			}
			
		}
		
		return nomesCompativeis;
		
	}

	public Cliente vizualizarEspecifico(String telefone) {
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		Cliente clienteCompativel = null;
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
