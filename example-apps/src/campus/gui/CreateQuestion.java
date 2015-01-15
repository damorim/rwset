package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.DropMode;

import java.awt.TextArea;

import javax.swing.JScrollPane;

import application.Application;
import create.Question;

import java.awt.Button;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateQuestion extends JFrame {
	private int count = 0;
	private JPanel contentPane;
	private JTextField textFieldTituloQuestao;
	private JLabel lbEnunciado;
	private JLabel lbRespostaCorreta;
	private JTextField textFieldRespostaCorreta;
	private Button buttonCriar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateQuestion frame = new CreateQuestion();
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
	public CreateQuestion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit kit = Toolkit.getDefaultToolkit();  
        Dimension tamanhoTela = kit.getScreenSize();  
          
        int width = tamanhoTela.width;  
        int height = tamanhoTela.height;  
          
        this.setLocation( (width / 2) - (666/2), (height / 2) - (311/2) );
		

		JLabel lbTituloQuestao = new JLabel("T\u00EDtulo da Quest\u00E3o: *");
		lbTituloQuestao.setBounds(27, 16, 147, 14);
		contentPane.add(lbTituloQuestao);

		textFieldTituloQuestao = new JTextField();
		
		
		textFieldTituloQuestao.setBounds(27, 41, 597, 20);
		contentPane.add(textFieldTituloQuestao);
		textFieldTituloQuestao.setColumns(10);

		lbEnunciado = new JLabel("Enunciado:");
		lbEnunciado.setBounds(27, 75, 77, 14);
		contentPane.add(lbEnunciado);
		final JTextArea textAreaEnunciado = new JTextArea("");


		textAreaEnunciado.setLineWrap(true);
		JScrollPane scrollEnunciado = new JScrollPane(textAreaEnunciado,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollEnunciado.setBounds(27, 100, 597, 63);
		scrollEnunciado.setEnabled(true);
		scrollEnunciado.setVisible(true);
		contentPane.add(scrollEnunciado);

		lbRespostaCorreta = new JLabel("Resposta Correta:");
		lbRespostaCorreta.setBounds(27, 175, 137, 14);
		contentPane.add(lbRespostaCorreta);

		textFieldRespostaCorreta = new JTextField();
	
		textFieldRespostaCorreta.setBounds(27, 200, 217, 20);
		contentPane.add(textFieldRespostaCorreta);
		textFieldRespostaCorreta.setColumns(10);

		Button buttonCancelar = new Button("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartingScreen window = new StartingScreen();
				window.frame.setVisible(true);
				dispose();
			}
		});
		buttonCancelar.setBounds(466, 240, 70, 22);
		contentPane.add(buttonCancelar);

		buttonCriar = new Button("Criar");
		buttonCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldTituloQuestao.getText().equals("") && !textAreaEnunciado.getText().equals("") && !textFieldRespostaCorreta.getText().equals("")){
					if (Application.global.addQuestionGlobal(new Question(textFieldTituloQuestao.getText(), textAreaEnunciado.getText(),
							textFieldRespostaCorreta.getText()))) {
						System.out.println("Questão adicionada");
						StartingScreen window = new StartingScreen();
						window.frame.setVisible(true);
						dispose();

					} else
						JOptionPane.showMessageDialog(null,"A questão já existe no banco, por favor, insira outro nome","Não Adicionado",JOptionPane.INFORMATION_MESSAGE);    
					
					
				}else{
					JOptionPane.showMessageDialog(null,"Preencha todos os campos","Falta de Informação",JOptionPane.INFORMATION_MESSAGE);    
				}
			}
		});
		buttonCriar.setBounds(554, 240, 70, 22);
		contentPane.add(buttonCriar);

	}
}
