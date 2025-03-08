package Proyecto2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SolicitudArticulos2 {

    private String departamento;
    private Articulo2 articulo;
    private int cantidadSolicitada;
    private String estado; // "Pendiente", "Aprobado", "Rechazado", "Listo para recoger"
    private String fecha;

    public static ArrayList<SolicitudArticulos2> solicitudes = new ArrayList<>();

    public SolicitudArticulos2(String departamento, Articulo2 articulo, int cantidadSolicitada) {
        this.departamento = departamento;
        this.articulo = articulo;
        this.cantidadSolicitada = cantidadSolicitada;
        this.estado = "Pendiente";
        this.fecha = fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Inicializar la fecha
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

        String departamento = JOptionPane.showInputDialog("Ingrese su departamento:");
        if (departamento == null || departamento.isEmpty()) return;

        String lista = "Artículos disponibles:\n";
        for (int i = 0; i < Articulo2.articulos.size(); i++) {
            lista += (i + 1) + ". " + Articulo2.articulos.get(i).getNombre() + "\n";
        }

        int index = Integer.parseInt(JOptionPane.showInputDialog(lista + "Ingrese el número del artículo:")) - 1;
        if (index < 0 || index >= Articulo2.articulos.size()) {
            JOptionPane.showMessageDialog(null, "Selección inválida.");
            return;
        }

        Articulo2 articuloSeleccionado = Articulo2.articulos.get(index);

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad solicitada:"));
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
            return;
        }

        solicitudes.add(new SolicitudArticulos2(departamento, articuloSeleccionado, cantidad));
        JOptionPane.showMessageDialog(null, "Solicitud creada correctamente.");
    }

    // 🔹 Ver solicitudes
    public static void verSolicitudes() {
        if (solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.");
            return;
        }

        String lista = "Solicitudes:\n";
        for (int i = 0; i < solicitudes.size(); i++) {
            lista += (i + 1) + ". " + solicitudes.get(i) + "\n";
        }
        JOptionPane.showMessageDialog(null, lista);
    }

    // 🔹 Actualizar estado de solicitud (admin)
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
                solicitud.setEstado(nuevoEstado);
                JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    // 🔹 Eliminar una solicitud
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
