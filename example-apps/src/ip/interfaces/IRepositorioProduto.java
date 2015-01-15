package dados.interfaces;


import dados.entidade.Produto;
import dados.IteratorClasse;

public interface IRepositorioProduto {

	public void adicionar(Produto produto);
	public void remover(String codigo);
	public void atualizar(Produto produto);
	public Produto procurar(String codigo);

	IteratorClasse<Produto> iterator();
	
}