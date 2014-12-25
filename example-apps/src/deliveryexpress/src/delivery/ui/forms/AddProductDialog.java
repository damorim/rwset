package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import delivery.business.exceptions.client.NomeObrigatorioException;
import delivery.business.exceptions.product.CodigoJaExistenteException;
import delivery.business.exceptions.product.CodigoObrigatorioException;
import delivery.business.exceptions.product.TamanhoObrigatorioException;
import delivery.business.exceptions.product.ValorInvalidoException;
import delivery.business.exceptions.product.ValorObrigatorioException;
import delivery.business.frontage.Fachada;
import delivery.ui.MainWindow;


public class AddProductDialog extends JDialog {

	private static final long serialVersionUID = 6858544012971515041L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTextField txtTamanho;
	private JTextField txtValor;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JLabel lblTamanho;
	private JLabel lblValor;

	public AddProductDialog(final MainWindow mw, final Fachada fachada) {
		setMinimumSize(new Dimension(450, 220));
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Adicionar produto");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
				JButton okButton = new JButton("Adicionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String nome = txtNome.getText();
						String descricao = txtDescricao.getText();
						String tamanho = txtTamanho.getText();
						BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(txtValor.getText()));
						try {
							fachada.adicionarProduto(nome, descricao, tamanho, valor);
						} catch (CodigoJaExistenteException e) {
							JOptionPane.showMessageDialog(null, "Código já existente.");
						} catch (ValorInvalidoException e) {
							JOptionPane.showMessageDialog(null, "Valor inválido.");
						} catch (ValorObrigatorioException e) {
							JOptionPane.showMessageDialog(null, "Valor é obrigatório.");
						} catch (CodigoObrigatorioException e) {
							JOptionPane.showMessageDialog(null, "Código é obrigatório.");
						} catch (NomeObrigatorioException e) {
							JOptionPane.showMessageDialog(null, "Nome é origatório.");
						} catch (TamanhoObrigatorioException e) {
							JOptionPane.showMessageDialog(null, "Tamanho é obrigatório.");
						}
						mw.atualizarTabelaClientes(fachada.filtrarClientes(nome));
						mw.setTxtFiltroProdutos(nome);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}