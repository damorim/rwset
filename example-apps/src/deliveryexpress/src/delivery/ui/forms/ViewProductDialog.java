package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;

import delivery.business.Produto;
import delivery.business.frontage.Fachada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTextField txtTamanho;
	private JTextField txtValor;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JLabel lblTamanho;
	private JLabel lblValor;

	public ViewProductDialog(final Fachada fachada, String codigo) {
		setMinimumSize(new Dimension(450, 220));
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Visualizar produto");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNome = new JLabel("Nome:");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.anchor = GridBagConstraints.EAST;
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.gridx = 0;
			gbc_lblNome.gridy = 0;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			txtNome = new JTextField();
			txtNome.setEditable(false);
			lblNome.setLabelFor(txtNome);
			GridBagConstraints gbc_txtNome = new GridBagConstraints();
			gbc_txtNome.insets = new Insets(0, 0, 5, 0);
			gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNome.gridx = 1;
			gbc_txtNome.gridy = 0;
			contentPanel.add(txtNome, gbc_txtNome);
			txtNome.setColumns(10);
		}
		{
			lblDescricao = new JLabel("Descrição:");
			GridBagConstraints gbc_lblDescricao = new GridBagConstraints();
			gbc_lblDescricao.anchor = GridBagConstraints.EAST;
			gbc_lblDescricao.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescricao.gridx = 0;
			gbc_lblDescricao.gridy = 1;
			contentPanel.add(lblDescricao, gbc_lblDescricao);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setEditable(false);
			lblDescricao.setLabelFor(txtDescricao);
			GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
			gbc_txtDescricao.insets = new Insets(0, 0, 5, 0);
			gbc_txtDescricao.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDescricao.gridx = 1;
			gbc_txtDescricao.gridy = 1;
			contentPanel.add(txtDescricao, gbc_txtDescricao);
			txtDescricao.setColumns(10);
		}
		{
			lblTamanho = new JLabel("Tamanho:");
			GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
			gbc_lblTamanho.anchor = GridBagConstraints.EAST;
			gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
			gbc_lblTamanho.gridx = 0;
			gbc_lblTamanho.gridy = 2;
			contentPanel.add(lblTamanho, gbc_lblTamanho);
		}
		{
			txtTamanho = new JTextField();
			txtTamanho.setEditable(false);
			lblTamanho.setLabelFor(txtTamanho);
			GridBagConstraints gbc_txtTamanho = new GridBagConstraints();
			gbc_txtTamanho.insets = new Insets(0, 0, 5, 0);
			gbc_txtTamanho.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTamanho.gridx = 1;
			gbc_txtTamanho.gridy = 2;
			contentPanel.add(txtTamanho, gbc_txtTamanho);
			txtTamanho.setColumns(10);
		}
		{
			lblValor = new JLabel("Valor:");
			GridBagConstraints gbc_lblValor = new GridBagConstraints();
			gbc_lblValor.anchor = GridBagConstraints.EAST;
			gbc_lblValor.insets = new Insets(0, 0, 0, 5);
			gbc_lblValor.gridx = 0;
			gbc_lblValor.gridy = 3;
			contentPanel.add(lblValor, gbc_lblValor);
		}
		{
			txtValor = new JTextField();
			txtValor.setEditable(false);
			lblValor.setLabelFor(txtValor);
			GridBagConstraints gbc_txtValor = new GridBagConstraints();
			gbc_txtValor.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtValor.gridx = 1;
			gbc_txtValor.gridy = 3;
			contentPanel.add(txtValor, gbc_txtValor);
			txtValor.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		Produto produto = fachada.retornaProduto(codigo);
		txtNome.setText(produto.getNome());
		txtDescricao.setText(produto.getDescricao());
		txtTamanho.setText(produto.getTamanho());
		txtValor.setText(String.valueOf(produto.getValor()));
		
	}
}