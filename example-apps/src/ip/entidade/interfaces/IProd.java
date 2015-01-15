package dados.entidade.interfaces;

import java.math.BigDecimal;

public interface IProd {
	
	public void setNome(String nome);
	public void setCodigo(String codigo);
	public void setPreco(BigDecimal preco);
	public String getNome();
	public String getCodigo();
	public BigDecimal getPreco();
	
}