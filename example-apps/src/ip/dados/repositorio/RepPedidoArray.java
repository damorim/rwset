package dados.repositorio;

import java.util.Vector;

import dados.IteratorClasse;
import dados.interfaces.IRepositorioPedido;
import dados.entidade.Pedido;


@SuppressWarnings("rawtypes")
public class RepPedidoArray implements IRepositorioPedido, Iterable {
	private Pedido[] pedidos;
	private int indice;

	public boolean isVazia() {
		if (indice == 0 && pedidos[0].equals(null)) 
			return true;
		return false;
	}
	
	public RepPedidoArray() {
		pedidos = new Pedido[1000];
		indice = 0;
	}

	public void adicionar(Pedido pedido) {

		if(this.indice < this.pedidos.length) {
			pedidos[indice] = pedido;
			indice++;
		} else {
			Pedido[] aux = new Pedido[this.pedidos.length * 2];
			for(int i = 0; i < this.indice; i++) {
				aux[i] = this.pedidos[i];
			}

			this.pedidos = aux;
			this.adicionar(pedido);
			}

	}
	
	public void remover(String codigo) {
		for(int i = 0; i < indice; i++) {
			if(pedidos[i].getCodigo().equals(codigo)) {
				pedidos[i] = null;
				if(i < indice - 1 && pedidos[i + 1] != null) {
					pedidos[i] = pedidos[i + 1];
					pedidos[i + 1] = null;
				}
			}
		}

		if(pedidos[this.indice - 1] == null)
			indice--;
	}

	public Pedido procurar(String codigo) {
		for(int i = 0; i < indice; i++) {
			if(pedidos[i].getCodigo().equals(codigo))
				return pedidos[i];
		}

		return null;
	}
	
	public void imprimir() {
		for(int i = 0; i < indice; i++) {
			System.out.print(pedidos[i].getCodigo());
		}

	}

	public void atualizar(Pedido pedido) {
		for(int i = 0; i < indice; i++) {
			if(pedidos[i].getCodigo().equals(pedido.getCodigo()))
				pedidos[i] = pedido;
		}
	}
	
	public void sort() {

		for (int i = 0; i < indice - 1; ++i)  {
			for (int j = i + 1; j < indice; ++j)  {
				if (pedidos[i].getCodigo().compareTo(pedidos[j].getCodigo()) > 0) {  
					Pedido aux = pedidos[i];  
					pedidos[i] = pedidos[j];  
					pedidos[j] = aux;  
				}  
			}
		}

	}

	public IteratorClasse<Pedido> iterator() {
		Vector<Pedido> listPedido = new Vector<Pedido>();
		for(int i = 0; i < indice; i++) {
			listPedido.add(pedidos[i]);
		}

		IteratorClasse<Pedido> iterator = new IteratorClasse<Pedido>(listPedido);
		return iterator;
	}

	public Pedido[] getLista() {
		return this.pedidos;
	}


}