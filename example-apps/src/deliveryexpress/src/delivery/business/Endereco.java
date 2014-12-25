package delivery.business;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;

	/**
	 * Construtor com o parâmetro complementos, não-obrigatório
	 * 
	 * @param logradouro
	 *            String contendo o logradouro
	 * @param numero
	 *            String contendo o número
	 * @param complemento
	 *            String contendo o complemento
	 * @param bairro
	 *            String contendo o bairro
	 * @param cidade
	 *            String contendo a cidade
	 */
	public Endereco(String logradouro, String numero, String complemento,
			String bairro, String cidade) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	/**
	 * Construtor sem o parâmetro complementos
	 * 
	 * @param logradouro
	 *            String contendo o logradouro
	 * @param numero
	 *            String contendo o número
	 * @param bairro
	 *            String contendo o bairro
	 * @param cidade
	 *            String contendo a cidade
	 */
	public Endereco(String logradouro, String numero, String bairro,
			String cidade) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	/*
	 * Getters e setters
	 */
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String imprimir() {
		return "Endere�o: " + logradouro + "\n" + "Numero " + numero + "\n"
				+ "Complemento: " + complemento + "\n" + "Cidade: " + cidade;

	}
}
