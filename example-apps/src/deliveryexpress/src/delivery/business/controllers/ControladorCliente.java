package delivery.business.controllers;

import java.util.ArrayList;
import delivery.business.Cliente;
import delivery.business.exceptions.client.*;
import delivery.data.interfaces.RepositorioObjetos;

/*
 * ControladorCliente, responsável pela comunicação com os repositórios
 */
public class ControladorCliente {

	private RepositorioObjetos<Cliente> repo;

	/**
	 * Construtor
	 * 
	 * @param rep
	 *            Repositório de objetos Cliente
	 */
	public ControladorCliente(RepositorioObjetos<Cliente> rep) {
		this.repo = rep;
	}

	/**
	 * Verifica a existência de um objeto Cliente
	 * 
	 * @param telefone
	 *            telefone do cliente a se procurar
	 * @return true se o cliente existir, falso se não
	 */
	public boolean existeCliente(String telefone) {
		return repo.existe(telefone);
	}

	/**
	 * Retorna objeto Cliente
	 * 
	 * @param telefone
	 *            telefone do cliente que se procura
	 * @return o objeto Cliente
	 */
	public Cliente retornaCliente(String telefone) {
		return repo.vizualizarEspecifico(telefone);
	}

	/**
	 * Remove cliente
	 * 
	 * @param telefone
	 *            telefone do cliente que se quer remover
	 * @throws ClienteNaoCadastradoException
	 *             Lançada se o cliente não não existir no repositórios
	 */
	public void removerCliente(String telefone)
			throws ClienteNaoCadastradoException {
		if (existeCliente(telefone)) {
			repo.remover(retornaCliente(telefone));
		} else {
			throw new ClienteNaoCadastradoException();
		}
	}

	/**
	 * Adiciona objeto Cliente
	 * 
	 * @param cliente
	 *            cliente a ser adicionado
	 * @throws CPFObrigatorioException
	 *             Lançada caso o atributo CPF do objeto Cliente for nulo
	 * @throws NomeObrigatorioException
	 *             Lançada caso o atributo nome do objeto Cliente for nulo
	 * @throws TelefoneObrigatorioException
	 *             Lançada caso o atributo telefone do objeto Cliente for nulo
	 * @throws BairroObrigatorioException
	 *             Lançada caso o atributo bairro do objeto Endereco do Cliente
	 *             for nulo
	 * @throws CidadeObrigatorioException
	 *             Lançada caso o atributo cidade do objeto Endereco do Cliente
	 *             for nulo
	 * @throws LogradouroObrigatorioException
	 *             Lançada caso o atributo logradouro do objeto Endereco do
	 *             Cliente for nulo
	 * @throws NumeroObrigatorioException
	 *             Lançada caso o atributo do objeto Endereco do Cliente for
	 *             nulo
	 * @throws TelefoneExistenteException
	 *             Lançada caso já exita um objeto Cliente com o mesmo atributo
	 *             telefone
	 */
	public void adicionarCliente(Cliente cliente)
			throws CPFObrigatorioException, NomeObrigatorioException,
			TelefoneObrigatorioException, BairroObrigatorioException,
			CidadeObrigatorioException, LogradouroObrigatorioException,
			NumeroObrigatorioException, TelefoneExistenteException {

		if (cliente.getCpf().isEmpty()) {

			throw new CPFObrigatorioException();
		} else if (cliente.getNome().isEmpty()) {

			throw new NomeObrigatorioException();
		} else if (cliente.gettelefone().isEmpty()) {

			throw new TelefoneObrigatorioException();
		} else if (cliente.getEndereco().getBairro().isEmpty()) {

			throw new BairroObrigatorioException();
		} else if (cliente.getEndereco().getCidade().isEmpty()) {

			throw new CidadeObrigatorioException();
		} else if (cliente.getEndereco().getLogradouro().isEmpty()) {

			throw new LogradouroObrigatorioException();
		} else if (cliente.getEndereco().getNumero().isEmpty()) {

			throw new NumeroObrigatorioException();
		} else {

			if (existeCliente(cliente.gettelefone())) {
				System.out.println("Telefone de " + cliente.getNome()
						+ " já existe no repositório.");
				throw new TelefoneExistenteException();
			} else {
				repo.adicionar(cliente);
			}

		}

	}

	/**
	 * Filtra os objetos Cliente com base no nome
	 * 
	 * @param filtro
	 *            Stirng contendo o filtro a se pesquisar
	 * @return ArrayList<String>
	 */
	public ArrayList<Cliente> filtrarClientes(String filtro) {
		return repo.vizualizar(filtro);
	}

	/**
	 * Verifica a validade da String nome
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarNome(String nome) throws NomeObrigatorioException {
		if (nome.isEmpty()) {
			throw new NomeObrigatorioException();
		}
	}

	/**
	 * Verifica a validade do CPF
	 * @param cpf
	 * String a ser validada
	 * @throws CPFObrigatorioException
	 * Lançada caso o parâmetro seja nulo
	 */
	public void verificarCPF(String cpf) throws CPFObrigatorioException {
		if (cpf.isEmpty()) {
			throw new CPFObrigatorioException();
		}
	}

	/**
	 * Verifica a validade da String telefone
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarTelefone(String telefone)
			throws TelefoneObrigatorioException {
		if (telefone.isEmpty()) {
			throw new TelefoneObrigatorioException();
		}
	}

	/**
	 * Verifica a validade da String logradouro
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarlogradouro(String logradouro)
			throws LogradouroObrigatorioException {
		if (logradouro.isEmpty()) {
			throw new LogradouroObrigatorioException();
		}
	}

	/**
	 * Verifica a validade da String cidade
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarCidade(String cidade)
			throws CidadeObrigatorioException {
		if (cidade.isEmpty()) {
			throw new CidadeObrigatorioException();
		}
	}

	/**
	 * Verifica a validade da String bairro
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarBairro(String bairro)
			throws BairroObrigatorioException {
		if (bairro.isEmpty()) {
			throw new BairroObrigatorioException();
		}
	}

	/**
	 * Verifica a validade da String numero
	 * 
	 * @param nome
	 *            String a ser validada
	 * @throws NomeObrigatorioException
	 *             Lançada caso o parâmetro seja nulo
	 */
	public void verificarNumero(String numero)
			throws NumeroObrigatorioException {
		if (numero.isEmpty()) {
			throw new NumeroObrigatorioException();
		}
	}

}
