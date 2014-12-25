package delivery.data.repositories.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.iterator.Iterator;
import delivery.constants.Constants;
import delivery.business.Cliente;

public class RepositorioClientesFile implements RepositorioObjetos<Cliente> {

	private String nomeArquivo;
	private ArrayList<Cliente> clientes;
	/**
	 *  Instancia um reposit�rio em arquivo de objetos do tipo Cliente
	 */
	public RepositorioClientesFile() {

		this.nomeArquivo = Constants.registeredClientsPath;

		this.clientes = new ArrayList<Cliente>();
		File verificador = new File(this.nomeArquivo);

		if (verificador.exists()) {
			verificador.delete();
			// try {
			// FileInputStream arquivo = new FileInputStream(nomeArquivo);
			// ObjectInputStream lerArquivo = new ObjectInputStream(arquivo);
			//
			// @SuppressWarnings("unchecked")
			// Iterator<Cliente> clientesGravados = (Iterator<Cliente>)
			// lerArquivo.readObject();
			// this.clientes = clientesGravados.getObjetos();
			//
			// } catch(FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
			// }
		}
	}

	public void adicionar(Cliente cliente) {
		this.clientes.add(cliente);
		ObjectOutputStream escrever = null;
		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			escrever = new ObjectOutputStream(file);
			Iterator<Cliente> iterador = new Iterator<Cliente>(clientes);
			escrever.writeObject(iterador);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				escrever.flush();
				escrever.close();
			} catch (IOException e) {
			}
		}
	}

	public boolean existe(String telefone) {
		Iterator<Cliente> clientesCadastrados = new Iterator<Cliente>(clientes);

		while (clientesCadastrados.hasNext()) {
			if (clientesCadastrados.next().gettelefone()
					.equalsIgnoreCase(telefone)) {
				return true;
			}
		}

		return false;
	}

	public void remover(Cliente cliente) {
		this.clientes.remove(cliente);
		ObjectOutputStream reescrever = null;

		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			reescrever = new ObjectOutputStream(file);
			Iterator<Cliente> iterador = new Iterator<Cliente>(this.clientes);
			reescrever.writeObject(iterador);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reescrever.flush();
				reescrever.close();
			} catch (IOException e) {
			}
		}
	}

	public ArrayList<Cliente> vizualizar(String parteNome) {
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		ArrayList<Cliente> nomesCompativeis = new ArrayList<Cliente>();

		while (todosClientes.hasNext()) {
			Cliente cliente = todosClientes.next();

			if (cliente.getNome().contains(parteNome)) {
				nomesCompativeis.add(cliente);
			}
		}

		return nomesCompativeis;
	}

	public Cliente vizualizarEspecifico(String telefone) {
		Iterator<Cliente> todosClientes = new Iterator<Cliente>(this.clientes);
		Cliente clienteCompativel = null;
		Cliente clienteLoop = null;
		while (todosClientes.hasNext()) {
			clienteLoop = todosClientes.next();
			if (clienteLoop.gettelefone().equalsIgnoreCase(telefone)) {
				clienteCompativel = clienteLoop;
			}
		}

		return clienteCompativel;
	}
}
