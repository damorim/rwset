import globals.Banks;

import java.text.DecimalFormat;
import java.util.Scanner;

import create.Question;
import create.Questionnaire;

public class test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Banks global = new Banks();
		while (true) {
			System.out.println("----------------Q&A----------------"
					+ "\n1 - Criar Questões" + "\n2 - Criar Questionario"
					+ "\n3 - Ver Banco Questões"
					+ "\n4 - Ver Banco Questionário"
					+ "\n5 - Responder Questionário" + "\n6 - Sair"
					+ "\n---------------------MENU--------------------");
			int select = new Scanner(System.in).nextInt();
			switch (select) {
			case 1:
				System.out.println("Digite o titulo");
				String titulo = new Scanner(System.in).nextLine();
				System.out.println("Digite a Descrição");
				String descricao = new Scanner(System.in).nextLine();
				System.out.println("Digite a Resposta");
				String resposta = new Scanner(System.in).nextLine();
				if (global.addQuestionGlobal(new Question(titulo, descricao,
						resposta))) {
					System.out.println("Questão adicionada");

				} else
					System.out
							.println("A questão já existe no banco, por favor, insira outro nome");

				break;
			case 2:
				if (global.numberQuestions == 0) {
					System.out
							.println("Crie alguma questão para fazer um questionário");
				} else {
					System.out.println("Digite um titulo");
					String tituloQuestionnaire = new Scanner(System.in)
							.nextLine();
					System.out.println("Digite numero de questões");
					int num = new Scanner(System.in).nextInt();
					System.out.println("Digite o nome das questões:");
					String[] questions = new String[num];
					for (int i = 0; i < num; i++) {
						questions[i] = new Scanner(System.in).nextLine();
						System.out
								.println("Nome adicionado, caso não existir, não constará no questionário");
					}
					global.addQuestionnaireGlobal(new Questionnaire(
							tituloQuestionnaire, num, questions, global));
				}
				break;
			case 3:
				if (global.numberQuestions > 0) {
					String get[] = new String[global.numberQuestions];
					get = global.listQuestions();
					System.out
							.println("Digite a questão que deseja visualizar: ");
					String name = new Scanner(System.in).nextLine();
					Question q = global.getQuestion(name);
					if (!(q == null) && q.getTitle().equals(name)) {
						System.out.println("Título:");
						System.out.println(q.getTitle());
						System.out.println("Enunciado:");
						System.out.println(q.getDescription());
						System.out.println("Resposta:");
						System.out.println(q.getAnswer());
					} else {
						System.out.println("A questão não existe");
					}
				} else
					System.out
							.println("Não há questões disponíveis para listar");
				break;
			case 4:
				if (global.numberQuestionnaires > 0) {
					String getQuestionnaire[] = new String[global.numberQuestions];
					getQuestionnaire = global.listQuestionnaires();
					System.out
							.println("Digite o questionário que deseja visualizar: ");
					String name = new Scanner(System.in).nextLine();
					Questionnaire q = global.getQuestionnaire(name);
					if (!(q == null) && q.getTitle().equals(name)) {
						System.out.println("Título:");
						System.out.println(q.getTitle());
						System.out.println("questões:");
						Question[] var = q.getAllQuestions(
								q.getQuestionNames(), global);
						for (int i = 0; i < var.length; i++) {
							System.out.println(var[i].getTitle());
						}

						System.out.println("Número de Questões:");
						System.out.println(q.getQuestionsNumber());
					} else {
						System.out.println("A questão não existe");
					}
				} else {
					System.out.println("Não há questionários para listar");
				}
				break;
			case 5:
				int count = 0;
				String getQuestionnaireAns[] = global.listQuestionnaires();
				System.out.println("Qual questionário responder?");
				String name = new Scanner(System.in).nextLine();
				Questionnaire q = global.getQuestionnaire(name);
				if (!(q == null) && q.getTitle().equals(name)) {
					System.out.println("Título:");
					System.out.println(q.getTitle());
					Question[] var = q.getAllQuestions(q.getQuestionNames(),
							global);
					for (int i = 0; i < var.length; i++) {
						System.out.println(var[i].getTitle()
								+ "\nLeia com atenção e responda:" + "\n"
								+ var[i].getDescription());
						String answer = new Scanner(System.in).nextLine();
						var[i].setUserAnswer(answer);
					}

					System.out.println("Resumo:");
					for (int i = 0; i < var.length; i++) {

						System.out.println("Questão " + (i + 1) + ":");
						if (!(var[i].getAnswer().equals(var[i].getUserAnswer()))) {
							System.out.println("Resposta usuário: "
									+ var[i].getUserAnswer() + " Correta: "
									+ var[i].getAnswer() + " ERROU");
						} else {
							System.out.println("Resposta usuário: "
									+ var[i].getUserAnswer() + " Correta: "
									+ var[i].getAnswer() + " ACERTOU");
							count++;
						}

					}
					String total;
					if (count / var.length > 0)
						total = new DecimalFormat("#.00").format(count
								/ var.length);
					else
						total = "0.00";

					System.out.println("Total: " + total);
				} else {
					System.out.println("A questão não existe");
				}

			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Comando não reconhecido");
			}

		}
	}
}
