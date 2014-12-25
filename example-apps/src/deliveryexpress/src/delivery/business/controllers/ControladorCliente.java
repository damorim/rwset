package delivery.business.controllers;

import java.util.ArrayList;
import delivery.business.Cliente;
import delivery.business.exceptions.client.*;
import delivery.data.interfaces.RepositorioObjetos;

/*
 * ControladorCliente, respons�vel pela comunica��o com os reposit�rios
 */
public class ControladorCliente {

	private RepositorioObjetos<Cliente> repo;

	/**
	 * Construtor
	 * 
	 * @param rep
	 *            Reposit�rio de objetos Cliente
	 */
	public ControladorCliente(RepositorioObjetos<Cliente> rep) {
		this.repo = rep;
	}

	/**
	 * Verifica a exist�ncia de um objeto Cliente
	 * 
	 * @param telefone
	 *            telefone do cliente a se procurar
	 * @return true se o cliente existir, falso se n�o
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
	 *             Lan�ada se o cliente n�o n�o existir no reposit�rios
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
	 *             Lan�ada caso o atributo CPF do objeto Cliente for nulo
	 * @throws NomeObrigatorioException
	 *             Lan�ada caso o atributo nome do objeto Cliente for nulo
	 * @throws TelefoneObrigatorioException
	 *             Lan�ada caso o atributo telefone do objeto Cliente for nulo
	 * @throws BairroObrigatorioException
	 *             Lan�ada caso o atributo bairro do objeto Endereco do Cliente
	 *             for nulo
	 * @throws CidadeObrigatorioException
	 *             Lan�ada caso o atributo cidade do objeto Endereco do Cliente
	 *             for nulo
	 * @throws LogradouroObrigatorioException
	 *             Lan�ada caso o atributo logradouro do objeto Endereco do
	 *             Cliente for nulo
	 * @throws NumeroObrigatorioException
	 *             Lan�ada caso o atributo do objeto Endereco do Cliente for
	 *             nulo
	 * @throws TelefoneExistenteException
	 *             Lan�ada caso j� exita um objeto Cliente com o mesmo atributo
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
						+ " j� existe no reposit�rio.");
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
	 *             Lan�ada caso o par�metro seja nulo
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
	 * Lan�ada caso o par�metro seja nulo
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
	 *             Lan�ada caso o par�metro seja nulo
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
	 *             Lan�ada caso o par�metro seja nulo
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
	 *             Lan�ada caso o par�metro seja nulo
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
	 *             Lan�ada caso o par�metro seja nulo
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
	 *             Lan�ada caso o par�metro seja nulo
	 */
	public void verificarNumero(String numero)
			throws NumeroObrigatorioException {
		if (numero.isEmpty()) {
			throw new NumeroObrigatorioException();
		}
	}

}
