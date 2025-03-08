package Proyecto2;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SolicitudArticulos2 {

    private String departamento;
    private Articulo2 articulo;
    private int cantidadSolicitada;
    private String estado; 
    private String fecha;

    public static ArrayList<SolicitudArticulos2> solicitudes = new ArrayList<>();

    public SolicitudArticulos2(String departamento, Articulo2 articulo, int cantidadSolicitada) {
        this.departamento = departamento;
        this.articulo = articulo;
        this.cantidadSolicitada = cantidadSolicitada;
        this.estado = "Pendiente";
        this.fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDepartamento() {
        return departamento;
    }

    public Articulo2 getArticulo() {
        return articulo;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Departamento: " + departamento + ", Artículo: " + articulo.getNombre() +
                ", Cantidad: " + cantidadSolicitada + ", Estado: " + estado + ", Fecha: " + fecha;
    }

    // 🔹 Crear una solicitud de artículo
    public static void crearSolicitud() {
        if (Articulo2.articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos disponibles para solicitar.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField departamentoField = new JTextField();
        JComboBox<Articulo2> articuloComboBox = new JComboBox<>();
        JTextField cantidadField = new JTextField();

        
        for (Articulo2 articulo : Articulo2.articulos) {
            articuloComboBox.addItem(articulo);
        }

        panel.add(new JLabel("Departamento:"));
        panel.add(departamentoField);
        panel.add(new JLabel("Artículo:"));
        panel.add(articuloComboBox);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Crear Solicitud", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String departamento = departamentoField.getText();
            Articulo2 articuloSeleccionado = (Articulo2) articuloComboBox.getSelectedItem();
            String cantidadStr = cantidadField.getText();

            if (!departamento.isEmpty() && articuloSeleccionado != null && !cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0) {
                        solicitudes.add(new SolicitudArticulos2(departamento, articuloSeleccionado, cantidad));
                        JOptionPane.showMessageDialog(null, "Solicitud creada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            }
        }
    }

   
    public static void verSolicitudes() {
        if (solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.");
            return;
        }

        StringBuilder lista = new StringBuilder("Solicitudes:\n");
        for (int i = 0; i < solicitudes.size(); i++) {
            lista.append(i + 1).append(". ").append(solicitudes.get(i)).append("\n");
        }

        JTextArea textArea = new JTextArea(lista.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Solicitudes", JOptionPane.INFORMATION_MESSAGE);
    }

    
    public static void actualizarEstadoSolicitud() {
        if (solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes para gestionar.");
            return;
        }

        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la solicitud a actualizar:")) - 1;
            if (index < 0 || index >= solicitudes.size()) {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
                return;
            }

            SolicitudArticulos2 solicitud = solicitudes.get(index);

            String[] opciones = {"Aprobado", "Rechazado", "Listo para recoger"};
            String nuevoEstado = (String) JOptionPane.showInputDialog(null, "Seleccione el nuevo estado:",
                    "Actualizar Estado", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (nuevoEstado != null) {
                
                if (nuevoEstado.equals("Aprobado")) {
                    Articulo2 articulo = solicitud.getArticulo();
                    int cantidadSolicitada = solicitud.getCantidadSolicitada();

                    
                    if (articulo.getCantidad() >= cantidadSolicitada) {
                        articulo.modificarCantidad(-cantidadSolicitada); 
                        solicitud.setEstado(nuevoEstado); 
                        JOptionPane.showMessageDialog(null, "Estado actualizado correctamente y cantidad descontada del inventario.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en el inventario para aprobar esta solicitud.");
                    }
                } else {
                   
                    solicitud.setEstado(nuevoEstado);
                    JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    
    public static void eliminarSolicitud() {
        if (solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes para eliminar.");
            return;
        }

        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la solicitud a eliminar:")) - 1;
            if (index < 0 || index >= solicitudes.size()) {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
                return;
            }

            solicitudes.remove(index);
            JOptionPane.showMessageDialog(null, "Solicitud eliminada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }
}