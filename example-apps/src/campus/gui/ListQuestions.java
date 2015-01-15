package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import create.Question;
import application.Application;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListQuestions extends JFrame {

	Question obj;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListQuestions frame = new ListQuestions();
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
	public ListQuestions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Toolkit kit = Toolkit.getDefaultToolkit();  
        Dimension tamanhoTela = kit.getScreenSize();  
          
        int width = tamanhoTela.width;  
        int height = tamanhoTela.height;  
          
        this.setLocation( (width / 2) - (450/2), (height / 2) - (508/2) );
		
		

		final JList list = new JList(Application.global.questionBank.toArray());

		list.setVisibleRowCount(10);
		list.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Question) {
					((JLabel) renderer).setText(((Question) value).getTitle());
				}
				return renderer;
			}
		});

		final JLabel label = new JLabel("AQUI");

		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(5, 5, 424, 251);

		contentPane.setLayout(null);

		contentPane.add(pane);

		JLabel lblT = new JLabel("T\u00EDtulo: ");
		lblT.setBounds(5, 267, 83, 14);
		contentPane.add(lblT);

		final JLabel lbtitulo = new JLabel(" -- titulo da questão");
		lbtitulo.setBounds(72, 267, 352, 14);
		contentPane.add(lbtitulo);

		JLabel lblResposta = new JLabel("Resposta:");
		lblResposta.setBounds(5, 292, 83, 14);
		contentPane.add(lblResposta);

		final JLabel lbresposta = new JLabel(" -- resposta correta");
		lbresposta.setBounds(72, 292, 352, 14);
		contentPane.add(lbresposta);

		JLabel lblEnunciado = new JLabel("Enunciado:");
		lblEnunciado.setBounds(5, 317, 83, 14);
		contentPane.add(lblEnunciado);

		final JLabel lbenunciado = new JLabel(" -- enunciado");
		lbenunciado.setVerticalAlignment(SwingConstants.TOP);
		lbenunciado.setBounds(72, 317, 352, 97);
		contentPane.add(lbenunciado);

		final JButton btnNewButton = new JButton("Deletar");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Application.global.removeQuestion(obj.getTitle())) {
					JOptionPane.showMessageDialog(null, "Questão deletada",
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
					ListQuestions frame = new ListQuestions();
					frame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Questão NÃO deletada, ocorreu algum imprevisto",
							"ERRO", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(335, 435, 89, 23);
		contentPane.add(btnNewButton);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					obj = (Question) list.getSelectedValue();
					if (obj != null) {
						lbtitulo.setText(obj.getTitle());
						lbresposta.setText(obj.getAnswer());
						lbenunciado.setText(obj.getDescription());
					}
					btnNewButton.setEnabled(true);
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartingScreen window = new StartingScreen();
				window.frame.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(231, 435, 89, 23);
		contentPane.add(btnVoltar);
	}
}
