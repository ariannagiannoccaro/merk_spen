package Proyecto2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Articulo2 {

    private String nombre;
    private String categoria;
    private int cantidad;
    private String estado;

    public static ArrayList<Articulo2> articulos = new ArrayList<>();

    public Articulo2(String nombre, String categoria, int cantidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.estado = cantidad > 0 ? "Disponible" : "No disponible";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void modificarCantidad(int cambio) {
        this.cantidad += cambio;
        if (this.cantidad < 0) this.cantidad = 0;
        actualizarEstado();
    }

    public String getEstado() {
        return estado;
    }

    private void actualizarEstado() {
        this.estado = (this.cantidad > 0) ? "Disponible" : "No disponible";
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Categoría: " + categoria + ", Cantidad: " + cantidad + ", Estado: " + estado;
    }

 
    public static void crearArticulo() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField nombreField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField cantidadField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Crear Artículo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String categoria = categoriaField.getText();
            String cantidadStr = cantidadField.getText();

            if (!nombre.isEmpty() && !categoria.isEmpty() && !cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    articulos.add(new Articulo2(nombre, categoria, cantidad));
                    JOptionPane.showMessageDialog(null, "Artículo creado correctamente.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            }
        }
    }

    
    public static void leerArticulos() {
        if (articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder("Artículos:\n");
        for (int i = 0; i < articulos.size(); i++) {
            lista.append(i + 1).append(". ").append(articulos.get(i)).append("\n");
        }

        JTextArea textArea = new JTextArea(lista.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Artículos", JOptionPane.INFORMATION_MESSAGE);
    }

    
    public static void actualizarArticulo() {
        if (articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos para actualizar.");
            return;
        }

        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el índice del artículo a actualizar:")) - 1;
            if (index < 0 || index >= articulos.size()) {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
                return;
            }

            Articulo2 articulo = articulos.get(index);

            JPanel panel = new JPanel(new GridLayout(2, 2));
            JTextField nombreField = new JTextField(articulo.getNombre());
            JTextField categoriaField = new JTextField(articulo.getCategoria());

            panel.add(new JLabel("Nombre:"));
            panel.add(nombreField);
            panel.add(new JLabel("Categoría:"));
            panel.add(categoriaField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Actualizar Artículo", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                articulo.setNombre(nombreField.getText());
                articulo.setCategoria(categoriaField.getText());
                JOptionPane.showMessageDialog(null, "Artículo actualizado correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    
    public static void modificarCantidadArticulo() {
        if (articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos registrados.");
            return;
        }

        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el índice del artículo a modificar:")) - 1;
            if (index < 0 || index >= articulos.size()) {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
                return;
            }

            Articulo2 articulo = articulos.get(index);
            String cambioStr = JOptionPane.showInputDialog("Ingrese la cantidad a agregar (+) o quitar (-):");
            int cambio = Integer.parseInt(cambioStr);

            articulo.modificarCantidad(cambio);
            JOptionPane.showMessageDialog(null, "Cantidad modificada correctamente.\nNuevo estado: " + articulo.getEstado());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    
    public static void eliminarArticulo() {
        if (articulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay artículos para eliminar.");
            return;
        }

        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el índice del artículo a eliminar:")) - 1;
            if (index < 0 || index >= articulos.size()) {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
                return;
            }

            articulos.remove(index);
            JOptionPane.showMessageDialog(null, "Artículo eliminado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }
}