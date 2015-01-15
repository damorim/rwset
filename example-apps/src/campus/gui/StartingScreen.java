package gui;

import globals.Banks;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingConstants;

import application.Application;
import create.Question;
import create.Questionnaire;

public class StartingScreen {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingScreen window = new StartingScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public StartingScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 447, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblBancoDeQuestes = new JLabel("Question\u00E1rio");
		lblBancoDeQuestes.setFont(new Font("Lucida Sans Unicode", Font.PLAIN,
				16));
		lblBancoDeQuestes.setBounds(164, 21, 109, 14);
		frame.getContentPane().add(lblBancoDeQuestes);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSair.setBounds(231, 186, 89, 23);
		frame.getContentPane().add(btnSair);

		Panel panel = new Panel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(88, 72, 255, 72);
		frame.getContentPane().add(panel);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();

		int width = tamanhoTela.width;
		int height = tamanhoTela.height;

		frame.setLocation((width / 2) - (447 / 2), (height / 2) - (257 / 2));

		JButton btnCriarQuestoes = new JButton("Criar Quest\u00E3o");
		btnCriarQuestoes.setBounds(65, 11, 126, 23);
		btnCriarQuestoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateQuestion window = new CreateQuestion();
				window.setVisible(true);
				frame.dispose();
			}
		});
		panel.setLayout(null);
		panel.add(btnCriarQuestoes);

		JButton btnAcessarBancoQ = new JButton("Acessar Banco de Questões");
		btnAcessarBancoQ.setBounds(27, 39, 200, 23);

		// btnAcessarBancoQ.addActionListener(new ActionListener(){
		// public void actionPerformed()
		// });
		panel.add(btnAcessarBancoQ);

		JButton btnCriarQuestionario = new JButton("Criar");
		btnCriarQuestionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application.questionsToSelect = new ArrayList<Question>();
				Application.selectedQuestions = new ArrayList<Question>();
				for (int i = 0; i < Banks.questionBank.size(); i++)
					Application.questionsToSelect.add(Banks.questionBank.get(i));
				CreateQuestion frame2 = new CreateQuestion();
				frame2.setVisible(true);
				frame.dispose();
			}
		});

		if (Banks.numberQuestions > 0) {
			btnCriarQuestionario.setEnabled(true);
		} else {
			btnCriarQuestionario.setEnabled(false);
		}
		btnCriarQuestionario.setBounds(330, 186, 89, 23);
		frame.getContentPane().add(btnCriarQuestionario);
		btnAcessarBancoQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ListQuestions lista = new ListQuestions();
				lista.setVisible(true);
				frame.dispose();
			}
		});

	}
}
