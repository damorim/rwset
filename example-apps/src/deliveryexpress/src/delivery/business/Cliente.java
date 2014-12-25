package delivery.business;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private String telefone;
	private String observacoes;
	private Endereco endereco;

	/**
	 * Construtor com parâmetro observacoes, não-obrigatório
	 * 
	 * @param cpf
	 *            String contendo o CPF
	 * @param nome
	 *            String contendo o nome
	 * @param telefone
	 *            String contendo o telefone
	 * @param observacoes
	 *            String contendo as observações
	 * @param endereco
	 *            String contendo uma instância do objeto Endereco
	 */
	public Cliente(String cpf, String nome, String telefone,
			String observacoes, Endereco endereco) {

		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.observacoes = observacoes;
	}

	/**
	 * Construtor sem parâmetro observações
	 * 
	 * @param cpf
	 *            String contendo o CPF
	 * @param nome
	 *            String contendo o nome
	 * @param telefone
	 *            String contendo o telefone
	 * @param endereco
	 *            String contendo o endereço
	 */
	public Cliente(String cpf, String nome, String telefone, Endereco endereco) {

		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	/*
	 * Getters e setters
	 */

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void settelefone(String telefone) {
		this.telefone = telefone;
	}

	public String gettelefone() {
		return telefone;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getObservacoes() {
		return observacoes;
	}
	
	/*
	 * Override do método da classe Object
	 */
	public String toString() {
		return String.format("[Nome do Cliente: %s \nCPF: %s ]", this.nome,
				this.cpf);
	}

}
