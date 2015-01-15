package create;

import globals.Banks;

public class Questionnaire {
	private String title;
	private int questionsNumber;
	private Question questionTitles[];
	private String questionNames[];

	public Questionnaire(String title, int questionsNumber, String[] questions,
			Banks bank) {
		this.title = title;
		this.questionsNumber = questionsNumber;
		this.questionNames = questions;
		questionTitles = new Question[questionsNumber];
		for (int i = 0; i < questions.length; i++) {
			Question obj = bank.getQuestion(questions[i]);
			if (obj == null) {
				System.out.println("Questão " + questions[i]
						+ " não encontrada no banco");
				this.questionsNumber--;
			} else
				bank.getQuestion(questions[i]).qNumber++;
		}
	}

	public int getQuestionsNumber() {
		return questionsNumber;
	}

	public void setQuestionsNumber(int questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public Question[] getQuestions() {
		return questionTitles;
	}

	public void setQuestions(Question[] questions) {
		this.questionTitles = questions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Question[] getAllQuestions(String[] questions, Banks bank) {
		Question get[] = new Question[questionsNumber];
		int m = 0;
		for (int i = 0; i < questions.length; i++) {
			Question obj = bank.getQuestion(questions[i]);
			if (obj == null)
				System.out.println("Questão " + questions[i]
						+ " não encontrada no banco");
			else {
				if (m < questionsNumber) {
					get[m] = obj;
					m++;
				}
			}
		}
		return get;
	}

	public String[] getQuestionNames() {
		return questionNames;
	}

	public void setQuestionNames(String questionNames[]) {
		this.questionNames = questionNames;
	}
}