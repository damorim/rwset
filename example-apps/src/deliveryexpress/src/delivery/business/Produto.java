package delivery.business;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nome;
	private String tamanho;
	private BigDecimal valor;
	private String descricao;

	/**
	 * Construtor sem o parâmetro descricao, não-obrigatório
	 * 
	 * @param codigo
	 *            String contendo o código do produto
	 * @param nome
	 *            String contendo o nome do produto
	 * @param tamanho
	 *            String contendo o tamanho do produto
	 * @param valor
	 *            BigDecimal contendo o valor do produto
	 */
	public Produto(String codigo, String nome, String tamanho, BigDecimal valor) {

		this.codigo = codigo;
		this.nome = nome;
		this.tamanho = tamanho;
		this.valor = valor;
	}

	/**
	 * Construtor com o parâmetro descricao
	 * 
	 * @param codigo
	 *            String contendo o código do produto
	 * @param nome
	 *            String contendo o nome do produto
	 * @param tamanho
	 *            String contendo o tamanho do produto
	 * @param valor
	 *            BigDecimal contendo o valor do produto
	 * @param descricao
	 *            String contendo a descrição do produto
	 */
	public Produto(String codigo, String nome, String tamanho,
			BigDecimal valor, String descricao) {

		this.codigo = codigo;
		this.nome = nome;
		this.tamanho = tamanho;
		this.valor = valor;
		this.descricao = descricao;
	}

	/*
	 * Getters e setters
	 */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}
}
