package delivery.data.repositories.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.iterator.Iterator;
import delivery.constants.Constants;
import delivery.business.Produto;

public class RepositorioProdutosFile implements RepositorioObjetos<Produto> {

	private String nomeArquivo;
	private ArrayList<Produto> produtos;
	/**
	 * Instancia um reposit�rio em arquivo de objetos do tipo Produto
	 */
	public RepositorioProdutosFile() {

		this.nomeArquivo = Constants.registeredProductsPath;

		this.produtos = new ArrayList<Produto>();
		File verificador = new File(this.nomeArquivo);

		if (verificador.exists()) {
			verificador.delete();
			// try{
			//
			// FileInputStream arquivo = new FileInputStream(nomeArquivo);
			// ObjectInputStream lerArquivo = new ObjectInputStream(arquivo);
			//
			// @SuppressWarnings("unchecked")
			// Iterator<Produto> produtosGravados =(Iterator<Produto>)
			// lerArquivo.readObject();
			//
			// this.produtos = produtosGravados.getObjetos();
			// System.out.println("fuuuuuncionou");
			//
			// }catch(FileNotFoundException e){
			// e.printStackTrace();
			// }catch (IOException e){
			// System.out.println("fuuuuu2");
			// }catch (ClassNotFoundException e){
			// System.out.println("fuuuuu3");
			//
			// }
		}
	}

	public void adicionar(Produto produto) {
		this.produtos.add(produto);
		ObjectOutputStream escrever = null;
		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			escrever = new ObjectOutputStream(file);
			Iterator<Produto> iterador = new Iterator<Produto>(this.produtos);
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

	public boolean existe(String codigo) {
		Iterator<Produto> ProdutosCadastrados = new Iterator<Produto>(produtos);

		while (ProdutosCadastrados.hasNext()) {
			if (ProdutosCadastrados.next().getCodigo().equalsIgnoreCase(codigo)) {
				return true;
			}
		}
		
		return false;
	}

	public void remover(Produto produto) {
		this.produtos.remove(produto);
		ObjectOutputStream reescrever = null;

		try {
			FileOutputStream file = new FileOutputStream(nomeArquivo);
			reescrever = new ObjectOutputStream(file);
			Iterator<Produto> iterador = new Iterator<Produto>(
					this.produtos);
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

	public ArrayList<Produto> vizualizar(String parteNome) {
		Iterator<Produto> todosProdutos = new Iterator<Produto>(this.produtos);
		ArrayList<Produto> nomesCompativeis = new ArrayList<Produto>();
		Produto proxProduto;

		while (todosProdutos.hasNext()) {
			proxProduto = todosProdutos.next();

			if (proxProduto.getNome().contains(parteNome)) {
				nomesCompativeis.add(proxProduto);
			}
		}
		
		return nomesCompativeis;
	}

	public Produto vizualizarEspecifico(String codigo) {
		Iterator<Produto> todosProdutos = new Iterator<Produto>(this.produtos);
		Produto ProdutoCompativel = null;
		Produto proxProduto;

		while (todosProdutos.hasNext()) {
			proxProduto = todosProdutos.next();

			if (proxProduto.getCodigo().equalsIgnoreCase(codigo)) {
				ProdutoCompativel = proxProduto;
			}
		}

		return ProdutoCompativel;
	}

}
