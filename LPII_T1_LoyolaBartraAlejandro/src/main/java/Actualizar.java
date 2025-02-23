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

public class Actualizar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model; // Declarar el modelo como atributo
    private JTextField txtNombre, txtPrecio, txtDescripcion, txtEstado, txtFabricado, txtVencimiento;
    private int productoId; // Para almacenar el ID del producto seleccionado
    private JButton cancelButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	Actualizar dialog = new Actualizar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Actualizar() {
        setBounds(100, 100, 600, 541);  // Ajusta el tamaño del diálogo
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Etiqueta de encabezado
        JLabel lblNewLabel = new JLabel("Lista de productos");
        lblNewLabel.setBounds(250, 20, 137, 13);
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

        // Campos de texto para editar
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

        // Botón Actualizar
        JButton updateButton = new JButton("Actualizar");
        updateButton.setActionCommand("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });
        buttonPane.add(updateButton);

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

        // Agregar un listener para seleccionar un producto y cargarlo en los campos de texto
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                productoId = (Integer) model.getValueAt(row, 0);
                txtNombre.setText((String) model.getValueAt(row, 1));
                txtPrecio.setText(String.valueOf(model.getValueAt(row, 2)));
                txtDescripcion.setText((String) model.getValueAt(row, 3));
                txtEstado.setText((String) model.getValueAt(row, 4));
                txtFabricado.setText(String.valueOf(model.getValueAt(row, 5)));
                txtVencimiento.setText(String.valueOf(model.getValueAt(row, 6)));
            }
        });
    }

    // Método para actualizar el producto
    private void actualizarProducto() {
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String descripcion = txtDescripcion.getText();
        String estado = txtEstado.getText();
        String fabricado = txtFabricado.getText();
        String vencimiento = txtVencimiento.getText();

        // Crear una nueva fecha de fabricación y vencimiento (convertir de String)
        // Asumimos que las fechas están en formato "yyyy-MM-dd"
        // Si no es así, deberás ajustar el formato de fecha.
        try {
            Repositorio repositorio = new Repositorio();
            repositorio.editarProducto(productoId, nombre, precio, descripcion, estado, 
                java.sql.Date.valueOf(fabricado), java.sql.Date.valueOf(vencimiento));
            
            cargarDatos(); // Recargar los datos en la tabla
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
