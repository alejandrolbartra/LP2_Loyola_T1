import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Repositorio.Repositorio;
import modelo.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Eliminar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model; // Declarar el modelo como atributo
    private JButton cancelButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	Eliminar dialog = new Eliminar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Eliminar() {
        setBounds(100, 100, 600, 400);  // Ajusta el tamaño del diálogo
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Etiqueta de encabezado
        JLabel lblNewLabel = new JLabel("Selecciona el producto a ser eliminado:");
        lblNewLabel.setBounds(50, 20, 475, 13);
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

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Botón Cancel
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        // Botón Eliminar
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setActionCommand("Eliminar");
        deleteButton.addActionListener(e -> eliminarProducto());
        buttonPane.add(deleteButton);
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

    // Método para eliminar el producto seleccionado
    private void eliminarProducto() {
        // Obtener la fila seleccionada en la tabla
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            // Obtener el ID del producto seleccionado
            int id = (Integer) model.getValueAt(selectedRow, 0); // Suponiendo que el ID está en la primera columna

            // Eliminar el producto usando el repositorio
            Repositorio repositorio = new Repositorio();
            repositorio.eliminarProducto(id);

            // Recargar los datos en la tabla después de eliminar
            cargarDatos();
        } else {
            System.out.println("No se ha seleccionado ningún producto.");
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
