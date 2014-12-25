package domino;

public class Jogador {
	private Mao mao;
	private String nome;
	private int maiorCarroca;
	private Peca maiorCarrocaPeca;

	public Jogador(String nome) {
		this.nome = nome;
		mao = new Mao();
		maiorCarroca = -1;
	}

	public void setMao(Mao mao) {
		this.mao = mao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}	

	public Mao getMao() {
		return mao;
	}

	public void inserirPeca(Peca P) {
		mao.add(P);
		int x = P.carroca();
		if(x > maiorCarroca) {
			maiorCarroca = x;
			maiorCarrocaPeca = P;
		}
	}

	public int getMaiorCarroca() {
		return maiorCarroca;
	}

	public Peca getMaiorCarrocaPeca() {
		return maiorCarrocaPeca;
	}

	public Peca removerPeca(int index){
		Peca P = mao.get(index);
		mao.remove(index);
		return P;
	}
	
	public String toString()
	{  
		return this.nome +" tamanho da mao: " + this.mao.size() + "\n" + this.mao.toString();
	}
}
