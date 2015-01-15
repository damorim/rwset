package dados.repositorio;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioPedido;
import dados.entidade.Pedido;


public class RepPedidoLista implements IRepositorioPedido{

	int numero_elementos; //Só pra saber se a lista esta vazia e tb o num. de el.
	Pedido primeiro = new Pedido(); //O primeiro elemento da lista vai ser o do final, o ï¿½ltimo adicionado
	Pedido ultimo =  new Pedido(); //O ultimo da lista sempre vai apontar pra null

	public RepPedidoLista() {
		this.numero_elementos = 0; 
		this.primeiro = null;
		this.ultimo = null;

	}
	//LISTA ENCADEADA

	public boolean isVazia() {
		return (primeiro == null && ultimo == null);
	}

	public void adicionar(Pedido produto) {
		/*Qnd vc adiciona 1 elemento a lista, cria-se um objeto pedido
		 *e a esse objeto vai ser atribuï¿½do o valor de primeiro, que na verdade
		 *vai se tornar o elemento depois do primeiro. E o novo primeiro, vai ser
		 *pedido, que ï¿½ o objeto criado.
		 */
		Pedido pedido = new Pedido(produto);
		if (isVazia()) {
			this.primeiro = pedido;
			this.ultimo = pedido;

		}
		else {
			((Pedido) pedido).setProx((Pedido) this.primeiro);
		}
		this.primeiro = pedido;
		numero_elementos++;
	}

	public void inserirNoFinal(Pedido produto)  {
		this.numero_elementos++;
		Pedido pedido = new Pedido(produto);
		if (isVazia())
			primeiro = pedido;
		else
			((Pedido) ultimo).setProx((Pedido) pedido);
		this.ultimo = pedido;
	}


	public void imprimir() {
		if (this.numero_elementos == 0)
			System.out.print("[]");
		else {
			System.out.print("[");
			Pedido aux = this.primeiro;
			for(int i=0; i < this.numero_elementos-1;i++) {
				System.out.print(aux.getCodigo()+' ');
				aux = ((Pedido)aux).getProx();
			}
			System.out.print(((Pedido)aux).getCodigo()+"]");



		}
	}

	public Pedido procurar(String codigo){ //Sistema de busca retornando um objeto
		Pedido aux = new Pedido(this.primeiro);
		
		if (((Pedido)aux).getCodigo().equals(codigo)) {
			return primeiro;
		}
		else {
			for(int i=0; i <= numero_elementos - 1;i++) {
				if (((Pedido)aux).getProx().getCodigo().equals(codigo)) {
					return ((Pedido)aux).getProx();
				}
				else
					aux = ((Pedido)aux).getProx();




			}

		}
		return null;
	}


	public void remover(String codigo){ //Excluir um objeto da lista
		Pedido aux, auxAnterior = new Pedido(); //Auxiliar, vai servir de apontador
		aux = this.primeiro;
		auxAnterior = null;


		if (this.primeiro.getCodigo().equals(codigo)) {
			this.primeiro = ((Pedido) primeiro).getProx();
			this.numero_elementos--;

		}
		else {
			while (aux !=null && !aux.getCodigo().equals(codigo)) {
				auxAnterior = aux;
				aux = ((Pedido) aux).getProx();
			}
			if(aux != null) {
				((Pedido) auxAnterior).setProx(((Pedido) aux).getProx());
				this.numero_elementos--;
			}
			if(aux == ultimo) {
				ultimo = auxAnterior;
			}


		}


	}
	
	
	@Override
	public IteratorClasse<Pedido> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizar(Pedido produto) {

		
		procurar(produto.getCodigo()).setCliente(produto.getCliente());
		procurar(produto.getCodigo()).setData(produto.getData());
		procurar(produto.getCodigo()).setPreco(produto.getPreco());
		procurar(produto.getCodigo()).setQuantidade(produto.getQuantidade());
		procurar(produto.getCodigo()).setProdutos(produto.getProdutos());
	}





}