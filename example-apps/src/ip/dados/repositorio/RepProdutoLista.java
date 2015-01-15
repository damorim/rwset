package dados.repositorio;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioProduto;
import dados.entidade.Livro;
import dados.entidade.Produto;



public class RepProdutoLista implements IRepositorioProduto{

	int numero_elementos; //Só pra saber se a lista esta vazia e tb o num. de el.
	Produto primeiro = new Livro(); //O primeiro elemento da lista vai ser o do final, o ï¿½ltimo adicionado
	Produto ultimo =  new Livro(); //O ultimo da lista sempre vai apontar pra null

	public RepProdutoLista() {
		this.numero_elementos = 0; 
		this.primeiro = null;
		this.ultimo = null;

	}
	//LISTA ENCADEADA

	public boolean isVazia() {
		return (primeiro == null && ultimo == null);
	}

	public void adicionar(Produto produto) {
		/*Qnd vc adiciona 1 elemento a lista, cria-se um objeto livro
		 *e a esse objeto vai ser atribuï¿½do o valor de primeiro, que na verdade
		 *vai se tornar o elemento depois do primeiro. E o novo primeiro, vai ser
		 *livro, que ï¿½ o objeto criado.
		 */
		Produto livro = new Livro(produto);
		if (isVazia()) {
			this.primeiro = livro;
			this.ultimo = livro;

		}
		else {
			((Livro) livro).setProx((Livro) this.primeiro);
		}
		this.primeiro = livro;
		numero_elementos++;
	}

	public void inserirNoFinal(Produto produto)  {
		this.numero_elementos++;
		Produto livro = new Livro(produto);
		if (isVazia())
			primeiro = livro;
		else
			((Livro) ultimo).setProx((Livro) livro);
		this.ultimo = livro;
	}


	public void imprimir() {
		if (this.numero_elementos == 0)
			System.out.print("[]");
		else {
			System.out.print("[");
			Produto aux = this.primeiro;
			for(int i=0; i < this.numero_elementos-1;i++) {
				System.out.print(aux.getCodigo()+' ');
				aux = ((Livro)aux).getProx();
			}
			System.out.print(((Livro)aux).getCodigo()+"]");



		}
	}

	public Produto procurar(String codigo){ //Sistema de busca retornando um objeto
		Produto aux = new Livro(this.primeiro);

		if (((Livro)aux).getCodigo().equals(codigo)) {
			return primeiro;
		}
		else {
			for(int i=0; i <= numero_elementos - 1;i++) {
				if (((Livro)aux).getProx().getCodigo().equals(codigo)) {
					return ((Livro)aux).getProx();
				}
				else
					aux = ((Livro)aux).getProx();




			}

		}
		return null;
	}


	public void remover(String codigo){ //Excluir um objeto da lista
		Produto aux, auxAnterior = new Livro(); //Auxiliar, vai servir de apontador
		aux = this.primeiro;
		auxAnterior = null;


		if (this.primeiro.getCodigo().equals(codigo)) {
			this.primeiro = ((Livro) primeiro).getProx();
			this.numero_elementos--;

		}
		else {
			while (aux !=null && !aux.getCodigo().equals(codigo)) {
				auxAnterior = aux;
				aux = ((Livro) aux).getProx();
			}
			if(aux != null) {
				((Livro) auxAnterior).setProx(((Livro) aux).getProx());
				this.numero_elementos--;
			}
			if(aux == ultimo) {
				ultimo = auxAnterior;
			}


		}


	}


	@Override
	public IteratorClasse<Produto> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizar(Produto produto) {


		procurar(produto.getCodigo()).setDescricao(produto.getDescricao());
		procurar(produto.getCodigo()).setNome(produto.getNome());
		procurar(produto.getCodigo()).setPreco(produto.getPreco());


	}





}