import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class inicioSesion extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private Map<String, String> usuarios; // Simulación de base de datos

    public inicioSesion() {
        // Configuración de la ventana
        setTitle("Sistema de Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Inicializar "base de datos"
        usuarios = new HashMap<>();
        usuarios.put("admin", "1234"); // Usuario de prueba

        // Componentes de la interfaz
        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        JButton btnValidar = new JButton("Validar");
        JButton btnRecuperar = new JButton("Recuperar Contraseña");

        // Acciones de los botones
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                if (validarInformacion(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String nuevaContrasena = JOptionPane.showInputDialog("Ingrese nueva contraseña:");

                if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
                    recuperarContrasena(usuario, nuevaContrasena);
                    JOptionPane.showMessageDialog(null, "Contraseña actualizada");
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía");
                }
            }
        });

        // Agregar componentes a la ventana
        add(lblUsuario);
        add(txtUsuario);
        add(lblContrasena);
        add(txtContrasena);
        add(btnValidar);
        add(btnRecuperar);

        setVisible(true);
    }

    // Métodos de lógica
    public boolean validarInformacion(String idUsuario, String contrasena) {
        return usuarios.containsKey(idUsuario) && usuarios.get(idUsuario).equals(contrasena);
    }

    public void recuperarContrasena(String idUsuario, String nuevaContrasena) {
        if (usuarios.containsKey(idUsuario)) {
            usuarios.put(idUsuario, nuevaContrasena);
        }
    }

    public static void main(String[] args) {
        new inicioSesion();
    }
}
