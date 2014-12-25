package domino;


public class Dupla 
{
	private Jogador dupla[];
	private int pontos;
	
	public Dupla(Jogador jogador1, Jogador jogador2) 
	{
		pontos = 0;
		dupla = new Jogador [2];
		if(Math.random()<0.5) {
			dupla[0] = jogador1;
			dupla[1] = jogador2;
		} else {
			dupla[0] = jogador2;
			dupla[1] = jogador1;
		}
	}
	
	public void adicionarPontos(int i) 
	{
		this.pontos += i;
	}
	
	public int contarPecas() 
	{
		int soma = 0;
		
		for (Peca peca : dupla[0].getMao()) 
		{
			soma += peca.getSoma();
		}
		
		for (Peca peca : dupla[1].getMao()) 
		{
			soma += peca.getSoma();
		}
		
		return soma;
	}

	public Jogador[] getDupla() 
	{
		return dupla;
	}

	public int getPontos() 
	{
		return pontos;
	}
	public String toString()
	{
		return dupla[0].toString() + "\n" + dupla[1].toString();
	}
	
}
