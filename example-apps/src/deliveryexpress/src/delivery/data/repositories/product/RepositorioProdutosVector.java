package delivery.data.repositories.product;

import java.util.Vector;
import delivery.business.Produto;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.data.iterator.Iterator;
import java.util.ArrayList;

public class RepositorioProdutosVector implements RepositorioObjetos<Produto> {
	
	private Vector<Produto> produtos;
	/**
	 * Instancia um repositório em vector de objetos do tipo Produto
	 */
	public RepositorioProdutosVector (){
		this.produtos = new Vector<Produto>();
		
	}
	
	public void adicionar(Produto Produto){
		this.produtos.add(Produto);		
	}
	
	public void remover (Produto Produto){
		this.produtos.remove(Produto);
	}
	
	public boolean existe(String codigo){
		boolean achou = false;
		
		Iterator<Produto> produtosCadastrados = new Iterator<Produto>(this.produtos);
		Produto proxProduto;
		
		while (produtosCadastrados.hasNext() && achou == false){
			proxProduto = produtosCadastrados.next();
			
			if (proxProduto.getCodigo().equalsIgnoreCase(codigo)){
				achou = true;
			}
			
		}
		
		return achou;
	}
	
	public ArrayList<Produto> vizualizar(String parteNome){
		Iterator<Produto> todosProdutos = new Iterator<Produto>(this.produtos);
		ArrayList<Produto> nomesCompatives = new ArrayList<Produto>();
		Produto proxProduto;
		
		while(todosProdutos.hasNext()){
			proxProduto = todosProdutos.next();
			
			if(proxProduto.getNome().contains(parteNome)){
				nomesCompatives.add(proxProduto);
			}
			
			
		}
		
		return nomesCompatives;
	}

	
	public Produto vizualizarEspecifico(String codigo) {
		Iterator<Produto> todosProdutos = new Iterator<Produto>(this.produtos);
		Produto produtoCompativel = null;
		Produto proxProduto;
		
		while(todosProdutos.hasNext()){
			proxProduto = todosProdutos.next();
			
			if(proxProduto.getCodigo().equalsIgnoreCase(codigo)){
				produtoCompativel = proxProduto;
			}
		}
		
		return produtoCompativel;
		
	}

}