package dados.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import dados.IteratorClasse;
import dados.entidade.Pedido;
import dados.interfaces.IRepositorioPedido;

public class RepPedidoArquivo implements IRepositorioPedido {

	private Vector<Pedido> vetorPedidos;

	@SuppressWarnings({ "unchecked", "resource" })
	public RepPedidoArquivo() throws IOException {
		this.vetorPedidos = new Vector<Pedido>();

		File arquivo = new File("arquivoPedido.txt");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		if (arquivo.length() != 0) {
			ObjectInputStream reader = null;
			try {
				FileInputStream fileInput = new FileInputStream("arquivoPedido.txt");

				reader = new ObjectInputStream(fileInput);
				this.vetorPedidos = (Vector<Pedido>) reader.readObject();
			} catch (ClassNotFoundException e) { 
			} 
		}
	}

	public void adicionar(Pedido pedido) {
		this.vetorPedidos.add(pedido);
		ObjectOutputStream gravar = null;

		try {

			FileOutputStream writer = new FileOutputStream("arquivoPedido.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorPedidos);
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

	public void remover(String codigo) {
		for (int i = 0; i < this.vetorPedidos.size(); i++) {
			if (this.vetorPedidos.elementAt(i).getPreco() == null) {

			}
			else if (this.vetorPedidos.elementAt(i).getCodigo().equals(codigo)) {
				this.vetorPedidos.remove(i);
			}
		}

		ObjectOutputStream gravar = null;

		FileOutputStream writer;
		try {
			writer = new FileOutputStream("arquivoPedido.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorPedidos);
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
	public Pedido procurar(String codigo) {

		//Cria um objeto FileInputStream
		FileInputStream fileStream;
		int indiceAchado = 0;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoPedido.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);

			this.vetorPedidos = (Vector<Pedido>) os.readObject();
			for (int indice = 0; indice < vetorPedidos.size(); indice++) {
				if (this.vetorPedidos.elementAt(indice).getCodigo().equals(codigo)) {
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
		return this.vetorPedidos.elementAt(indiceAchado);

	}
	//O método atualizar é basicamente feito o procurar, porém depois que ele atualiza um elemento
	//Ele deve re-serializar tudo
	@SuppressWarnings("unchecked")
	public void atualizar(Pedido pedidosubs){
		FileInputStream fileStream;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoPedido.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);
			//Deserializa o vector e verifica se o cpf do elemento é igual 
			//O cpf do objeto, atualiza e re-serializa.
			int indiceAchado = 0;
			this.vetorPedidos = (Vector<Pedido>) os.readObject();
			for (int indice = 0; indice < vetorPedidos.size(); indice++) {
				if (this.vetorPedidos.elementAt(indice).getCodigo().equals(pedidosubs.getCodigo())) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
					this.vetorPedidos.elementAt(indiceAchado).setCliente(pedidosubs.getCliente());
					this.vetorPedidos.elementAt(indiceAchado).setData(pedidosubs.getData());
					this.vetorPedidos.elementAt(indiceAchado).setPreco(pedidosubs.getPreco());
					this.vetorPedidos.elementAt(indiceAchado).setProdutos(pedidosubs.getProdutos());
					this.vetorPedidos.elementAt(indiceAchado).setQuantidade(pedidosubs.getQuantidade());
				}


			}
			ObjectOutputStream gravar = null;

			try {

				FileOutputStream writer = new FileOutputStream("arquivoPedido.txt", false);
				gravar = new ObjectOutputStream(writer);
				gravar.writeObject(this.vetorPedidos);
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


	// ObjectOutputStream escreve por cima do arquivo antigo (append false)
	public Vector<Pedido> getListaPedidos() {
		return this.vetorPedidos;
	}

	@Override
	public IteratorClasse<Pedido> iterator() {
		// TODO Auto-generated method stub
		Vector<Pedido> listPedidos = new Vector<Pedido>();
		for (int i = 0; i < this.vetorPedidos.size(); i++) {
			listPedidos.add(this.vetorPedidos.elementAt(i));
		}
		IteratorClasse<Pedido> iterator = new IteratorClasse<Pedido>(listPedidos);
		return iterator;

	}
}