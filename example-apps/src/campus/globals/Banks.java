package globals;

import java.util.ArrayList;
import java.util.Iterator;

import create.Question;
import create.Questionnaire;

public class Banks {

	public static int numberQuestions = 0;
	public static int numberQuestionnaires = 0;
	public static ArrayList<Question> questionBank = new ArrayList<Question>();
	public static ArrayList<Questionnaire> questionnaireBank = new ArrayList<Questionnaire>();
	

	public boolean addQuestionGlobal(Question question) {
		if (!hasQuestion(question.getTitle())) {
			questionBank.add(question);
			this.numberQuestions++;
			return true;
		}
		return false;
	}

	public boolean removeQuestion(String title) {
		Iterator<Question> it = questionBank.iterator();
		while (it.hasNext()) {
			Question obj = it.next();
			if (obj.getTitle().equalsIgnoreCase(title)) {
				if (obj.qNumber > 0) {
					System.out
							.println("A questão não pode ser removida pois está em um questionário");
					return false;
				}
				this.numberQuestions--;
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasQuestion(String title) {
		Iterator<Question> it = questionBank.iterator();
		while (it.hasNext()) {
			Question obj = it.next();
			if (obj.getTitle().equalsIgnoreCase(title)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeQuestionnaire(String title) {
		Iterator<Questionnaire> it = questionnaireBank.iterator();
		while (it.hasNext()) {
			Questionnaire obj = it.next();
			if (obj.getTitle().equalsIgnoreCase(title)) {
				for (int i = 0; i < obj.getQuestionsNumber(); i++) {
					this.getQuestion(title).qNumber--;
				}
				this.numberQuestionnaires--;
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasQuestionnaire(String title) {
		Iterator<Question> it = questionBank.iterator();
		while (it.hasNext()) {
			Question obj = it.next();
			if (obj.getTitle().equalsIgnoreCase(title)) {
				return true;
			}
		}
		return false;
	}

	public boolean addQuestionnaireGlobal(Questionnaire questionnaire) {
		if (!hasQuestionnaire(questionnaire.getTitle())) {
			questionnaireBank.add(questionnaire);
			this.numberQuestionnaires++;
			return true;
		}
		return false;
	}

	public String[] listQuestions() {
		String list[] = new String[this.numberQuestions];
		Iterator<Question> it = questionBank.iterator();
		while (it.hasNext()) {
			int i = 0;
			Question obj = it.next();
			System.out.println(obj.getTitle() + "\n");
			list[i] = obj.getTitle();

		}
		return list;
	}

	public String[] listQuestionnaires() {
		String list[] = new String[this.numberQuestionnaires];
		Iterator<Questionnaire> it = questionnaireBank.iterator();
		while (it.hasNext()) {
			int i = 0;
			Questionnaire obj = it.next();
			System.out.println(obj.getTitle() + "\n");
			list[i] = obj.getTitle();
		}
		return list;
	}

	public Question getQuestion(String title) {
		Iterator<Question> it = questionBank.iterator();
		while (it.hasNext()) {
			Question obj = it.next();
			if (obj.getTitle().equals(title))
				return obj;

		}
		System.out.println("Questão não encontrada");
		return null;
	}

	public Questionnaire getQuestionnaire(String title) {
		Iterator<Questionnaire> it = questionnaireBank.iterator();
		while (it.hasNext()) {
			Questionnaire obj = it.next();
			if (obj.getTitle().equals(title))
				return obj;

		}
		System.out.println("Questionário não encontrado");
		return null;
	}

}
