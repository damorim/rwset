package dados.repositorio;

import dados.interfaces.IRepositorioProduto;
import dados.entidade.Produto;
import dados.IteratorClasse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;



public class RepProdutoArquivo implements IRepositorioProduto{

	private Vector<Produto> vetorProdutos;


	@SuppressWarnings({ "unchecked", "resource" })
	public RepProdutoArquivo() throws IOException {
		this.vetorProdutos = new Vector<Produto>();

		File arquivo = new File("arquivoProduto.txt");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		if (arquivo.length() != 0) {
			ObjectInputStream reader = null;
			try {
				FileInputStream fileInput = new FileInputStream("arquivoProduto.txt");

				reader = new ObjectInputStream(fileInput);
				this.vetorProdutos = (Vector<Produto>) reader.readObject();
			} catch (ClassNotFoundException e) { 
			} 
		}
	}

	public void adicionar(Produto produto) {
		this.vetorProdutos.add(produto);
		ObjectOutputStream gravar = null;

		try {

			FileOutputStream writer = new FileOutputStream("arquivoProduto.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorProdutos);
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
		for (int i = 0; i < this.vetorProdutos.size(); i++) {
			if (this.vetorProdutos.elementAt(i).getPreco() == null) {

			}
			else if (this.vetorProdutos.elementAt(i).getCodigo().equals(cpf)) {
				this.vetorProdutos.remove(i);
			}
		}

		ObjectOutputStream gravar = null;

		FileOutputStream writer;
		try {
			writer = new FileOutputStream("arquivoProduto.txt", false);
			gravar = new ObjectOutputStream(writer);
			gravar.writeObject(this.vetorProdutos);
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
	public Produto procurar(String cpf) {

		//Cria um objeto FileInputStream
		FileInputStream fileStream;
		int indiceAchado = 0;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoProduto.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);

			this.vetorProdutos = (Vector<Produto>) os.readObject();
			for (int indice = 0; indice < vetorProdutos.size(); indice++) {
				if (this.vetorProdutos.elementAt(indice).getCodigo().equals(cpf)) {
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
		return this.vetorProdutos.elementAt(indiceAchado);

	}
	//O método atualizar é basicamente feito o procurar, porém depois que ele atualiza um elemento
	//Ele deve re-serializar tudo
	@SuppressWarnings("unchecked")
	public void atualizar(Produto produtosubs){
		FileInputStream fileStream;
		ObjectInputStream os = null;
		try {
			fileStream = new FileInputStream("arquivoProduto.txt");
			//Cria um ObjectInputStream
			os = new ObjectInputStream(fileStream);
			//Deserializa o vector e verifica se o cpf do elemento é igual 
			//O cpf do objeto, atualiza e re-serializa.
			int indiceAchado = 0;
			this.vetorProdutos = (Vector<Produto>) os.readObject();
			for (int indice = 0; indice < vetorProdutos.size(); indice++) {
				if (this.vetorProdutos.elementAt(indice).getCodigo().equals(produtosubs.getCodigo())) {
					indiceAchado = indice;
					//System.out.print(indiceAchado);
					this.vetorProdutos.elementAt(indiceAchado).setDescricao(produtosubs.getDescricao());
					this.vetorProdutos.elementAt(indiceAchado).setNome(produtosubs.getNome());
					this.vetorProdutos.elementAt(indiceAchado).setPreco(produtosubs.getPreco());

				}


			}
			ObjectOutputStream gravar = null;

			try {

				FileOutputStream writer = new FileOutputStream("arquivoProduto.txt", false);
				gravar = new ObjectOutputStream(writer);
				gravar.writeObject(this.vetorProdutos);
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



	public IteratorClasse<Produto> iterator() {
		Vector<Produto> listProdutos = new Vector<Produto>();
		for (int i = 0; i < this.vetorProdutos.size(); i++) {
			listProdutos.add(this.vetorProdutos.elementAt(i));
		}
		IteratorClasse<Produto> iterator = new IteratorClasse<Produto>(listProdutos);
		return iterator;
	}
}