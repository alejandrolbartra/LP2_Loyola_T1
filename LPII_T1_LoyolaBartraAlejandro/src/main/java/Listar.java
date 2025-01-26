import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Listar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model; // Declarar el modelo como atributo
    private JTextField txtId, txtNombre, txtPrecio, txtDescripcion, txtEstado, txtFabricado, txtVencimiento;
    private JButton CancelarBtn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Listar dialog = new Listar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Listar() {
        setBounds(100, 100, 600, 599);  // Ajusta el tamaño del diálogo
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

        // Campos de texto para mostrar la información del producto seleccionado
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 270, 100, 20);
        contentPanel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 270, 200, 20);
        txtId.setEditable(false); // Desactivar la edición
        contentPanel.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 300, 100, 20);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 300, 200, 20);
        txtNombre.setEditable(false); // Desactivar la edición
        contentPanel.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 330, 100, 20);
        contentPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 330, 200, 20);
        txtPrecio.setEditable(false); // Desactivar la edición
        contentPanel.add(txtPrecio);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 360, 100, 20);
        contentPanel.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 360, 200, 20);
        txtDescripcion.setEditable(false); // Desactivar la edición
        contentPanel.add(txtDescripcion);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(50, 390, 100, 20);
        contentPanel.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setBounds(150, 390, 200, 20);
        txtEstado.setEditable(false); // Desactivar la edición
        contentPanel.add(txtEstado);

        JLabel lblFabricado = new JLabel("Fabricado:");
        lblFabricado.setBounds(50, 420, 100, 20);
        contentPanel.add(lblFabricado);

        txtFabricado = new JTextField();
        txtFabricado.setBounds(150, 420, 200, 20);
        txtFabricado.setEditable(false); // Desactivar la edición
        contentPanel.add(txtFabricado);

        JLabel lblVencimiento = new JLabel("Vencimiento:");
        lblVencimiento.setBounds(50, 450, 100, 20);
        contentPanel.add(lblVencimiento);

        txtVencimiento = new JTextField();
        txtVencimiento.setBounds(150, 450, 200, 20);
        txtVencimiento.setEditable(false); // Desactivar la edición
        contentPanel.add(txtVencimiento);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Botón Cancel
        CancelarBtn = new JButton("Cancelar");
        CancelarBtn.addActionListener(this);
        CancelarBtn.setActionCommand("Cancel");
        buttonPane.add(CancelarBtn);

        // Agregar listener para seleccionar una fila de la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    mostrarDetallesProducto(filaSeleccionada);
                }
            }
        });
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

    // Método para mostrar los detalles del producto seleccionado
    private void mostrarDetallesProducto(int fila) {
        int id = (int) model.getValueAt(fila, 0);
        String nombre = (String) model.getValueAt(fila, 1);
        double precio = (double) model.getValueAt(fila, 2);
        String descripcion = (String) model.getValueAt(fila, 3);
        String estado = (String) model.getValueAt(fila, 4);
        String fabricado = model.getValueAt(fila, 5).toString();
        String vencimiento = model.getValueAt(fila, 6).toString();

        // Mostrar los datos en los campos de texto
        txtId.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtPrecio.setText(String.valueOf(precio));
        txtDescripcion.setText(descripcion);
        txtEstado.setText(estado);
        txtFabricado.setText(fabricado);
        txtVencimiento.setText(vencimiento);
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == CancelarBtn) {
			actionPerformedCancelButton(e);
		}
	}
	protected void actionPerformedCancelButton(ActionEvent e) {
		dispose();


	}
}
