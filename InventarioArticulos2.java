package Proyecto2;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class InventarioArticulos2 {

    
    public static void consultarInventario() {
        if (Articulo2.articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos en el inventario.");
            return;
        }

        StringBuilder lista = new StringBuilder("Inventario de Artículos:\n");
        for (Articulo2 articulo : Articulo2.articulos) {
            lista.append(articulo).append("\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());
    }

    
    public static void mostrarArticulosSolicitados() {
        if (SolicitudArticulos2.solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.");
            return;
        }

        HashMap<String, Integer> conteo = new HashMap<>();

        // Contar la cantidad de solicitudes por artículo
        for (SolicitudArticulos2 solicitud : SolicitudArticulos2.solicitudes) {
            String nombreArticulo = solicitud.getArticulo().getNombre();
            conteo.put(nombreArticulo, conteo.getOrDefault(nombreArticulo, 0) + solicitud.getCantidadSolicitada());
        }

        // Crear un StringBuilder para almacenar los datos
        StringBuilder datos = new StringBuilder("Artículos más solicitados:\n\n");
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            datos.append("Artículo: ").append(entry.getKey())
                 .append(" - Cantidad solicitada: ").append(entry.getValue())
                 .append("\n");
        }

        
        JOptionPane.showMessageDialog(null, datos.toString(), "Artículos más solicitados", JOptionPane.INFORMATION_MESSAGE);
    }

    
    public static void generarReportePedidosPorDepartamento() {
        if (SolicitudArticulos2.solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.");
            return;
        }

        HashMap<String, Integer> pedidosPorDepto = new HashMap<>();

        
        for (SolicitudArticulos2 solicitud : SolicitudArticulos2.solicitudes) {
            String depto = solicitud.getDepartamento();
            pedidosPorDepto.put(depto, pedidosPorDepto.getOrDefault(depto, 0) + solicitud.getCantidadSolicitada());
        }

        
        StringBuilder reporte = new StringBuilder("Reporte de Pedidos por Departamento:\n\n");
        for (Map.Entry<String, Integer> entry : pedidosPorDepto.entrySet()) {
            reporte.append("Departamento: ").append(entry.getKey())
                  .append(" - Artículos solicitados: ").append(entry.getValue())
                  .append("\n");
        }

        
        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte por Departamento", JOptionPane.INFORMATION_MESSAGE);
    }

    
    public static void generarReportePedidosPorFecha() {
        if (SolicitudArticulos2.solicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.");
            return;
        }

        HashMap<String, Integer> pedidosPorFecha = new HashMap<>();

        
        for (SolicitudArticulos2 solicitud : SolicitudArticulos2.solicitudes) {
            String fecha = solicitud.getFecha(); // Asumiendo que tienes un método getFecha() en SolicitudArticulos2
            pedidosPorFecha.put(fecha, pedidosPorFecha.getOrDefault(fecha, 0) + solicitud.getCantidadSolicitada());
        }

        
        StringBuilder reporte = new StringBuilder("Reporte de Pedidos por Fecha:\n\n");
        for (Map.Entry<String, Integer> entry : pedidosPorFecha.entrySet()) {
            reporte.append("Fecha: ").append(entry.getKey())
                  .append(" - Artículos solicitados: ").append(entry.getValue())
                  .append("\n");
        }

        
        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte por Fecha", JOptionPane.INFORMATION_MESSAGE);
    }

}