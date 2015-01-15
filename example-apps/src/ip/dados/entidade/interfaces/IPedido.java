package dados.entidade.interfaces;

import java.math.BigDecimal;
import java.util.Date;

import dados.entidade.Cliente;
import dados.entidade.Produto;

public interface IPedido {
	public void setCodigo(String codigo);
	public String getCodigo();
	public void setCliente(Cliente cliente);
	public Cliente getCliente();
	public void setProdutos(Produto[] produto);
	public Produto[] getProdutos();
	public void setData(Date data);
	public Date getData();
	public void setQuantidade(int quantidade);
	public int getQuantidade();
	public BigDecimal getPreco();
	public void setPreco(BigDecimal preco);
}