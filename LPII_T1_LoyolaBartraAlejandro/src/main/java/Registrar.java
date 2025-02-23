import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Repositorio.Repositorio;
import modelo.Producto;

public class Registrar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model; // Declarar el modelo como atributo
    private JTextField txtNombre, txtPrecio, txtDescripcion, txtEstado, txtFabricado, txtVencimiento;
    private JButton cancelButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	Registrar dialog = new Registrar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Registrar() {
        setBounds(100, 100, 600, 571);  // Ajusta el tamaño del diálogo
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Etiqueta de encabezado
        JLabel lblNewLabel = new JLabel("Registrar nuevo producto");
        lblNewLabel.setBounds(250, 20, 200, 13);
        contentPanel.add(lblNewLabel);

        // Inicializar el modelo de la tabla
        model = new DefaultTableModel(
            new String[] {"ID", "Nombre", "Precio", "Descripción", "Estado", "Fabricado", "Vence"},
            0 // Número de filas iniciales
        );

        // Crear la tabla con el modelo
        table = new JTable(model);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 500, 186);
        contentPanel.add(scrollPane);

        // Llenar la tabla con datos
        cargarDatos();

        // Campos de texto para registrar un nuevo producto
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 270, 100, 20);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 270, 200, 20);
        contentPanel.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 300, 100, 20);
        contentPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 300, 200, 20);
        contentPanel.add(txtPrecio);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 330, 100, 20);
        contentPanel.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 330, 200, 20);
        contentPanel.add(txtDescripcion);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(50, 360, 100, 20);
        contentPanel.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setBounds(150, 360, 200, 20);
        contentPanel.add(txtEstado);

        JLabel lblFabricado = new JLabel("Fabricado:");
        lblFabricado.setBounds(50, 390, 100, 20);
        contentPanel.add(lblFabricado);

        txtFabricado = new JTextField();
        txtFabricado.setBounds(150, 390, 200, 20);
        contentPanel.add(txtFabricado);

        JLabel lblVencimiento = new JLabel("Vencimiento:");
        lblVencimiento.setBounds(50, 420, 100, 20);
        contentPanel.add(lblVencimiento);

        txtVencimiento = new JTextField();
        txtVencimiento.setBounds(150, 420, 200, 20);
        contentPanel.add(txtVencimiento);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Botón Registrar
        JButton registerButton = new JButton("Registrar");
        registerButton.setActionCommand("Registrar");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });
        buttonPane.add(registerButton);

        // Botón Cancel
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    // Método para cargar datos desde la base de datos
    private void cargarDatos() {
        Repositorio repositorio = new Repositorio();
        List<Producto> productos = repositorio.listarProductos();

        // Limpiar el modelo de la tabla antes de agregar datos
        model.setRowCount(0);

        // Iterar sobre los productos y agregarlos al modelo
        for (Producto producto : productos) {
            model.addRow(new Object[] {
                producto.getIdproductot1(),
                producto.getNombret1(),
                producto.getPreciot1(),
                producto.getDescripciont1(),
                producto.getEstadot1(),
                producto.getFechafabrit1(),
                producto.getFechavencimt1()
            });
        }
    }

    // Método para registrar el producto
    private void registrarProducto() {
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String descripcion = txtDescripcion.getText();
        String estado = txtEstado.getText();
        String fabricado = txtFabricado.getText();
        String vencimiento = txtVencimiento.getText();

        // Crear una nueva fecha de fabricación y vencimiento (convertir de String)
        try {
            Repositorio repositorio = new Repositorio();
            repositorio.crearProducto(nombre, precio, descripcion, estado, 
                java.sql.Date.valueOf(fabricado), java.sql.Date.valueOf(vencimiento));
            
            cargarDatos(); // Recargar los datos en la tabla
            limpiarCampos(); // Limpiar los campos después de registrar
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtEstado.setText("");
        txtFabricado.setText("");
        txtVencimiento.setText("");
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			actionPerformedCancelButton(e);
		}
	}
	protected void actionPerformedCancelButton(ActionEvent e) {
		dispose();

	}
}
