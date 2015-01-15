package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Cursor;

import javax.swing.SwingConstants;

import java.awt.Panel;

import javax.swing.Icon;
import javax.swing.JTextArea;

import create.Question;
import application.Application;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestionarioUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionarioUsuario frame = new QuestionarioUsuario("", "",
							0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuestionarioUsuario(String titulo, String enunciado, int numero) {
		System.out.println(Application.questaoAtual);
		System.out
				.println("Arraysize: " + Application.selectedQuestions.size());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel label = new JLabel("" + numero + ")");
		label.setBounds(10, 11, 46, 14);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(69, 233, 86, 20);
		textField.setText(Application.arrayAnswer[Application.questaoAtual]);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("R - ");
		label_1.setBounds(10, 236, 46, 14);
		contentPane.add(label_1);

		Panel panel = new Panel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = 1;
				Question[] array = new Question[Application.selectedQuestions
						.size() + 1];
				Iterator<Question> it = Application.selectedQuestions
						.iterator();
				while (it.hasNext()) {
					Question obj = it.next();
					array[i] = obj;
					i++;
				}
				if (Application.questaoAtual < Application.selectedQuestions
						.size()) {
					Application.arrayAnswer[Application.questaoAtual] = textField.getText();
					Application.questaoAtual++;
					QuestionarioUsuario f = new QuestionarioUsuario(
							array[Application.questaoAtual].getTitle(),
							array[Application.questaoAtual]
									.getDescription(),
							Application.questaoAtual);

					
					f.setVisible(true);
					dispose();
				}

			}
		});
		panel.setBackground(SystemColor.control);
		panel.setBounds(625, 233, 25, 25);
		ImageIcon pic = new ImageIcon(
				"10409969_688739357883672_1590550356_n.jpg");
		panel.add(new JLabel(pic));
		contentPane.add(panel);

		Panel panel_1 = new Panel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = 1;
				Question[] array = new Question[Application.selectedQuestions
						.size() + 1];
				Iterator<Question> it = Application.selectedQuestions
						.iterator();
				while (it.hasNext()) {
					Question obj = it.next();
					array[i] = obj;
					i++;
				}
				if (Application.questaoAtual > 1) {
					Application.questaoAtual--;
					QuestionarioUsuario f = new QuestionarioUsuario(
							array[Application.questaoAtual].getTitle(),
							array[Application.questaoAtual]
									.getDescription(),
							Application.questaoAtual);
					
					f.setVisible(true);

					dispose();
				}

			}
		});
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(585, 233, 25, 25);
		ImageIcon pic_1 = new ImageIcon(
				"10568009_688739361217005_488992022_n.jpg");
		panel_1.add(new JLabel(pic_1));
		contentPane.add(panel_1);

		JLabel label_2 = new JLabel((Icon) null);
		panel_1.add(label_2);

		JTextArea textArea = new JTextArea(enunciado);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(66, 11, 584, 190);
		contentPane.add(textArea);

		JButton btnNewButton = new JButton("Terminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.arrayAnswer[Application.questaoAtual] = textField.getText();
				int count = 0;
				for (int i = 1; i < Application.arrayAnswer.length; i++) {
					System.out.println("Resposta: " + i + " " + Application.arrayAnswer[i]);
					System.out.println("Resposta Correta: " + i + " " + Application.arrayAnswerCorrect[i]);
					if(Application.arrayAnswer[i].equals(Application.arrayAnswerCorrect[i])){
						count++;
					}
				}
				System.out.println(count + " e " + Application.arrayAnswer.length);
				double total =  ((double) (count) / (double) (Application.arrayAnswer.length-1));
				System.out.println(total);
				String results;
				if (total > 0)
					results = new DecimalFormat("#.##").format(total*100);
				else
					results = "0.00";
		 		JOptionPane.showMessageDialog(null,results+" %","Resultado",JOptionPane.INFORMATION_MESSAGE);    
				StartingScreen window = new StartingScreen();
				Application.questaoAtual = 1;
				Application.selectedQuestions = new ArrayList<Question>();
				window.frame.setVisible(true);
				dispose();
			}
		});

		btnNewButton.setBounds(289, 248, 89, 23);
		contentPane.add(btnNewButton);

		if (Application.questaoAtual == Application.selectedQuestions.size()) {
			btnNewButton.setVisible(true);
			panel.setVisible(false);
			panel_1.setVisible(true);
		} else {
			btnNewButton.setVisible(false);
			panel.setVisible(true);
			panel_1.setVisible(true);
		}

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();

		int width = tamanhoTela.width;
		int height = tamanhoTela.height;

		this.setLocation((width / 2) - (666 / 2), (height / 2) - (311 / 2));
	}
}
