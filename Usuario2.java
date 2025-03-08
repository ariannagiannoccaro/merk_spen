package Proyecto2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Usuario2 {

    private String nombre;
    private String departamento;
    private String contraseña;
    private String correo;

    private static ArrayList<Usuario2> usuarios = new ArrayList<>();

    // 🔹 Agregamos un usuario administrador por defecto
    static {
        usuarios.add(new Usuario2("Artemio", "Admin", "admin123", "admin@empresa.com"));
    }

    public Usuario2(String nombre, String departamento, String contraseña, String correo) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    // Métodos Getter y Setter
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Departamento: " + departamento + ", Correo: " + correo;
    }

    // 🔹 CRUD dentro de la misma clase
    public static void crearUsuario() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField nombreField = new JTextField();
        JTextField departamentoField = new JTextField();
        JPasswordField contraseñaField = new JPasswordField();
        JTextField correoField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Departamento:"));
        panel.add(departamentoField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contraseñaField);
        panel.add(new JLabel("Correo:"));
        panel.add(correoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Crear Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String departamento = departamentoField.getText();
            String contraseña = new String(contraseñaField.getPassword());
            String correo = correoField.getText();

            if (!nombre.isEmpty() && !departamento.isEmpty() && !contraseña.isEmpty() && !correo.isEmpty()) {
                usuarios.add(new Usuario2(nombre, departamento, contraseña, correo));
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            }
        }
    }

    public static void leerUsuarios() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder("Usuarios:\n");
        for (int i = 0; i < usuarios.size(); i++) {
            lista.append(i + 1).append(". ").append(usuarios.get(i)).append("\n");
        }

        JTextArea textArea = new JTextArea(lista.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void actualizarUsuario() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para actualizar.");
            return;
        }

        String indexStr = JOptionPane.showInputDialog("Ingrese el número del usuario a actualizar:");
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < usuarios.size()) {
                Usuario2 usuario = usuarios.get(index);

                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField nombreField = new JTextField(usuario.getNombre());
                JTextField departamentoField = new JTextField(usuario.getDepartamento());
                JPasswordField contraseñaField = new JPasswordField(usuario.getContraseña());
                JTextField correoField = new JTextField(usuario.getCorreo());

                panel.add(new JLabel("Nombre:"));
                panel.add(nombreField);
                panel.add(new JLabel("Departamento:"));
                panel.add(departamentoField);
                panel.add(new JLabel("Contraseña:"));
                panel.add(contraseñaField);
                panel.add(new JLabel("Correo:"));
                panel.add(correoField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Actualizar Usuario", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    usuario.setNombre(nombreField.getText());
                    usuario.setDepartamento(departamentoField.getText());
                    usuario.setContraseña(new String(contraseñaField.getPassword()));
                    usuario.setCorreo(correoField.getText());

                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    public static void eliminarUsuario() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para eliminar.");
            return;
        }

        String indexStr = JOptionPane.showInputDialog("Ingrese el número del usuario a eliminar:");
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < usuarios.size()) {
                usuarios.remove(index);
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Índice inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    // Método para validar el inicio de sesión
    public static Usuario2 validarCredenciales(String departamento, String contraseña) {
        for (Usuario2 u : usuarios) {
            if (u.getDepartamento().equalsIgnoreCase(departamento) && u.getContraseña().equals(contraseña)) {
                return u;
            }
        }
        return null;
    }
}