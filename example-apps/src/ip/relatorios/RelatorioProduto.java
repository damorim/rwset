package relatorios;

import dados.entidade.Pedido;
import dados.interfaces.IRepositorioPedido;
import dados.IteratorClasse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class RelatorioProduto implements Iterable {


	/*
	 * A implementação de RelatorioProduto é bem similar a RelatorioCliente.
	 * A estrutura do relatório, em si, é um pouco diferente, mas a lógica
	 * é basicamente a mesma: inicializamos atributos e geramos o relatório
	 * através de métodos para popularem 2 iterators e escreverem no arquivo.
	 */

	// Atributos //
	private IteratorClasse<Pedido> iteratorPedido;
	private Date dataInicial;
	private Date dataFinal;
	private String codigo;
	private File arquivo;
	private IteratorClasse<String> informacoesRelatorio;
	private IteratorClasse<String> retornavel;
	private String nomeArquivo;

	// Construtor: inicializa os atributos e chama o método escreveRelatorio(). //
	public RelatorioProduto (IRepositorioPedido repositorioPedidos, Date dataInicial,
			Date dataFinal, String codigo) throws IOException{
		this.iteratorPedido = repositorioPedidos.iterator();
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.codigo = codigo;
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dataAtual = new Date();
		String auxiliar = formatador.format(dataAtual);
		String nomeArquivo = "Relatorio de Produtos " + auxiliar + ".txt";
		this.arquivo = new File (nomeArquivo);
		this.nomeArquivo = nomeArquivo;
		this.arquivo.createNewFile();
		this.escreveRelatorio();

	}

	// Guarda as informações que constarão no relatório //
	private void escreveRelatorio(){
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicialStr, dataFinalStr = "";
		if (this.dataInicial == null){
			dataInicialStr = "__/__/____";
		} else {
			dataInicialStr = formatador.format(this.dataInicial);
		}
		if (this.dataFinal == null){
			dataFinalStr = "__/__/____";
		} else {
			dataFinalStr = formatador.format(this.dataFinal);
		}

		Vector<String> codigosEncontrados = new Vector<String>();
		Vector<Pedido> pedidosEncontrados = new Vector<Pedido>();

		IteratorClasse<Pedido> iterator = this.iteratorPedido;
		while (iterator.hasNext()){
			Pedido auxiliar = iterator.next();
			for (int i = 0; i<auxiliar.getProdutos().length; i++){
				if(auxiliar.getProdutos()[i].getCodigo().contains(this.codigo)){
					pedidosEncontrados.add(auxiliar);
					boolean jaAdicionou = false;
					for (int j = 0; j<codigosEncontrados.size(); j++){
						if (codigosEncontrados.elementAt(j).
								equals(auxiliar.getProdutos()[i].getCodigo())){
							jaAdicionou = true;
						}
					}

					if (!jaAdicionou){
						codigosEncontrados.add(auxiliar.getProdutos()[i].getCodigo());
					}

				}
			}
		}

		String primeiraLinha = "Código Identificador: " + this.codigo;
		String segundaLinha = "Período de Compras: " + dataInicialStr +
		" - " + dataFinalStr;
		String terceiraLinha = "Códigos Encontrados: ";
		if (codigosEncontrados.isEmpty()){
			terceiraLinha = terceiraLinha + "Nenhum.";
		} else {
			for (int i = 0; i<codigosEncontrados.size(); i++){
				terceiraLinha = terceiraLinha + codigosEncontrados.elementAt(i);
				if (i != codigosEncontrados.size()-1){
					terceiraLinha = terceiraLinha + ", ";
				} else {
					terceiraLinha = terceiraLinha + ".";
				}
			}
		}

		IteratorClasse<Pedido> iterator2 = new IteratorClasse<Pedido>(pedidosEncontrados);
		Vector<Pedido> dados = new Vector<Pedido>();

		while (iterator2.hasNext()){
			Pedido auxiliar = iterator2.next();
			if ((this.dataInicial == null || auxiliar.getData().after(this.dataInicial))
					&& (this.dataFinal == null || auxiliar.getData().before(this.dataFinal))){
				dados.add(auxiliar);
			}
		}
		Collections.sort(dados);

		Vector<String> linhas = new Vector<String>();

		linhas.add(primeiraLinha);
		linhas.add(segundaLinha);
		linhas.add(terceiraLinha);

		NumberFormat nf = NumberFormat.getCurrencyInstance();

		for (int i = 0; i<dados.size(); i++){
			String dataPedido = formatador.format(dados.elementAt(i).getData());
			String nomePedido = dados.elementAt(i).getCliente().getNome();
			String valorPedido = nf.format(dados.elementAt(i).getPreco().doubleValue());
			String codigoPedido = dados.elementAt(i).getCodigo();

			String linha = dataPedido + " | CI: " + codigoPedido + " | " + valorPedido + " | " + nomePedido;
			linhas.add(linha);
		}

		this.informacoesRelatorio = new IteratorClasse<String>(linhas);
		this.retornavel = new IteratorClasse<String>(linhas);
	}

	// Escreve no arquivo //
	public void escreveArquivo() throws IOException{
		PrintWriter writer = new PrintWriter (new FileWriter (this.nomeArquivo));
		while (this.informacoesRelatorio.hasNext()){
			String linha = this.informacoesRelatorio.next();
			writer.println(linha);
		}
		writer.close();
	}

	// Retorna o iterator a ser utilizado na GUI //
	public IteratorClasse<String> iterator(){
		return this.retornavel;
	}
}