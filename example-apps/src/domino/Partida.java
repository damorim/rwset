package domino;

public class Partida{
	private Tabuleiro tabuleiro;
	private Dupla duplas[];
	public Jogador jogadorAtual;
	public Jogador proximoJogador;
	private int ij [];
	private boolean fim;
	private Jogador primeiroJogador;
	private int contToque;
	
	public int getContToque() 
	{
		return contToque;
	}

	public Dupla getDuplaVencedora()
	{
		Dupla duplaV;
		if(fim)
			duplaV = getDuplaDaVez();
		else if(duplas[0].contarPecas()<duplas[1].contarPecas())
			duplaV = duplas[1];
		else
			duplaV = duplas[0];
		return duplaV;
	}
	
	public Dupla getDuplaDaVez()
	{
		return duplas[ij[1]];
	}
	
	public Dupla[] getDuplas() 
	{
		return duplas;
	}

	public Jogador getPrimeiroJogador() 
	{
		return primeiroJogador;
	}
	public boolean isFim() 
	{
		return fim;
	}
	
	public Partida (Dupla dupla1, Dupla dupla2) 
	{
		duplas = new Dupla[2];
		contToque = 0;
		if(Math.random()<0.5) 
		{
			duplas[0] = dupla1;
			duplas[1] = dupla2;
		} else 
		{
			duplas[0] = dupla2;
			duplas[1] = dupla1;
		}
	}
	public void iniciarPartida(int quantidadeDePecasNaMao)
	{
		contToque=0;
		fim = false;
		
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<2; j++) 
			{				
				duplas[j].getDupla()[i].getMao().clear();
			}
		}
		
		Peca pecas[] = new Peca[28];
		int aux = 0;
		if (quantidadeDePecasNaMao > 7) 
			quantidadeDePecasNaMao = 7;
		
		for(int i=0; i<7; i++) 
		{
			for(int j=0; j<=i; j++) 
			{
				pecas[aux] = new Peca(i,j);
				aux++;
			}
		}
		
		aux--;
		int fim = 27;
		int random;
		int contAUX=0;
		
		for(int i=0; i<quantidadeDePecasNaMao; i++)
		{
			//jogador 0 dupla 0
			random = (int)(Math.random() * aux);
			duplas[0].getDupla()[0].inserirPeca( pecas[random]);
			pecas[random] = pecas[fim];
			fim--;
			aux--;
			
			//jogador 0 dupla 1
			random = (int)(Math.random() * aux);
			duplas[1].getDupla()[0].inserirPeca( pecas[random]);
			pecas[random] = pecas[fim];
			fim--;
			aux--;
			
			//jogador 1 dupla 0
			random = (int)(Math.random() * aux);
			duplas[0].getDupla()[1].inserirPeca( pecas[random]);
			pecas[random] = pecas[fim];
			fim--;
			aux--;
			
			//jogador 1 dupla 1
			random = (int)(Math.random() * aux);
			duplas[1].getDupla()[1].inserirPeca( pecas[random]);
			pecas[random] = pecas[fim];
			fim--;
			aux--;

			contAUX++;
		}
		
		ij = new int [2];
		int maior=-1;
		//gambiarra para varrer todos jogadores.
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<2; j++) 
			{
				if(duplas[j].getDupla()[i].getMaiorCarroca()>maior) 
				{
					ij[0] = i;
					ij[1] = j;
					maior = duplas[j].getDupla()[i].getMaiorCarroca();
				}
			}
		}
		
		jogadorAtual = duplas[ij[1]].getDupla()[ij[0]];
		primeiroJogador = jogadorAtual;
		calcNext();
		Peca peca = jogadorAtual.getMaiorCarrocaPeca();
		tabuleiro = new Tabuleiro(peca);
		jogadorAtual.getMao().remove(peca);
		jogadorAtual = proximoJogador;
		calcNext();
		//colocaPeca(jogadorAtual.getMaiorCarrocaPeca(), 0);
	}
	
	public void calcNext()
	{
		/*if(ij[1]==0){
			ij[1]=1;
		}else{			
			if(ij[0]==0) ij[0]=1;
			else ij[0]=0;
			ij[1]=0;
		}*/
		if(ij[1] == 1)
			ij[0] = (ij[0]+1) %2;
		ij[1] = (ij[1]+1)%2;
		proximoJogador = duplas[ij[1]].getDupla()[ij[0]];
	}
	
	public Jogador getJogadorAtual() 
	{
		return jogadorAtual;
	}
	
	public Jogador getProximoJogador() 
	{
		
		return proximoJogador;
	}

	public boolean podeColocarPeca(Peca peca, boolean lado)
	{
		Peca ponta;
		if(lado) ponta = tabuleiro.get(tabuleiro.size()-1);
		else ponta = tabuleiro.get(0);
		
		if(ponta.getVizinhos()[0] == null) 
		{
			if(peca.getNumeros()[0]==ponta.getNumeros()[0] || peca.getNumeros()[1]==ponta.getNumeros()[0])
				return true;
			else
				return false;
		} else 
		{
			if(peca.getNumeros()[0]==ponta.getNumeros()[1] || peca.getNumeros()[1]==ponta.getNumeros()[1])
				return true;
			else
				return false;
		}
	}
	
	public Tabuleiro getTabuleiro() 
	{
		return tabuleiro;
	}
	
	public void colocarPeca(Peca peca, boolean lado)
	{
		Peca ponta;
		int size = tabuleiro.size();
		int L;
		if(lado)
		{
			ponta = tabuleiro.get(size-1);
			L = size;
		}
		else 
		{
			ponta = tabuleiro.get(0);
			L = 0;
		}
		if(ponta.getVizinhos()[0] == null) 
		{
			if(peca.getNumeros()[0]==ponta.getNumeros()[0]) 
			{
				jogadorAtual.getMao().remove(peca);
				peca.getVizinhos()[0]=ponta;
				ponta.getVizinhos()[0]=peca;
			}
			else 
			{
				jogadorAtual.getMao().remove(peca);
				peca.getVizinhos()[1]=ponta;
				ponta.getVizinhos()[0]=peca;
			}
		} else 
		{
			if(peca.getNumeros()[0]==ponta.getNumeros()[1]) 
			{
				jogadorAtual.getMao().remove(peca);
				peca.getVizinhos()[0]=ponta;
				ponta.getVizinhos()[1]=peca;
			}
			else 
			{
				jogadorAtual.getMao().remove(peca);
				peca.getVizinhos()[1]=ponta;
				ponta.getVizinhos()[1]=peca;
			}
		}
		
		tabuleiro.add(L, peca);
	}
	
	public int pontosGanho (Peca P)
	{
		if(P == null)
			return 1;
		
		Peca aux1, aux2;
		aux1 = new Peca(P.getNumeros()[0],P.getNumeros()[0]);
		aux2 = new Peca(P.getNumeros()[1],P.getNumeros()[1]);		
		
		if(podeColocarPeca(P, true) && podeColocarPeca(P, false) && P.carroca()!=-1)
		{
			return 4;
		} else if((podeColocarPeca(aux1, true) && podeColocarPeca(aux2, false)) || (podeColocarPeca(aux2, true) && podeColocarPeca(aux1, false)))
		{
			return 3;
		} else if(P.carroca() != -1)
		{
			return 2;
		} else 
		{
			return 1;
		}
	}
	
	
	
	public String toString()
	{
		return "Tabuleiro: \n" + tabuleiro.toString()+"\n Dupla1: \n"  + duplas[0].toString() + "\n Dupla 2: \n" + duplas[1].toString();
	}
}
