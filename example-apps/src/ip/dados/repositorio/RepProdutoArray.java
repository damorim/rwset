package dados.repositorio;

import java.util.Vector;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioProduto;
import dados.entidade.Produto;

@SuppressWarnings("rawtypes")
public class RepProdutoArray implements IRepositorioProduto, Iterable {
	
	
	private Produto[] produtos;
	private int indice;

	public boolean isVazia() {
		if (indice == 0 && produtos[0].equals(null)) 
			return true;
		return false;
	}
	
	public RepProdutoArray() {
		produtos = new Produto[2000];
		indice = 0;
	}

	public void adicionar(Produto produto) {
		if(this.indice < this.produtos.length) {
			produtos[indice] = produto;
			indice++;
		} else {
			Produto[] aux = new Produto[this.produtos.length * 2];
			for(int i = 0; i < indice; i++) {
				aux[i] = produtos[i];
			}

			this.produtos = aux;
			this.adicionar(produto);


		}
	}
	

	public void remover(String codigo) {
		for(int i = 0; i < indice; i++) {
			if(produtos[i].getCodigo().equals(codigo)) {
				produtos[i] = null;
				if(i < indice - 1 && produtos[i + 1] != null) {
					produtos[i] = produtos[i + 1];
					produtos[i + 1] = null;
				}
			}
		}

		if(produtos[this.indice - 1] == null)
			indice--;
	}

	public Produto procurar(String codigo) {
		for(int i = 0; i < indice; i++) {
			if(produtos[i].getCodigo().equals(codigo))
				return produtos[i];
		}

		return null;
	}

	public void atualizar(Produto produto) {
		for(int i = 0; i < indice; i++) {
			if(produtos[i].getCodigo().equals(produto.getCodigo()))
				produtos[i] = produto;
		}
	}
	
	public void imprimir() {
		for(int i = 0; i < indice; i++) {
			System.out.print(produtos[i].getCodigo());
		}

	}
	
	public void sort() {

		for (int i = 0; i < indice - 1; ++i)  {
			for (int j = i + 1; j < indice; ++j)  {
				if (produtos[i].getCodigo().compareTo(produtos[j].getCodigo()) > 0) {  
					Produto aux = produtos[i];  
					produtos[i] = produtos[j];  
					produtos[j] = aux;  
				}  
			}
		}

	}
	public IteratorClasse<Produto> iterator() {
		Vector<Produto> listProd = new Vector<Produto>();
		for(int i = 0; i < indice; i++) {
			listProd.add(produtos[i]);
		}

		IteratorClasse<Produto> iterator = new IteratorClasse<Produto>(listProd);
		return iterator;
	}
}

	