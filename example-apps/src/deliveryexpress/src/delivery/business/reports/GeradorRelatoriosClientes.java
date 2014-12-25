package delivery.business.reports;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import delivery.constants.Constants;
import delivery.business.Cliente;
import delivery.business.Pedido;
import delivery.business.controllers.ControladorCliente;
import delivery.business.controllers.ControladorPedido;

public class GeradorRelatoriosClientes {

	private ControladorCliente clientes;
	private ControladorPedido pedidos;
	private String parteNome;
	private String dataInicial;
	private String dataFinal;

	public GeradorRelatoriosClientes(ControladorCliente controlClientes,
			ControladorPedido controlPedidos, String parteNome,
			String dataInicial, String dataFinal) {
		this.clientes = controlClientes;
		this.pedidos = controlPedidos;
		this.parteNome = parteNome;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;
	}

	public ArrayList<String> gerarRelatorio() {

		ArrayList<String> relatorioFinal = new ArrayList<String>();

		ArrayList<Cliente> clientesCompativeis = clientes
				.filtrarClientes(parteNome);
		ArrayList<Pedido> pedidosPeriodo = pedidos.filtrarPedidosData(
				dataInicial, dataFinal);

		for (Pedido pedidoDoPeriodo : pedidosPeriodo) {
			for (Cliente cliente : clientesCompativeis) {
				if (cliente.gettelefone().equalsIgnoreCase(
						pedidoDoPeriodo.getTelefoneCliente())) {

					String data = pedidoDoPeriodo
							.getDataPedidoStringFormatado();
					String nomeCliente = cliente.getNome();
					int quantidadeProdutos = pedidoDoPeriodo
							.getQuantidadeProdutos();
					String[] codigosProdutos = new String[quantidadeProdutos];

					for (int k = 0; k < pedidoDoPeriodo.getProdutos().length; k++) {
						codigosProdutos[k] = pedidoDoPeriodo.getProdutos()[k]
								.getCodigo();
					}

					double valorPedido = pedidoDoPeriodo.getValor()
							.doubleValue();
					// adequa as informa��es na formata��o do relatorio
					relatorioFinal.add(formatarRelatorio(data, nomeCliente,
							quantidadeProdutos, codigosProdutos, valorPedido));
				}
			}
		}

		/*
		 * Escrita do relat�rio num arquivo
		 */
		FileWriter outFile = null;
		PrintWriter out = null;
		try {
			outFile = new FileWriter(Constants.relatoriosPath
					+ this.gerarNomeRelatorio());
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String s : relatorioFinal) {
			out.println(s);
		}
		out.close();

		return relatorioFinal;
	}

	private String gerarNomeRelatorio() {
		GregorianCalendar calendar = new GregorianCalendar();

		int mes = calendar.get(Calendar.MONTH) + 1;

		String nomeRelatorio = "Relatorio Clientes "
				+ calendar.get(Calendar.YEAR) + "" + mes + ""
				+ calendar.get(Calendar.DAY_OF_MONTH) + ""
				+ calendar.get(Calendar.HOUR_OF_DAY) + ""
				+ calendar.get(Calendar.MINUTE) + ""
				+ calendar.get(Calendar.SECOND) + ".txt";

		return nomeRelatorio;
	}

	private String formatarRelatorio(String data, String nomeCliente,
			int quantidadeProdutos, String[] codigosProdutos, double valorPedido) {
		String relatorio = "";

		relatorio += data + " | " + nomeCliente + " | " + quantidadeProdutos
				+ " Produtos | ";

		for (int i = 0; i < codigosProdutos.length; i++) {
			relatorio += "C: " + codigosProdutos[i] + " | ";
		}

		relatorio += "R$ " + valorPedido + "\n";

		return relatorio;
	}

}
