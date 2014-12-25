package delivery.data.repositories.product;

import delivery.data.iterator.Iterator;
import delivery.data.interfaces.RepositorioObjetos;
import delivery.business.Produto;
import java.util.ArrayList;

public class RepositorioProdutosArray implements RepositorioObjetos<Produto> {
	
	private Produto[] produtos;
	private int indice;
	private int capacidade;
	/**
	 * Instancia um repositório em array de objetos do tipo Produto
	 */
	public RepositorioProdutosArray (){
		
		this.capacidade = 50;
		this.produtos = new Produto [capacidade];
		this.indice = 0;
	
	}
	
	private int getIndice(String codigo){
		int posicao = -1;
		
		for (int i = 0; i < this.indice; i++) {
			
			if (codigo.equalsIgnoreCase(produtos[i].getCodigo())){
				posicao = i;
			}
		}
		return posicao;
		
	}
	
	
	private void expandirArray(){
		this.capacidade += 10;
		
		Produto[] novoArray = new Produto[capacidade];
		
		for (int i = 0; i < produtos.length; i++) {
			novoArray[i] = produtos[i];
		}
		this.produtos = novoArray;
	}
	
	
	public void adicionar(Produto Produto){
		
		if(this.indice == this.capacidade){
			this.expandirArray();
		}
		
		this.produtos[this.indice] = Produto;
		this.indice ++;
	}
	
	public void remover (Produto produto){
		
		int posicao = this.getIndice(produto.getCodigo());
		
		this.produtos[posicao] = this.produtos[indice - 1];
		indice --;
	}
	
	public boolean existe(String codigo){
		
		int posicao = this.getIndice(codigo);
		
		if (posicao >= 0){
			return true;
		}else {
			return false;
		}
		
	}
	
	public ArrayList<Produto> vizualizar(String parteNome){
		Iterator<Produto> todosprodutos = new Iterator<Produto>(this.produtos);
		ArrayList<Produto> nomesCompativeis = new ArrayList<Produto>();
		Produto proxProduto;
		while(todosprodutos.hasNext()) {
			proxProduto= todosprodutos.next();
			
			if(proxProduto.getNome().contains(parteNome)){
				nomesCompativeis.add(proxProduto);
			}
			
		}
		
		return nomesCompativeis;
		
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
