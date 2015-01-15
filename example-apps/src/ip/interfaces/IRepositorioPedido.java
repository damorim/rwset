package dados.interfaces;

import dados.entidade.Pedido;
import dados.IteratorClasse;

public interface IRepositorioPedido {

	public void adicionar(Pedido pedido);
	public void remover(String codigo);
	public void atualizar(Pedido pedido);
	public Pedido procurar(String codigo);
	
	IteratorClasse<Pedido> iterator();
}