package delivery.data.interfaces;

import java.util.ArrayList;

import delivery.business.Pedido;

public interface RepositorioPedidos extends RepositorioObjetos<Pedido> {

	/**
	 * Retorna uma lista de pedidos em um determinado periodo
	 * 
	 * @param dataInicial
	 *            Data de inicio do per�odo
	 * @param dataFinal
	 *            Data do final do per�odo
	 * @return Lista de objetos do tipo Pedido
	 */
	ArrayList<Pedido> vizualizarPeriodo(String dataInicial, String dataFinal);

	/**
	 * Quantidade de pedidos por cliente
	 * @param telefone
	 * telefone do cliente
	 * @return a quantidade de pedidos 
	 */
	int quantidadePedidosTelefone(String telefone);

}
