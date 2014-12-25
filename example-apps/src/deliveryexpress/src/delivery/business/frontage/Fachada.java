package delivery.business.frontage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import delivery.constants.Constants;
import delivery.business.*;
import delivery.business.controllers.*;
import delivery.business.exceptions.client.*;
import delivery.business.exceptions.product.*;
import delivery.business.exceptions.requests.DataObrigatorioException;
import delivery.business.exceptions.requests.ProdutosObrigatorioException;
import delivery.business.exceptions.requests.QuantidadeInvalidaException;
import delivery.business.reports.GeradorRelatorioProdutos;
import delivery.business.reports.GeradorRelatoriosClientes;
import delivery.data.interfaces.*;
import delivery.data.repositories.client.*;
import delivery.data.repositories.product.*;
import delivery.data.repositories.order.*;

public class Fachada {

	/*
	 * Declara��o dos atributos que ser�o inst�ncias de reposit�rios e
	 * controladores da classe Fachada.
	 */
	private RepositorioObjetos<Cliente> repClientes;
	private RepositorioObjetos<Produto> repProduto;
	private RepositorioPedidos repPedido;
	private ControladorCliente controlCliente;
	private ControladorProduto controlProduto;
	private ControladorPedido controlPedido;

	public Fachada() {

		/*
		 * Leitura do arquivo config.txt para discernir qual o tipo de
		 * reposit�rio a ser usado.
		 */
		
		try {
			InputStream input = ClassLoader.getSystemResourceAsStream(Constants.repositoryTypePath);
			BufferedInputStream stream = new BufferedInputStream(input);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(stream));
			String line = bufRead.readLine();

			/*
			 * Dependendo do conte�do do arquivo, criar as int�ncias dos
			 * reposit�rios, sejam eles do tipo Array, Vector ou File.
			 */

			if (line.equalsIgnoreCase("ARRAY")) {

				this.repClientes = new RepositorioClientesArray();
				this.repPedido = new RepositorioPedidoArray();
				this.repProduto = new RepositorioProdutosArray();

			} else if (line.equalsIgnoreCase("VECTOR")) {

				this.repClientes = new RepositorioClientesVector();
				this.repPedido = new RepositorioPedidoVector();
				this.repProduto = new RepositorioProdutosVector();

			} else if (line.equalsIgnoreCase("FILE")) {

				this.repClientes = new RepositorioClientesFile();
				this.repPedido = new RepositorioPedidoFile();
				this.repProduto = new RepositorioProdutosFile();

			}

			/*
			 * Cria��o dos controladores, que funcionar�o como interface entre a
			 * classe Fachada e os reposit�rios.
			 */

			this.controlCliente = new ControladorCliente(repClientes);
			this.controlProduto = new ControladorProduto(repProduto);
			this.controlPedido = new ControladorPedido(repPedido, repClientes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * CLIENTES
	 */

	/**
	 * Filtra os clientes salvos no reposit�rio
	 * 
	 * @param parteNome
	 *            usado para filtrar os clientes
	 * @return clientes que passaram no filtro
	 */
	public ArrayList<Cliente> filtrarClientes(String parteNome) {
		return controlCliente.filtrarClientes(parteNome);
	}

	/**
	 * Cria um objeto Cliente e o envia para o controlador de objetos do tipo
	 * Cliente para ser adicionado ao reposit�rio
	 * 
	 * @param nome
	 *            nome do cliente
	 * @param cpf
	 *            cpf do cliente
	 * @param telefone
	 *            telefone do cliente
	 * @param logradouro
	 *            logradouro do cliente
	 * @param numero
	 *            numero da resid�ncia do cliente
	 * @param complemento
	 *            informa��es complementares sobre o endere�o do cliente
	 * @param cidade
	 *            cidade em que o cliente mora
	 * @param bairro
	 *            bairro onde o cliente mora
	 * @param observacoes
	 *            coment�rios adicionais
	 * @throws CPFObrigatorioException
	 *             Ocorre se o par�metro "cpf" estiver vazio
	 * @throws NomeObrigatorioException
	 *             Ocorre se o par�metro "nome" estiver vazio
	 * @throws TelefoneObrigatorioException
	 *             Ocorre se o par�metro "telefone" estiver vazio
	 * @throws BairroObrigatorioException
	 *             Ocorre se o par�metro "bairro" estiver vazio
	 * @throws CidadeObrigatorioException
	 *             Ocorre se o par�metro "cidade" estiver vazio
	 * @throws LogradouroObrigatorioException
	 *             Ocorre se o par�metro "logradouro" estiver vazio
	 * @throws NumeroObrigatorioException
	 *             Ocorre se o par�metro "numero" estiver vazio
	 * @throws TelefoneExistenteException
	 *             Ocorre se j� existir um cliente cadastrado com esse mesmo
	 *             telefone
	 */
	public void adicionarCliente(String nome, String cpf, String telefone,
			String logradouro, String numero, String complemento,
			String cidade, String bairro, String observacoes)
			throws CPFObrigatorioException, NomeObrigatorioException,
			TelefoneObrigatorioException, BairroObrigatorioException,
			CidadeObrigatorioException, LogradouroObrigatorioException,
			NumeroObrigatorioException, TelefoneExistenteException {

		Cliente cliente = null;
		Endereco endereco = null;

		// O atributo "complemento" da classe Endereco, essa atributo de
		// Cliente, n�o é obrigat�rio.
		if (complemento.isEmpty()) {
			endereco = new Endereco(logradouro, numero, bairro, cidade);
		} else {
			endereco = new Endereco(logradouro, numero, complemento, bairro,
					cidade);
		}
		// O atributo observacoes também n�o é obrigat�rio.
		if (observacoes.isEmpty()) {
			cliente = new Cliente(cpf, nome, telefone, endereco);
		} else {
			cliente = new Cliente(cpf, nome, telefone, observacoes, endereco);
		}

		controlCliente.adicionarCliente(cliente);
	}

	/**
	 * Verifica a existência de um objeto Cliente
	 * 
	 * @param telefone
	 *            String usada para encontrar o objeto Cliente
	 * @return boolean Retorna verdadeiro se existe, falso se n�o existe
	 */
	public boolean existeCliente(String telefone) {
		return controlCliente.existeCliente(telefone);
	}

	/**
	 * Retorna objeto Cliente com determinado telefone
	 * 
	 * @param telefone
	 *            String usada para identificar o objeto Cliente
	 * @return Objeto Cliente
	 */
	public Cliente retornaCliente(String telefone) {
		return controlCliente.retornaCliente(telefone);
	}

	/**
	 * Remove determinado objeto Cliente
	 * 
	 * @param telefone
	 *            String usada para identificar o objeto Cliente
	 * @throws ClienteNaoCadastradoException
	 *             Lan�ada se tal objeto n�o for encontrado
	 */
	public void removerCliente(String telefone)
			throws ClienteNaoCadastradoException {
		controlCliente.removerCliente(telefone);
	}

	/*
	 * PRODUTOS
	 */

	/**
	 * Filtra os objetos Produto usando parte do nome
	 * 
	 * @param parteNome
	 *            String usada para filtrar os objetos
	 * @return ArrayList<Produto>
	 */
	public ArrayList<Produto> filtrarProdutos(String parteNome) {
		return controlProduto.filtrarProdutos(parteNome);
	}

	/**
	 * Verifica a existência de determinado objeto Produto
	 * 
	 * @param codigo
	 *            String usada para identificar o objeto
	 * @return boolean
	 */
	public boolean existeProduto(String codigo) {
		return controlProduto.existeProduto(codigo);
	}

	/**
	 * Retorna o objeto Produto com base no c�digo
	 * 
	 * @param codigo
	 *            String usada para identificar o objeto
	 * @return Produto
	 */
	public Produto retornaProduto(String codigo) {
		return controlProduto.retornaProduto(codigo);
	}

	/**
	 * Adiciona um objeto Produto
	 * 
	 * @param nome
	 *            String contendo o nome do produto
	 * @param descricao
	 *            String contento a descri��o do produto
	 * @param tamanho
	 *            String contendo o tamanho do produto
	 * @param valor
	 *            BigDecimal contendo o valor do produto
	 * @throws CodigoJaExistenteException
	 *             Lan�ada se já existe um objeto Produto com o mesmo c�digo
	 * @throws ValorInvalidoException
	 *             Lan�ada se o par�metro valor for inválido
	 * @throws ValorObrigatorioException
	 *             Lan�ada caso o par�metro valor seja nulo
	 * @throws CodigoObrigatorioException
	 *             Lan�ada caso o par�metro c�digo seja nulo
	 * @throws NomeObrigatorioException
	 *             Lan�ada caso o par�metro nome seja nulo
	 * @throws TamanhoObrigatorioException
	 *             Lan�ada caso o par�metro tamanho seja nulo
	 */
	public void adicionarProduto(String nome, String descricao, String tamanho,
			BigDecimal valor) throws CodigoJaExistenteException,
			ValorInvalidoException, ValorObrigatorioException,
			CodigoObrigatorioException, NomeObrigatorioException,
			TamanhoObrigatorioException {

		controlProduto.adicionarProduto(new Produto(nome, descricao, tamanho,
				valor));
	}

	/**
	 * Remove objeto Produto
	 * 
	 * @param codigo
	 *            String usada para identificar o objeto
	 * @throws ProdutoNaoCadastradoException
	 */
	public void removerProduto(String codigo)
			throws ProdutoNaoCadastradoException {
		controlProduto.removerProduto(codigo);
	}

	/*
	 * PEDIDOS
	 */

	/**
	 * Filtra pedidos com base no período em que foram realizados
	 * 
	 * @param dataInicial
	 *            String contento a data inicial que se deve filtrar
	 * @param dataFinal
	 *            String contendo a data final que se deve filtrar
	 * @return ArrayList<Pedido>
	 */
	public ArrayList<Pedido> filtrarPedidos(String dataInicial, String dataFinal) {
		return controlPedido.filtrarPedidosData(dataInicial, dataFinal);
	}

	/**
	 * Adiciona objeto Pedido
	 * 
	 * @param pedido
	 *            Objeto Pedido a ser adicionado
	 * @throws DataObrigatorioException
	 *             Lan�ada caso o atributo data do objeto for nulo
	 * @throws TelefoneObrigatorioException
	 *             Lan�ada caso o atributo telefone for nulo
	 * @throws QuantidadeInvalidaException
	 *             Lan�ada caso a quantidade de objetos Produto for maior que a
	 *             permitida
	 * @throws ProdutosObrigatorioException
	 *             Lan�ada caso a quantidade de objetos Produto for nula
	 * @throws ClienteNaoCadastradoException
	 *             Lan�ada caso o cliente associado ao pedido n�o existir
	 */
	public void adicionarPedido(Pedido pedido) throws DataObrigatorioException,
			TelefoneObrigatorioException, QuantidadeInvalidaException,
			ProdutosObrigatorioException, ClienteNaoCadastradoException {
		controlPedido.adicionarPedidos(pedido);
	}

	/**
	 * Remove objeto Pedido
	 * 
	 * @param codigo
	 *            String contento o c�digo do objeto a ser removido
	 */
	public void removerPedido(String codigo) {
		controlPedido.removerPedido(codigo);
	}

	/**
	 * Retorna objeto Pedido
	 * 
	 * @param codigo
	 *            String contento o c�digo do objeto a ser retornado
	 * @return Pedido
	 */
	public Pedido retornaPedido(String codigo) {
		return controlPedido.retornaPedido(codigo);
	}

	/*
	 * Relatorios
	 */

	/**
	 * Gera um relat�rio de clientes com base no nome e per�odo de realiza��o
	 * de pedidos
	 * 
	 * @param parteNome
	 *            parte do nome do cliente
	 * @param dataInicial
	 *            data inicial do per�odo de realiza��o de pedidos
	 * @param dataFinal
	 *            data final do per�odo de realiza��o de pedidos
	 * @return String
	 */
	public ArrayList<String> gerarRelatorioClientes(String parteNome,
			String dataInicial, String dataFinal) {
		/*
		 * Cria o gerador de relat�rios dos clientes, passando os par�metros e
		 * retorna a String gerada
		 */

		return new GeradorRelatoriosClientes(controlCliente, controlPedido,
				parteNome, dataInicial, dataFinal).gerarRelatorio();
	}

	/**
	 * Gera um relat�rio com base no c�digo de um produto e o período de
	 * raliza��o dos pedidos
	 * 
	 * @param codigoProduto
	 *            String contendo o c�digo do produto a ser filtrado para o
	 *            relat�rio
	 * @param dataInicial
	 *            String contendo a data inicial do per�odo de realiza��o dos
	 *            pedidos
	 * @param dataFinal
	 *            String contendo a data final do per�odo de realiza��o dos
	 *            pedidos
	 * @return ArrayList<String>
	 * @throws CodigoObrigatorioException
	 *             Lan�ada caso n�o seja encontrado objeto Pedido com o c�digo
	 */
	public ArrayList<String> gerarRelatorioProdutos(String codigoProduto,
			String dataInicial, String dataFinal)
			throws CodigoObrigatorioException {

		/*
		 * Cria o gerador de relat�rios dos produtos, passando os par�metros e
		 * retorna a String gerada caso o par�metro c�digo n�o seja nulo
		 */

		if (!codigoProduto.isEmpty()) {
			return new GeradorRelatorioProdutos(controlCliente, controlPedido,
					codigoProduto, dataInicial, dataFinal).gerarRelatorio();
		} else {
			throw new CodigoObrigatorioException();
		}
	}
	
	public int quantidadePedidosTelefone(String telefone) {
		return controlPedido.quantidadePedidosTelefone(telefone);
	}
}
