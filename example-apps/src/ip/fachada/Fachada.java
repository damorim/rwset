package fachada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import negocio.ControladorAdm;
import negocio.ControladorCliente;
import negocio.ControladorPedido;
import negocio.ControladorProduto;
import relatorios.RelatorioAdm;
import relatorios.RelatorioCliente;
import relatorios.RelatorioProduto;
import dados.IteratorClasse;
import dados.entidade.Administrador;
import dados.entidade.Cliente;
import dados.entidade.Livro;
import dados.entidade.Pedido;
import dados.entidade.Produto;
import dados.excel.ExcelRow;
import dados.excel.ExcelSheet;
import dados.excel.ExcelWorkbook;
import dados.excel.exception.CellNumberFormatException;
import dados.excel.exception.CellStringFormatException;
import dados.excel.exception.IOExcelException;
import dados.excel.exception.NotDefinedCellException;
import dados.excel.exception.NotDefinedRowException;
import dados.excel.exception.SheetNotFoundException;
import dados.repositorio.RepAdmArquivo;
import dados.repositorio.RepAdmArray;
import dados.repositorio.RepAdmLista;
import dados.repositorio.RepClienteArquivo;
import dados.repositorio.RepClienteArray;
import dados.repositorio.RepClienteLista;
import dados.repositorio.RepPedidoArquivo;
import dados.repositorio.RepPedidoArray;
import dados.repositorio.RepPedidoLista;
import dados.repositorio.RepProdutoArquivo;
import dados.repositorio.RepProdutoArray;
import dados.repositorio.RepProdutoLista;
import exceptions.CampoNaoPreenchidoException;
import exceptions.CodigoInvalidoException;
import exceptions.NomeInvalidoException;
import exceptions.TipoRepositorioInvalidoException;
import exceptions.administrador.AdmNaoEncontradoException;
import exceptions.cliente.BairroInvalidoException;
import exceptions.cliente.CidadeInvalidaException;
import exceptions.cliente.ClienteNaoEncontradoException;
import exceptions.cliente.CpfInvalidoException;
import exceptions.cliente.CpfJaExisteException;
import exceptions.cliente.EstadoInvalidoException;
import exceptions.cliente.TelefoneInvalidoException;
import exceptions.cliente.TelefoneJaExisteException;
import exceptions.pedido.DataInvalidaException;
import exceptions.pedido.DatasInvalidasException;
import exceptions.pedido.PedidoInvalidoException;
import exceptions.pedido.PedidoNaoEncontradoException;
import exceptions.pedido.QuantidadeProdutosInvalidaException;
import exceptions.produto.AnoInvalidoException;
import exceptions.produto.ProdutoNaoEncontradoException;
import exceptions.produto.ValorInvalidoException;

public class Fachada {

	private ControladorCliente controladorCliente;
	private ControladorPedido controladorPedido;
	private ControladorProduto controladorProduto;
	private ControladorAdm controladorAdm;
	private String tipoRepositorio;

	@SuppressWarnings({ "resource" })
	public Fachada() throws IOException, TipoRepositorioInvalidoException,
	IOExcelException, SheetNotFoundException, CellStringFormatException, CellNumberFormatException, PedidoNaoEncontradoException, exceptions.administrador.CpfJaExisteException, exceptions.administrador.CpfInvalidoException, exceptions.administrador.TelefoneInvalidoException, exceptions.administrador.TelefoneJaExisteException, DatasInvalidasException, AnoInvalidoException {
		File arquivo = new File("config.txt");
		if (arquivo.exists()) {
			BufferedReader leitor = new BufferedReader(new FileReader("config.txt"));
			this.tipoRepositorio = leitor.readLine();
			if (this.tipoRepositorio != null) {
				if (this.tipoRepositorio.equalsIgnoreCase("array")) {
					this.controladorCliente = new ControladorCliente(new RepClienteArray());
					this.controladorPedido = new ControladorPedido(new RepPedidoArray());
					this.controladorProduto = new ControladorProduto(new RepProdutoArray());
					this.controladorAdm = new ControladorAdm(new RepAdmArray());
				} else if (this.tipoRepositorio.equalsIgnoreCase("lista")) {
					this.controladorCliente = new ControladorCliente(new RepClienteLista());
					this.controladorPedido = new ControladorPedido(new RepPedidoLista());
					this.controladorProduto = new ControladorProduto(new RepProdutoLista());
					this.controladorAdm = new ControladorAdm(new RepAdmLista());
				} else if (this.tipoRepositorio.equalsIgnoreCase("arquivo")) {
					this.controladorCliente = new ControladorCliente(new RepClienteArquivo());
					this.controladorPedido = new ControladorPedido(new RepPedidoArquivo());
					this.controladorProduto = new ControladorProduto(new RepProdutoArquivo());
					this.controladorAdm = new ControladorAdm(new RepAdmArquivo());
				} else {
					throw new TipoRepositorioInvalidoException();
				}
			} else {
				throw new TipoRepositorioInvalidoException();
			}
		} else {
			throw new TipoRepositorioInvalidoException();
		}

		this.lerAdministradores();
		this.lerClientes();
		this.lerProdutos();
		this.lerPedidos();
	}


	public void lerAdministradores() throws IOExcelException, SheetNotFoundException,
	CellStringFormatException, exceptions.administrador.CpfJaExisteException, exceptions.administrador.CpfInvalidoException, exceptions.administrador.TelefoneInvalidoException, exceptions.administrador.TelefoneJaExisteException {
		ExcelWorkbook arquivo = new ExcelWorkbook("workbookAdm.xls");
		ExcelSheet planilha = arquivo.getSheet("Administradores");
		boolean continua = true;

		for(int i = 1; continua; i++) {
			String numeroStr = "";

			try {
				ExcelRow linha = planilha.getRow(i);
				String nome = linha.getCell(0).getStringCellValue();
				String cpf = linha.getCell(1).getStringCellValue();
				String logradouro = linha.getCell(2).getStringCellValue();
				int numero = 0;
				try {
					numero = (int) linha.getCell(3).getNumericCellValue();
				} catch (CellNumberFormatException e) {
					numeroStr = linha.getCell(3).getStringCellValue();
				}

				String complemento = "";
				try {
					complemento = linha.getCell(4).getStringCellValue();
				} catch (NotDefinedCellException ex) {
				}

				String bairro = linha.getCell(5).getStringCellValue();
				String cidade = linha.getCell(6).getStringCellValue();
				String telefone = linha.getCell(7).getStringCellValue();
				String senha = linha.getCell(8).getStringCellValue();
				String uf = linha.getCell(9).getStringCellValue();

				if (numero != 0) {
					numeroStr = "" + numero;
				} else if (numeroStr.equals("")) {
					numeroStr = null;
				}

				try {

					this.adicionarAdministrador(cpf, telefone, nome, logradouro, numeroStr, complemento, bairro, cidade, senha, uf);

				} catch (BairroInvalidoException e) {
				} catch (CidadeInvalidaException e) {
				} catch (TelefoneJaExisteException e) {
				} catch (CampoNaoPreenchidoException e) {
				} catch (CpfInvalidoException e) {
				} catch (TelefoneInvalidoException e) {
				} catch (CpfJaExisteException e) {
				} catch (NomeInvalidoException e) {
				} catch (EstadoInvalidoException e) {
				}
				
			} catch (NotDefinedRowException ex) {
				continua = false;
			} catch (NotDefinedCellException ex) {
				continua = false;
			}
		}
	}

	public void lerClientes() throws IOExcelException, SheetNotFoundException,
	CellStringFormatException {

		ExcelWorkbook arquivo = new ExcelWorkbook("workbookCli.xls");
		ExcelSheet planilha = arquivo.getSheet("Clientes");
		boolean continua = true;

		for (int i = 1; continua; i++) {
			String numeroStr = "";

			try {
				ExcelRow linha = planilha.getRow(i);
				String nome = linha.getCell(0).getStringCellValue();
				String cpf = linha.getCell(1).getStringCellValue();
				String logradouro = linha.getCell(2).getStringCellValue();
				int numero = 0;
				try {
					numero = (int) linha.getCell(3).getNumericCellValue();
				} catch (CellNumberFormatException e) {
					numeroStr = linha.getCell(3).getStringCellValue();
				}

				String complemento = "";
				try {
					complemento = linha.getCell(4).getStringCellValue();
				} catch (NotDefinedCellException ex) {
				}

				String bairro = linha.getCell(5).getStringCellValue();
				String cidade = linha.getCell(6).getStringCellValue();
				String telefone = linha.getCell(7).getStringCellValue();
				String senha = linha.getCell(8).getStringCellValue();
				String uf = linha.getCell(9).getStringCellValue();

				if (numero != 0) {
					numeroStr = "" + numero;
				} else if (numeroStr.equals("")) {
					numeroStr = null;
				}

				try {

					this.adicionarCliente(cpf, telefone, nome, logradouro, numeroStr, complemento, bairro, cidade, senha, uf);

				} catch (BairroInvalidoException e) {
				} catch (CidadeInvalidaException e) {
				} catch (TelefoneJaExisteException e) {
				} catch (CampoNaoPreenchidoException e) {
				} catch (CpfInvalidoException e) {
				} catch (TelefoneInvalidoException e) {
				} catch (CpfJaExisteException e) {
				} catch (NomeInvalidoException e) {
				} 
				
			} catch (NotDefinedRowException ex) {
				continua = false;
			} catch (NotDefinedCellException ex) {
				continua = false;
			}
		}
	}

	
	public void lerProdutos() throws IOExcelException, SheetNotFoundException,
	CellStringFormatException, AnoInvalidoException {
		ExcelWorkbook arquivo = new ExcelWorkbook("workbookPro.xls");
		ExcelSheet produtos = arquivo.getSheet("Produtos");
		boolean continua = true;
		for (int i = 1; continua; i++) {
			try {
				ExcelRow linha = produtos.getRow(i);
				String codigoProduto = "";

				try {
					int codigo = (int) linha.getCell(0).getNumericCellValue();
					codigoProduto = "" + codigo;
				} catch (CellNumberFormatException e) {
					codigoProduto = linha.getCell(0).getStringCellValue();
				}

				String nomeProduto = linha.getCell(1).getStringCellValue();
				String descricaoProduto = "";
				try {
					descricaoProduto = linha.getCell(2).getStringCellValue();
				} catch (NotDefinedCellException e) {
				}

				boolean invalido = false;
				String valorFormatado = "";
				try {
					double valor = (double) linha.getCell(3).getNumericCellValue();
					valorFormatado = "" + valor;
				} catch (CellNumberFormatException e) {

					String valor = linha.getCell(3).getStringCellValue();

					for (int j = 0; j < valor.length(); j++) {
						if (valor.charAt(j) == ',') {
							String parteUm = valor.substring(0, j);
							String parteDois = valor.substring(j + 1);
							valor = parteUm + "." + parteDois;
						}
						if (valor.charAt(j) != ',' && valor.charAt(j) != '.'
							&& !(("" + valor.charAt(j)).matches("\\d{1}"))) {
							invalido = true;
						}
					}
					valorFormatado = valor;
				}

				
				String nomeAutor = null;
				try {
					nomeAutor = linha.getCell(4).getStringCellValue();
				} catch (NotDefinedCellException e){

				}
				String editora = null;
				try {
					editora = linha.getCell(5).getStringCellValue();
				} catch (NotDefinedCellException e) {

				}
				String anoPublicacao = null;
				try {
					anoPublicacao = linha.getCell(6).getStringCellValue();
				} catch (NotDefinedCellException e) {

				}			

			if (!invalido) {
				try {
					this.adicionarProduto(codigoProduto, nomeProduto, descricaoProduto, valorFormatado, nomeAutor, editora, anoPublicacao);
				} catch (ValorInvalidoException e) {
				} catch (CampoNaoPreenchidoException e) {
				} catch (CodigoInvalidoException e) {
				} catch (NomeInvalidoException e) {
				}
			}
		} catch (NotDefinedRowException e) {
			continua = false;
		} catch (NotDefinedCellException e) {
			continua = false;
		}
	}


}


public void lerPedidos() throws IOExcelException, SheetNotFoundException,
CellStringFormatException, CellNumberFormatException, PedidoNaoEncontradoException, DatasInvalidasException {
	ExcelWorkbook arquivo = new ExcelWorkbook("workbookPed.xls");
	ExcelSheet pedidos = arquivo.getSheet("Pedidos");
	boolean continua = true;
	for (int i = 1; continua; i++) {
		try {
			ExcelRow linha = pedidos.getRow(i);

			String codigoPedido = "";
			try {
				int codigo = (int) linha.getCell(0).getNumericCellValue();
				codigoPedido = "" + codigo;
			} catch (CellNumberFormatException e) {
				codigoPedido = linha.getCell(0).getStringCellValue();
			}

			String telefoneCliente = linha.getCell(1).getStringCellValue();

			boolean campoValido = true;
			boolean clienteEncontrado = true;
			Cliente cliente = null;
			try {
				cliente = this.controladorCliente.buscaTelefone(telefoneCliente);
			} catch (ClienteNaoEncontradoException e) {
				clienteEncontrado = false;
			} catch (TelefoneInvalidoException e) {
				campoValido = false;
			}

			String quantidade;
			int quantidadeInt = 0;
			try {
				quantidadeInt = (int) linha.getCell(2).getNumericCellValue();
			} catch (CellNumberFormatException e) {
			}

			if (quantidadeInt == 0) {
				quantidade = null;
			} else {
				quantidade = "" + quantidadeInt;
			}

			quantidade = "" + quantidadeInt;
			String[] produtos;
			try {
				String produtosPedido = linha.getCell(3).getStringCellValue();
				produtos = produtosPedido.split("-");
			} catch (CellStringFormatException e) {
				produtos = new String[1];
				produtos[0] = "" + (int) linha.getCell(3).getNumericCellValue();
			}
			String dataStr = linha.getCell(4).getStringCellValue();

			dataStr = dataStr.replaceAll("\\(", "");
				dataStr = dataStr.replaceAll("\\)", "");

				DateFormat df = DateFormat.getDateInstance();
				Date data = null;

				try {
					data = df.parse(dataStr);
				} catch (ParseException e1) {
					campoValido = false;
				}
				if (!(this.verificaData(dataStr))) {
					campoValido = false;
				}
				IteratorClasse<Pedido> iterator = null;
				
				iterator = this.controladorPedido.getListaPedidos(null, null);
				
				while (iterator.hasNext()) {
					Pedido auxiliar = iterator.next();
					if (codigoPedido.equals(auxiliar.getCodigo())) {
						campoValido = false;
					}
				}

				if (campoValido && clienteEncontrado) {
					String cpf = cliente.getCpf();
					String telefone = cliente.getContato().getTelefone();
					String nome = cliente.getNome();
					String logradouro = cliente.getContato().getRua();
					String numero = cliente.getContato().getNumero();
					String complemento = "";
					if (!(cliente.getContato().getComplemento().equals("") || cliente.getContato().getComplemento() == null)) {
						complemento = cliente.getContato().getComplemento();
					}
					String bairro = cliente.getContato().getBairro();
					String cidade = cliente.getContato().getCidade();

					boolean invalido = false;
					Produto[] arrayProdutos = new Produto[produtos.length];
					for (int j = 0; j < produtos.length; j++) {
						try {
							arrayProdutos[j] = this.visualizarCadastroProduto(produtos[j]);
						} catch (ProdutoNaoEncontradoException ex) {
							invalido = true;
						}
					}

					if (!invalido) {
						try {
							this.controladorPedido.adicionar(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade, codigoPedido, cidade, arrayProdutos, data, quantidade, cidade);
						} catch (QuantidadeProdutosInvalidaException e) {
						} catch (ProdutoNaoEncontradoException e) {
						}
					}
				}
			} catch (NotDefinedRowException e) {
				continua = false;
			} catch (NotDefinedCellException e) {
				continua = false;
			}
		}

	}

	public void adicionarAdministrador(String cpf, String telefone, String nome, String logradouro, String numero, String complemento,
		String bairro, String cidade, String senha, String uf) throws CpfInvalidoException, TelefoneInvalidoException, CampoNaoPreenchidoException,
		CpfJaExisteException, TelefoneJaExisteException, NomeInvalidoException, BairroInvalidoException, CidadeInvalidaException, EstadoInvalidoException, exceptions.administrador.CpfJaExisteException, exceptions.administrador.CpfInvalidoException, exceptions.administrador.TelefoneInvalidoException, exceptions.administrador.TelefoneJaExisteException {

		if (!this.apenasLetras(nome)) {
			throw new NomeInvalidoException();
		} else if (!this.apenasLetras(bairro)) {
			throw new BairroInvalidoException();
		} else if (!this.apenasLetras(cidade)) {
			throw new CidadeInvalidaException();
		}

		this.controladorAdm.adicionar(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade, senha, uf);

	}
	
	public void adicionarCliente(String cpf, String telefone, String nome, String logradouro, String numero, String complemento,
		String bairro, String cidade, String senha, String uf) throws CpfInvalidoException,
	TelefoneInvalidoException, CampoNaoPreenchidoException, CpfJaExisteException,
	TelefoneJaExisteException, NomeInvalidoException, BairroInvalidoException,
	CidadeInvalidaException {

		if (!this.apenasLetras(nome)) {
			throw new NomeInvalidoException();
		} else if (!this.apenasLetras(bairro)) {
			throw new BairroInvalidoException();
		} else if (!this.apenasLetras(cidade)) {
			throw new CidadeInvalidaException();
		}

		this.controladorCliente.adicionar(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade, senha, uf);

	}

	public void adicionarProduto(String codigo, String nome, String descricao, String valor, String nomeAutor, String editora, String anoPublicacao) throws ValorInvalidoException, CampoNaoPreenchidoException,
	CodigoInvalidoException, NomeInvalidoException, AnoInvalidoException {
		if (this.apenasLetras(nome)) {
			this.controladorProduto.adicionar(codigo, nome, descricao, valor, nomeAutor, editora, anoPublicacao);
		} else {
			throw new NomeInvalidoException();
		}
	}

	public void adicionarPedido(String cpf, String[] codigosProdutos) throws CpfInvalidoException, ClienteNaoEncontradoException,
	ProdutoNaoEncontradoException, PedidoInvalidoException, PedidoNaoEncontradoException, DatasInvalidasException {

		boolean produtosCadastrados = true;

		Cliente auxiliar = null;
		Produto[] produtos = new Livro[codigosProdutos.length];

		auxiliar = this.controladorCliente.buscaCpf(cpf); 

		for (int i = 0; i < codigosProdutos.length && produtosCadastrados; i++) {
			produtos[i] = this.controladorProduto.buscaCodigo(codigosProdutos[i]);
		}


		String telefone = auxiliar.getContato().getTelefone();
		String nome = auxiliar.getNome();
		String logradouro = auxiliar.getContato().getRua();
		String numero = auxiliar.getContato().getNumero();
		String complemento = "";
		if (auxiliar.getContato().getComplemento() != null && !auxiliar.getContato().equals("")) {
			complemento = auxiliar.getContato().getComplemento();
		}
		String bairro = auxiliar.getContato().getBairro();
		String cidade = auxiliar.getContato().getCidade();
		String senha = auxiliar.getSenha();
		String uf = auxiliar.getContato().getUf();
		Calendar calendario = Calendar.getInstance();
		Date data = calendario.getTime();
		String quantidadeProdutosStr = "" + produtos.length;

		IteratorClasse<Pedido> iterator = null;
		
		iterator = this.controladorPedido.getListaPedidos(null, null);
		
		String codigoUltimo = "0";
		while (iterator.hasNext()) {
			codigoUltimo = iterator.next().getCodigo();
		}

		String codigo = "" + (Integer.parseInt(codigoUltimo) + 1);
		try {
			this.controladorPedido.adicionar(cpf, telefone, nome, logradouro, numero, complemento, bairro, cidade,
				uf, codigo, produtos, data, quantidadeProdutosStr, senha);
		} catch (QuantidadeProdutosInvalidaException ex) {
		}
	}

	public void removerAdm(String cpf) throws CpfInvalidoException {
		try {
			this.controladorAdm.remover(cpf);
		} catch (exceptions.administrador.CpfInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removerCliente(String cpf) throws CpfInvalidoException {
		try {
			this.controladorCliente.remover(cpf);
		} catch (ClienteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancelarPedido(String codigo) throws PedidoNaoEncontradoException {
		this.controladorPedido.remover(codigo);
	}

	public void removerProduto(String codigo) throws ProdutoNaoEncontradoException {
		this.controladorProduto.remover(codigo);
	}

	public IteratorClasse<Administrador> visualizarAdministradores(String nome) throws AdmNaoEncontradoException {
		IteratorClasse<Administrador> iterator = this.controladorAdm.buscaNome(nome);
		return iterator;
	}

	public IteratorClasse<Cliente> visualizarClientes(String nome) throws ClienteNaoEncontradoException {
		IteratorClasse<Cliente> iterator = this.controladorCliente.buscaNome(nome);
		return iterator;
	}

	public Administrador visualizarCadastroAdmCpf(String cpf) throws AdmNaoEncontradoException, CpfInvalidoException {
		Administrador administrador = null;
		try {
			administrador = this.controladorAdm.buscaCpf(cpf);
		} catch (exceptions.administrador.CpfInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return administrador;
	}

	public Cliente visualizarCadastroClienteTelefone(String telefone) throws
	ClienteNaoEncontradoException, TelefoneInvalidoException {
		Cliente cliente = this.controladorCliente.buscaTelefone(telefone);
		return cliente;
	}

	public Cliente visualizarCadastroClienteCpf(String cpf) throws
	ClienteNaoEncontradoException, CpfInvalidoException {
		Cliente cliente = this.controladorCliente.buscaCpf(cpf);
		return cliente;
	}

	public IteratorClasse<Produto> visualizarProdutos(String nome) throws
	ProdutoNaoEncontradoException {
		IteratorClasse<Produto> iterator = this.controladorProduto.buscaNome(nome);
		return iterator;
	}

	public IteratorClasse<Produto> buscarProdutoParteCodigo(String codigo) throws
	ProdutoNaoEncontradoException {
		IteratorClasse<Produto> iterator = this.controladorProduto.buscaParteCodigo(codigo);
		return iterator;
	}

	public Produto visualizarCadastroProduto(String codigo) throws ProdutoNaoEncontradoException {
		Produto produto = this.controladorProduto.buscaCodigo(codigo);
		return produto;
	}


	public IteratorClasse<Pedido> visualizarPedidos(String dataInicialStr, String dataFinalStr)
	throws DataInvalidaException, DatasInvalidasException {
		Date dataInicial = null;
		Date dataFinal = null;

		dataInicial = this.parseData(dataInicialStr);
		dataFinal = this.parseData(dataFinalStr);

		IteratorClasse<Pedido> iterator = this.controladorPedido.getListaPedidos(dataInicial, dataFinal);
		return iterator;
	}

	public IteratorClasse<String> fazerRelatorioAdm(String nomeAdm) {

		RelatorioAdm relatorio = null;
		try {
			relatorio = new RelatorioAdm(this.controladorPedido.getRepositorio(), nomeAdm);
			relatorio.escreveArquivo();
		} catch (IOException e) {
		}

		IteratorClasse<String> informacoes = (IteratorClasse<String>) relatorio.iterator();
		return informacoes;		
	}

	public IteratorClasse<String> fazerRelatorioCliente(String dataInicialStr,
		String dataFinalStr, String nomeCliente) throws DataInvalidaException,
	DatasInvalidasException {

		Date dataInicial = null;
		Date dataFinal = null;
		dataInicial = this.parseData(dataInicialStr);
		dataFinal = this.parseData(dataFinalStr);

		if (dataInicial != null && dataFinal != null) {
			if (!dataInicial.before(dataFinal)) {
				throw new DatasInvalidasException();
			}
		}

		RelatorioCliente relatorio = null;
		try {
			relatorio = new RelatorioCliente(this.controladorPedido.getRepositorio(),
				dataInicial, dataFinal, nomeCliente);
			relatorio.escreveArquivo();
		} catch (IOException e) {
		}

		IteratorClasse<String> informacoes = relatorio.iterator();
		return informacoes;
	}

	public IteratorClasse<String> fazerRelatorioProduto(String dataInicialStr,
		String dataFinalStr, String codigoProduto) throws DataInvalidaException,
	CampoNaoPreenchidoException, DatasInvalidasException {

		Date dataInicial = null;
		Date dataFinal = null;
		dataInicial = this.parseData(dataInicialStr);
		dataFinal = this.parseData(dataFinalStr);

		if (dataInicial != null && dataFinal != null) {
			if (!dataInicial.before(dataFinal)) {
				throw new DatasInvalidasException();
			}
		}

		RelatorioProduto relatorio = null;
		try {
			relatorio = new RelatorioProduto(this.controladorPedido.getRepositorio(), dataInicial, dataFinal, codigoProduto);
			relatorio.escreveArquivo();
		} catch (IOException e) {
		}

		IteratorClasse<String> informacoes = relatorio.iterator();
		return informacoes;
	}

	private Date parseData(String data) throws DataInvalidaException {
		Date date = null;
		DateFormat formatador = DateFormat.getDateInstance();
		boolean valida = false;
		if (!(data == null || data.trim().equals(""))) {
			try {
				date = formatador.parse(data);
				if (this.verificaData(data)) {
					valida = true;
				} else {
					valida = false;
				}
			} catch (ParseException ex) {
			}
		} else {
			valida = true;
		}

		if (!valida) {
			throw new DataInvalidaException();
		}

		return date;
	}

	private boolean verificaData(String data) {
		String[] partes = data.split("/");
		boolean dataValida = false;
		int dia = Integer.parseInt(partes[0]);
		int mes = Integer.parseInt(partes[1]);
		int ano = Integer.parseInt(partes[2]);
		if (partes[2].length() != 4) {
			dataValida = false;

		} else if (ano > 0) { 
			if (mes > 0 && mes < 13) { 
				if (dia > 0 && dia < 32) {
					if (mes == 1 || mes == 3 || mes == 5 || mes == 7
						|| mes == 8 || mes == 10 || mes == 12) {
						dataValida = true;
				} else if (mes == 2) {
					if ((ano % 4 == 0 && ano % 100 != 0)
						|| (ano % 400 == 0)) {
						if (dia < 30) {
							dataValida = true;
						}
					} else {

						if (dia < 29) {
							dataValida = true;
						}
					}

				} else {
					if (dia < 31) {
						dataValida = true;
					}
				}
			}
		}
	}

	return dataValida;
}

private boolean apenasLetras(String palavra) {
	Pattern pattern = Pattern.compile("[a-zA-Z\\sÇçÛûÙùÚúÒòÓóÕõÔôÌìÍíÈèÉéÊêÀàÁáÃã]+");
	Matcher matcher = pattern.matcher(palavra);
	return matcher.matches();

}
}