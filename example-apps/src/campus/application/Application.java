package application;

import java.util.ArrayList;

import create.Question;
import globals.Banks;
import gui.StartingScreen;

public class Application {

	public static Banks global = new Banks();
	public static ArrayList<Question> selectedQuestions = new ArrayList<Question>();
	public static ArrayList<Question> questionsToSelect = new ArrayList<Question>();
	public static int questaoAtual = 1;
	public static String arrayAnswer[];
	public static String arrayAnswerCorrect[];


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StartingScreen window = new StartingScreen();
		window.frame.setVisible(true);
		
	}

}
