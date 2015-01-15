package dados.repositorio;

import dados.interfaces.IRepositorioCliente;
import dados.entidade.Cliente;
import dados.IteratorClasse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;



public class RepClienteArquivo implements IRepositorioCliente{

	private Vector<Cliente> vetorClientes;


	@SuppressWarnings({ "unchecked", "resource" })
	public RepClienteArquivo() throws IOException {
		this.vetorClientes = new Vector<Cliente>();

		File arquivo = new File("arquivoCliente.txt");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		if (arquivo.length() != 0) {
			ObjectInputStream reader = null;
			try {
				FileInputStream fileInput = new FileInputStream("arquivoCliente.txt");

				reader = new ObjectInputStream(fileInput);
				this.vetorClientes = (Vector<Cliente>) reader.readObject();
			} catch (ClassNotFoundException e) { 
			} 
		}
	}

	public void adicionar(Cliente cliente) {
		this.vetorClientes.add(cliente);
		ObjectOutputStream gravar = null;

		try {

			FileOutputStream writer = new FileOutputStream("arquivoCliente.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorClientes);
			gravar.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				gravar.close();
			} catch (IOException e) {
			}
		}
	}

	public void remover(String cpf) {
		for (int i = 0; i < this.vetorClientes.size(); i++) {
			if (this.vetorClientes.elementAt(i).getContato() == null) {

			}
			else if (this.vetorClientes.elementAt(i).getCpf().equals(cpf)) {
				this.vetorClientes.remove(i);
			}
		}

		ObjectOutputStream gravar = null;

		FileOutputStream writer;
		try {
			writer = new FileOutputStream("arquivoCliente.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorClientes);
			gravar.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				gravar.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Cliente procurar(String cpf) {

		//Cria um objeto FileInputStream
		FileInputStream fileStream;
		int indiceAchado = 0;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoCliente.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);

			this.vetorClientes = (Vector<Cliente>) os.readObject();
			for (int indice = 0; indice < vetorClientes.size(); indice++) {
				if (this.vetorClientes.elementAt(indice).getCpf().equals(cpf)) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
				}



			} 
		} catch (FileNotFoundException e) {			
		} catch (IOException ex) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
			}

		}
		return this.vetorClientes.elementAt(indiceAchado);

	}
	//O método atualizar é basicamente feito o procurar, porém depois que ele atualiza um elemento
	//Ele deve re-serializar tudo
	@SuppressWarnings("unchecked")
	public void atualizar(Cliente cliente){
		FileInputStream fileStream;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoCliente.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);
			//Deserializa o vector e verifica se o cpf do elemento é igual 
			//O cpf do objeto, atualiza e re-serializa.
			int indiceAchado = 0;
			this.vetorClientes = (Vector<Cliente>) os.readObject();
			for (int indice = 0; indice < vetorClientes.size(); indice++) {
				if (this.vetorClientes.elementAt(indice).getCpf().equals(cliente.getCpf())) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
					this.vetorClientes.elementAt(indiceAchado).setContato(cliente.getContato());
					this.vetorClientes.elementAt(indiceAchado).setNome(cliente.getNome());
					this.vetorClientes.elementAt(indiceAchado).setSenha(cliente.getSenha());

				}


			}
			ObjectOutputStream gravar = null;

			try {

				FileOutputStream writer = new FileOutputStream("arquivoCliente.txt", false);
				gravar = new ObjectOutputStream(writer);
				gravar.writeObject(this.vetorClientes);
				gravar.flush();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					gravar.close();

				} catch (IOException e) {
				}
			}

		} catch (FileNotFoundException e) {			
		} catch (IOException ex) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
			}

		}


	}



	public IteratorClasse<Cliente> iterator() {
		Vector<Cliente> listClientes = new Vector<Cliente>();
		for (int i = 0; i < this.vetorClientes.size(); i++) {
			listClientes.add(this.vetorClientes.elementAt(i));
		}
		IteratorClasse<Cliente> iterator = new IteratorClasse<Cliente>(listClientes);
		return iterator;
	}
	
}
