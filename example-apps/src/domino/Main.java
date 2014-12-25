package domino;

import java.util.Scanner;

public class Main {
  
	public static void main(String[] args)
	{
		Jogador jogadores[] = new Jogador[4];
		Dupla duplas[] = new Dupla[2];
		Scanner in = new Scanner(System.in);
		
		System.out.println("Bem vindo ao domin� do cin!");
		
		//System.out.println("##### DEFININDO JOGADORES #####");
		
		for(int i = 1; i <= 4; i++)
		{
			System.out.print("-> Player " + i + ": ");
			jogadores[i - 1] = new Jogador(in.nextLine());
		}
		
		//System.out.println("##### SETANDO DUPLAS #####");
		
		duplas[0] = new Dupla(jogadores[0], jogadores[1]);
		duplas[1] = new Dupla(jogadores[2], jogadores[3]);
		
		//System.out.println("##### CRIANDO PARTIDA #####");
		
		Partida minhaPartida = new Partida(duplas[0], duplas[1]);
		
		//System.out.println("##### INICIANDO PARTIDA #####");
		
		int qtdPecas = 3;
		minhaPartida.iniciarPartida(qtdPecas);
		
		System.out.println("\nO jogador " + minhaPartida.getPrimeiroJogador().getNome() + " come�ou o jogo!\n");
		
		boolean acabou = false;
		
		while(!acabou)
		{
			System.out.println("##### ESTADO ATUAL DO JOGO #####\n\n" + minhaPartida.toString());
			
			for(int i = 0; i < 4; i++)
			{
				acabou = acabou || jogadores[0].getMao().size() == 0;
			}
			
			if(acabou)
			{
				System.out.println("##### ACABOU #####");
			} else
			{
				Jogador j = minhaPartida.getJogadorAtual();
				Mao m = j.getMao();
				System.out.println("Jogador " + j.getNome() + " selecione a pe�a e o lado: ");
				for(int i = 0; i < m.size(); i++)
				{
					System.out.println(i + ": " + m.get(i).toString());
				}
				boolean pode = false;
				int in1 = 0, in2 = 0;
				while(!pode)
				{
					System.out.print("Sua sele��o: ");
					in1 = in.nextInt();
					System.out.print("E o lado (0 ou 1): ");
					in2 = in.nextInt();
					pode = minhaPartida.podeColocarPeca(m.get(in1), in2 == 1);
				}
				if(pode)
					minhaPartida.colocarPeca(j.removerPeca(in1), in2 == 1);
			}
		}
	}
}
