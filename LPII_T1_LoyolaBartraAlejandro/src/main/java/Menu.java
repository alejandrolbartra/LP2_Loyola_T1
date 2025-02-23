import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Menu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton BtnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnListar;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Menu dialog = new Menu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Menu() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("MENU PRINCIPAL");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(156, 22, 164, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			BtnRegistrar = new JButton("Registrar producto");
			BtnRegistrar.addActionListener(this);
			BtnRegistrar.setBounds(156, 45, 164, 21);
			contentPanel.add(BtnRegistrar);
		}
		{
			btnActualizar = new JButton("Actualizar producto");
			btnActualizar.addActionListener(this);
			btnActualizar.setBounds(156, 76, 164, 21);
			contentPanel.add(btnActualizar);
		}
		{
			btnEliminar = new JButton("Eliminar producto");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(156, 107, 164, 21);
			contentPanel.add(btnEliminar);
		}
		{
			btnListar = new JButton("Listar producto");
			btnListar.addActionListener(this);
			btnListar.setBounds(156, 138, 164, 21);
			contentPanel.add(btnListar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			actionPerformedCancelButton(e);
		}
		if (e.getSource() == btnListar) {
			actionPerformedBtnListar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == BtnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
	    // Crear e instanciar el diálogo Registrar
	    Registrar registrarDialog = new Registrar();
	    registrarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    registrarDialog.setVisible(true);
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
	    // Crear e instanciar el diálogo Actualizar
	    Actualizar actualizarDialog = new Actualizar();
	    actualizarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    actualizarDialog.setVisible(true);
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
	    // Crear e instanciar el diálogo Eliminar
	    Eliminar eliminarDialog = new Eliminar();
	    eliminarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    eliminarDialog.setVisible(true);
	}

	protected void actionPerformedBtnListar(ActionEvent e) {
	    // Crear e instanciar el diálogo Listar
	    Listar listarDialog = new Listar();
	    listarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    listarDialog.setVisible(true);
	}
	protected void actionPerformedCancelButton(ActionEvent e) {
		dispose();
	}
}
