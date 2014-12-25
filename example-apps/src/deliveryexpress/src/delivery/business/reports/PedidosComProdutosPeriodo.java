package delivery.business.reports;

import java.util.ArrayList;

import delivery.business.Pedido;
import delivery.data.interfaces.InterfaceIterator;
import delivery.data.iterator.Iterator;

public class PedidosComProdutosPeriodo {


	public ArrayList<Pedido> pedidosDesejados(ArrayList<Pedido> pedidosComProdutos, ArrayList<Pedido> pedidosPeriodo){
		ArrayList<Pedido> pedidosDesejados = new ArrayList<Pedido>();
		InterfaceIterator<Pedido> pedidosContemProdutos = new Iterator<Pedido>(pedidosComProdutos);
		InterfaceIterator<Pedido> pedidosDoPeriodo = new Iterator<Pedido>(pedidosPeriodo);
		
		Pedido proxPedidoPeriodo = null;
		Pedido proxPedidoComProduto = null;
		String codigoString1 = null;
		String codigoString2 = null;

		while (pedidosContemProdutos.hasNext()) {
			proxPedidoComProduto = pedidosContemProdutos.next();
			codigoString1 = proxPedidoComProduto.getCodidoPedido() + "";

			while (pedidosDoPeriodo.hasNext()) {
				proxPedidoPeriodo = pedidosDoPeriodo.next();
				codigoString2 = proxPedidoPeriodo.getCodidoPedido() + "";
				
				if(codigoString1.equalsIgnoreCase(codigoString2)){
					pedidosDesejados.add(proxPedidoComProduto);
				}
			}
		}	
		return pedidosDesejados;

	}

}
